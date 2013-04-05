package model.productIdentification;

import common.util.IHttpClient;

/**
 * Finds descriptions for Product objects using the Amazon Product Search API
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class AmazonApi implements ProductIdentificationPlugin {

	@Override
	public String getDescriptionForProduct(String productBarcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClient(IHttpClient client) {
		// TODO Auto-generated method stub
	}

}
