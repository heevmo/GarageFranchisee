package view.administrator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Alex
 */
public class NewUserPanel extends JPanel {

    private NewUserPanelListener listener;

    private JButton addBtn;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField confirmField;
    private JTextField emailField;

    private JTextField middleNameField;
    private JTextField locationField;
    private JTextField divisionField;
    private JTextField organizationField;
    private JTextField officeNumberField;
    private JTextField mobileNumberField;

    private ButtonGroup roleGroup;

    public NewUserPanel() {

        middleNameField = new JTextField(10);
        locationField = new JTextField(10);
        divisionField = new JTextField(10);
        organizationField = new JTextField(10);
        officeNumberField = new JTextField(10);
        mobileNumberField = new JTextField(10);

        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        usernameField = new JTextField(10);
        passwordField = new JTextField(10);
        confirmField = new JTextField(10);
        emailField = new JTextField(10);

        roleGroup = new ButtonGroup();

        addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String role = roleGroup.getSelection().getActionCommand();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();

                String passwordConfirmed = confirmField.getText();

                Boolean b1 = !password.equals("") && !username.equals("")
                        && !firstName.equals("") && !lastName.equals("");

                if (!b1) {
                    JOptionPane.showMessageDialog(NewUserPanel.this, 
                            "Please make sure that all field are filled.", 
                            "Empty fields", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Boolean b2 = password.equals(passwordConfirmed);

                if (!b2) {
                    JOptionPane.showMessageDialog(NewUserPanel.this, 
                            "Please make sure that passwords match.", 
                            "Passwords mismatch", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (b1 && b2) {

                    UserEvent event = new UserEvent(NewUserPanel.this, 
                            role, firstName, lastName, username, password, email);

                    if (listener != null) {
                        listener.panelEventOccured(event);
                    }
                }

                NewUserPanel.this.clearFields();
            }
        });
        
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("New User"));
        this.layoutNewUserPanel();
    }
    
    /**
     *
     */
    public void setNewUserPanelListener(NewUserPanelListener listener) {
        this.listener = listener;
    }

    /**
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(0, 0);
    }

    /**
     *
     */
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        emailField.setText("");
        confirmField.setText("");

        middleNameField.setText("");
        locationField.setText("");
        divisionField.setText("");
        organizationField.setText("");
        officeNumberField.setText("");
        mobileNumberField.setText("");

    }

    /**
     * Laying out components in New User Panel
     */
    private void layoutNewUserPanel() {

        // JButton group
        JRadioButton administratorRadio = new JRadioButton("Administrator");
        JRadioButton franchiseeRadio = new JRadioButton("Franchisee");
        JRadioButton forepersonRadio = new JRadioButton("Foreperson");
        JRadioButton mechanicRadio = new JRadioButton("Mechanic");
        JRadioButton receptionistRadio = new JRadioButton("Receptionist");
        administratorRadio.setSelected(true);

        administratorRadio.setActionCommand("Administrator");
        franchiseeRadio.setActionCommand("Franchisee");
        forepersonRadio.setActionCommand("Foreperson");
        mechanicRadio.setActionCommand("Mechanic");
        receptionistRadio.setActionCommand("Receptionist");

        roleGroup.add(administratorRadio);
        roleGroup.add(franchiseeRadio);
        roleGroup.add(forepersonRadio);
        roleGroup.add(mechanicRadio);
        roleGroup.add(receptionistRadio);

        // Role panel
        JPanel rolePanel = new JPanel();

        rolePanel.setBorder(BorderFactory.createTitledBorder("Role"));
        rolePanel.setLayout(
                new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.add(administratorRadio);

        rolePanel.add(franchiseeRadio);

        rolePanel.add(forepersonRadio);

        rolePanel.add(mechanicRadio);

        rolePanel.add(receptionistRadio);

        GridBagConstraints gbc = new GridBagConstraints();

        // First row
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weighty = 0.25;

        this.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;

        this.add(
                new JLabel("Middle Name:"), gbc);

        gbc.gridx = 2;

        this.add(
                new JLabel("Last Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridheight = 6;

        this.add(rolePanel, gbc);

        // Next row  
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;

        this.add(firstNameField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;

        this.add(
                middleNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;

        this.add(lastNameField, gbc);

        // Next row
        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(
                new JLabel("Location:"), gbc);

        gbc.gridx = 1;

        this.add(
                new JLabel("Division:"), gbc);

        gbc.gridx = 2;

        this.add(
                new JLabel("Organization:"), gbc);

        // Next row   
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;

        this.add(locationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        this.add(divisionField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;

        this.add(organizationField, gbc);

        // Next row
        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 4;

        this.add(new JLabel("Office Number:"), gbc);

        gbc.gridx = 1;

        this.add(new JLabel("E-Mail Address:"), gbc);

        gbc.gridx = 2;

        this.add(new JLabel("Mobile Number:"), gbc);

        // Next row    
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;

        this.add(officeNumberField, gbc);

        gbc.gridx = 1;

        this.add(emailField, gbc);

        gbc.gridx = 2;

        this.add(mobileNumberField, gbc);

        // Next row
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.weighty = 0.25;
        gbc.gridx = 0;
        gbc.gridy = 6;

        this.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;

        this.add(new JLabel("Password:"), gbc);

        gbc.gridx = 2;

        this.add(new JLabel("Confirm Password:"), gbc);

        // Next row   
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;

        this.add(usernameField, gbc);

        gbc.gridx = 1;

        this.add(passwordField, gbc);

        gbc.gridx = 2;

        this.add(confirmField, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridheight = 2;

        this.add(addBtn, gbc);
    }
}
