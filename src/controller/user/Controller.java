
package controller.user;

import model.Database;

/**
 *
 * @author Alex
 */
public class Controller {
    
    Database db;
    
    public Controller() {
        db = new Database();
        db.connect();
    }
}
