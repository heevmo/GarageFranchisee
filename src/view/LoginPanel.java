package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import view.administrator.AdministratorFrame;
import view.franchisee.FranchiseeFrame;

/**
 *
 * @author Alex
 */
public class LoginPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {

        titleLabel = new JLabel("GARage IT System");
        userLabel = new JLabel("User ID: ");
        userField = new JTextField(10);
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");

        titleLabel.setFont(new Font("Courier", Font.ROMAN_BASELINE, 24));
        
        Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
        Border insideBorder = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

        this.layoutComponents();

        // Get the user and password when login button is clicked
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                char[] password = passwordField.getPassword();
                login(user, password);
            }
        });

        this.setPreferredSize(new Dimension(300, 200));
        
    }

    /**
     * Laying out the components in the panel.
     */
    private void layoutComponents() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // First row - title
        gc.fill = GridBagConstraints.NONE;
        gc.ipady = 40;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridwidth = 2;
        gc.gridy = 0;
        gc.gridx = 0;
        this.add(titleLabel, gc);

        gc.ipady = 0;

        // Next row - user
        gc.gridy++;
        gc.gridx = 0;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(userLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        this.add(userField, gc);

        // Next row - password
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(passwordLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(passwordField, gc);

        // Next row - login button
        gc.gridy++;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.weighty = 2;
        this.add(loginButton, gc);
    }
    
    public JButton getLoginButton() {
        return this.loginButton;
    }

    /**
     *
     * @param user
     * @param pass
     */
    private void login(String user, char[] password) {

        String pass = new String(password);

        switch (user) {
            case "":
                JOptionPane.showMessageDialog(LoginPanel.this, 
                        "Please enter the user ID and password.", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Please enter the user ID and password.");
                break;
            case "admin":
                if (pass.equals("admin")) {
                    JFrame f = (JFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                    f.dispose();
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new AdministratorFrame().setVisible(true);
                        }
                    });
                    
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this,
                            "Entered password is incorrect.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "franchisee":
                if (pass.equals("franchisee")) {
                    JFrame f = (JFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                    f.dispose();
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new FranchiseeFrame().setVisible(true);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this,
                            "Entered password is incorrect.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                JOptionPane.showMessageDialog(LoginPanel.this,
                        "User does not exist.", "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }
}
