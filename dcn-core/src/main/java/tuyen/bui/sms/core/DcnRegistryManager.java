package tuyen.bui.sms.core;

import lombok.AllArgsConstructor;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import tuyen.bui.sms.config.DcnProperties;
import tuyen.bui.sms.config.RegistrationProperties;
import tuyen.bui.sms.listener.DcnListenerImpl;
import tuyen.bui.sms.listener.RowChangeListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@AllArgsConstructor
public class DcnRegistryManager {

    private final DataSource dataSource;
    private final DcnProperties dcnProperties;

    public void register() throws SQLException {
        Connection connection = dataSource.getConnection();
        OracleConnection oracleConnection = connection.unwrap(OracleConnection.class);
        for (RegistrationProperties registration : dcnProperties.getRegistrations()) {
            register(registration, oracleConnection);
        }
    }

    public void register(RegistrationProperties registrationProperties, OracleConnection connection) throws SQLException {
        Properties properties = registrationProperties.generateProperties();
        DatabaseChangeRegistration registration = connection.registerDatabaseChangeNotification(properties);
        RowChangeListener rowChangeListener = registrationProperties.getRowChangeListener();
        DcnListenerImpl dcnListener = new DcnListenerImpl(rowChangeListener);
        registration.addListener(dcnListener);
        associateQuery(registrationProperties, connection, registration);
    }

    private void associateQuery(RegistrationProperties registrationProperties,
                                OracleConnection connection, DatabaseChangeRegistration registration) throws SQLException {
        Statement statement = connection.createStatement();
        ((OracleStatement)statement).setDatabaseChangeRegistration(registration);
        String associateQuery = registrationProperties.getAssociateQuery();
        statement.executeQuery(associateQuery);
    }
}
