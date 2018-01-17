package view.franchisee.jobs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import controller.ControllerVehicle;
import model.Customer;
import model.Vehicle;
import view.franchisee.customersvehicles.CustomerVehicleListener;

/**
 *
 * @author Alex
 */
public class SearchPanel extends JPanel {

	
	private CustomerTablePanel customerTablePanel;
	private VehiclesTablePanel vehiclesTablePanel;
	
	private CustomerVehicleListener customerVehilceListener;
	private NewJobListener newJobListener;
    private int Customer_ID;
    private int Vehicle_ID;

	public SearchPanel() {
		customerTablePanel = new CustomerTablePanel();
		vehiclesTablePanel = new VehiclesTablePanel(){
        	
        	public void clearSelectedCustomer() {
        		if(customerVehilceListener != null) {
        			customerVehilceListener.clear();
        		}
        	}
        };;

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 0;
		this.add(customerTablePanel, gbc);
		gbc.gridy = 1;
		this.add(vehiclesTablePanel, gbc);
		
		
		// filter vehicles by customer
		JTable cTable = customerTablePanel.getTable();
		cTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = cTable.rowAtPoint(e.getPoint());
				cTable.getSelectionModel().setSelectionInterval(row, row);
				
				if (row > -1) {
					if (customerVehilceListener != null) {
						Customer_ID = (int) cTable.getValueAt(row, 0);
						customerVehilceListener.loadByCustomer(Customer_ID);
						System.out.println("Customer_ID:" + Customer_ID);
						System.out.println("Vehicle_ID:" + Vehicle_ID);
					}
				}

			}
		});
		
		// fill fields
		JTable vTable = vehiclesTablePanel.getTable();
		vTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = vTable.rowAtPoint(e.getPoint());
				vTable.getSelectionModel().setSelectionInterval(row, row);
				String model = (String) vTable.getValueAt(row, 3);;
				String make = (String) vTable.getValueAt(row, 2);
				String registraion = (String) vTable.getValueAt(row, 1);;
				int id = -1;
				if (row > -1) {
					if (newJobListener != null) {
						Vehicle_ID = (int) vTable.getValueAt(row, 0);

						id = newJobListener.getCustomerByVehicle(Vehicle_ID);
						Customer_ID = id;
						System.out.println("Customer_ID:" + Customer_ID);
						System.out.println("Vehicle_ID:" + Vehicle_ID);
						
						int i = customerTablePanel.getTable().getRowCount();
						
						while(i-- > 0) {
							if((int)customerTablePanel.getTable().getValueAt(i, 0) == Customer_ID)
								id = i;
						}
						
						String telephone = "";
						String customer = "";
						if (id > -1 && id < customerTablePanel.getTable().getRowCount()) {
							// get contents from table model
							JTable cTable = customerTablePanel.getTable();
							customer = (String) cTable.getValueAt(id, 1);
							telephone = (String) cTable.getValueAt(id, 5);
						}
						
						JobFieldsEvent jobFields = new JobFieldsEvent(customer, telephone, model, make, registraion);
						
						newJobListener.setFields(jobFields);
					}

				}

			}
		});
	}

	public VehiclesTablePanel getVehiclesTablePanel() {
		return vehiclesTablePanel;
	}

	public void setSorter() {
		customerTablePanel.setSorter();
		vehiclesTablePanel.setSorter();
	}

	public void setCustomerData(List<Customer> db) {
		customerTablePanel.setCustomerData(db);
	}

	public void setVehicleData(List<Vehicle> db) {
		vehiclesTablePanel.setVehiclesData(db);
	}
	
	public void setListener(CustomerVehicleListener l) {
		customerVehilceListener = l;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(0, 0);
	}
	
	public void setNewJobListener(NewJobListener l) {
		newJobListener = l;
	}
}
