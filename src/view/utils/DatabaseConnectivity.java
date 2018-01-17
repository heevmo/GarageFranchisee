package view.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectivity {
	
	private static Connection conn;
	
	public static void connect() {
		
		if (conn != null) {
			return;
		}
		
		try {
			// dynamically load the class for JDBC 3.x compatibility
			Class.forName("com.MySQL.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.getMessage();
		}
		String url = "jdbc:mysql://localhost:3306/garits";
		try {
			conn = DriverManager.getConnection(url, "root", "pass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
