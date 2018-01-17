package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import view.franchisee.customersvehicles.VehicleEvent;
import view.utils.DatabaseConnectivity;

/**
 *
 * @author Alex
 */
public class VehiclesDatabase {

	private List<Vehicle> vehiclesDatabaseNewJob;
	// keeps the vehicles when a new customer is added
	private List<Vehicle> temporaryVehicles;

	private List<Vehicle> vehiclesDatabaseEdit;

	private List<Vehicle> database;
	
	private int vehicleId;

	// keeps all vehicles database for New Job Tab and
	// Edit Customer Tab
	private List<Vehicle> vehiclesDatabase;

	public VehiclesDatabase() {
		// used to display in jobs and edit vehicles panel
		vehiclesDatabaseNewJob = new LinkedList<>();
		// used to display in add new customer panel
		temporaryVehicles = new LinkedList<>();
		// used to display in edit vehicles tab
		vehiclesDatabaseEdit = new LinkedList<>();

		vehiclesDatabase = new LinkedList<>();
	}

	public List<Vehicle> getDatabaseVehicles() {
		return Collections.unmodifiableList(vehiclesDatabaseNewJob);
	}

	public List<Vehicle> getTemporaryVehicles() {
		return Collections.unmodifiableList(temporaryVehicles);
	}

	public List<Vehicle> getEditDatabaseVehicles() {
		return Collections.unmodifiableList(vehiclesDatabaseEdit);
	}

	public void deleteFromTemporaryList(int index) {
		temporaryVehicles.remove(index);
		Vehicle.setCount(temporaryVehicles.size() + 1);
		for (int i = index; i < temporaryVehicles.size(); i++) {
			Vehicle v = temporaryVehicles.get(i);
			v.setId(i + 1);
		}
	}

	public int addTemporaryVehicle(Vehicle v) {
		// check if the vehicle was already added in temporary LinkedList
		// for the new customer

		for (Vehicle vehicle : temporaryVehicles) {
			String regNo = vehicle.getRegNo();
			String make = vehicle.getMake();
			String model = vehicle.getModel();
			String engine = vehicle.getEngSer();
			String chasis = vehicle.getChNo();
			String colour = vehicle.getColour();

			if (regNo.equals(v.getRegNo())) {
				Vehicle.setCount(temporaryVehicles.size() + 1);
				return 1;
			} else if (engine.equals(v.getEngSer())) {
				Vehicle.setCount(temporaryVehicles.size() + 1);
				return 2;
			} else if (chasis.equals(v.getChNo())) {
				Vehicle.setCount(temporaryVehicles.size() + 1);
				return 3;
			}
		}
		// check if the vehicle exists in database associated to another
		// customer
		for (Vehicle vehicle : vehiclesDatabase) {
			String regNo = vehicle.getRegNo();
			String make = vehicle.getMake();
			String model = vehicle.getModel();
			String engine = vehicle.getEngSer();
			String chasis = vehicle.getChNo();
			String colour = vehicle.getColour();

			if (regNo.equals(v.getRegNo())) {
				Vehicle.setCount(temporaryVehicles.size() + 1);
				return 4;
			} else if (engine.equals(v.getEngSer())) {
				Vehicle.setCount(temporaryVehicles.size() + 1);
				return 5;
			} else if (chasis.equals(v.getChNo())) {
				Vehicle.setCount(temporaryVehicles.size() + 1);
				return 6;
			}
		}

		// add to temporary list
		temporaryVehicles.add(v);
		return 0;
	}

