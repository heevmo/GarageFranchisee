package view;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.administrator.AdministratorFrame;
import view.franchisee.FranchiseeFrame;

/**
 *
 * @author Alex
 */
public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                 new MainFrame().setVisible(true);
                 new AdministratorFrame().setVisible(true);
                 new FranchiseeFrame().setVisible(true);
            }

        });
    }
}
