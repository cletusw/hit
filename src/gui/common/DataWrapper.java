package gui.common;

import gui.product.ProductData;
import model.Product;

public class DataWrapper {
	public static ProductData wrap(Product product, int count) {
		ProductData productData = new ProductData();

		productData.setBarcode(product.getBarcode());
		productData.setCount(Integer.toString(count));
		productData.setDescription(product.getDescription());
		productData.setShelfLife(Integer.toString(product.getShelfLife()));
		productData.setSize(product.getSize().toString());
		productData.setSupply(Integer.toString(product.getThreeMonthSupply()));
		productData.setTag(product);

		return productData;
	}
}
