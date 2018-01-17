package view.franchisee.customersvehicles;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Vehicle;

/**
 *
 * @author Alex
 */
public class AddNewCustomerPanel extends JPanel {

	private JPanel customerDetails;
	private JPanel vehicleDetails;
	private JButton addCustomerButton;
	private JRadioButton publicCustomerRadio;
	private JRadioButton businessCustomerRadio;
	private ButtonGroup customerGroup;
	private JRadioButton fixedRadio;
	private JRadioButton flexibleRadio;
	private JRadioButton variableRadio;
	private ButtonGroup discountGroup;
	private JTextField flexibleField;

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField companyNameField;
	private JTextField vatField;
	private JTextField addressField;
	private JTextField postCodeField;
	private JTextField telephoneField;
	private JTextField emailField;
	private JTextField faxField;

	private NewCustomerVehicleListener listener;
	
	//
	private VehicleDetailsTablePanel vehiclesPanel;

	public VehicleDetailsTablePanel getVehiclesPanel() {
		return vehiclesPanel;
	}

	public AddNewCustomerPanel() {

		// customer details panel
		customerDetails = new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		this.layoutCustomerDetailsPanel();
		customerDetails.setBorder(BorderFactory.createTitledBorder("Customer Details"));

		// vehicles details panel
		vehicleDetails = new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		this.layoutVehicleDetailsPanel();
		vehicleDetails.setBorder(BorderFactory.createTitledBorder("Vehicles Details"));

		// addCustomer button
		addCustomerButton = new JButton("Add Customer");
		addCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String companyName = companyNameField.getText();
				String vat = vatField.getText();
				String address = addressField.getText();
				String postcode = postCodeField.getText();
				String telephone = telephoneField.getText();
				String email = emailField.getText();
				String fax = faxField.getText();
				
				String customerClass = "";
				String name = "";
				if (publicCustomerRadio.isSelected()) {
					customerClass = publicCustomerRadio.getActionCommand();
					name = firstName + " " + lastName;
				} else if (businessCustomerRadio.isSelected()) {
					customerClass = businessCustomerRadio.getActionCommand();
					name = companyName;
				}
				
				// check to see if all fields are filled
				// first name and VAT are optional
				Boolean b1 = !name.equals("") && !address.equals("") && 
						!postcode.equals("") && !telephone.equals("");

				// otherwise a warning message is shown
				if (!b1) {
					JOptionPane.showMessageDialog(customerDetails, "Please make sure that all * fields are filled.",
							"Empty fields", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				CustomerEvent event = new CustomerEvent(customerDetails, name, postcode, address, customerClass, telephone, fax, email);
				if (listener != null) {
					listener.addCustomerToDatabase(event);
				}
				
				firstNameField.setText("");
				lastNameField.setText("");
				companyNameField.setText("");
				vatField.setText("");
				addressField.setText("");
				postCodeField.setText("");
				telephoneField.setText("");
				emailField.setText("");
				faxField.setText("");
			}

		});
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};

		//

		this.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		buttonPanel.add(addCustomerButton);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 0;
		gbc.gridx = 0;
		this.add(customerDetails, gbc);
		gbc.gridy = 0;
		gbc.gridx = 1;
		this.add(vehicleDetails, gbc);
		gbc.insets = new Insets(3, 0, 0, 0);
		gbc.weighty = 0.1;
		gbc.gridy = 1;
		gbc.gridx = 1;
		this.add(buttonPanel, gbc);

	}

	private void layoutCustomerDetailsPanel() {
		publicCustomerRadio = new JRadioButton("Public Customer");
		publicCustomerRadio.setActionCommand("Public");
		publicCustomerRadio.setSelected(true);
		publicCustomerRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (publicCustomerRadio.isSelected()) {
					companyNameField.setText("");
					vatField.setText("");
					companyNameField.setEnabled(false);
					vatField.setEnabled(false);
					firstNameField.setEnabled(true);
					lastNameField.setEnabled(true);
				}
			}

		});
		businessCustomerRadio = new JRadioButton("Business Customer");
		businessCustomerRadio.setActionCommand("Business");
		businessCustomerRadio.setSelected(true);
		businessCustomerRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (businessCustomerRadio.isSelected()) {
					firstNameField.setText("");
					lastNameField.setText("");
					firstNameField.setEnabled(false);
					lastNameField.setEnabled(false);
					companyNameField.setEnabled(true);
					vatField.setEnabled(true);
				}
			}

		});
		customerGroup = new ButtonGroup();
		customerGroup.add(publicCustomerRadio);
		customerGroup.add(businessCustomerRadio);
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		companyNameField = new JTextField(10);
		vatField = new JTextField(10);
		companyNameField.setEnabled(false);
		vatField.setEnabled(false);
		addressField = new JTextField(14);
		postCodeField = new JTextField(10);
		telephoneField = new JTextField(10);
		emailField = new JTextField(14);
		faxField = new JTextField(10);
		fixedRadio = new JRadioButton("fixed");
		flexibleRadio = new JRadioButton("flexible");
		variableRadio = new JRadioButton("variable");
		fixedRadio.setActionCommand("fixed");
		flexibleRadio.setActionCommand("flexible");
		variableRadio.setActionCommand("variable");
		discountGroup = new ButtonGroup();
		discountGroup.add(fixedRadio);
		discountGroup.add(flexibleRadio);
		discountGroup.add(variableRadio);
		flexibleField = new JTextField();
		JPanel discountPanel = new JPanel();
		discountPanel.setLayout(new BoxLayout(discountPanel, BoxLayout.Y_AXIS));
		discountPanel.setBorder(BorderFactory.createTitledBorder("Discount "));
		discountPanel.add(fixedRadio);
		discountPanel.add(flexibleRadio);
		discountPanel.add(variableRadio);
		discountPanel.add(flexibleField);

		customerDetails.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 5, 0, 0);

		gbc.weightx = 1;
		gbc.ipady = 10;
		gbc.weighty = 0.05;
		gbc.gridx = 0;
		gbc.gridy = 0;
		customerDetails.add(publicCustomerRadio, gbc);

		gbc.ipady = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		customerDetails.add(new JLabel("First Name: "), gbc);

		gbc.weighty = 0.1;
		gbc.gridy = 2;
		customerDetails.add(firstNameField, gbc);

		gbc.weighty = 0.05;
		gbc.gridy = 3;
		customerDetails.add(businessCustomerRadio, gbc);

		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		customerDetails.add(new JLabel("Company Name * : "), gbc);

		gbc.weighty = 1;
		gbc.gridy = 5;
		customerDetails.add(companyNameField, gbc);

		gbc.weighty = 0;
		gbc.gridx = 1;
		gbc.gridy = 1;
		customerDetails.add(new JLabel("Last Name * : "), gbc);

		gbc.weighty = 0.2;
		gbc.gridy = 2;
		customerDetails.add(lastNameField, gbc);

		gbc.weighty = 0;
		gbc.gridy = 4;
		customerDetails.add(new JLabel("VAT: "), gbc);

		gbc.weighty = 1;
		gbc.gridy = 5;
		customerDetails.add(vatField, gbc);

		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new GridBagLayout());

		gbc.gridy = 0;
		addressPanel.add(new JLabel("Address * : "), gbc);

		gbc.gridy = 1;
		addressPanel.add(addressField, gbc);

		gbc.gridy = 2;
		addressPanel.add(new JLabel("Post Code * : "), gbc);

		gbc.gridy = 3;
		addressPanel.add(postCodeField, gbc);

		gbc.gridy = 4;
		addressPanel.add(new JLabel("Telephone No * : "), gbc);

		gbc.gridy = 5;
		addressPanel.add(telephoneField, gbc);

		gbc.gridy = 6;
		addressPanel.add(new JLabel("E-mail address: "), gbc);

		gbc.gridy = 7;
		addressPanel.add(emailField, gbc);

		gbc.gridy = 8;
		addressPanel.add(new JLabel("Fax: "), gbc);

		gbc.gridy = 9;
		addressPanel.add(faxField, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 5;
		customerDetails.add(addressPanel, gbc);

	}

	private void layoutVehicleDetailsPanel() {
		vehicleDetails.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 0;

		// set panel
		JPanel setPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		setPanel.add(new JLabel("Set No of Vehicles: "));
		JTextField noOfVField = new JTextField(2);
		setPanel.add(noOfVField);
		JButton setBtn = new JButton("Set");
		setPanel.add(setBtn);

		gbc.gridy = 0;
		// vehicleDetails.add(setPanel, gbc);

		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		vehiclesPanel = new VehicleDetailsTablePanel();
		vehicleDetails.add(vehiclesPanel, gbc);

		//
		JPanel detailsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc2.weightx = 1;
		gbc2.insets = new Insets(0, 5, 0, 0);

		gbc2.gridx = 0;
		gbc2.gridy = 0;
		detailsPanel.add(new JLabel("Registration No * : "), gbc2);
		JTextField regNoField = new JTextField(10);
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		detailsPanel.add(regNoField, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 2;
		detailsPanel.add(new JLabel("Engine Serial * : "), gbc2);
		JTextField engSerField = new JTextField(10);
		gbc2.gridx = 0;
		gbc2.gridy = 3;
		detailsPanel.add(engSerField, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 0;
		detailsPanel.add(new JLabel("Make * : "), gbc2);
		JTextField makeField = new JTextField(10);
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		detailsPanel.add(makeField, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 2;
		detailsPanel.add(new JLabel("Chasis No * : "), gbc2);
		JTextField chNoField = new JTextField(10);
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		detailsPanel.add(chNoField, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 0;
		detailsPanel.add(new JLabel("Model * : "), gbc2);
		JTextField modelField = new JTextField(10);
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		detailsPanel.add(modelField, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 2;
		detailsPanel.add(new JLabel("Colour * : "), gbc2);
		JTextField colourField = new JTextField(10);
		gbc2.gridx = 2;
		gbc2.gridy = 3;
		detailsPanel.add(colourField, gbc2);

		// delete vehicle from temporary list
		JButton deleteVehicleBtn = new JButton("Delete");
		deleteVehicleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = vehiclesPanel.getSelectedRow();
				if (row != -1) {
					int option = JOptionPane.showConfirmDialog(vehicleDetails,
							"Are you sure you want to delete the selected row?", "Delete vehicle", JOptionPane.WARNING_MESSAGE,
							JOptionPane.YES_NO_OPTION);
					if (listener != null && option == 0) {
						listener.deleteFromTemporaryList(row);
						vehiclesPanel.refresh();
					}
				} else {
					JOptionPane.showMessageDialog(vehicleDetails, "Please select a row in order to delete it.", 
							"Select a row", JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		gbc2.anchor = GridBagConstraints.PAGE_START;
		gbc2.gridx = 3;
		gbc2.gridy = 1;
		detailsPanel.add(deleteVehicleBtn, gbc2);

		JButton addVehicleBtn = new JButton("Add Vehicle");

		// add action listener to the addVehicle button to add vehicles
		// in the temporary list
		addVehicleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String regNo = regNoField.getText();
				String engSer = engSerField.getText();
				String make = makeField.getText();
				String chNo = chNoField.getText();
				String model = modelField.getText();
				String colour = colourField.getText();

				// check to see if all fields are filled
				Boolean b1 = !regNo.equals("") && !engSer.equals("") && !make.equals("") && !chNo.equals("")
						&& !model.equals("") && !colour.equals("");

				// otherwise a warning message is shown
				if (!b1) {
					JOptionPane.showMessageDialog(vehiclesPanel, "Please make sure that all * fields are filled.",
							"Empty fields", JOptionPane.WARNING_MESSAGE);
					return;
				}

				VehicleEvent event = new VehicleEvent(vehicleDetails, regNo, engSer, make, chNo, model, colour);

				if (listener != null) {
					listener.addToTemporaryList(event);
				}

				// clear text fields
				regNoField.setText("");
				engSerField.setText("");
				makeField.setText("");
				chNoField.setText("");
				modelField.setText("");
				colourField.setText("");
			}
		});

		gbc2.gridx = 3;
		gbc2.gridy = 3;
		detailsPanel.add(addVehicleBtn, gbc2);

		gbc.gridy = 2;
		gbc.weighty = 0.25;
		vehicleDetails.add(detailsPanel, gbc);
	}
	
	public void setCustomerVehicleListener(NewCustomerVehicleListener l) {
		listener = l;
	}

	public JPanel getVehicleDetails() {
		return vehicleDetails;
	}

	public void setNewVehicleData(List<Vehicle> db) {
		vehiclesPanel.setNewVehicleData(db);
	}
	

}
