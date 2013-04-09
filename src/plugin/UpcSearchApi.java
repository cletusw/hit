package plugin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.util.IHttpClient;

/**
 * Finds descriptions for Product objects using the UpcSearch API at upcdatabase.org
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class UpcSearchApi implements ProductIdentificationPlugin {

	/*
	 * For implementation purposes:
	 * 
	 * Url is of format http://upcdatabase.org/api/json/<KEY>/<UPC>
	 */
	private final String baseUrl = "http://upcdatabase.org/api/json/";
	private final String apiKey = "249dbc28bb5c3d7dbdbcf6a564dec307";
	private IHttpClient client;

	public UpcSearchApi() {
		client = null;
	}

	@Override
	public String getDescriptionForProduct(String productBarcode) {
		if (client == null)
			return null;
		String content = client.getHttpRequest(baseUrl + apiKey + "/" + productBarcode);
		if (content == null)
			return null;

		JsonParser parser = new JsonParser();
		JsonObject jObject = (JsonObject) parser.parse(content);

		if (jObject.get("itemname") != null
				&& jObject.get("itemname").getAsString().length() > 0) {
			return jObject.get("itemname").getAsString();
		} else if (jObject.get("description") != null
				&& jObject.get("description").getAsString().length() > 0) {
			return jObject.get("description").getAsString();
		}

		// no valid response
		return null;
	}

	@Override
	public void setClient(IHttpClient client) {
		this.client = client;

	}
}
