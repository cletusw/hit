package gui.modellistener;

import gui.inventory.IInventoryView;

import java.util.Observable;
import java.util.Observer;

import model.Action;
import model.Action.ActionType;
import model.Item;
import model.ItemManager;

/**
 * Controller object that acts as a liaison between the model's ItemManager and the GUI view.
 * 
 * @author Matt Hess
 * @version 1.0 CS 340 Group 4 Phase 2
 * 
 */
public class ItemListener extends InventoryListener implements Observer {

	public ItemListener(IInventoryView view, ItemManager itemManager) {
		super(view);
		itemManager.addObserver(this);
	}

	/**
	 * Method intended to notify the view when ItemManager sends a "change" notice.
	 * 
	 * @param o
	 *            The object being observed (which has access to the model)
	 * @param arg
	 *            Object passed to the view so the view can determine which changes to make
	 * 
	 * @pre o != null
	 * @pre arg != null
	 * @pre o instanceof ItemManager
	 * @post true
	 * 
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		Action action = (Action) arg1;
		ActionType type = action.getAction();
		Item item = (Item) action.getObject();

		boolean restoreSelected = true;
		switch (action.getAction()) {
		case DELETE:
			restoreSelected = false;

		case CREATE:

		case EDIT:

			updateProducts(true);

			updateItems(restoreSelected);

			break;
		}
	}

}
