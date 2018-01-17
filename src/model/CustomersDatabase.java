/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import view.franchisee.customersvehicles.CustomerUpdateEvent;
import view.utils.DatabaseConnectivity;

/**
 *
 * @author Alex
 */
public class CustomersDatabase {

	private List<Customer> customersDatabase;
	private List<Customer> editCustomersDatabase;
	private int customerId;

	public CustomersDatabase() {
		customersDatabase = new LinkedList<>();
		editCustomersDatabase = new LinkedList<>();
	}

	public List<Customer> getCustomers() {
		return Collections.unmodifiableList(customersDatabase);
	}

	public List<Customer> getEditCustomers() {
		return Collections.unmodifiableList(editCustomersDatabase);
	}

	public int addCustomerToDatabase(Customer c) throws SQLException {
		int i = -1;

		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();
		String insertSQL = "INSERT INTO customers (Customer_ID, Name, Postcode, "
				+ "Address, CustomerClass, Telephone, Fax, Email)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement insertStatement = conn.prepareStatement(insertSQL);
		
		// first insert in the database
		if (customersDatabase.size() == 0) {
			insertStatement.setInt(1, 1);
			insertStatement.setString(2, c.getName());
			insertStatement.setString(3, c.getPostcode());
			insertStatement.setString(4, c.getAddress());
			insertStatement.setString(5, c.getCustomerClass());
			insertStatement.setString(6, c.getTelephone());
			insertStatement.setString(7, c.getFax());
			insertStatement.setString(8, c.getEmail());
			insertStatement.executeUpdate();
			i = 1;
			return i;
		} else {
			// set the id of the last customer inserted
			String countSQL = "SELECT Customer_ID FROM customers ORDER BY Customer_ID DESC LIMIT 1;";
			Statement countStatement = conn.createStatement();
			ResultSet results = countStatement.executeQuery(countSQL);
			results.next();
			customerId = results.getInt("Customer_ID") + 1;
			countStatement.close();
			results.close();
		}

		for (Customer customer : customersDatabase) {
			String name = customer.getName();
			String postcode = customer.getPostcode();
			if (customer.equals(c)) {
				insertStatement.close();
				DatabaseConnectivity.disconnect();
				return i;
			}
		}

		insertStatement.setInt(1, customerId);
		insertStatement.setString(2, c.getName());
		insertStatement.setString(3, c.getPostcode());
		insertStatement.setString(4, c.getAddress());
		insertStatement.setString(5, c.getCustomerClass());
		insertStatement.setString(6, c.getTelephone());
		insertStatement.setString(7, c.getFax());
		insertStatement.setString(8, c.getEmail());
		insertStatement.executeUpdate();
		insertStatement.close();
		DatabaseConnectivity.disconnect();
		i = customerId;
		return i;
	}

	public int load() throws SQLException {

		int i = -1;

		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();

		String sql = "SELECT * FROM customers";
		Statement selectStatement = conn.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);

		customersDatabase.clear();
		editCustomersDatabase.clear();
		while (results.next()) {
			int id = results.getInt("Customer_ID");
			String customerClass = results.getString("CustomerClass");
			String name = results.getString("Name");
			String postcode = results.getString("Postcode");
			String address = results.getString("Address");
			String telephone = results.getString("Telephone");
			String fax = results.getString("Fax");
			String email = results.getString("Email");
			Customer customer = new Customer(id, name, postcode, address, customerClass, telephone, fax, email);
			customersDatabase.add(customer);
			editCustomersDatabase.add(customer);
		}

		results.close();
		selectStatement.close();
		DatabaseConnectivity.disconnect();

		i = customersDatabase.size();

		return i;
	}

	public void deleteCustomerById(int Customer_ID) throws SQLException {
		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();
		String sql = "DELETE FROM customers WHERE Customer_ID = ?";
		PreparedStatement deleteStatement = conn.prepareStatement(sql);
		deleteStatement.setInt(1, Customer_ID);
		deleteStatement.executeUpdate();
		deleteStatement.close();
		DatabaseConnectivity.disconnect();
	}
	
	public void updateCustomerDetails(CustomerUpdateEvent customerEvent) throws SQLException {
		
		DatabaseConnectivity.connect();
		Connection conn = DatabaseConnectivity.getConnection();

		String sql = "UPDATE customers SET Name = ?, PostCode = ?, Address = ?, Telephone = ?, Fax = ?, Email = ?"
				+ "WHERE Customer_ID = ?;";
		PreparedStatement updateStatement = conn.prepareStatement(sql);
	
		updateStatement.setString(1, customerEvent.getName());
		updateStatement.setString(2, customerEvent.getPostcode());
		updateStatement.setString(3, customerEvent.getAddress());
		updateStatement.setString(4, customerEvent.getTelephone());
		updateStatement.setString(5, customerEvent.getFax());
		updateStatement.setString(6, customerEvent.getEmail());
		updateStatement.setInt(7, customerEvent.getCustomer_ID());
		updateStatement.executeUpdate();
		
		updateStatement.close();
		DatabaseConnectivity.disconnect();
		
	}
}
