package view.administrator;

import java.util.EventObject;

/**
 *
 * @author Alex
 */
public class UserEvent extends EventObject {
    
    private String role;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public UserEvent(Object object, String role, String firstName, String lastName,
            String username, String password, String email) {

        super(object);
        
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
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
