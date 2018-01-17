package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Alex
 */
public class TopToolBar extends JPanel {
    
    private Calendar now;
    private JTextField timeField;
    private JTextField dateField;
    
    public TopToolBar() {
        Border outsideBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        Border insideBorder = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        
        now = Calendar.getInstance();
        SimpleDateFormat fdate = new SimpleDateFormat("EEE  dd/MM/yyyy");
        SimpleDateFormat ftime = new SimpleDateFormat("HH:mm:ss");
        
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(new JLabel("Date:"));
        dateField = new JTextField(10);
        dateField.setEditable(false);
        dateField.setText(fdate.format(now.getTime()));
        dateField.setBackground(Color.WHITE);
        this.add(dateField);
        this.add(new JLabel("Time:"));
        timeField = new JTextField(6);
        timeField.setText(ftime.format(now.getTime()));
        timeField.setEditable(false);
        timeField.setBackground(Color.WHITE);
        this.add(timeField);
        
        new Timer(1000, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                now = Calendar.getInstance();
                dateField.setText(fdate.format(now.getTime()));
                timeField.setText(ftime.format(now.getTime()));
            } 
        }).start();
    }

}
