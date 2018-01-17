package view.franchisee.customersvehicles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Customer;
import model.Vehicle;
import view.franchisee.jobs.CustomerTablePanel;
import view.franchisee.jobs.VehiclesTablePanel;

/**
 *
 * @author Alex
 */
public class EditCustomersVehiclesPanel extends JPanel {

	private CustomerVehicleListener listener;

	private JPanel customerDetails;
	private JPanel vehicleDetails;
	private JButton addCustomerButton;
	private JRadioButton publicCustomerRadio;
	private JRadioButton businessCustomerRadio;
	private ButtonGroup customerGroup;
	private JRadioButton noneRadio;
	private JRadioButton fixedRadio;
	private JRadioButton flexibleRadio;
	private JRadioButton variableRadio;
	private ButtonGroup discountGroup;
	private JTextField variableField;

	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField companyNameField;
	private JTextField vatField;
	private JTextField addressField;
	private JTextField postCodeField;
	private JTextField telephoneField;
	private JTextField emailField;
	private JTextField faxField;

	private JPanel customersDatabase;
	private JPanel vehiclesDatabase;
	private CustomerTablePanel customerTablePanel;

	private VehiclesTablePanel vehiclesTablePanel;

	private JButton updateBtn;
	private JButton delBtn;
	private JCheckBox editCustomerBox;
	private JCheckBox creditB;
	private JTextField regNoField2;
	private JTextField engSerField2;
	private JTextField makeField2;
	private JTextField chasisNoField2;
	private JTextField modelField2;
	private JTextField colourField2;
	private JTextField regNoField;
	private JTextField engSerField;
	private JTextField makeField;
	private JTextField chasisNoField;
	private JTextField modelField;
	private JTextField colourField;
	private JButton addVehicleBtn;
	private JButton deletBtn;
	private JButton editVehicleBtn;
	private JCheckBox editVehicleBox;

	private int Customer_ID;
	private int Vehicle_ID;

	public EditCustomersVehiclesPanel() {
		customersDatabase = new JPanel(new GridBagLayout()) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		vehiclesDatabase = new JPanel(new GridBagLayout()) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		customerDetails = new JPanel(new GridBagLayout()) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		customerDetails.setBorder(BorderFactory.createTitledBorder("Customers Details"));
		vehicleDetails = new JPanel(new GridBagLayout()) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};

