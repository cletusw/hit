package test.model.productIdentification;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import model.productIdentification.AmazonApi;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.util.IHttpClient;

public class AmazonApiTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescriptionForProduct() {
		IHttpClient c = createMock(IHttpClient.class);
		String response = "<ItemLookupResponse "
				+ "xmlns=\"http://webservices.amazon.com/AWSECommerceService/2011-08-01\">"
				+ "<Items>" + "<Request>" + "<IsValid>True</IsValid>" + "</Request>"
				+ "<Item>" + "<ItemAttributes>" + "<Manufacturer>Lagasse Inc.</Manufacturer>"
				+ "<ProductGroup>Home</ProductGroup>"
				+ "<Title>Ajax Cleaner Bonus Size, 28 Oz</Title>" + "</ItemAttributes>"
				+ "</Item>" + "</Items>" + "</ItemLookupResponse>";
		expect(c.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);

		replayAll();

		AmazonApi api = new AmazonApi(c);
		String desc = api.getDescriptionForProduct("035000053640");
		assertTrue(desc != null);
		assertTrue(desc.equals("Ajax with Bleach Poweder Cleaner"));

		verifyAll();
	}

}
