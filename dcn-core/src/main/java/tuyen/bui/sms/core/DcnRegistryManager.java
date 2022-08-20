package tuyen.bui.sms.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import tuyen.bui.sms.config.DcnProperties;
import tuyen.bui.sms.config.RegistrationProperties;
import tuyen.bui.sms.listener.DatabaseChangeListenerImpl;
import tuyen.bui.sms.listener.DcnListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor
@Component
//@ConditionalOnBean({DataSource.class, DcnProperties.class})
@Slf4j
public class DcnRegistryManager {

    private final DataSource dataSource;
    private final DcnProperties dcnProperties;
    private final ApplicationContext context;
    private List<DatabaseChangeRegistration> registrationList = new ArrayList<>();

    @PostConstruct
    public void register() throws SQLException {
        log.info("Start register dcn!");
        Connection connection = dataSource.getConnection();
        OracleConnection oracleConnection = connection.unwrap(OracleConnection.class);
        unregisterRemainRegistration(oracleConnection);
        for (RegistrationProperties registrationProperties : dcnProperties.getRegistrations()) {
            DcnListener dcnListener = (DcnListener) context.getBean(registrationProperties.getListener());
            dcnListener.beforeRegister();
            register(registrationProperties, oracleConnection, dcnListener);
            dcnListener.afterRegister();
        }
    }

    private void unregisterRemainRegistration(OracleConnection connection) {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            log.info(hostName);
            log.info(hostAddress);
            Statement stmt= connection.createStatement();
            String query = "select regid,callback from USER_CHANGE_NOTIFICATION_REGS where callback like '%" + hostAddress + "%'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                long regid = rs.getLong(1);
                String callback = rs.getString(2);
                connection.unregisterDatabaseChangeNotification(regid,callback);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(RegistrationProperties registrationProperties, OracleConnection connection, DcnListener dcnListener) throws SQLException {
        Properties properties = registrationProperties.generateProperties();
        DatabaseChangeRegistration registration = connection.registerDatabaseChangeNotification(properties);
        DatabaseChangeListenerImpl databaseChangeListener = new DatabaseChangeListenerImpl(dcnListener);
        registration.addListener(databaseChangeListener);
        associateQuery(registrationProperties, connection, registration);
        registrationList.add(registration);
    }

    private void associateQuery(RegistrationProperties registrationProperties,
                                OracleConnection connection, DatabaseChangeRegistration registration) throws SQLException {
        Statement statement = connection.createStatement();
        ((OracleStatement) statement).setDatabaseChangeRegistration(registration);
        String associateQuery = registrationProperties.getAssociateQuery();
        statement.executeQuery(associateQuery);
        statement.close();
        log.info("Register dcn id {} table {} done! {}", registration.getRegId(), registration.getTables(), associateQuery);
    }

    @PreDestroy
    public void unregister() {
        try {
            Connection connection = dataSource.getConnection();
            OracleConnection oracleConnection = connection.unwrap(OracleConnection.class);
            registrationList.forEach(registration -> unregister(oracleConnection, registration));
        } catch (Exception e) {
            log.info("Unregister dcn error!", e);
        }
    }

    private void unregister(OracleConnection oracleConnection, DatabaseChangeRegistration registration) {
        log.info("Unregister dcn id {}!", registration.getRegId());
        try {
            oracleConnection.unregisterDatabaseChangeNotification(registration);
        } catch (Exception e) {
            log.info("Unregister dcn error id {}!", registration.getRegId(), e);
        }
    }
}