	public int addVehiclesToDatabase(int customerId) throws SQLException {

		int i = temporaryVehicles.size();

		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();

		String insertSQL = "INSERT INTO vehicles (Vehicle_ID, Customer_ID, RegistrationNo, Make, Model, EngineSerial, ChasisNo, Colour)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement insertStatement = conn.prepareStatement(insertSQL);

		// first insert in the database
		if (vehiclesDatabase.size() == 0) {
			vehicleId = 1;
		} else {
			// set the id of the last vehicles inserted
			String countSQL = "SELECT Vehicle_ID FROM vehicles ORDER BY Vehicle_ID DESC LIMIT 1;";
			Statement countStatement = conn.createStatement();
			ResultSet results = countStatement.executeQuery(countSQL);
			results.next();
			vehicleId = results.getInt("Vehicle_ID") + 1;
			countStatement.close();
			results.close();
		}
		for (Vehicle vehicle : temporaryVehicles) {
			System.out.println("customer ID: " + customerId);
			String regNo = vehicle.getRegNo();
			String make = vehicle.getMake();
			String model = vehicle.getModel();
			String engine = vehicle.getEngSer();
			String chasis = vehicle.getChNo();
			String colour = vehicle.getColour();
			insertStatement.setInt(1, vehicleId++);
			insertStatement.setInt(2, customerId);
			insertStatement.setString(3, regNo);
			insertStatement.setString(4, make);
			insertStatement.setString(5, model);
			insertStatement.setString(6, engine);
			insertStatement.setString(7, chasis);
			insertStatement.setString(8, colour);
			insertStatement.executeUpdate();

		}

		insertStatement.close();
		DatabaseConnectivity.disconnect();
		temporaryVehicles.clear();
		return i;
	}

	/**
	 * 
	 * @throws SQLException
	 */
	public void load(int d) throws SQLException {

		if (d == 1) {
			vehiclesDatabaseNewJob.clear();
			vehiclesDatabaseNewJob.addAll(vehiclesDatabase);
		} else if (d == 2) {
			vehiclesDatabaseEdit.clear();
			vehiclesDatabaseEdit.addAll(vehiclesDatabase);
		} else {
			vehiclesDatabase.clear();
			vehiclesDatabaseNewJob.clear();
			vehiclesDatabaseEdit.clear();

			DatabaseConnectivity.connect();
			Connection conn = DatabaseConnectivity.getConnection();
			String sql = "SELECT * FROM vehicles";
			Statement selectStatement = conn.createStatement();
			ResultSet results = selectStatement.executeQuery(sql);
			while (results.next()) {
				int id = results.getInt("Vehicle_ID");
				String regNo = results.getString("RegistrationNo");
				String make = results.getString("Make");
				String model = results.getString("Model");
				String engine = results.getString("EngineSerial");
				String chasis = results.getString("ChasisNo");
				String colour = results.getString("Colour");
				Vehicle vehicle = new Vehicle(id, regNo, make, model, engine, chasis, colour);
				// used to load the database when program starts or
				vehiclesDatabase.add(vehicle);
			}
			// database displayed in New Job Vehicles table
			vehiclesDatabaseNewJob.addAll(vehiclesDatabase);
			// database displayed in Edit Vehicles table
			vehiclesDatabaseEdit.addAll(vehiclesDatabase);
			results.close();
			selectStatement.close();
			DatabaseConnectivity.disconnect();
		}
		Vehicle.setCount(temporaryVehicles.size() + 1);
	}

	public void loadByCustomer(int customer_ID, int i) throws SQLException {

		if (i == 1) {
			vehiclesDatabaseNewJob.clear();
			database = vehiclesDatabaseNewJob;
		} else {
			vehiclesDatabaseEdit.clear();
			database = vehiclesDatabaseEdit;
		}

		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();

		String sql = "SELECT * FROM vehicles WHERE Customer_ID = ?";
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setInt(1, customer_ID);
		ResultSet results = selectStatement.executeQuery();
		while (results.next()) {
			int id = results.getInt("Vehicle_ID");
			String regNo = results.getString("RegistrationNo");
			String make = results.getString("Make");
			String model = results.getString("Model");
			String engine = results.getString("EngineSerial");
			String chasis = results.getString("ChasisNo");
			String colour = results.getString("Colour");
			Vehicle vehicle = new Vehicle(id, regNo, make, model, engine, chasis, colour);
			database.add(vehicle);
		}

		Vehicle.setCount(temporaryVehicles.size() + 1);

		results.close();
		selectStatement.close();
		DatabaseConnectivity.disconnect();
	}

