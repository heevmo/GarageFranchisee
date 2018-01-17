package view.administrator;

import view.TopToolBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.ControllerUser;
import view.BottomToolBar;
import view.utils.FileExtension;

/**
 *
 * @author Alex
 */
public class AdministratorFrame extends JFrame {

    private MenuBar menuBar;
    private TopToolBar topToolBar;
    private BottomToolBar bottomToolBar;
    private AdministratorPanel adminPanel;
    private ControllerUser controller;

    private JFileChooser fileChooser;

    public AdministratorFrame() {
        super("Administrator");

        menuBar = new MenuBar(this);
        topToolBar = new TopToolBar();
        adminPanel = new AdministratorPanel();
        bottomToolBar = new BottomToolBar();

        // create a users database through ControllerUser class
        controller = new ControllerUser();

        //
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new UsersFileFilter());

        // add users to the database through controller
        adminPanel.setData(controller.getUsersDatabase());

        // 
        adminPanel.getNewUserPanel().setNewUserPanelListener(new NewUserPanelListener() {
            @Override
            public void panelEventOccured(UserEvent e) {
                controller.addUser(e);
                adminPanel.getUsersPanel().getUsersTablePanel().refresh();
            }
        });

        adminPanel.getUsersPanel().getUsersTablePanel().setEditUserPanelListener(new EditUserPanelListener() {
            public void panelEventOccure(UserEvent e) {
                adminPanel.getEditUserPanel().setFields(e);
            }
        });
        
        adminPanel.getUsersPanel().getBackUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(AdministratorFrame.this, "Archive") == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        if ((file != null)) {
                            if (file.exists()) {
                                if (FileExtension.getFileExtension(file.getName()) != null
                                        && FileExtension.getFileExtension(file.getName()).equals("garits")) {
                                    int action = JOptionPane.showConfirmDialog(AdministratorFrame.this,
                                            "File \" " + file.getName() + " \" exists. Do you want to overwite it?", "File exists",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (action == JOptionPane.OK_OPTION)
                                        controller.backUp(file);
                                } else {
                                    JOptionPane.showMessageDialog(AdministratorFrame.this,
                                            "File \"" + file.getName() + "\" has unknown format. Cannot overwrite it.",
                                            "Unknown format", JOptionPane.OK_OPTION);
                                }
                            } else {
                                if (FileExtension.getFileExtension(file.toString()) != null) {
                                    String f = FileExtension.trimExtension(file.toString());
                                    file = new File(f + ".garits");
                                } else {
                                    file = new File(file.toString() + ".garits");
                                }
                                if (file.exists()) {
                                    int action = JOptionPane.showConfirmDialog(AdministratorFrame.this,
                                            "File \" " + file.getName() + " \" exists. Do you want to overwite it?", "File exists",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (action == JOptionPane.OK_OPTION)
                                        controller.backUp(file);
                                    return;
                                } else {
                                    controller.backUp(file);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(AdministratorFrame.this, "Could not backup data to file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });

        // 
        adminPanel.getUsersPanel().getRestoreBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(AdministratorFrame.this, "Restore") == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        if (file != null) {
                            if (file.exists()) {
                                if (FileExtension.getFileExtension(file.getName()) != null
                                        && FileExtension.getFileExtension(file.getName()).equals("garits")) {
                                    controller.restore(file);
                                    adminPanel.getUsersPanel().getUsersTablePanel().refresh();
                                } else {
                                    JOptionPane.showMessageDialog(AdministratorFrame.this,
                                            "File \"" + file.getName() + "\" has unknown format. Cannot restore from it.",
                                            "Unknown format", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(AdministratorFrame.this, "Could not restore data form file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        //
        adminPanel.getUsersPanel().getUsersTablePanel().getTable().setTableListener(new TableListener() { 
            @Override
            public void rowDeleted(int row) {
                
                controller.removeUser(row);
                adminPanel.getUsersPanel().getUsersTablePanel().refreshDeletedRows(row);
            }
            
        });

        this.getContentPane()
                .setLayout(new BorderLayout());

        this.getContentPane()
                .add(topToolBar, BorderLayout.NORTH);

        this.getContentPane()
                .add(adminPanel, BorderLayout.CENTER);

        this.getContentPane()
                .add(bottomToolBar, BorderLayout.SOUTH);

        this.pack();

        // Position the window in the center of the screen 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(dim.width
                / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.setResizable(false);
    }

    public ControllerUser getController() {
        return this.controller;
    }

    public AdministratorPanel getAdminPanel() {
        return this.adminPanel;
    }
}
