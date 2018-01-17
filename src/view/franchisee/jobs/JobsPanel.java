package view.franchisee.jobs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.RequiredTask;

/**
 *
 * @author Alex
 */
public class JobsPanel extends JPanel {
    
    private RequiredTasksTablePanel requiredTasksTablePanel;   
    
    private JTextField custField;
	private JTextField telField;
    private JTextField modelField ;
    private JTextField dateField ;
    private JTextField makeField;
    private JTextField vehRegNoField;
            
	public JobsPanel() {
        
        requiredTasksTablePanel = new RequiredTasksTablePanel();
        
        this.setBorder(BorderFactory.createTitledBorder("Job"));
        this.layoutJobPanel();
    }
    
    public void setRequiredTaskData(List<RequiredTask> db) {
        requiredTasksTablePanel.setRequiredTaskData(db);
    }
    
    private void layoutJobPanel() {
        
        // job details
        JPanel jobPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        jobPanel.setBorder(BorderFactory.createTitledBorder("Job Details"));
        
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 1;
        gbc.gridy = 0;
        jobPanel.add(new JLabel("Job No: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 2;
        JTextField jobNoField = new JTextField(5);
        jobNoField.setEditable(false);
        jobNoField.setBackground(Color.WHITE);
        jobPanel.add(jobNoField, gbc);
        
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        jobPanel.add(new JLabel("Vehicle Registration No: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 1;

        vehRegNoField = new JTextField(7);
        vehRegNoField.setEditable(false);
        vehRegNoField.setBackground(Color.WHITE);
        jobPanel.add(vehRegNoField, gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 2;
        jobPanel.add(new JLabel("Make: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;

        makeField = new JTextField(7);
        makeField.setEditable(false);
        makeField.setBackground(Color.WHITE);
        jobPanel.add(makeField, gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 3;
        jobPanel.add(new JLabel("Customer Name: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 3;
        custField = new JTextField(10);
        custField.setEditable(false);
        custField.setBackground(Color.WHITE);
        jobPanel.add(custField, gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 2;
        gbc.gridy = 1;
        jobPanel.add(new JLabel("Date Booked In: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 3;
        gbc.gridy = 1;

        dateField = new JTextField(7);
        dateField.setEditable(false);
        dateField.setBackground(Color.WHITE);
        jobPanel.add(dateField, gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 2;
        gbc.gridy = 2;
        jobPanel.add(new JLabel("Model: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 3;
        gbc.gridy = 2;
        modelField = new JTextField(7);
        modelField.setEditable(false);
        modelField.setBackground(Color.WHITE);
        jobPanel.add(modelField, gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridx = 2;
        gbc.gridy = 3;
        jobPanel.add(new JLabel("Tel: "), gbc);
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 3;
        gbc.gridy = 3;
  
        telField = new JTextField(7);
        telField.setEditable(false);
        telField.setBackground(Color.WHITE);
        jobPanel.add(telField, gbc);
        
        // description of work panel
        JPanel descriptionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2= new GridBagConstraints();
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Required tasks"));
        
        JPanel timePanel = new JPanel();
        JButton newTaskBtn = new JButton("New Task");
        timePanel.add(newTaskBtn);
        JButton removeTaskBtn = new JButton("Delete");
        timePanel.add(removeTaskBtn);
        timePanel.add(new JLabel("      Total Estimated Time: "));
        JTextField hoursField = new JTextField(2);
        hoursField.setBackground(Color.WHITE);
        hoursField.setEditable(false);
        timePanel.add(hoursField);
        timePanel.add(new JLabel("hours"));
        JTextField minutesField = new JTextField(2);
        minutesField.setBackground(Color.WHITE);
        minutesField.setEditable(false);
        timePanel.add(minutesField);
        timePanel.add(new JLabel("minutes"));
        
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx =1;
        gbc2.weighty = 1;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        descriptionPanel.add(requiredTasksTablePanel, gbc2);
        
        gbc2.insets = new Insets(5,0,0,0);
        gbc2.weighty = 0.05;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        descriptionPanel.add(timePanel, gbc2);
        
        // service type panel
        JPanel servicePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3= new GridBagConstraints();
        gbc3.weightx = 1;
        servicePanel.setBorder(BorderFactory.createTitledBorder("Service Type"));
        JCheckBox motBox = new JCheckBox("MoT Certificate");
        servicePanel.add(motBox, gbc3);
        JCheckBox regularBox = new JCheckBox("Regular Service");
        servicePanel.add(regularBox, gbc3);
        JCheckBox accidentBox = new JCheckBox("Accident Repair");
        servicePanel.add(accidentBox);
        JCheckBox otherBox = new JCheckBox("Other");
        servicePanel.add(otherBox, gbc3);
        
        JButton addJobBtn = new JButton("Add Job");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.fill = GridBagConstraints.BOTH;
                
        gbc4.weightx = 1;
        gbc4.weighty = 0.15;
        gbc4.gridy = 0;
        this.add(jobPanel, gbc4);
        
        gbc4.weighty = 1;
        gbc4.gridy++;
        this.add(descriptionPanel, gbc4);
        
        gbc4.weighty = 0.15;
        gbc4.gridy++;
        this.add(servicePanel, gbc4);
        
        gbc4.insets = new Insets(5,0,3,2);
        gbc4.anchor = GridBagConstraints.LAST_LINE_END;
        gbc4.weighty = 0;
        gbc4.fill = GridBagConstraints.NONE;
        gbc4.gridy++;
        this.add(addJobBtn, gbc4);
    }
    
    public JTextField getCustField() {
		return custField;
	}

	public void setCustField(JTextField custField) {
		this.custField = custField;
	}

	public JTextField getTelField() {
		return telField;
	}

	public void setTelField(JTextField telField) {
		this.telField = telField;
	}

	public void setFields(JobFieldsEvent jobFields) {
	    custField.setText(jobFields.getCustomer());;
		telField.setText(jobFields.getTelephone());;
	    modelField.setText(jobFields.getModel()); ;
	    makeField.setText(jobFields.getMake());;
	    vehRegNoField.setText(jobFields.getRegistration());
	}

}
