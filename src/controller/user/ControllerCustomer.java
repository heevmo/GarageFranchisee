package controller.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Customer;
import model.CustomersDatabase;
import view.franchisee.customersvehicles.CustomerEvent;
import view.franchisee.customersvehicles.CustomerUpdateEvent;
import view.utils.DatabaseConnectivity;

/**
 *
 * @author Alex
 */
public class ControllerCustomer {
    
    private CustomersDatabase db;
    
    public ControllerCustomer() {
        db = new CustomersDatabase();
    }
    
    public List<Customer> getJobsCustomersDatabase() {
        return db.getCustomers();
    }
    
    public List<Customer> getEditCustomersDatabase() {
        return db.getEditCustomers();
    }
    
    public int load () {
    	int i = -1;
		try {
			i = db.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
    }
    
    public int addCustomerToDatabase(CustomerEvent e) {
		String name = e.getName();
		String address = e.getAddress();
		String postcode = e.getPostcode();
		String telephone = e.getTelephone();
		String fax = e.getFax();
		String email = e.getEmail();
		String customerClass = e.getCustomerClass();
		Customer c = new Customer(name, postcode, address, 
				customerClass, telephone, fax, email);
		
		int i = -1;
		try {
			i = db.addCustomerToDatabase(c);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return i;
    }
    
    public void deleteCustomerById(int Customer_ID) {
    	try {
			db.deleteCustomerById(Customer_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void updateCustomerDetails(CustomerUpdateEvent customerEvent) {
		try {
			db.updateCustomerDetails(customerEvent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
