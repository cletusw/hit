package test.model.productIdentification;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import plugin.UpcDatabaseApi;

import common.util.HttpClient;
import common.util.IHttpClient;

public class UpcDatabaseApiTest extends EasyMockSupport {

	@Test
	public void testGetDescriptionForProduct() {
		// IHttpClient c = createMock(IHttpClient.class);
		// String response = expect(c.getHttpRequest((String)
		// EasyMock.notNull())).andStubReturn(response);

		IHttpClient client = new HttpClient();

		replayAll();

		UpcDatabaseApi api = new UpcDatabaseApi(client);
		String desc = api.getDescriptionForProduct("035000053640");
		assertTrue(desc != null);
		assertTrue(desc.equals("Ajax with Bleach Poweder Cleaner"));

		verifyAll();
	}
}
