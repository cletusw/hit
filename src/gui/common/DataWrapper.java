package gui.common;

import gui.item.ItemData;
import gui.product.ProductData;
import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.StorageUnit;

public class DataWrapper {
	/**
	 * Wraps an Item from our model in the intermediary ItemData class
	 * 
	 * @param item
	 *            Item to wrap
	 * @return ItemData version of item
	 */
	public static ItemData wrap(Item item) {
		ItemData itemData = new ItemData();

		itemData.setBarcode(item.getBarcode());
		itemData.setEntryDate(item.getEntryDate());
		itemData.setExpirationDate(item.getExpirationDate());
		ProductContainer container = item.getContainer();
		if (container instanceof StorageUnit) {
			itemData.setStorageUnit(container.getName());
		} else if (container instanceof ProductGroup) {
			itemData.setProductGroup(container.getName());
		}
		itemData.setTag(item);

		return itemData;
	}

	/**
	 * Wraps a Product from our model in the intermediary ProductData class
	 * 
	 * @param product
	 *            Product to wrap
	 * @param count
	 *            Number of items of Product product in the parent container
	 * @return ProductData version of product
	 */
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
