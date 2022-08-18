package tuyen.bui.sms.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.dcn.*;

@AllArgsConstructor
@Slf4j
public class DatabaseChangeListenerImpl implements DatabaseChangeListener {

    private DcnListener dcnListener;

    @Override
    public void onDatabaseChangeNotification(final DatabaseChangeEvent changeEvent) {
        log.info("Oracle DCN processEvent - START {}", changeEvent);
        try {
            switch (changeEvent.getEventType()) {
                case QUERYCHANGE:
                    handleQueryChangeEvent(changeEvent);
                    break;
                case OBJCHANGE:
                    handleObjectChangeEvent(changeEvent);
                    break;
            }
        } catch (Exception ex) {
            log.error("processEvent error", ex);
        }
        log.info("Oracle DCN processEvent - END");
    }

    private void handleQueryChangeEvent(DatabaseChangeEvent changeEvent) {
        final QueryChangeDescription[] queryChangeArray = changeEvent.getQueryChangeDescription();
        for (QueryChangeDescription queryChange : queryChangeArray) {
            final TableChangeDescription[] tableChangeArray = queryChange.getTableChangeDescription();
            handleTableChange(tableChangeArray);
        }
    }

    private void handleObjectChangeEvent(DatabaseChangeEvent changeEvent) {
        final TableChangeDescription[] tableChangeArray = changeEvent.getTableChangeDescription();
        handleTableChange(tableChangeArray);
    }

    private void handleTableChange(TableChangeDescription[] tableChangeArray) {
        for (TableChangeDescription tableChange : tableChangeArray) {
            final RowChangeDescription[] rowChangeArray = tableChange.getRowChangeDescription();
            final String table = getTableName(tableChange);
            handleRowChange(table, rowChangeArray);
        }
    }

    private String getTableName(TableChangeDescription tableChange) {
        String tableName = tableChange.getTableName();
        if (tableName.contains(".")) {
            // format tableName of tableChange is user.tableName
            return tableName.split("\\.")[1];
        }
        return tableName;
    }

    private void handleRowChange(String table, RowChangeDescription[] rowChangeArray) {
        for (RowChangeDescription rowChange : rowChangeArray) {
            try {
                final String rowID = rowChange.getRowid().stringValue();
                dcnListener.onRowChange(table, rowID, rowChange.getRowOperation());
            } catch (Exception ex) {
                log.error("handleEvent error", ex);
            }
        }
    }
}
