package gui.modellisteners;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;

import java.util.Observable;
import java.util.Observer;

import model.Action;
import model.Product;
import model.ProductContainer;
import model.ProductManager;

/**
 * Controller object that acts as a liaison between the model's ProductManager and the GUI
 * view.
 * 
 * @author Matt Hess
 * @version 1.0 CS 340 Group 4 Phase 2
 * 
 */
public class ProductListener extends InventoryListener implements Observer {

	public ProductListener(IInventoryView view, ProductManager productManager) {
		super(view);
		productManager.addObserver(this);
	}

	/**
	 * Method intended to notify the view when ProductManager sends a "change" notice.
	 * 
	 * @param o
	 *            The object being observed (which has access to the model)
	 * @param arg
	 *            Object passed to the view so the view can determine which changes to make
	 * 
	 * @pre o != null
	 * @pre arg != null
	 * @pre o instanceof ProductManager
	 * @post true
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		Action action = (Action) arg;
		Product product = (Product) action.getObject();

		ProductContainerData productContainerData = view.getSelectedProductContainer();
		ProductContainer container = (ProductContainer) productContainerData.getTag();
		boolean restoreSelected = true;

		// fall-through cases on purpose
		switch (action.getAction()) {
		case DELETE:
			restoreSelected = false;
		case CREATE:
		case EDIT:

			updateProducts(restoreSelected);
			break;
		}
		updateItems(true);
	}
}
