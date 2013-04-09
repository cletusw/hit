package plugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Finds descriptions for Product objects using the UpcSearch API at upcdatabase.org
 * 
 * @author Seth
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class UpcDatabaseApi implements ProductIdentificationPlugin {

	/*
	 * For implementation purposes:
	 * 
	 * Url is of format http://upcdatabase.org/api/json/<KEY>/<UPC>
	 */
	private final String baseUrl = "http://www.upcdatabase.com/item/";

	private IHttpClient client;

	public UpcDatabaseApi() {
		client = null;
	}

	public UpcDatabaseApi(IHttpClient c) {
		client = c;
	}

	/**
	 * Returns the String description for the specified product barcode, or null if none found.
	 * 
	 * @pre productBarcode != null
	 * @post return a String with the product's description, or null if not found.
	 */
	@Override
	public String getDescriptionForProduct(String productBarcode) {
		if (client == null) {
			throw new IllegalStateException("HTTP client is null!");
		}
		if (productBarcode == null) {
			throw new IllegalStateException("Product barcode is null!");
		}
		String content = client.getHttpRequest(baseUrl + "/" + productBarcode);
		if (content == null) {
			return null;
		}

		Pattern pattern = Pattern
				.compile("<tr><td>Description</td><td></td><td>(.*)</td></tr>");
		Matcher matcher = pattern.matcher(content);
		String result = null;
		if (matcher.find()) {
			if (matcher.groupCount() >= 1) {
				result = matcher.group(1);
			}
		}

		if (result == null)
			return null;
		else
			return result;
	}

	@Override
	public void setClient(IHttpClient client) {
		this.client = client;
	}
}
