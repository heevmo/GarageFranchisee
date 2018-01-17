package view.franchisee.jobs;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import model.Customer;

/**
 *
 * @author Alex
 */
public class CustomerTablePanel extends JPanel {

    private JTable table;
    public JTable getTable() {
		return table;
	}

	private CustomerTableModel tableModel;


	private JScrollPane scrollPane;
    private JPanel filterPanel;
    private JLabel lastNameLabel;
    private JLabel postCodeLabel;
    private JTextField nameField;
    private JTextField postCodeField;
    
    // 
    private TableRowSorter<CustomerTableModel> sorter;

    public CustomerTablePanel() {
        tableModel = new CustomerTableModel();
        table = new JTable(tableModel) ;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
      
        this.setBorder(BorderFactory.createTitledBorder("Customers"));
        
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
        lastNameLabel = new JLabel("Name: ");
        nameField = new JTextField(10);
        nameField.getDocument().addDocumentListener(
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
        postCodeLabel = new JLabel("Post Code: ");
        postCodeField = new JTextField(8);
        postCodeField.getDocument().addDocumentListener(
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
        filterPanel.add(lastNameLabel);
        filterPanel.add(nameField);
        filterPanel.add(postCodeLabel);
        filterPanel.add(postCodeField);
        
        gbc.weighty = 0;
        gbc.gridy = 1; 
        this.add(filterPanel,gbc);
    }

    public void setCustomerData(List<Customer> db) {
        tableModel.setData(db);
    }
    
    private void newFilter() {
    	RowFilter<CustomerTableModel, Object> name = null;
    	RowFilter<CustomerTableModel, Object> postcode = null;
    	List<RowFilter<CustomerTableModel,Object>> filters = 
    			new ArrayList<RowFilter<CustomerTableModel,Object>>();
    	RowFilter<CustomerTableModel, Object> compoundRowFilter = null;
    	try {
    		name = RowFilter.regexFilter(nameField.getText(), 1);
    		postcode = RowFilter.regexFilter(postCodeField.getText(), 2);
    	    filters.add(name);
    	    filters.add(postcode);
    	    compoundRowFilter = RowFilter.andFilter(filters);
    	} catch(PatternSyntaxException e) {
    		return;
    	}
    	sorter.setRowFilter(compoundRowFilter);
    }
    
    public void setSorter() {
    	sorter = new TableRowSorter<CustomerTableModel>(tableModel);
    	table.setRowSorter(sorter);
    }
    
    public CustomerTableModel getTableModel() {
		return tableModel;
	}

}
