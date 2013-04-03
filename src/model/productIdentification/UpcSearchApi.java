package model.productIdentification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

	@Override
	public String getDescriptionForProduct(String productBarcode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Makes an HTTP request to the given url, returning the content
	 * 
	 * @param url
	 *            webpage to request
	 * @return string content of the page requested or null if the url given is invalid, or an
	 *         IOException occurs
	 */
	private String getHttpRequest(String url) {
		try {
			StringBuilder content = new StringBuilder();
			URL requestUrl = new URL(url);
			URLConnection connection;
			try {
				connection = requestUrl.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					System.out.println(inputLine);
				in.close();
			} catch (IOException e) {
				return null;
			}

			return content.toString();
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
