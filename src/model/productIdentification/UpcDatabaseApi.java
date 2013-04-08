package model.productIdentification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.util.IHttpClient;

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
	private final String apiKey = ""; // "249dbc28bb5c3d7dbdbcf6a564dec307";

	private IHttpClient client;

	public UpcDatabaseApi() {
		client = null;
	}

	public UpcDatabaseApi(IHttpClient c) {
		client = c;
	}

	@Override
	public String getDescriptionForProduct(String productBarcode) {
		if (client == null) {
			throw new IllegalStateException("Client is null!");
		}
		String content = client.getHttpRequest(baseUrl + apiKey + "/" + productBarcode);
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
				System.err.println(result);
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
