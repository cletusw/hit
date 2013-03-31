package model.productIdentification;

import model.Product;

/**
 * Initializes and maintains ProductIdentificationPlugins.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 */
public class ProductIdentificationPluginManager {

	private ProductIdentificationPlugin root;

	/**
	 * Uses the ProductIdentificationPlugin to determine the desription of the product
	 * 
	 * @param product
	 * @return the description for the product, or null if not found
	 * 
	 * @pre product != null
	 * @post true
	 */
	public String getDescriptionForProduct(Product product) {
		return root.getDescriptionForProduct(product);
	}

	/**
	 * Loads all plugins based on config file
	 */
	public void loadPlugins() {

	}

}
