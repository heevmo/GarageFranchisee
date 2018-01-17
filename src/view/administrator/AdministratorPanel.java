package view.administrator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.User;

/**
 *
 * @author Alex
 */
public class AdministratorPanel extends JPanel {

    private UsersPanel usersPanel;
    private EditUserPanel editUserPanel;
    private NewUserPanel newUserPanel;
    private TableListener listener;

    public AdministratorPanel() {

        // Panels - Users Panel
        usersPanel = new UsersPanel();

        // Panels - Edit User Panel
        editUserPanel = new EditUserPanel();

        // Panels - New User Panel
        newUserPanel = new NewUserPanel();

        // admin panel
        Border outsideBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        Border insideBorder = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

        this.layoutAdminPanel();

        this.setPreferredSize(new Dimension(1020, 500));
        this.setVisible(true);
    }

    /**
     * 
     */
    public NewUserPanel getNewUserPanel() {
        return this.newUserPanel;
    }
    
    /**
     * 
     */
    public UsersPanel getUsersPanel() {
        return this.usersPanel;
    }
    
        /**
     * 
     */
    public EditUserPanel getEditUserPanel() {
        return this.editUserPanel;
    }
    
    /**
     *
     * @param db
     */
    public void setData(List<User> db) {
        usersPanel.setData(db);
    }

    /**
     * Laying out components in AdministratorPanel Panel
     */
    private void layoutAdminPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        this.add(usersPanel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridheight = 1;
        this.add(editUserPanel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        this.add(newUserPanel, gbc);

    }
    
    /**
     * 
     */
    public void setTableListener(TableListener listener) {
        this.listener = listener;
    }

}
