package model;

import java.io.Serializable;

import model.report.ReportManager;

/**
 * System for tracking home storage inventories.
 */
@SuppressWarnings("serial")
public class HomeInventoryTracker implements Serializable {
	private final ItemManager itemManager;
	private final ProductManager productManager;
	private final ProductContainerManager productContainerManager;
	private final ReportManager reportManager;

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
		reportManager = new ReportManager(productContainerManager, productManager, itemManager);
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

	public ReportManager getReportManager() {
		return reportManager;
	}
}
