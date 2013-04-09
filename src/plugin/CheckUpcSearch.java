package plugin;

import common.util.IHttpClient;

public class CheckUpcSearch implements ProductIdentificationPlugin {

	// Example: http://www.checkupc.com/product-54091
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
