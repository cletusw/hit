package model;

import java.io.Serializable;

/**
 * System for tracking home storage inventories.
 */
@SuppressWarnings("serial")
public class HomeInventoryTracker implements Serializable {
	private ItemManager itemManager;
	private ProductManager productManager;
	private ProductContainerManager productContainerManager;

	/**
	 * Initializes the HomeInventoryTracker.
	 * 
	 * @pre true
	 * @post true
	 */
	public HomeInventoryTracker() {
		itemManager = new ConcreteItemManager();
		productManager = new ConcreteProductManager();
		productContainerManager = new ConcreteProductContainerManager();
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public ProductContainerManager getProductContainerManager() {
		return productContainerManager;
	}

	public ProductManager getProductManager() {
		return productManager;
	}
}
