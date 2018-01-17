package view.franchisee;

import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import model.Customer;
import model.RequiredTask;
import model.Vehicle;

/**
 *
 * @author Alex
 */
public class FranchiseePanel extends JTabbedPane {

    private JobsTab jobsTab;
    public JobsTab getJobsTab() {
		return jobsTab;
	}

	private CustomersVehiclesTab customersVehiclesTab;
    private SparePartsTab sparePartsTab;

    public FranchiseePanel() {
        jobsTab = new JobsTab();
        customersVehiclesTab = new CustomersVehiclesTab();
        sparePartsTab = new SparePartsTab();

        this.addTab("Jobs", jobsTab);
        this.addTab("Customers / Vehicles", customersVehiclesTab);
        this.addTab("Spare Parts", sparePartsTab);

        // franchisee panel
        Border outsideBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        Border insideBorder = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        this.setPreferredSize(new Dimension(1050, 500));
        this.setVisible(true);
    }
    
    public void setSorter() {
    	jobsTab.setSorter();
    	customersVehiclesTab.setSorter();
    }

    public void setCustomerData(List<Customer> db) {
        jobsTab.setCustomerData(db);
    }

    public void setVehicleData(List<Vehicle> db) {
        jobsTab.setVehicleData(db);
    }
    
    public void setRequiredTaskData(List<RequiredTask> db) {
        jobsTab.setRequiredTaskData(db);
    }

    public void setNewVehicleData(List<Vehicle> db) {
        customersVehiclesTab.setNewVehicleData(db);
    }
    
    public void setCustomerVehicleDataEdit(List<Customer> dbc, List<Vehicle> dbv) {
        customersVehiclesTab.setCustomerVehicleDataEdit(dbc, dbv);
    }
    
    public CustomersVehiclesTab getCustomersVehiclesTab() {
        return customersVehiclesTab;
    }
}
