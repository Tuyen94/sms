package tuyen.bui.sms.config;

import lombok.Data;
import oracle.jdbc.OracleConnection;

import java.util.List;
import java.util.Properties;

@Data
public class RegistrationProperties {
    private String port;
    private List<String> operations;
    private String associateQuery;
    private String listener;
    private String queryChange = "true";

    public Properties generateProperties() {
        Properties properties = new Properties();
        properties.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
        properties.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION, queryChange);
        if (port != null) {
            properties.setProperty(OracleConnection.NTF_LOCAL_TCP_PORT, port);
        }
        if (operations != null) {
            if (!operations.contains("INSERT")) {
                properties.setProperty(OracleConnection.DCN_IGNORE_INSERTOP, "true");
            }
            if (!operations.contains("UPDATE")) {
                properties.setProperty(OracleConnection.DCN_IGNORE_UPDATEOP, "true");
            }
            if (!operations.contains("DELETE")) {
                properties.setProperty(OracleConnection.DCN_IGNORE_DELETEOP, "true");
            }
        }
        return properties;
    }
}
