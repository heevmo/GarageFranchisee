package view.administrator;

import java.util.EventListener;

/**
 *
 * @author Alex
 */
public interface NewUserPanelListener extends EventListener {
    public void panelEventOccured(UserEvent e);
}
