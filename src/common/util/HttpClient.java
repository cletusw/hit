package common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
	/**
	 * Makes an HTTP request to the given url, returning the content
	 * 
	 * @param url
	 *            webpage to request
	 * @return string content of the page requested or null if the url given is invalid, or an
	 *         IOException occurs
	 */
	public String getHttpRequest(String url) {
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
