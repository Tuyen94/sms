package tuyen.bui.sms.test;

import oracle.jdbc.dcn.RowChangeDescription;
import org.springframework.stereotype.Component;
import tuyen.bui.sms.listener.DcnListener;

@Component
public class OrderOutboxListener implements DcnListener {
    @Override
    public void onRowChange(String table, String rowID, RowChangeDescription.RowOperation rowOperation) {

    }
}
