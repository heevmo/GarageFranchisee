package view.administrator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Alex
 */
public class EditUserPanel extends JPanel {

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

    private JCheckBox edit;
    private JButton setBtn;
    private JButton deleteBtn;
    
    private EditUserPanelListener listener;

    public EditUserPanel() {

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

        // buttons
        setBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");

        // check box
        edit = new JCheckBox();
        edit.setText("Edit");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = edit.isSelected();
                setFieldsEditable(isTicked);
            }
        });

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Edit User"));
        this.layoutEditUserPanel();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(0, 0);
    }
    
    public void setFields(UserEvent e) {
        firstNameField.setText(e.getFirstName());
        lastNameField.setText(e.getLastName());
        usernameField.setText(e.getUsername());
        passwordField.setText(e.getPassword());
        confirmField.setText("");
        emailField.setText(e.getEmail());
        String role = e.getRole();
        Enumeration<AbstractButton> ee = roleGroup.getElements();
        while (ee.hasMoreElements()) {
            AbstractButton b = ee.nextElement();
            if (b.getActionCommand().equals(role)) {
                b.setSelected(true);
            }
        }
        
        //middleNameField.setText(e.getFirstName());
        //locationField.setText(e.getFirstName());
        //divisionField.setText(e.getFirstName());
        //organizationField.setText(e.getFirstName());
        //officeNumberField.setText(e.getFirstName());
        //mobileNumberField.setText(e.getFirstName());
    }

    /**
     * Laying out components in Edit User Panel
     */
    private void layoutEditUserPanel() {

        // JRadioButton group
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
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.add(administratorRadio);
        rolePanel.add(franchiseeRadio);
        rolePanel.add(forepersonRadio);
        rolePanel.add(mechanicRadio);
        rolePanel.add(receptionistRadio);
        // Disable radio buttons and text fields
        this.setFieldsEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();

        // First row
        //gbc.insets = new Insets(0, 0, 10, 0);
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        this.add(edit, gbc);

        // Next row
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        this.add(new JLabel("Middle Name:"), gbc);
        gbc.gridx = 2;
        this.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 3;
        gbc.gridheight = 8;
        this.add(rolePanel, gbc);

        // Next row 
        gbc.ipady = 0;
        gbc.gridheight = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(firstNameField, gbc);
        gbc.gridx = 1;
        this.add(middleNameField, gbc);
        gbc.gridx = 2;
        this.add(lastNameField, gbc);

        // Next row
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        this.add(new JLabel("Division:"), gbc);
        gbc.gridx = 2;
        this.add(new JLabel("Organization:"), gbc);

        // Next row   
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(locationField, gbc);
        gbc.gridx = 1;
        this.add(divisionField, gbc);
        gbc.gridx = 2;
        this.add(organizationField, gbc);

        // Next row
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Office Number:"), gbc);
        gbc.gridx = 1;
        this.add(new JLabel("E-Mail Address:"), gbc);
        gbc.gridx = 2;
        this.add(new JLabel("Mobile Number:"), gbc);

        // Next row  
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(officeNumberField, gbc);
        gbc.gridx = 1;
        this.add(emailField, gbc);
        gbc.gridx = 2;
        this.add(mobileNumberField, gbc);

        // Next row
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        this.add(new JLabel("Password:"), gbc);
        gbc.gridx = 2;
        this.add(new JLabel("Confirm Password:"), gbc);

        // Next row   
        gbc.gridy++;
        gbc.gridx = 0;
        this.add(usernameField, gbc);
        gbc.gridx = 1;
        this.add(passwordField, gbc);
        gbc.gridx = 2;
        this.add(confirmField, gbc);

        // Next row
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        this.add(setBtn, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        this.add(deleteBtn, gbc);
    }

    /**
     * Set text fields and radio button group editable / uneditable
     */
    private void setFieldsEditable(boolean isTicked) {
        firstNameField.setEnabled(isTicked);
        lastNameField.setEnabled(isTicked);
        usernameField.setEnabled(isTicked);
        passwordField.setEnabled(isTicked);
        confirmField.setEnabled(isTicked);
        emailField.setEnabled(isTicked);
        middleNameField.setEnabled(isTicked);
        locationField.setEnabled(isTicked);
        divisionField.setEnabled(isTicked);
        organizationField.setEnabled(isTicked);
        officeNumberField.setEnabled(isTicked);
        mobileNumberField.setEnabled(isTicked);
        Enumeration<AbstractButton> e = roleGroup.getElements();
        while (e.hasMoreElements()) {
            e.nextElement().setEnabled(isTicked);
        }
        setBtn.setEnabled(isTicked);
    }
}
