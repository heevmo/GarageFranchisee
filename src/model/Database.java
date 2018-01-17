package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public class Database {

    private Connection con;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.getMessage();
        }

        String url = "jdbc:mysql://localhost:3306/garits";
        try {
            con = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
        System.out.println("Connected");
    }

    public void disconnect() {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        System.out.println("Disconnected");
    }
}
