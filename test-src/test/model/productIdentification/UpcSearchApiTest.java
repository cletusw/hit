package test.model.productIdentification;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertTrue;
import model.productIdentification.UpcSearchApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.util.IHttpClient;

public class UpcSearchApiTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescriptionForProduct() {
		IHttpClient c = createMock(IHttpClient.class);
		// expect(c.getHttpRequest("")).andStubReturn("");

		UpcSearchApi usa = new UpcSearchApi(c);
		String desc = usa.getDescriptionForProduct("035000053640");
		assertTrue(desc.equals("Ajax with Bleach Poweder Cleaner"));
	}
}
