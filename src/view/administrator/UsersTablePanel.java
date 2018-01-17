package view.administrator;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.User;
import view.Table;

/**
 *
 * @author Alex
 */
public class UsersTablePanel extends JPanel {

    private Table table;
    private UsersTableModel tableModel;
    private UserEvent event;
    private EditUserPanelListener listener;

    public UsersTablePanel() {

        tableModel = new UsersTableModel();
        table = new Table(tableModel);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     *
     */
    public void setEditUserPanelListener(EditUserPanelListener listener) {
        this.listener = listener;
    }

    /**
     *
     * @param db
     */
    public void setData(List<User> db) {
        tableModel.setData(db);
    }

    /**
     *
     */
    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    public void refreshDeletedRows(int row) {
        tableModel.fireTableRowsDeleted(row, row);
    }

    public Table getTable() {
        return this.table;
    }
}

