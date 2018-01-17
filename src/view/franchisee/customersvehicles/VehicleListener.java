
package view.franchisee.customersvehicles;

/**
 *
 * @author Alex
 */
public interface VehicleListener {
    public void addToTemporaryList(VehicleEvent e);
    public void deleteFromTemporaryList(int index);
}
