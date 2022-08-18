package tuyen.bui.sms.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import tuyen.bui.sms.config.DcnProperties;
import tuyen.bui.sms.config.RegistrationProperties;
import tuyen.bui.sms.listener.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@AllArgsConstructor
@Component
//@ConditionalOnBean({DataSource.class, DcnProperties.class})
@Slf4j
public class DcnRegistryManager {

    private final DataSource dataSource;
    private final DcnProperties dcnProperties;
    private final ApplicationContext context;

    @PostConstruct
    public void register() throws SQLException {
        log.info("Start register dcn!");
        Connection connection = dataSource.getConnection();
        OracleConnection oracleConnection = connection.unwrap(OracleConnection.class);
        for (RegistrationProperties registrationProperties : dcnProperties.getRegistrations()) {
            DcnListener dcnListener = (DcnListener) context.getBean(registrationProperties.getListener());
            dcnListener.beforeRegister();
            register(registrationProperties, oracleConnection, dcnListener);
            dcnListener.afterRegister();
        }
    }

    public void register(RegistrationProperties registrationProperties, OracleConnection connection, DcnListener dcnListener) throws SQLException {
        Properties properties = registrationProperties.generateProperties();
        DatabaseChangeRegistration registration = connection.registerDatabaseChangeNotification(properties);
        DatabaseChangeListenerImpl databaseChangeListener = new DatabaseChangeListenerImpl(dcnListener);
        registration.addListener(databaseChangeListener);
        associateQuery(registrationProperties, connection, registration);
    }

    private void associateQuery(RegistrationProperties registrationProperties,
                                OracleConnection connection, DatabaseChangeRegistration registration) throws SQLException {
        Statement statement = connection.createStatement();
        ((OracleStatement)statement).setDatabaseChangeRegistration(registration);
        String associateQuery = registrationProperties.getAssociateQuery();
        statement.executeQuery(associateQuery);
        log.info("Register id {} table {} done! {}", registration.getRegId(), registration.getTables(), associateQuery);
    }
}
