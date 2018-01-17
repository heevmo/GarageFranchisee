package view.administrator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.User;

/**
 *
 * @author Alex
 */
public class UsersTableModel extends AbstractTableModel {

    private List<User> db;

    private String[] columnNames = {"Id", "Role", "Username",
        "Password", "First Name", "Last Name", "E-Mail" , "Date added"};

    public void setData(List<User> db) {
        this.db = db;
    }
    
    // Date
    SimpleDateFormat f = new SimpleDateFormat("dd / MM / yyyy");
    Calendar now = Calendar.getInstance();

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = db.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getRole();
            case 2:
                return user.getUsername();
            case 3:
                return user.getPassword();
            case 4:
                return user.getFirstName();
            case 5: 
                return user.getLastName();
            case 6:
                return user.getEmail();
            case 7:
                return f.format(now.getTime());
            default:
                return null;
        }
    }

}
