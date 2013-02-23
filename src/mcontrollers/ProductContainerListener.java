package mcontrollers;

import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import model.Action;
import model.Action.ActionType;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.StorageUnit;

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
	 *            The object being observed (which has access to the model)
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
		Action action = (Action) arg;
		ActionType type = action.getAction();

		if (action.getAction().equals(ActionType.CREATE)) {
			Object pc = action.getObject();
			if (pc instanceof ProductGroup) {
				// Get data for inserted PC
				ProductGroup newGroup = (ProductGroup) pc;
				ProductContainerData newData = new ProductContainerData();
				newData.setName(newGroup.getName());
				newData.setTag(newGroup);

				// Get data for parent PC
				ProductContainerData parentData = view.getSelectedProductContainer();

				// Insert
				view.insertProductContainer(parentData, newData, parentData.getChildCount());
				view.selectProductContainer(newData);
			} else {
				// Get data for new SU
				StorageUnit newStorageUnit = (StorageUnit) pc;
				ProductContainerData newData = new ProductContainerData();
				newData.setName(newStorageUnit.getName());
				newData.setTag(newStorageUnit);

				// Get data for parent (main root)
				ProductContainerData parent = view.getSelectedProductContainer();

				// Insert
				view.insertProductContainer(parent, newData, parent.getChildCount());
				view.selectProductContainer(newData);
			}
		} else if (type.equals(ActionType.EDIT)) {
			ProductContainerData data = view.getSelectedProductContainer();
			ProductContainer container = (ProductContainer) action.getObject();
			view.renameProductContainer(data, container.getName(), 0);
			view.selectProductContainer(data);
		} else if (type.equals(ActionType.DELETE)) {
			ProductContainerData data = view.getSelectedProductContainer();
			view.deleteProductContainer(data);
		}
	}

	private ProductContainerData loadProductContainerData(ProductContainerData parentData,
			ProductContainer container) {
		ProductContainerData pcData = new ProductContainerData(container.getName());
		pcData.setTag(container);
		parentData.addChild(pcData);
		Iterator<ProductGroup> productGroupIterator = container.getProductGroupIterator();
		while (productGroupIterator.hasNext()) {
			ProductGroup child = productGroupIterator.next();
			pcData = loadProductContainerData(pcData, child);
		}
		return parentData;
	}
}
