package view.franchisee.jobs;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.RequiredTask;

/**
 *
 * @author Alex
 */
public class RequiredTasksTableModel extends AbstractTableModel {

    private List<RequiredTask> db;
    
    private String[] columnNames = {"ID", "Description of required task", "Estimated Time"};
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public int getRowCount() {
        return db.size();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    } 
    
    public void setData(List<RequiredTask> db) {
        this.db = db;
    }
    
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
}
