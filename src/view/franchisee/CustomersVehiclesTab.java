package view.franchisee;

import java.util.List;
import javax.swing.JTabbedPane;
import model.Customer;
import model.Vehicle;
import view.franchisee.customersvehicles.AddNewCustomerPanel;
import view.franchisee.customersvehicles.EditCustomersVehiclesPanel;

/**
 *
 * @author Alex
 */
public class CustomersVehiclesTab extends JTabbedPane {

    private AddNewCustomerPanel addNewCustomerPanel;
    private EditCustomersVehiclesPanel editCustomersVehiclesPanel;

    public CustomersVehiclesTab() {
        addNewCustomerPanel = new AddNewCustomerPanel();
        editCustomersVehiclesPanel = new EditCustomersVehiclesPanel();

        this.addTab("Add New Customer", addNewCustomerPanel);
        this.addTab("Edit Customers / Vehicles", editCustomersVehiclesPanel);
    }
    
    public void setSorter() {
    	editCustomersVehiclesPanel.setSorter();
    }

	public void setNewVehicleData(List<Vehicle> db) {
        addNewCustomerPanel.setNewVehicleData(db);
    }
    
    public void setCustomerVehicleDataEdit(List<Customer> dbc, List<Vehicle> dbv) {
        editCustomersVehiclesPanel.setCustomerData(dbc);
        editCustomersVehiclesPanel.setVehicleData(dbv);
    }
    
    public AddNewCustomerPanel getAddNewCustomerPanel() {
        return addNewCustomerPanel;
    }
    
    public EditCustomersVehiclesPanel getEditCustomersVehiclesPanel() {
		return editCustomersVehiclesPanel;
	}
}
