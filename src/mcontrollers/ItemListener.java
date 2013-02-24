package mcontrollers;

import gui.common.DataWrapper;
import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import model.Action;
import model.Action.ActionType;
import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainer;

/**
 * Controller object that acts as a liaison between the model's ItemManager and the GUI view.
 * 
 * @author Matt Hess
 * @version 1.0 CS 340 Group 4 Phase 2
 * 
 */
public class ItemListener implements Observer {

	private final IInventoryView view;

	public ItemListener(IInventoryView view, ItemManager itemManager) {
		itemManager.addObserver(this);
		this.view = view;
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

		switch (action.getAction()) {
		case CREATE:

		case EDIT:

		case DELETE:
			ProductContainerData parent = view.getSelectedProductContainer();
			ProductContainer pc = (ProductContainer) parent.getTag();
			ArrayList<ItemData> itemsToDisplay = new ArrayList<ItemData>();
			ItemData[] itemArray = new ItemData[0];
			Iterator<Item> itemIterator = pc.getItemsIterator();
			while (itemIterator.hasNext()) {
				ItemData id = DataWrapper.wrap(itemIterator.next());
				itemsToDisplay.add(id);
			}
			view.setItems(itemsToDisplay.toArray(itemArray));
			// view.selectItem(DataWrapper.wrap(item));

			// TODO: update the count of the selectedProduct
			updateProductView(pc);
			break;
		}
	}

	private void updateProductView(ProductContainer container) {
		Iterator<Product> iter = container.getProductsIterator();
		ProductData[] products = new ProductData[container.getProductsSize()];

		int i = 0;
		while (iter.hasNext()) {
			Product prod = iter.next();
			ProductData productData = new ProductData();
			productData.setBarcode(prod.getBarcode());
			productData.setDescription(prod.getDescription());
			productData.setShelfLife(String.valueOf(prod.getShelfLife()));
			productData.setSize(String.valueOf(prod.getSize().getQuantity()));
			productData.setSupply(String.valueOf(prod.getThreeMonthSupply()));
			productData.setTag(prod);
			products[i++] = productData;
			productData.setCount(String.valueOf(container.getItemsForProduct(prod).size()));
		}

		view.setProducts(products);
	}
}
