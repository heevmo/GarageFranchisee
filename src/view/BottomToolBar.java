package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class BottomToolBar extends JPanel {

    private BufferedImage logo;

    public BottomToolBar() {
        
        Dimension dim = this.getPreferredSize();
        dim.height = 40;
        this.setPreferredSize(dim);

        try {
            logo = ImageIO.read(new File("photos/logo.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logo, 7, 3, 98, 34, this);
    }
}
