package model.productIdentification;

/**
 * Initializes and maintains ProductIdentificationPlugins.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 */
public class ProductIdentificationPluginWrapper {

	private ProductIdentificationPlugin plugin;
	private ProductIdentificationPluginWrapper successor;

	/**
	 * Uses the ProductIdentificationPlugin to determine the description of the product
	 * 
	 * @param productBarcode
	 * @return the description for the product, or null if not found
	 * 
	 * @pre product != null
	 * @post true
	 */
	public String getDescriptionForProduct(String productBarcode) {
		String description = plugin.getDescriptionForProduct(productBarcode);
		if (description == null && successor != null)
			return successor.getDescriptionForProduct(productBarcode);
		return null;
	}

	/**
	 * Get the successor ProductIdentificationPlugin
	 * 
	 * @return successor ProductIdentificationPlugin
	 */
	public ProductIdentificationPluginWrapper getSuccessor() {
		return successor;
	}

	/**
	 * Add a successor ProductIdentificationPlugin for the case that this cannot find the
	 * description of the Product
	 * 
	 * @param plugin
	 *            ProductIdentificationPlugin to add to the end of the chain
	 */
	public void setSuccessor(ProductIdentificationPluginWrapper plugin) {
		successor = plugin;
	}
}
