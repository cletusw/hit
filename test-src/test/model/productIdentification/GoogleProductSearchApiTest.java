package test.model.productIdentification;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import plugin.GoogleProductSearchApi;
import plugin.IHttpClient;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GoogleProductSearchApiTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDescriptionForProduct() {
		IHttpClient client = createMock(IHttpClient.class);
		String response = "{\"kind\":\"shopping#products\",\"etag\":\"\'XaqKkawJfLTlLXNjFXUj" + 
				"BJ5f0kI/CZ-8nGjE0gOrVy5Dh7FObroQSmo\'\",\"id\":\"tag:google.com,2010:shoppi" + 
				"ng/products\",\"selfLink\":\"https://www.googleapis.com/shopping/search/v1/" +
				"public/products?country=US&restrictBy=gtin:0885911003940\",\"totalItems\":5" +
				",\"startIndex\":1,\"itemsPerPage\":25,\"currentItemCount\":5,\"requestId\":" +
				"\"0CMjzqMqOubYCFQWT5wodWyoAAA\",\"items\":[{\"kind\":\"shopping#product\"," +
				"\"id\":\"tag:google.com,2010:shoppingproducts/473085/14855940918342724793\"" +
				",\"selfLink\":\"https://www.googleapis.com/shopping/search/v1/public/produc" +
				"ts/473085/gid/14855940918342724793\",\"product\":{\"googleId\":\"1485594091" +
				"8342724793\",\"author\":{\"name\":\"IdealTrueValueHomeCenter\",\"accountId" +
				"\":\"473085\"},\"creationTime\":\"2013-03-16T23:24:47.000Z\",\"modification" +
				"Time\":\"2013-03-19T01:28:27.000Z\",\"country\":\"US\",\"language\":\"en\"," +
				"\"title\":\"14.4V Cyclonic Action Cordless Dustbuster\",\"description\":\"14.4V" +
				",CyclonicActionCordlessDustbuster,Rotating,SlimNozzleTargetsDirtInTightSpac" +
				"es,50%MoreReach,LessBending&BetterAccessibility,Brush&CreviceToolAccessorie" +
				"sAreLocatedOn-Board,NoLooseItems,WideMouthDesignToScoopUpLargeDebris,Cyclon" +
				"icActionSpinsDust&DebrisAwayFromTheFilterSoSuctionPowerStaysStrong,Transluc" +
				"ent,BaglessDirtBowlMakesItEasyToSeeDirt&Empty,Removable,WashableDirtBowl&Fi" +
				"ltersAllowForAThorough,HygienicCleaning,3StageFiltrationMeansLessClogging&C" +
				"leanerAirExhaust,3VersatileWaysToStore:Horizontally,Vertically,OrMountOnAWa" +
				"ll,ChargingIndicatorLightAllowsYouToAlwaysKnowYouHaveAGoodConnection,Lightw" +
				"eight&PortableForQuickCleanUps,EnergyStarApprovedChargingSystemSavesEnergy&" +
				"HelpsProtectBatteries,ForReplacementFilterUseVF110,TV#143-992.\",\"link\":" +
				"\"http://www.idealtruevalue.com/servlet/the-130747/Detail\",\"brand\":\"Bla" +
				"ckandDecker\",\"condition\":\"new\",\"gtin\":\"00885911003940\",\"gtins\":[" +
				"\"00885911003940\"],\"mpns\":[\"CHV1408\"],\"inventories\":[{\"channel\":\"" +
				"online\",\"availability\":\"inStock\",\"price\":50.06,\"currency\":\"USD\"}" +
				"],\"images\":[{\"link\":\"http://www.idealtruevalue.com/catalog/149564.jpg" +
				"\",\"status\":\"available\"}]}}]}";
		
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);

		replayAll();

		GoogleProductSearchApi api = new GoogleProductSearchApi(); 
		api.setClient(client);
		String desc = api.getDescriptionForProduct("0885911003940");
		assertTrue(desc != null);
		assertTrue(desc.equals("14.4V Cyclonic Action Cordless Dustbuster"));

		verifyAll();
	}
	
	@Test
	public void testGetNullDescriptionForProduct() {
		IHttpClient client = createMock(IHttpClient.class);
		String response = null;
		expect(client.getHttpRequest((String) EasyMock.notNull())).andStubReturn(response);

		replayAll();
		
		GoogleProductSearchApi api = new GoogleProductSearchApi(); 
		api.setClient(client);
		String desc = api.getDescriptionForProduct("088591100394a");
		assertTrue(desc == null);
		
		verifyAll();
	}

}
