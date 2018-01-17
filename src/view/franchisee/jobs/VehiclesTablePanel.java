
package view.franchisee.jobs;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import model.Vehicle;

/**
 *
 * @author Alex
 */
public class VehiclesTablePanel extends JPanel{
    
    private JTable table;
	private VehiclesTableModel tableModel;
    private JScrollPane scrollPane;
    private JPanel filterPanel;
    private JLabel registrationLabel;
    private JTextField registrationField;
    private TableRowSorter<VehiclesTableModel> sorter;

    public VehiclesTablePanel() {
        tableModel = new VehiclesTableModel();
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        
        this.setBorder(BorderFactory.createTitledBorder("Vehicles"));
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0; 
        this.add(scrollPane,gbc);
        
        // filter panel
        filterPanel = new JPanel();
        JLabel filter = new JLabel("FILTER BY      ");
        JButton clearBtn = new JButton("Clear Selection");
        clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearSelectedCustomer();
				System.out.println("piu");
			}
        	
        });
        registrationLabel = new JLabel("Registration No: ");
        registrationField = new JTextField(10);
        registrationField.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(filter);
        filterPanel.add(registrationLabel);
        filterPanel.add(registrationField);
        filterPanel.add(new JLabel("                     "));
        filterPanel.add(clearBtn);
        
        gbc.weighty = 0;
        gbc.gridy = 1; 
        this.add(filterPanel,gbc);
    }

    public VehiclesTableModel getTableModel() {
		return tableModel;
	}

	public void setVehiclesData(List<Vehicle> db) {
        tableModel.setData(db);
    }
    
    private void newFilter() {
    	RowFilter<VehiclesTableModel, Object> registration = null;
    	try {
    		registration = RowFilter.regexFilter(registrationField.getText(), 1);
    	} catch(PatternSyntaxException e) {
    		return;
    	}
    	sorter.setRowFilter(registration);
    }
    
    public void setSorter() {
    	sorter = new TableRowSorter<VehiclesTableModel>(tableModel);
    	table.setRowSorter(sorter);
    }
    
    public void clearSelectedCustomer() {
    	
    }
    
    public JTable getTable() {
		return table;
	}

}
