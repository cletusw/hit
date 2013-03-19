package gui.common;

import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
	 * Wraps a Collection of Products from our model in the intermediary ProductData class
	 * 
	 * @param products
	 *            Products to wrap
	 * @return ProductData[] version of Products
	 */
	public static ProductData[] wrap(Map<Product, Set<Item>> products) {
		ArrayList<ProductData> wrappedProducts = new ArrayList<ProductData>();

		for (Product product : products.keySet()) {
			wrappedProducts.add(wrap(product, products.get(product).size()));
		}

		return wrappedProducts.toArray(new ProductData[0]);
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

	/**
	 * Wraps a ProductContainer from our model in the intermediary ProductContainerData class
	 * 
	 * @param productContainer
	 *            ProductContainer to wrap
	 * @return ProductContainerData version of productContainer
	 */
	public static ProductContainerData wrap(ProductContainer productContainer) {
		ProductContainerData productContainerData = new ProductContainerData(
				productContainer.getName());

		productContainerData.setTag(productContainer);

		return productContainerData;
	}

	/**
	 * Wraps a Collection of Items from our model in the intermediary ItemData class
	 * 
	 * @param items
	 *            Items to wrap
	 * @return ItemData[] version of Items
	 */
	public static ItemData[] wrap(Set<Item> items) {
		ArrayList<ItemData> wrappedItems = new ArrayList<ItemData>();

		for (Item item : items) {
			wrappedItems.add(wrap(item));
		}

		return wrappedItems.toArray(new ItemData[0]);
	}
}
