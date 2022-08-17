package tuyen.bui.sms.listener;

import lombok.AllArgsConstructor;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;

@AllArgsConstructor
public class DcnListenerImpl implements DatabaseChangeListener {

    private RowChangeListener rowChangeListener;

    @Override
    public void onDatabaseChangeNotification(DatabaseChangeEvent databaseChangeEvent) {

    }
}
