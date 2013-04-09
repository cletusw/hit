package test.model.productIdentification;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import plugin.IHttpClient;
import plugin.ProductIdentificationPluginManager;


public class UpcSearchApiTest extends EasyMockSupport {
	private IHttpClient client;
	private ProductIdentificationPluginManager manager;

	@Before
	public void setUp() throws Exception {
		client = createMock(IHttpClient.class);
		manager = new ProductIdentificationPluginManager("plugin.UpcSearchApi", client);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescriptionForProduct() {
		String description = "Ajax with Bleach Poweder Cleaner";
		String response = "{" + "valid: \"true\"," + "number: \"0035000053640\","
				+ "itemname: \"" + description + "\"," + "description: \"\","
				+ "price: \"0.00\"," + "ratingsup: 0," + "ratingsdown: 0" + "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		String d = manager.getDescriptionForProduct("035000053640");
		assertTrue(d.equals(description));
	}

	@Test
	public void testGetEmptyDescriptionForProduct() {
		String response = "{" + "valid: \"true\"," + "number: \"0035000053640\","
				+ "itemname: \"\"," + "description: \"\"," + "price: \"0.00\","
				+ "ratingsup: 0," + "ratingsdown: 0" + "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		String d = manager.getDescriptionForProduct("035000053640");
		assertTrue(d == null);
	}

	@Test
	public void testGetInvalidDescriptionForProduct() {
		String response = "{" + "valid: \"false\"," + "reason: \"Non-numeric code entered.\""
				+ "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		String d = manager.getDescriptionForProduct("035000053640");
		assertTrue(d == null);
	}

	@Test
	public void testGetNoDescriptionForProduct() {
		String response = "{" + "valid: \"false\","
				+ "reason: \"Code not found in database.\"" + "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		String d = manager.getDescriptionForProduct("035000053640");
		assertTrue(d == null);
	}
}
