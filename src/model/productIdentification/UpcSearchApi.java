package model.productIdentification;

import model.Product;

/**
 * Finds descriptions for Product objects using the UpcSearch API at upcdatabase.org
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class UpcSearchApi extends ProductIdentificationPlugin {

	/*
	 * For implementation purposes:
	 * 
	 * Url is of format http://upcdatabase.org/api/json/<KEY>/<UPC>
	 */
	private String baseUrl = "http://upcdatabase.org/api/json/";
	private String apiKey = "249dbc28bb5c3d7dbdbcf6a564dec307";

	@Override
	public String getDescriptionForProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
