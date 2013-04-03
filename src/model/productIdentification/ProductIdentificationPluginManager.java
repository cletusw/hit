package model.productIdentification;

/**
 * Initializes and maintains ProductIdentificationPlugins.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 */
public class ProductIdentificationPluginManager {

	private ProductIdentificationPluginWrapper root;

	/**
	 * Uses the ProductIdentificationPlugin to determine the desription of the product
	 * 
	 * @param productBarcode
	 * @return the description for the product, or null if not found
	 * 
	 * @pre product != null
	 * @post true
	 */
	public String getDescriptionForProduct(String productBarcode) {
		return root.getDescriptionForProduct(productBarcode);
	}

	/**
	 * Loads all plugins based on config file
	 */
	public void loadPlugins() {

	}
}
