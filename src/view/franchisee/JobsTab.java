package view.franchisee;

import java.util.List;
import view.franchisee.jobs.NewCasualCustomerJobPanel;
import view.franchisee.jobs.PendingJobsPanel;
import view.franchisee.jobs.NewJobPanel;
import view.franchisee.jobs.CompletedJobsPanel;
import javax.swing.JTabbedPane;
import model.Customer;
import model.RequiredTask;
import model.Vehicle;
import view.franchisee.jobs.UncompletedJobsPanel;

/**
 *
 * @author Alex
 */
public class JobsTab extends JTabbedPane {

    private NewJobPanel newJobPanel;
    public NewJobPanel getNewJobPanel() {
		return newJobPanel;
	}

	private PendingJobsPanel pendingJobsPanel;
    private CompletedJobsPanel completedJobsPanel;
    private UncompletedJobsPanel uncompletedJobsPanel;
    private NewCasualCustomerJobPanel newCasualCustomerJobPanel;

    public JobsTab() {
        newJobPanel = new NewJobPanel();
        pendingJobsPanel = new PendingJobsPanel();
        completedJobsPanel = new CompletedJobsPanel();
        uncompletedJobsPanel = new UncompletedJobsPanel();
        newCasualCustomerJobPanel = new NewCasualCustomerJobPanel();
           
        this.addTab("New Job", newJobPanel);
        this.addTab("Pending Jobs", pendingJobsPanel);
        this.addTab("Completed Jobs", completedJobsPanel);
        this.addTab("Uncompleted Jobs", uncompletedJobsPanel);
        this.addTab("New Casual Customer Job", newCasualCustomerJobPanel);
    }
    
    public void setSorter() {
    	newJobPanel.setSorter();
    }

    public void setCustomerData(List<Customer> db) {
        newJobPanel.setCustomerData(db);
    }
    
   public void setRequiredTaskData(List<RequiredTask> db) {
        newJobPanel.setRequiredTaskData(db);
    }

    public void setVehicleData(List<Vehicle> db) {
        newJobPanel.setVehicleData(db);
    }
}
