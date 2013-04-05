package model.productIdentification;

import java.util.HashMap;
import java.util.Map;

import common.util.IHttpClient;
import common.util.SignedRequestsHelper;

/**
 * Finds descriptions for Product objects using the Amazon Product Search API
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class AmazonApi implements ProductIdentificationPlugin {
	private static final String AWS_ACCESS_KEY_ID = "AKIAJSHRDD3C24XD7DMA";
	private static final String AWS_SECRET_KEY = "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";
	private static final String ENDPOINT = "ecs.amazonaws.com";
	private final IHttpClient client;

	public AmazonApi(IHttpClient client) {
		this.client = client;
	}

	@Override
	public String getDescriptionForProduct(String productBarcode) {
		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID,
					AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String requestUrl = null;

		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2011-08-01");
		params.put("Operation", "ItemLookup");
		params.put("AssociateTag", "7546-6649-9191");
		params.put("SearchIndex", "All");
		params.put("IdType", "UPC");
		params.put("ItemId", productBarcode);

		requestUrl = helper.sign(params);

		String response = client.getHttpRequest(requestUrl);

		return response;
	}

	@Override
	public void setClient(IHttpClient client) {
		// TODO Auto-generated method stub
	}

}
