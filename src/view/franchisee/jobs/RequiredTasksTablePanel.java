package view.franchisee.jobs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.RequiredTask;

/**
 *
 * @author Alex
 */
public class RequiredTasksTablePanel extends JPanel {

    private JTable table;
    private RequiredTasksTableModel tableModel;
    private JScrollPane scrollPane;
    private JPanel filterPanel;
    private JLabel registrationLabel;
    private JTextField registrationField;

    public RequiredTasksTablePanel() {
        tableModel = new RequiredTasksTableModel();
        table = new JTable(tableModel);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setMinWidth(40);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMinWidth(340);
        table.getColumnModel().getColumn(1).setMaxWidth(340);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(100);
        scrollPane = new JScrollPane(table);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0;
        this.add(scrollPane, gbc);
    }  
    
    public void setRequiredTaskData(List<RequiredTask> db) {
        tableModel.setData(db);
    }
    
}
