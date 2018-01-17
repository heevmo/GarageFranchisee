package controller;

import java.sql.SQLException;
import java.util.List;

import model.Vehicle;
import model.VehiclesDatabase;
import view.franchisee.customersvehicles.VehicleEvent;
import view.utils.DatabaseConnectivity;

/**
 *
 * @author Alex
 */
public class ControllerVehicle {

	private VehiclesDatabase db;

	public ControllerVehicle() {
		db = new VehiclesDatabase();
	}

	public List<Vehicle> getTemporaryVehicles() {
		return db.getTemporaryVehicles();
	}

	public List<Vehicle> getDatabaseVehicles() {
		return db.getDatabaseVehicles();
	}
	
	public List<Vehicle> getEditDatabaseVehicles() {
		return db.getEditDatabaseVehicles();
	}

	public int addVehicleToTemporaryList(VehicleEvent e) {

		String regNum = e.getRegNo();
		String engSer = e.getEngSer();
		String make = e.getMake();
		String chNo = e.getChNo();
		String model = e.getModel();
		String colour = e.getColour();

		Vehicle vehicle = new Vehicle(regNum, make, model, engSer, chNo, colour);
		int i = db.addTemporaryVehicle(vehicle);
		return i;

	}

	public int addVehiclesToDatabase(int customerId) {
		int i = -1;
		try {
			i = db.addVehiclesToDatabase(customerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void deleteFromTemporaryList(int index) {
		db.deleteFromTemporaryList(index);
	}

	public void load(int d) {
		try {
			db.load(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadByCustomer(int Customer_ID, int i) {
		try {
			db.loadByCustomer(Customer_ID, i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCustomerByVehicle(int Vehicle_ID) {
		int Customer_ID = -1;
		try {
			Customer_ID = db.getCustomerByVehicle(Vehicle_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Customer_ID;
	}
	
	public void deleteVehicleFromDatabase(int Vehicle_ID) {
		try {
			db.deleteVehicleFromDatabase(Vehicle_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteVehicleByCustomerFromDatabase(int Customer_ID) {
		try {
			db.deleteVehicleByCustomerFromDatabase(Customer_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateVehicleDetails(VehicleEvent customerEvent) {
		try {
			db.updateVehicleDetails(customerEvent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
