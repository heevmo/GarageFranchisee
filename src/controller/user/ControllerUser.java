package controller.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import model.User;
import model.UsersDatabase;
import view.administrator.UserEvent;

/**
 *
 * @author Alex
 */
public class ControllerUser {

    private UsersDatabase db;

    public ControllerUser() {
        db = new UsersDatabase();
    }

    public List<User> getUsersDatabase() {
        return db.getUsers();
    }

    public void removeUser(int index) {
        db.removeUser(index);
    }

    public void addUser(UserEvent e) {
        
        String role = e.getRole();
        String firstName = e.getFirstName();
        String lastName = e.getLastName();
        String username = e.getUsername();
        String password = e.getPassword();
        String email = e.getEmail();

        User user = new User(role, firstName, lastName, username, password, email);
        db.addUser(user);
    }
    
    public void backUp(File file) throws IOException {
        db.saveToFile(file);
    }
    
    public void restore(File file) throws IOException {
        db.loadFromFile(file);
    }
}
