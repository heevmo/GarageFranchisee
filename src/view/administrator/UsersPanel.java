package view.administrator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.User;

/**
 *
 * @author Alex
 */
public class UsersPanel extends JPanel {

    private JButton backUpBtn;
    private JButton restoreBtn;

    private UsersTablePanel usersTablePanel;


    public UsersPanel() {
        usersTablePanel = new UsersTablePanel();

        backUpBtn = new JButton("Archive");
        restoreBtn = new JButton("Restore");
        
        setBorder(BorderFactory.createTitledBorder("Users Database"));
        layoutUsersPanel();

    }

    public JButton getBackUpBtn() {
        return backUpBtn;
    }

    public JButton getRestoreBtn() {
        return restoreBtn;
    }
        
    /**
     *
     */
    public UsersTablePanel getUsersTablePanel() {
        return this.usersTablePanel;
    }

    /**
     *
     */
    public void setData(List<User> db) {
        usersTablePanel.setData(db);
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
     * Laying out components in Users Panel
     */
    private void layoutUsersPanel() {

        this.setLayout(new GridBagLayout());
        // filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter users database"));
        // filter panel - first column
        JPanel firstCol = new JPanel();
        firstCol.setLayout(new BoxLayout(firstCol, BoxLayout.Y_AXIS));
        firstCol.add(new JLabel("Username: "));
        firstCol.add(Box.createRigidArea(new Dimension(0, 12)));
        firstCol.add(new JLabel("Last Name: "));
        firstCol.add(Box.createRigidArea(new Dimension(0, 12)));
        firstCol.add(new JLabel("First Name: "));
        firstCol.add(Box.createRigidArea(new Dimension(0, 8)));
        // filter panel - second column
        JPanel secondCol = new JPanel();
        secondCol.setLayout(new BoxLayout(secondCol, BoxLayout.Y_AXIS));
        for (int i = 0; i < 3; i++) {
            secondCol.add(new JTextField(10));
            secondCol.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        //filter panel - third column
        JPanel thirdCol = new JPanel();
        thirdCol.setBorder(BorderFactory.createTitledBorder("Role"));
        thirdCol.setLayout(new BoxLayout(thirdCol, BoxLayout.Y_AXIS));
        thirdCol.add(new JCheckBox("Administrator"));
        thirdCol.add(new JCheckBox("Franchisee"));
        thirdCol.add(new JCheckBox("Foreperson"));
        thirdCol.add(new JCheckBox("Mechanic"));
        thirdCol.add(new JCheckBox("Receptionist"));
        filterPanel.add(firstCol);
        filterPanel.add(secondCol);
        filterPanel.add(thirdCol);

        // manage buttons panel
        JPanel managePanel = new JPanel();
        managePanel.setBorder(BorderFactory.createTitledBorder("Manage users database"));
        managePanel.add(backUpBtn);
        managePanel.add(restoreBtn);

        GridBagConstraints gbc = new GridBagConstraints();

        // add table panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(usersTablePanel, gbc);

        // add filter panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.weighty = 0;
        gbc.gridy = 1;
        this.add(filterPanel, gbc);

        // add buttons panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        this.add(managePanel, gbc);
    }
}
