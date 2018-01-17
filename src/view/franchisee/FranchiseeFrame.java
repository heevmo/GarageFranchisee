package view.franchisee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ControllerCustomer;
import controller.ControllerTask;
import controller.ControllerVehicle;
import view.BottomToolBar;
import view.MenuBar;
import view.TopToolBar;
import view.franchisee.customersvehicles.CustomerEvent;
import view.franchisee.customersvehicles.CustomerUpdateEvent;
import view.franchisee.customersvehicles.CustomerVehicleListener;
import view.franchisee.customersvehicles.NewCustomerVehicleListener;
import view.franchisee.customersvehicles.VehicleEvent;
import view.franchisee.jobs.JobFieldsEvent;
import view.franchisee.jobs.NewJobListener;

/**
 *
 * @author Alex
 */
public class FranchiseeFrame extends JFrame {

	private TopToolBar topToolBar;
	private BottomToolBar bottomToolBar;
	private FranchiseePanel franchiseePanel;
	private MenuBar menuBar;
	private ControllerCustomer controllerCustomer;
	private ControllerVehicle controllerVehicle;
	private ControllerTask controllerTask;

	public FranchiseeFrame() {
		super("Franchisee");
		//
		controllerCustomer = new ControllerCustomer();
		controllerVehicle = new ControllerVehicle();
		controllerTask = new ControllerTask();

		//
		topToolBar = new TopToolBar();
		bottomToolBar = new BottomToolBar();
		franchiseePanel = new FranchiseePanel();
		menuBar = new MenuBar(this);

		//
		franchiseePanel.setCustomerData(controllerCustomer.getJobsCustomersDatabase());
		franchiseePanel.setVehicleData(controllerVehicle.getDatabaseVehicles());
		franchiseePanel.setNewVehicleData(controllerVehicle.getTemporaryVehicles());
		franchiseePanel.setRequiredTaskData(controllerTask.getRequiredTasksDatabase());
		franchiseePanel.setCustomerVehicleDataEdit(controllerCustomer.getEditCustomersDatabase(),
				controllerVehicle.getEditDatabaseVehicles());

		// load vehicles from database into lists
		controllerVehicle.load(0);
		controllerCustomer.load();

		//
		franchiseePanel.setSorter();

		//
		franchiseePanel.getCustomersVehiclesTab().getAddNewCustomerPanel()
				.setCustomerVehicleListener(new NewCustomerVehicleListener() {

					@Override
					public void addToTemporaryList(VehicleEvent e) {
						int i = controllerVehicle.addVehicleToTemporaryList(e);
						JPanel panel = franchiseePanel.getCustomersVehiclesTab().getAddNewCustomerPanel()
								.getVehiclesPanel();
						if (i == 1) {
							JOptionPane.showMessageDialog(panel,
									"Vehicle with registration number \" " + e.getRegNo() + " \" already added.",
									"Vehicle exists", JOptionPane.ERROR_MESSAGE);
						} else if (i == 2) {
							JOptionPane.showMessageDialog(panel,
									"Vehicle with engine number \" " + e.getEngSer() + " \" already added.",
									"Vehicle exists", JOptionPane.ERROR_MESSAGE);
						} else if (i == 3) {
							JOptionPane.showMessageDialog(panel,
									"Vehicle with chasis number \" " + e.getChNo() + " \" already added.",
									"Vehicle exists", JOptionPane.ERROR_MESSAGE);
						} else if (i == 4) {
							JOptionPane.showMessageDialog(panel,
									"Vehicle with registration number \" " + e.getRegNo()
											+ " \" exists in the database registered to another customer.",
									"Vehicle exists", JOptionPane.ERROR_MESSAGE);
						} else if (i == 5) {
							JOptionPane.showMessageDialog(panel,
									"Vehicle with engine number \" " + e.getEngSer()
											+ " \" exists in the database registered to another customer.",
									"Vehicle exists", JOptionPane.ERROR_MESSAGE);
						} else if (i == 6) {
							JOptionPane.showMessageDialog(panel,
									"Vehicle with chasis number \" " + e.getChNo()
											+ " \" exists in the database registered to another customer.",
									"Vehicle exists", JOptionPane.ERROR_MESSAGE);
						}

						franchiseePanel.getCustomersVehiclesTab().getAddNewCustomerPanel().getVehiclesPanel().refresh();
						

					}

					@Override
					public void deleteFromTemporaryList(int index) {
						controllerVehicle.deleteFromTemporaryList(index);
					}

					@Override
					public void addCustomerToDatabase(CustomerEvent e) {
						int customerId = controllerCustomer.addCustomerToDatabase(e);
						if (customerId == -1) {
							JOptionPane.showMessageDialog(FranchiseeFrame.this,
									"Customer \" " + e.getName() + " \" exists in the database.", "Customer exists",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						// get customer id to use as foreign key
						controllerCustomer.load();
						// refresh table in edit customers after a new customer is added
						franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getCustomerTablePanel().getTableModel().fireTableDataChanged();
						int j = controllerVehicle.addVehiclesToDatabase(customerId);
						if (j == 0) {
							JOptionPane.showMessageDialog(FranchiseeFrame.this,
									"Customer \" " + e.getName()
											+ " \" added in the database with no vehicle associated.",
									"Customer with no vehicle associated", JOptionPane.WARNING_MESSAGE);
						}
						franchiseePanel.getCustomersVehiclesTab().getAddNewCustomerPanel().getVehiclesPanel().refresh();
						controllerVehicle.load(0);
					}

				});
		
		
		// -------------------------------------------------------------------
		// set listeners for new job panel tab
		// -------------------------------------------------------------------
		franchiseePanel.getJobsTab().getNewJobPanel().getSearchPanel().setListener(new CustomerVehicleListener() {
			public void loadByCustomer(int i) {
				controllerVehicle.loadByCustomer(i, 1);
				franchiseePanel.getJobsTab().getNewJobPanel().getSearchPanel().getVehiclesTablePanel().getTableModel()
						.fireTableDataChanged();
			}
			public void clear() {
				controllerVehicle.load(1);
				franchiseePanel.getJobsTab().getNewJobPanel().getSearchPanel().getVehiclesTablePanel().getTableModel()
				.fireTableDataChanged();
			}
			
			// -------------------------------------------------------------------
			// unimplemented methods
			// -------------------------------------------------------------------
			@Override
			public int getCustomerByVehicle(int i) {
				return -1;
			}
			@Override
			public void addVehicleToDatabase(VehicleEvent e, int Customer_ID) {
			}
			@Override
			public void deleteVehicle(int Vehicle_ID, int Customer_ID) {
			}
			@Override
			public void deleteCustomer(int Customer_ID) {
			}
			@Override
			public void updateCustomerDetails(CustomerUpdateEvent customerEvent) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void updateVehicleDetails(VehicleEvent customerEvent, int Customer_ID) {
				// TODO Auto-generated method stub
				
			}
			// -------------------------------------------------------------------
			// -------------------------------------------------------------------
		});
		
		franchiseePanel.getJobsTab().getNewJobPanel().getSearchPanel().setNewJobListener(new NewJobListener() {
        	
        	public void setFields(JobFieldsEvent jobFields) {
        		franchiseePanel.getJobsTab().getNewJobPanel().getJobsPanel().setFields(jobFields);
        		System.out.println("bla");
        	}

			@Override
			public int getCustomerByVehicle(int Vehicle_ID) {
				int Customer_ID = -1;
				Customer_ID = controllerVehicle.getCustomerByVehicle(Vehicle_ID);
				return Customer_ID;
			}
         });
      
		
		// -------------------------------------------------------------------
		// set listener for edit customer/vehicle panel tab
		// -------------------------------------------------------------------
		franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().setListener(new CustomerVehicleListener() {
			public void loadByCustomer(int i) {
				controllerVehicle.loadByCustomer(i, 2);
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getVehiclesTablePanel().getTableModel()
						.fireTableDataChanged();
			}
			
			// clear selection
			public void clear() {
				controllerVehicle.load(2);
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getVehiclesTablePanel().getTableModel()
				.fireTableDataChanged();
			}
			
			// get vehicle ID to fill the customer fields when a vehicle is selected
			public int getCustomerByVehicle(int Vehicle_ID) {
				int Customer_ID = -1;
				Customer_ID = controllerVehicle.getCustomerByVehicle(Vehicle_ID);
				return Customer_ID;
			}

			@Override
			public void addVehicleToDatabase(VehicleEvent e, int Customer_ID) {
				
				int i = controllerVehicle.addVehicleToTemporaryList(e);
				JPanel panel = franchiseePanel.getCustomersVehiclesTab().getAddNewCustomerPanel()
						.getVehiclesPanel();
				 if (i == 4) {
					JOptionPane.showMessageDialog(panel,
							"Vehicle with registration number \" " + e.getRegNo()
									+ " \" exists in the database registered to another customer.",
							"Vehicle exists", JOptionPane.ERROR_MESSAGE);
				} else if (i == 5) {
					JOptionPane.showMessageDialog(panel,
							"Vehicle with engine number \" " + e.getEngSer()
									+ " \" exists in the database registered to another customer.",
							"Vehicle exists", JOptionPane.ERROR_MESSAGE);
				} else if (i == 6) {
					JOptionPane.showMessageDialog(panel,
							"Vehicle with chasis number \" " + e.getChNo()
									+ " \" exists in the database registered to another customer.",
							"Vehicle exists", JOptionPane.ERROR_MESSAGE);
				}
				 
				controllerVehicle.addVehiclesToDatabase(Customer_ID);
				controllerVehicle.load(0);
				controllerVehicle.loadByCustomer(Customer_ID, 2);
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getVehiclesTablePanel().getTableModel()
				.fireTableDataChanged();
				
			}

			@Override
			public void deleteVehicle(int Vehicle_ID, int Customer_ID) {
				controllerVehicle.deleteVehicleFromDatabase(Vehicle_ID);
				controllerVehicle.load(0);
				controllerVehicle.loadByCustomer(Customer_ID, 2);
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getVehiclesTablePanel().getTableModel()
				.fireTableDataChanged();
			}

			@Override
			public void deleteCustomer(int Customer_ID) {
				controllerVehicle.deleteVehicleByCustomerFromDatabase(Customer_ID);
				controllerCustomer.deleteCustomerById(Customer_ID);
				controllerCustomer.load();
				controllerVehicle.load(0);
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getCustomerTablePanel().getTableModel().fireTableDataChanged();
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getVehiclesTablePanel().getTableModel().fireTableDataChanged();
			}

			@Override
			public void updateCustomerDetails(CustomerUpdateEvent customerEvent) {
				controllerCustomer.updateCustomerDetails(customerEvent);
				controllerCustomer.load();
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getCustomerTablePanel().getTableModel().fireTableDataChanged();
			}

			@Override
			public void updateVehicleDetails(VehicleEvent customerEvent, int Customer_ID) {
				controllerVehicle.updateVehicleDetails(customerEvent);
				controllerVehicle.load(0);
				controllerVehicle.loadByCustomer(Customer_ID, 2);
				franchiseePanel.getCustomersVehiclesTab().getEditCustomersVehiclesPanel().getVehiclesTablePanel().getTableModel().fireTableDataChanged();
			}

		});
		// -------------------------------------------------------------------
		// -------------------------------------------------------------------

		this.getContentPane().setLayout(new BorderLayout());

		this.getContentPane().add(topToolBar, BorderLayout.NORTH);

		this.getContentPane().add(franchiseePanel, BorderLayout.CENTER);

		this.getContentPane().add(bottomToolBar, BorderLayout.SOUTH);

		this.setJMenuBar(menuBar);

		this.pack();

		// Position the window in the center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);

	}
}
