/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.franchisee.customersvehicles;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import model.Vehicle;
import view.franchisee.jobs.VehiclesTableModel;

/**
 *
 * @author Alex
 */
public class VehicleDetailsTablePanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private VehiclesTableModel tableModel;

    public VehicleDetailsTablePanel() {
        tableModel = new VehiclesTableModel();
        table = new JTable(tableModel) {};
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);

        this.setBorder(BorderFactory.createTitledBorder("Vehicles"));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(scrollPane, gbc);
    }

    public void setNewVehicleData(List<Vehicle> db) {
        tableModel.setData(db);
    }
    
    public void refresh() {
    	tableModel.fireTableDataChanged();
    }
    
    public int getSelectedRow() {
    	return table.getSelectedRow();
    }
}
