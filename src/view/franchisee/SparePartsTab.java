
package view.franchisee;

import view.franchisee.spareparts.SellOrderPanel;
import view.franchisee.spareparts.EditSparePartsPanel;
import view.franchisee.spareparts.RecordNewSparePartPanel;
import view.franchisee.spareparts.PendingBuyOrderPanel;
import view.franchisee.spareparts.BuyOrderPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Alex
 */
public class SparePartsTab extends JTabbedPane {
    
    private BuyOrderPanel buyOrderPanel;
    private PendingBuyOrderPanel pendingBuyOrderPanel;
    private RecordNewSparePartPanel recordNewSparePart;
    private EditSparePartsPanel editSpareParts;
    private SellOrderPanel sellOrderPanel;
    
    public SparePartsTab() {
        buyOrderPanel = new BuyOrderPanel();
        pendingBuyOrderPanel = new PendingBuyOrderPanel();
        recordNewSparePart = new RecordNewSparePartPanel();
        editSpareParts = new EditSparePartsPanel();
        sellOrderPanel = new SellOrderPanel();
        
        this.addTab("Buy Order", buyOrderPanel);
        this.addTab("Pending Buy Orders", pendingBuyOrderPanel);
        this.addTab("Record New Spare Part", recordNewSparePart);
        this.addTab("Edit Spare Parts", editSpareParts);
        this.addTab("Sell Order", sellOrderPanel);
    }
}
