package controller.user;

import java.util.List;
import model.RequiredTask;
import model.RequiredTasksDatabase;

/**
 *
 * @author Alex
 */
public class ControllerTask {
    
    private RequiredTasksDatabase db;
    
    public ControllerTask() {
        db = new RequiredTasksDatabase();
    }
    
    public List<RequiredTask> getRequiredTasksDatabase() {
        return db.getRequiredTasks();
    }
}
