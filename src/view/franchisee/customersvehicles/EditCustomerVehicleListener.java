package view.franchisee.customersvehicles;

public interface EditCustomerVehicleListener {
	public void loadByCustomer(int i);
	public void clear();
	public int getCustomerByVehicle(int i);
	public void addVehicleToDatabase(VehicleEvent e, int Customer_ID);
	public void deleteVehicle(int Vehicle_ID, int Customer_ID);
	public void deleteCustomer(int Customer_ID);
	public void updateCustomerDetails(CustomerUpdateEvent customerEvent);
	public void updateVehicleDetails(VehicleEvent customerEvent);
}
