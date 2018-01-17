
package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class RequiredTasksDatabase {
    
    private List<RequiredTask> requiredTasks;
    
    public RequiredTasksDatabase() {
        requiredTasks = new LinkedList<>();
    }
    
    public List<RequiredTask> getRequiredTasks() {
        return Collections.unmodifiableList(requiredTasks);
    }
}
