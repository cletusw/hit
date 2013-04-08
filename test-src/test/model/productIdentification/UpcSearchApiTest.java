package test.model.productIdentification;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import model.productIdentification.UpcSearchApi;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.util.IHttpClient;

public class UpcSearchApiTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescriptionForProduct() {
		String description = "Ajax with Bleach Poweder Cleaner";
		IHttpClient client = createMock(IHttpClient.class);
		String response = "{" + "valid: \"true\"," + "number: \"0035000053640\","
				+ "itemname: \"" + description + "\"," + "description: \"\","
				+ "price: \"0.00\"," + "ratingsup: 0," + "ratingsdown: 0" + "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		UpcSearchApi usa = new UpcSearchApi();
		usa.setClient(client);
		String d = usa.getDescriptionForProduct("035000053640");
		assertTrue(d.equals(description));
	}

	@Test
	public void testGetEmptyDescriptionForProduct() {
		IHttpClient client = createMock(IHttpClient.class);
		String response = "{" + "valid: \"true\"," + "number: \"0035000053640\","
				+ "itemname: \"\"," + "description: \"\"," + "price: \"0.00\","
				+ "ratingsup: 0," + "ratingsdown: 0" + "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		UpcSearchApi usa = new UpcSearchApi();
		usa.setClient(client);
		String d = usa.getDescriptionForProduct("035000053640");
		assertTrue(d == null);
	}

	@Test
	public void testGetInvalidDescriptionForProduct() {
		IHttpClient client = createMock(IHttpClient.class);
		String response = "{" + "valid: \"false\"," + "reason: \"Non-numeric code entered.\""
				+ "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		UpcSearchApi usa = new UpcSearchApi();
		usa.setClient(client);
		String d = usa.getDescriptionForProduct("035000053640");
		assertTrue(d == null);
	}

	@Test
	public void testGetNoDescriptionForProduct() {
		IHttpClient client = createMock(IHttpClient.class);
		String response = "{" + "valid: \"false\","
				+ "reason: \"Code not found in database.\"" + "}";
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);
		replayAll();

		UpcSearchApi usa = new UpcSearchApi();
		usa.setClient(client);
		String d = usa.getDescriptionForProduct("035000053640");
		assertTrue(d == null);
	}
}
