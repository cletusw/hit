package plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Finds descriptions for Product objects using the Google ProductSearch API
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class GoogleProductSearchApi implements ProductIdentificationPlugin {
	private IHttpClient client;
	
	public GoogleProductSearchApi() {
		client = null;
	}
	
	@Override
	public void setClient(IHttpClient client) {
		this.client = client;
	}
	
	@Override
	public String getDescriptionForProduct(String productBarcode) {
		if(productBarcode == null)
			throw new IllegalArgumentException("Product barcode cannot be null");
		if (client == null)
			throw new NullPointerException("Client cannot be null");
		
		// api key for cs340group4w2013@gmail.com:
		String apiKey = "AIzaSyBrvERmxtgp101F1-eVJbKmnwGp5szO0WU";
		
		// build URL and send HTTP request
		String leftURL = "https://www.googleapis.com/shopping/search/v1/public/products?country=US&restrictBy=gtin:" + productBarcode;
		String rightURL = "&key=" + apiKey;
		String content = client.getHttpRequest(leftURL + rightURL);
		if (content == null)
			return null;
		
		JsonParser parser = new JsonParser();
		JsonElement parsed = parser.parse(content);
		if(parsed == null || parsed.isJsonNull())
			return null;
		
		return parseDescription(parsed.getAsJsonObject());
	}
	
	private String parseDescription(JsonObject o) {
		if(o == null || o.isJsonNull())
			return null;
		
		JsonElement totalItems = o.get("totalItems");
		if(totalItems.getAsString().equals("0"))
			return null;
		
		try {
		// get first element from "items" json array
		JsonElement result = o.get("items");
		JsonArray array = result.getAsJsonArray();
		JsonElement firstArrayElement = array.get(0);
		
		// get "product" object
		JsonElement firstNestedElement = firstArrayElement.getAsJsonObject();
		JsonObject productElement = firstNestedElement.getAsJsonObject();
		JsonElement nextProductElement = productElement.get("product");
		JsonObject productObject = nextProductElement.getAsJsonObject();
		
		JsonElement title = productObject.get("title");
		return title.getAsString();
		
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}
}
