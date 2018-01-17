package model;

import model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class UsersDatabase {
    
    private List<User> users;
    
    public UsersDatabase() {
        users = new LinkedList<>();
    }
    
    public void addUser(User user) {
        users.add(user);
    }
    
    public void removeUser(int index) {
        users.remove(index);
    }
    
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
    
    // Save and load the list of people
    public void saveToFile(File file) throws IOException {
        // wrap the file
        FileOutputStream fos = new FileOutputStream(file);
        // wrap the fos
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        User[] usersArray = users.toArray(new User[users.size()]);
        
        oos.writeObject(usersArray);
        
        oos.close();
    }
    
    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            User[] userArray =(User[]) ois.readObject();
            users.clear();
            users.addAll(Arrays.asList(userArray));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        
        
        ois.close();
    }
}
