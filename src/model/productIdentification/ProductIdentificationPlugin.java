package model.productIdentification;


/**
 * Finds descriptions for Product objects
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public abstract class ProductIdentificationPlugin {
	private ProductIdentificationPlugin successor;

	/**
	 * Returns the description of the Product
	 * 
	 * @param productBarcode
	 *            Product for which to find description
	 * @return description of Product product
	 * 
	 * @pre product != null
	 * @post true
	 */
	public abstract String getDescriptionForProduct(String productBarcode);

	/**
	 * Get the successor ProductIdentificationPlugin
	 * 
	 * @return successor ProductIdentificationPlugin
	 */
	public ProductIdentificationPlugin getSuccessor() {
		return successor;
	}

	/**
	 * Add a successor ProductIdentificationPlugin for the case that this cannot find the
	 * description of the Product
	 * 
	 * @param plugin
	 *            ProductIdentificationPlugin to add to the end of the chain
	 */
	public void setSuccessor(ProductIdentificationPlugin plugin) {
		successor = plugin;
	}

}
