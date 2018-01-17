package view.franchisee.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.User;
import model.Vehicle;

/**
 *
 * @author Alex
 */
public class VehiclesTableModel extends AbstractTableModel {

    private List<Vehicle> db;

    private String[] columnNames = {"ID", "Registration No", "Make", "Model",
        "Engine Serial", "Chasis Number", "Colour", "Date added"};
    
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
        Vehicle vehicle = db.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vehicle.getId();
            case 1:
                return vehicle.getRegNo();
            case 2:
                return vehicle.getMake();
            case 3:
                return vehicle.getModel();
            case 4:
                return vehicle.getEngSer();
            case 5:
                return vehicle.getChNo();
            case 6:
                return vehicle.getColour();
            case 7:
                return f.format(now.getTime());
            default:
                return null;
        }
    }

    public void setData(List<Vehicle> db) {
        this.db = db;
    }

}
