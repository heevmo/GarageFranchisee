package view.franchisee.jobs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Customer;
import model.RequiredTask;
import model.Vehicle;

/**
 *
 * @author Alex
 */
public class NewJobPanel extends JPanel {

    private SearchPanel searchPanel;
	private JobsPanel jobsPanel;
	
	public NewJobPanel() {
        searchPanel = new SearchPanel(){
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
        
        
        jobsPanel = new JobsPanel() {
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
              
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(searchPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(jobsPanel, gbc);
    }
    
    public void setSorter() {
    	searchPanel.setSorter();
    }

    public void setCustomerData(List<Customer> db) {
        searchPanel.setCustomerData(db);
    }

    public void setVehicleData(List<Vehicle> db) {
        searchPanel.setVehicleData(db);
    }
    
    public void setRequiredTaskData(List<RequiredTask> db) {
        jobsPanel.setRequiredTaskData(db);
    }
    public SearchPanel getSearchPanel() {
		return searchPanel;
	}
    public JobsPanel getJobsPanel() {
		return jobsPanel;
	}
}
