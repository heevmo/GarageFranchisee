package view.franchisee.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Customer;
import model.Vehicle;

/**
 *
 * @author Alex
 */
public class CustomerTableModel extends AbstractTableModel {
    
    private List<Customer> db;

    private String[] columnNames = {"ID", "Name", "Postcode", 
        "Address", "Customer Class", "Telephone", "Fax", "Email", "Discount", "Credit", "Date added"};
    
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
        Customer customer = db.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return customer.getId();
            case 1:
                return customer.getName();
            case 2:
                return customer.getPostcode();
            case 3:
                return customer.getAddress();
            case 4:
                return customer.getCustomerClass();
            case 5:
                return customer.getTelephone();
            case 6:
                return customer.getFax();
            case 7:
            	return customer.getEmail();
            case 10:
            	return f.format(now.getTime());
            default:
                return null;
        }
    }
    
    public void setData(List<Customer> db) {
        this.db = db;
    }
}