	public int getCustomerByVehicle(int Vehicle_ID) throws SQLException {
		int Customer_ID = -1;
		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();

		String sql = "SELECT * FROM vehicles WHERE Vehicle_ID = ?";
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setInt(1, Vehicle_ID);
		ResultSet results = selectStatement.executeQuery();
		while (results.next()) {
			Customer_ID = results.getInt("Customer_ID");
		}
		return Customer_ID;
	}

	public void deleteVehicleFromDatabase(int Vehicle_ID) throws SQLException {

		System.out.println("Delete " + Vehicle_ID);

		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();
		String sql = "DELETE FROM vehicles WHERE Vehicle_ID = ?";
		PreparedStatement deleteStatement = conn.prepareStatement(sql);
		deleteStatement.setInt(1, Vehicle_ID);
		deleteStatement.executeUpdate();
		deleteStatement.close();
		System.out.println("deleted");

		/*
		 * //-------------------------------------------------------------------
		 * ----------- // restore Vehicle_ID for the rest of vehicles // count
		 * the rest of vehicles String countSQL =
		 * "SELECT count(*) as Count FROM vehicles WHERE Vehicle_ID > ?";
		 * PreparedStatement countStatement = conn.prepareStatement(countSQL);
		 * countStatement.setInt(1, Vehicle_ID); ResultSet results =
		 * countStatement.executeQuery(); results.next(); int count =
		 * results.getInt("Count"); countStatement.close(); results.close();
		 * //-------------------------------------------------------------------
		 * ----------- // update String sqlUpdateVehicleID =
		 * //"ALTER TABLE vehicles DROP PRIMARY KEY; "
		 * "UPDATE vehicles SET Vehicle_ID = ? where Vehicle_ID > ?; "; //+
		 * "ALTER TABLE vehicles ADD CONSTRAINT Vehicle_ID PRIMARY KEY (Vehicle_ID);"
		 * ; Statement s = conn.createStatement();
		 * s.execute("ALTER TABLE vehicles DROP PRIMARY KEY; ");
		 * PreparedStatement updateStatement =
		 * conn.prepareStatement(sqlUpdateVehicleID); System.out.println(count);
		 * int id = Vehicle_ID; while(count-- > 0) { updateStatement.setInt(1,
		 * id++); updateStatement.setInt(2, Vehicle_ID);
		 * updateStatement.executeUpdate(); System.out.println("count"); }
		 */

		DatabaseConnectivity.disconnect();
	}

	public void deleteVehicleByCustomerFromDatabase(int Customer_ID) throws SQLException {
		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();
		String sql = "DELETE FROM vehicles WHERE Customer_ID = ?";
		PreparedStatement deleteStatement = conn.prepareStatement(sql);
		deleteStatement.setInt(1, Customer_ID);
		deleteStatement.executeUpdate();
		deleteStatement.close();
		DatabaseConnectivity.disconnect();
	}
	
	public void updateVehicleDetails(VehicleEvent vehicleEvent) throws SQLException {
		
		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();

		String sql = "UPDATE vehicles SET RegistrationNo = ?, Make = ?, Model = ?, EngineSerial = ?, ChasisNo = ?, Colour = ?"
				+ "WHERE Vehicle_ID = ?;";
		PreparedStatement updateStatement = conn.prepareStatement(sql);
	
		updateStatement.setString(1, vehicleEvent.getRegNo());
		updateStatement.setString(2, vehicleEvent.getMake());
		updateStatement.setString(3, vehicleEvent.getModel());
		updateStatement.setString(4, vehicleEvent.getEngSer());
		updateStatement.setString(5, vehicleEvent.getChNo());
		updateStatement.setString(6, vehicleEvent.getColour());
		updateStatement.setInt(7, vehicleEvent.getVehicle_ID());
		updateStatement.executeUpdate();
		
		updateStatement.close();
		DatabaseConnectivity.disconnect();
		
	}
}
