package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import view.administrator.TableListener;

/**
 *
 * @author Alex
 */
public class Table extends JTable {

    private JPopupMenu popup;
    private TableListener listener;

    public Table(AbstractTableModel tableModel) {
        super(tableModel);

        // Popup menu
        popup = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        popup.add(deleteItem);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int row = Table.this.rowAtPoint(e.getPoint());

                Table.this.getSelectionModel().setSelectionInterval(row, row);

                if ((row != -1) && e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(Table.this, e.getX(), e.getY());
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Table.this.getSelectedRow();
                if ((row != -1) && listener != null) {
                    int action = JOptionPane.showConfirmDialog(Table.this,
                            "Do you want to delete the selected row?",
                            "Delete confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (action == JOptionPane.OK_OPTION) {
                        listener.rowDeleted(row);
                    }
                }
            }

        });

        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setFillsViewportHeight(true);

    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
    }

    /**
     *
     */
    public void setTableListener(TableListener listener) {
        this.listener = listener;
    }

}
