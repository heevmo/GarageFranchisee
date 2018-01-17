package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import view.franchisee.FranchiseeFrame;

/**
 *
 * @author Alex
 */
public class MenuBar extends JMenuBar {
	private FranchiseeFrame frame;
	
    public MenuBar(FranchiseeFrame frame) {
    	this.frame = frame;
        // File
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem logoutItem = new JMenuItem("Logout");

        fileMenu.setMnemonic(KeyEvent.VK_F);
        logoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        logoutItem.setMnemonic(KeyEvent.VK_O);
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.setAccelerator(null);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        
        exitItem.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(view.MenuBar.this.getParent(),
                        "Do you want to exit the application ? ", "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
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

        fileMenu.add(exitItem);
        fileMenu.add(logoutItem);
        this.add(fileMenu);
        
    }
}
