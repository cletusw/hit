package test.model.productIdentification;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import plugin.IHttpClient;
import plugin.UpcDatabaseApi;

public class UpcDatabaseApiTest extends EasyMockSupport {

	@Test
	public void testGetDescriptionForProduct() {
		IHttpClient client = createMock(IHttpClient.class);
		String response = "<html><body><table>"
				+ "<tr><td>Description</td><td></td><td>Ajax</td></tr></table>"
				+ "</body></html>";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andReturn(response);

		replayAll();

		UpcDatabaseApi api = new UpcDatabaseApi(client);
		String desc = api.getDescriptionForProduct("035000053640");
		assertTrue(desc != null);
		assertTrue(desc.equals("Ajax"));

		verifyAll();
	}
}
