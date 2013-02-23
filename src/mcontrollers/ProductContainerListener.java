package mcontrollers;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;

/**
 * Controller object that acts as a liaison between the model's StorageUnitManager and the GUI
 * view.
 * 
 * @author Matt Hess
 * @version 1.0 CS 340 Group 4 Phase 2
 * 
 */
public class ProductContainerListener implements Observer {

	private IInventoryView view;

	public ProductContainerListener(IInventoryView view, ProductContainerManager manager) {
		manager.addObserver(this);
		this.view = view;
	}

	/**
	 * Method intended to notify the view when StorageUnitManager sends a "change" notice.
	 * 
	 * @param o
	 *            The
	 * @param arg
	 *            Object passed to the view so the view can determine which changes to make
	 * 
	 * @pre o != null
	 * @pre arg != null
	 * @pre o instanceof StorageUnitManager
	 * @post true
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("In PCListener.update()");
		// ProductContainerManager manager = (ProductContainerManager) o;
		if (arg instanceof ProductGroup) {
			ProductGroup newGroup = (ProductGroup) arg;
			ProductContainer parentContainer = newGroup.getContainer();

			ProductContainerData parentData = new ProductContainerData();
			parentData.setName(parentContainer.getName());
			parentData.setTag(parentContainer);
			Iterator it = parentContainer.getProductGroupIterator();
			while (it.hasNext()) {
				ProductContainer container = (ProductContainer) it.next();
				ProductContainerData childData = new ProductContainerData();
				childData.setName(container.getName());
				parentData.addChild(childData);
			}

			ProductContainerData newData = new ProductContainerData();
			newData.setName(newGroup.getName());
			newData.setTag(newGroup);

			int index = parentData.getChildCount();

			view.insertProductContainer(parentData, newData, index);
		}
	}
}
