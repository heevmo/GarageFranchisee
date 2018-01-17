package view.franchisee.customersvehicles;

public interface NewCustomerVehicleListener {
	public void addCustomerToDatabase(CustomerEvent e);
    public void addToTemporaryList(VehicleEvent e);
    public void deleteFromTemporaryList(int index);
}
