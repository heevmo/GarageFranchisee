package view.administrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import view.MainFrame;
import view.utils.FileExtension;

/**
 *
 * @author Alex
 */
public class MenuBar extends JMenuBar {

    private JFileChooser fileChooser;
    private AdministratorFrame frame;

    public MenuBar(AdministratorFrame frame) {

        this.frame = frame;

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new UsersFileFilter());

        this.createMenuBar();
    }

    /**
     *
     */
    private void createMenuBar() {

        // File
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        logoutItem.setMnemonic(KeyEvent.VK_O);
        exitItem.setMnemonic(KeyEvent.VK_E);

        logoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(frame.getParent(),
                        "Do you want to exit the application ? ", "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }/*
                Object[] o = {"OK"};
                int action = JOptionPane.showOptionDialog(frame,
                        "Spare part: Exhaust pipe \nThreshold level: 10 \nCurrent stock level: 3  ", "Stock level below threshold",
                        JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, o, null);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
                */
            }
        });

        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(frame,
                        "Do you want to log out ? ", "Log out",
                        JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                    new MainFrame().setVisible(true);
                }
            }

        });

        this.add(fileMenu);

        // Edit
        JMenu databaseMenu = new JMenu("Database");
        JMenuItem backUpItem = new JMenuItem("Backup ...");
        JMenuItem restoreItem = new JMenuItem("Restore ...");

        databaseMenu.add(backUpItem);
        databaseMenu.add(restoreItem);

        databaseMenu.setMnemonic(KeyEvent.VK_D);
        backUpItem.setMnemonic(KeyEvent.VK_B);
        restoreItem.setMnemonic(KeyEvent.VK_R);

        backUpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        restoreItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        this.add(databaseMenu);

        //
        backUpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(frame, "BackUp") == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        if ((file != null)) {
                            if (file.exists()) {
                                if (FileExtension.getFileExtension(file.getName()) != null
                                        && FileExtension.getFileExtension(file.getName()).equals("garits")) {
                                    int action = JOptionPane.showConfirmDialog(frame,
                                            "File \" " + file.getName() + " \" exists. Do you want to overwite it?", "File exists",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (action == JOptionPane.OK_OPTION)
                                        frame.getController().backUp(file);
                                } else {
                                    JOptionPane.showMessageDialog(frame,
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
                                    int action = JOptionPane.showConfirmDialog(frame,
                                            "File \" " + file.getName() + " \" exists. Do you want to overwite it?", "File exists",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (action == JOptionPane.OK_OPTION)
                                        frame.getController().backUp(file);
                                    return;
                                } else {
                                    frame.getController().backUp(file);
                                }
                                
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Could not backup data to file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //
        restoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showDialog(frame, "Restore") == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        if (file != null) {
                            if (file.exists()) {
                                if (FileExtension.getFileExtension(file.getName()) != null
                                        && FileExtension.getFileExtension(file.getName()).equals("garits")) {
                                    frame.getController().restore(file);
                                    frame.getAdminPanel().getUsersPanel().getUsersTablePanel().refresh();
                                } else {
                                    JOptionPane.showMessageDialog(frame,
                                            "File \"" + file.getName() + "\" has unknown format. Cannot restore from it.",
                                            "Unknown format", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Could not restore data form file.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });

        frame.setJMenuBar(this);
    }
}