		this.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new GridBagLayout());
		this.layoutComp();
		this.layoutCustomersDatabaseComp();
		this.layoutVehiclesDatabaseComp();
		this.layoutCustomerDetailsComp();
		this.layoutVehicleDetailsComp();
		this.setEditableCustomer(false);
		this.setEditableVehicle(false);

	}

	public VehiclesTablePanel getVehiclesTablePanel() {
		return vehiclesTablePanel;
	}

	public void setListener(CustomerVehicleListener l) {
		listener = l;
	}

	public void setSorter() {
		customerTablePanel.setSorter();
		vehiclesTablePanel.setSorter();
	}

	private void layoutCustomersDatabaseComp() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 0;
		customerTablePanel = new CustomerTablePanel();

		// select vehicles associated to a customer
		JTable table = customerTablePanel.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				setEditableCustomer(false);
				setEditableVehicle(false);
				editCustomerBox.setSelected(false);
				editVehicleBox.setSelected(false);

				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);

				if (row > -1) {
					Customer_ID = -1;
					if (listener != null) {
						Customer_ID = (int) table.getValueAt(row, 0);
						System.out.println(Customer_ID);
						listener.loadByCustomer(Customer_ID);
					}

					// get row details

					nameField.setText((String) table.getValueAt(row, 1));
					addressField.setText((String) table.getValueAt(row, 3));
					postCodeField.setText((String) table.getValueAt(row, 2));
					telephoneField.setText((String) table.getValueAt(row, 5));
					emailField.setText((String) table.getValueAt(row, 7));
					faxField.setText((String) table.getValueAt(row, 6));

					regNoField.setText("");
					engSerField.setText("");
					makeField.setText("");
					chasisNoField.setText("");
					modelField.setText("");
					colourField.setText("");

					regNoField2.setText("");
					engSerField2.setText("");
					makeField2.setText("");
					chasisNoField2.setText("");
					modelField2.setText("");
					colourField2.setText("");
				}
			}
		});
		customersDatabase.add(customerTablePanel, gbc);
	}

	private void layoutVehiclesDatabaseComp() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 0;
		vehiclesTablePanel = new VehiclesTablePanel() {

			public void clearSelectedCustomer() {
				if (listener != null) {
					listener.clear();
				}

				setEditableCustomer(false);
				editCustomerBox.setSelected(false);

				setEditableVehicle(false);
				editVehicleBox.setSelected(false);

				nameField.setText("");
				addressField.setText("");
				postCodeField.setText("");
				telephoneField.setText("");
				emailField.setText("");
				faxField.setText("");

				regNoField.setText("");
				engSerField.setText("");
				makeField.setText("");
				chasisNoField.setText("");
				modelField.setText("");
				colourField.setText("");

				regNoField2.setText("");
				engSerField2.setText("");
				makeField2.setText("");
				chasisNoField2.setText("");
				modelField2.setText("");
				colourField2.setText("");

				JTable table = customerTablePanel.getTable();
				table.clearSelection();
			}

		};
		vehiclesDatabase.add(vehiclesTablePanel, gbc);

		JTable table = vehiclesTablePanel.getTable();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				setEditableCustomer(false);
				setEditableVehicle(false);
				editCustomerBox.setSelected(false);
				editVehicleBox.setSelected(false);

				regNoField2.setText("");
				engSerField2.setText("");
				makeField2.setText("");
				chasisNoField2.setText("");
				modelField2.setText("");
				colourField2.setText("");

				regNoField2.setText("");
				engSerField2.setText("");
				makeField2.setText("");
				chasisNoField2.setText("");
				modelField2.setText("");
				colourField2.setText("");

				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);

				if (row > -1) {

					regNoField.setText((String) table.getValueAt(row, 1));
					engSerField.setText((String) table.getValueAt(row, 4));
					makeField.setText((String) table.getValueAt(row, 2));
					chasisNoField.setText((String) table.getValueAt(row, 5));
					modelField.setText((String) table.getValueAt(row, 3));
					colourField.setText((String) table.getValueAt(row, 6));

					// find customer associated with vehicle
					int id = -1;

					if (listener != null) {
						Vehicle_ID = (int) table.getValueAt(row, 0);
						id = listener.getCustomerByVehicle(Vehicle_ID);
						Customer_ID = id;
						System.out.println("Customer_ID:" + Customer_ID);
						System.out.println("Vehicle_ID:" + Vehicle_ID);
					}
					
					int i = customerTablePanel.getTable().getRowCount();
					
					while(i-- > 0) {
						if((int)customerTablePanel.getTable().getValueAt(i, 0) == Customer_ID)
							id = i;
					}
					
					if (id > -1 && id < customerTablePanel.getTable().getRowCount()) {
						// get contents from table model
						JTable cTable = customerTablePanel.getTable();
						nameField.setText((String) cTable.getValueAt(id, 1));
						addressField.setText((String) cTable.getValueAt(id, 3));
						postCodeField.setText((String) cTable.getValueAt(id, 2));
						telephoneField.setText((String) cTable.getValueAt(id, 5));
						emailField.setText((String) cTable.getValueAt(id, 7));
						faxField.setText((String) cTable.getValueAt(id, 6));
					}
				}
			}

		});
	}

	private void layoutCustomerDetailsComp() {
		publicCustomerRadio = new JRadioButton("Public Customer");
		businessCustomerRadio = new JRadioButton("Business Customer");
		publicCustomerRadio.setActionCommand("Public Customer");
		businessCustomerRadio.setActionCommand("Business Customer");
		customerGroup = new ButtonGroup();
		customerGroup.add(publicCustomerRadio);
		customerGroup.add(businessCustomerRadio);
		nameField = new JTextField(14);
		nameField.setBackground(Color.WHITE);
		lastNameField = new JTextField(10);
		companyNameField = new JTextField(10);
		vatField = new JTextField(10);
		addressField = new JTextField(14);
		addressField.setBackground(Color.WHITE);
		postCodeField = new JTextField(10);
		postCodeField.setBackground(Color.WHITE);
		telephoneField = new JTextField(10);
		telephoneField.setBackground(Color.WHITE);
		emailField = new JTextField(14);
		emailField.setBackground(Color.WHITE);
		faxField = new JTextField(10);
		faxField.setBackground(Color.WHITE);
		noneRadio = new JRadioButton("none");
		noneRadio = new JRadioButton("none");
		noneRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (noneRadio.isSelected()) {
					variableField.setEditable(false);
					variableField.setText("");
				}
			}
		});
		fixedRadio = new JRadioButton("fixed");
		fixedRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fixedRadio.isSelected()) {
					variableField.setEditable(false);
					variableField.setText("");
				}
			}
		});
		flexibleRadio = new JRadioButton("flexible");
		variableRadio = new JRadioButton("variable");
		fixedRadio.setActionCommand("fixed");
		flexibleRadio.setActionCommand("flexible");
		flexibleRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (flexibleRadio.isSelected()) {
					variableField.setEditable(false);
					variableField.setText("");
				}
			}
		});
		variableRadio.setActionCommand("variable");
		discountGroup = new ButtonGroup();
		discountGroup.add(noneRadio);
		discountGroup.add(fixedRadio);
		discountGroup.add(flexibleRadio);
		discountGroup.add(variableRadio);
		variableField = new JTextField(2);
		variableField.setEditable(false);
		variableField.setBackground(Color.WHITE);
		variableRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (variableRadio.isSelected()) {
					variableField.setEditable(true);
				}
			}
		});

		JPanel discountPanel = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		discountPanel.setBorder(BorderFactory.createTitledBorder("Discount plan"));
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		g.gridy = 0;
		discountPanel.add(noneRadio, g);
		g.gridy++;
		discountPanel.add(fixedRadio, g);
		g.gridy++;
		discountPanel.add(flexibleRadio, g);
		g.gridy++;
		discountPanel.add(variableRadio, g);
		g.anchor = GridBagConstraints.CENTER;
		discountPanel.add(variableField, g);

		customerDetails.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 5, 12, 0);
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.weighty = 0;
		gbc.gridx = 2;
		gbc.gridy = 2;
		editCustomerBox = new JCheckBox("Edit");
		editCustomerBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(customerDetails,
							"Please select a customer or vehicle in order to edit it.",
							"No customer or vehicle selected", JOptionPane.WARNING_MESSAGE);
					editCustomerBox.setSelected(false);
					return;
				}
				boolean isTicked = editCustomerBox.isSelected();
				setEditableCustomer(isTicked);
			}
		});
		gbc.ipadx = 26;
		customerDetails.add(editCustomerBox, gbc);
		gbc.ipadx = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.ipady = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		customerDetails.add(new JLabel("Name: "), gbc);

		gbc.gridy = 1;
		customerDetails.add(nameField, gbc);

		gbc.gridy = 2;
		customerDetails.add(discountPanel, gbc);

		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		JPanel creditPanel = new JPanel();
		creditPanel.setBorder(BorderFactory.createTitledBorder("Credit"));
		creditB = new JCheckBox("Credit");
		creditPanel.add(creditB);
		customerDetails.add(creditB, gbc);

		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new GridBagLayout());

		gbc.gridy = 0;
		addressPanel.add(new JLabel("Address: "), gbc);

		gbc.gridy = 1;
		addressPanel.add(addressField, gbc);

		gbc.gridy = 2;
		addressPanel.add(new JLabel("Post Code: "), gbc);

		gbc.gridy = 3;
		addressPanel.add(postCodeField, gbc);

		gbc.gridy = 4;
		addressPanel.add(new JLabel("Telephone No: "), gbc);

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

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 5;
		customerDetails.add(addressPanel, gbc);

		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// customer buttons update and delete
		updateBtn = new JButton("Update");
		// delete customer
		delBtn = new JButton("Delete");
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setEditableCustomer(false);
				editCustomerBox.setSelected(false);
				
				nameField.setText("");
				addressField.setText("");
				postCodeField.setText("");
				telephoneField.setText("");
				emailField.setText("");
				faxField.setText("");

				regNoField.setText("");
				engSerField.setText("");
				makeField.setText("");
				chasisNoField.setText("");
				modelField.setText("");
				colourField.setText("");

				regNoField2.setText("");
				engSerField2.setText("");
				makeField2.setText("");
				chasisNoField2.setText("");
				modelField2.setText("");
				colourField2.setText("");

				if (listener != null) {
					listener.deleteCustomer(Customer_ID);
				}
				
				
			}

		});
		gc.insets = new Insets(0, 0, 0, 10);
		btnPanel.add(updateBtn, gc);
		// update customer details
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = nameField.getText();
				String address = addressField.getText();
				String postcode = postCodeField.getText();
				String telephone = telephoneField.getText();
				String email = emailField.getText();
				String fax = faxField.getText();
				
				CustomerUpdateEvent c = new CustomerUpdateEvent(customerDetails, name, 
						address, postcode, telephone, email, fax, Customer_ID);
				System.out.println("update");
				if (listener != null) {
					listener.updateCustomerDetails(c);
				}
				
				editCustomerBox.setSelected(false);
				setEditableCustomer(false);
			}
		});
		gc.insets = new Insets(0, 0, 0, 0);
		btnPanel.add(delBtn, gc);

		gbc.gridx = 2;
		gbc.gridy = 4;
		customerDetails.add(btnPanel, gbc);

	}

	private void layoutVehicleDetailsComp() {

		JPanel editVehicle = new JPanel(new GridBagLayout()) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		editVehicle.setBorder(BorderFactory.createTitledBorder("Edit Vehicle"));

		GridBagConstraints gbc1 = new GridBagConstraints();
		editVehicleBox = new JCheckBox("Edit");
		editVehicleBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (regNoField.getText().equals("")) {
					JOptionPane.showMessageDialog(editVehicle, "Please select a vehicle in order to edit it.",
							"No vehicle selected", JOptionPane.WARNING_MESSAGE);
					editVehicleBox.setSelected(false);
					return;
				}
				boolean isTicked = editVehicleBox.isSelected();
				setEditableVehicle(isTicked);
			}
		});
		gbc1.anchor = GridBagConstraints.PAGE_START;
		gbc1.weightx = 1;
		gbc1.weighty = 0;
		gbc1.insets = new Insets(0, 5, 0, 0);

		gbc1.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		editVehicle.add(new JLabel("Registration No: "), gbc1);

		regNoField = new JTextField(10);
		regNoField.setBackground(Color.WHITE);
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		editVehicle.add(regNoField, gbc1);

		gbc1.gridx = 0;
		gbc1.gridy = 2;
		editVehicle.add(new JLabel("Engine Serial: "), gbc1);

		engSerField = new JTextField(10);

		engSerField.setBackground(Color.WHITE);
		gbc1.gridx = 0;
		gbc1.gridy = 3;
		editVehicle.add(engSerField, gbc1);

		gbc1.gridx = 1;
		gbc1.gridy = 0;
		editVehicle.add(new JLabel("Make: "), gbc1);

		makeField = new JTextField(10);
		makeField.setBackground(Color.WHITE);
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		editVehicle.add(makeField, gbc1);

		gbc1.gridx = 1;
		gbc1.gridy = 2;
		editVehicle.add(new JLabel("Chasis No: "), gbc1);

		chasisNoField = new JTextField(10);
		chasisNoField.setBackground(Color.WHITE);
		gbc1.gridx = 1;
		gbc1.gridy = 3;
		editVehicle.add(chasisNoField, gbc1);

		gbc1.gridx = 2;
		gbc1.gridy = 0;
		editVehicle.add(new JLabel("Model: "), gbc1);

		modelField = new JTextField(10);
		modelField.setBackground(Color.WHITE);
		gbc1.gridx = 2;
		gbc1.gridy = 1;
		editVehicle.add(modelField, gbc1);

		gbc1.gridx = 2;
		gbc1.gridy = 2;
		editVehicle.add(new JLabel("Colour: "), gbc1);

		colourField = new JTextField(10);
		colourField.setBackground(Color.WHITE);
		gbc1.gridx = 2;
		gbc1.gridy = 3;
		editVehicle.add(colourField, gbc1);
		
		// delete vehicle
		deletBtn = new JButton("Delete");
		deletBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setEditableVehicle(false);
				nameField.setText("");
				addressField.setText("");
				postCodeField.setText("");
				telephoneField.setText("");
				emailField.setText("");
				faxField.setText("");

				regNoField.setText("");
				engSerField.setText("");
				makeField.setText("");
				chasisNoField.setText("");
				modelField.setText("");
				colourField.setText("");

				regNoField2.setText("");
				engSerField2.setText("");
				makeField2.setText("");
				chasisNoField2.setText("");
				modelField2.setText("");
				colourField2.setText("");

				if (listener != null) {
					listener.deleteVehicle(Vehicle_ID, Customer_ID);
				}
			}

		});
		
		// update vehicle details button
		editVehicleBtn = new JButton("Update");
		editVehicleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String regNo = regNoField.getText();
				String engSer = engSerField.getText();
				String make = makeField.getText();
				String chasisNo = chasisNoField.getText();
				String model = modelField.getText();
				String colour = colourField.getText();
				
				if (regNoField.getText().equals("")) {
					return;
				}
				
				VehicleEvent vehicleEvent = new VehicleEvent(editVehicleBtn, regNo, engSer, make, chasisNo, model, colour, Vehicle_ID );
				
				if (listener != null) {
					listener.updateVehicleDetails(vehicleEvent, Customer_ID);
				}
				
				editVehicleBox.setSelected(false);
				setEditableVehicle(false);
			}
			
		});
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.gridy = 0;
		p.add(editVehicleBox, g);
		g.gridy++;
		p.add(deletBtn, g);
		g.insets = new Insets(5, 0, 0, 0);
		g.gridy++;
		p.add(editVehicleBtn, g);
		gbc1.gridx = 3;
		gbc1.gridy = 0;
		gbc1.gridheight = 4;
		editVehicle.add(p, gbc1);

		// --------------------------------------------------------------------
		JPanel addVehicle = new JPanel(new GridBagLayout()) {
			public Dimension getPreferredSize() {
				return new Dimension(0, 0);
			}
		};
		addVehicle.setBorder(BorderFactory.createTitledBorder("Add Vehicle"));
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc2.insets = new Insets(0, 5, 0, 0);
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		addVehicle.add(new JLabel("Registration No * : "), gbc2);

		regNoField2 = new JTextField(10);
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		addVehicle.add(regNoField2, gbc2);

		gbc2.gridx = 0;
		gbc2.gridy = 2;
		addVehicle.add(new JLabel("Engine Serial * : "), gbc2);

		engSerField2 = new JTextField(10);
		gbc2.gridx = 0;
		gbc2.gridy = 3;
		addVehicle.add(engSerField2, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 0;
		addVehicle.add(new JLabel("Make * : "), gbc2);

		makeField2 = new JTextField(10);
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		addVehicle.add(makeField2, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 2;
		addVehicle.add(new JLabel("Chasis No * : "), gbc2);

		chasisNoField2 = new JTextField(10);
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		addVehicle.add(chasisNoField2, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 0;
		addVehicle.add(new JLabel("Model * : "), gbc2);

		modelField2 = new JTextField(10);
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		addVehicle.add(modelField2, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 2;
		addVehicle.add(new JLabel("Colour * : "), gbc2);

		colourField2 = new JTextField(10);
		gbc2.gridx = 2;
		gbc2.gridy = 3;
		addVehicle.add(colourField2, gbc2);

		addVehicleBtn = new JButton("Add Vehicle");
		addVehicleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String regNo = regNoField2.getText();
				String engSer = engSerField2.getText();
				String make = makeField2.getText();
				String chNo = chasisNoField2.getText();
				String model = modelField2.getText();
				String colour = colourField2.getText();

				// check to see if all fields are filled
				Boolean b1 = !regNo.equals("") && !engSer.equals("") && !make.equals("") && !chNo.equals("")
						&& !model.equals("") && !colour.equals("");

				// otherwise a warning message is shown
				if (!b1) {
					JOptionPane.showMessageDialog(addVehicle, "Please make sure that all * fields are filled.",
							"Empty fields", JOptionPane.WARNING_MESSAGE);
					return;
				}

				VehicleEvent event = new VehicleEvent(addVehicle, regNo, engSer, make, chNo, model, colour);

				if (listener != null) {
					listener.addVehicleToDatabase(event, Customer_ID);
				}

				// clear text fields
				regNoField2.setText("");
				engSerField2.setText("");
				makeField2.setText("");
				chasisNoField2.setText("");
				modelField2.setText("");
				colourField2.setText("");
			}
		});
		gbc2.anchor = GridBagConstraints.PAGE_END;
		gbc2.weightx = 0;
		gbc2.gridx = 3;
		gbc2.gridy = 2;
		gbc2.gridheight = 2;
		addVehicle.add(addVehicleBtn, gbc2);

		// ------------------------------------------------
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		vehicleDetails.add(editVehicle, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		vehicleDetails.add(addVehicle, gbc);

	}

	private void layoutComp() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 1;
		gbc.weighty = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(customersDatabase, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(vehiclesDatabase, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(customerDetails, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(vehicleDetails, gbc);
	}

	public void setCustomerData(List<Customer> db) {
		customerTablePanel.setCustomerData(db);
	}

	public void setVehicleData(List<Vehicle> db) {
		vehiclesTablePanel.setVehiclesData(db);
	}

	private void setEditableCustomer(boolean isTicked) {
		nameField.setEditable(isTicked);
		addressField.setEditable(isTicked);
		postCodeField.setEditable(isTicked);
		telephoneField.setEditable(isTicked);
		emailField.setEditable(isTicked);
		faxField.setEditable(isTicked);
		Enumeration<AbstractButton> e = discountGroup.getElements();
		while (e.hasMoreElements()) {
			e.nextElement().setEnabled(isTicked);
		}
		creditB.setEnabled(isTicked);
		if (variableRadio.isSelected()) {
			variableField.setEditable(isTicked);
		}
		updateBtn.setEnabled(isTicked);
		delBtn.setEnabled(isTicked);
		regNoField2.setEnabled(isTicked);
		engSerField2.setEnabled(isTicked);
		makeField2.setEnabled(isTicked);
		chasisNoField2.setEnabled(isTicked);
		modelField2.setEnabled(isTicked);
		colourField2.setEnabled(isTicked);
		addVehicleBtn.setEnabled(isTicked);
	}

	private void setEditableVehicle(boolean isTicked) {
		editVehicleBtn.setEnabled(isTicked);
		deletBtn.setEnabled(isTicked);
		regNoField.setEditable(isTicked);
		engSerField.setEditable(isTicked);
		makeField.setEditable(isTicked);
		chasisNoField.setEditable(isTicked);
		modelField.setEditable(isTicked);
		colourField.setEditable(isTicked);
	}

	public CustomerTablePanel getCustomerTablePanel() {
		return customerTablePanel;
	}
}
