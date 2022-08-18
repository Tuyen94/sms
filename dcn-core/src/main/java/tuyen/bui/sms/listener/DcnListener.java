package tuyen.bui.sms.listener;

import oracle.jdbc.dcn.RowChangeDescription;

public interface DcnListener {
    void onRowChange(String table, String rowID, RowChangeDescription.RowOperation rowOperation);
    default void beforeRegister() {}
    default void afterRegister() {}
}
