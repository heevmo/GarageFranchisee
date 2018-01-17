package model;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class User implements Serializable {
    
    // Unique in the database
    private static int count = 1;

    private int id;
    private String role;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public User(String role, String firstName, String lastName, String username,
            String password, String email) {
        
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
