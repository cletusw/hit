package test.model.productIdentification;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import model.productIdentification.ProductIdentificationPluginManager;

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
		IHttpClient httpClient = createMock(IHttpClient.class);
		String response = "<ItemLookupResponse "
				+ "xmlns=\"http://webservices.amazon.com/AWSECommerceService/2011-08-01\">"
				+ "<Items>" + "<Request>" + "<IsValid>True</IsValid>" + "</Request>"
				+ "<Item>" + "<ItemAttributes>"
				+ "<Title>Ajax Cleaner Bonus Size, 28 Oz</Title>" + "</ItemAttributes>"
				+ "</Item>" + "<Item>" + "<ItemAttributes>"
				+ "<Title>Colgate Palmolive Co. 05364 \"Ajax\" Cleanser "
				+ "with Bleach 28 Oz (Pack of 24)</Title>" + "</ItemAttributes>" + "</Item>"
				+ "</Items>" + "</ItemLookupResponse>";
		expect(httpClient.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);

		replayAll();

		ProductIdentificationPluginManager manager = new ProductIdentificationPluginManager(
				"model.productIdentification.AmazonApi", httpClient);
		String desc = manager.getDescriptionForProduct("035000053640");
		assertTrue(desc != null);
		assertTrue(desc.equals("Ajax Cleaner Bonus Size, 28 Oz"));

		verifyAll();
	}

}
