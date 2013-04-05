package common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient implements IHttpClient {

	@Override
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

				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine + "\n");
					// System.out.println(inputLine);
				}
				in.close();
			} catch (IOException e) {
				return null;
			}

			System.out.println(content.toString());

			return content.toString();
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
