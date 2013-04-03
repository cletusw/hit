package common.util;

public interface IHttpClient {
	/**
	 * Makes an HTTP request to the given url, returning the content
	 * 
	 * @param url
	 *            webpage to request
	 * @return string content of the page requested or null if the url given is invalid, or an
	 *         IOException occurs
	 */
	public String getHttpRequest(String url);
}
