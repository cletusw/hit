package plugin;

import common.util.IHttpClient;

/**
 * Finds descriptions for Product objects
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public interface ProductIdentificationPlugin {

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
	public String getDescriptionForProduct(String productBarcode);

	public void setClient(IHttpClient client);
}
