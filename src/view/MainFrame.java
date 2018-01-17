package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Alex
 */
public class MainFrame extends JFrame {
    
    private LoginPanel loginPanel;
    private JButton loginButton;
    private BottomToolBar bottomToolBar;
    private JMenuBar menuBar;

    public MainFrame() {
        super("Login");
        menuBar =  new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        
        fileMenu.setMnemonic(KeyEvent.VK_F);

        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.setAccelerator(null);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        
        exitItem.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you want to exit the application ? ", "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
            
        });
        
        loginPanel = new LoginPanel();
        loginButton = loginPanel.getLoginButton();
        bottomToolBar = new BottomToolBar();
        
        this.getRootPane().setDefaultButton(loginButton);
        this.setLayout(new BorderLayout());
        this.add(loginPanel, BorderLayout.CENTER);
        this.add(bottomToolBar, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        
        this.setJMenuBar(menuBar);
        
        // Position the window in the center of the screen 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        
        this.setResizable(false);
        
    }
}
