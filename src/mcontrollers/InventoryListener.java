package mcontrollers;

import gui.common.DataWrapper;
import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Iterator;

import model.Item;
import model.Product;
import model.ProductContainer;

public abstract class InventoryListener {
	protected IInventoryView view;

	public InventoryListener(IInventoryView view) {
		this.view = view;
	}

	/**
	 * 
	 */
	public void updateAll() {
		updateProductContainers();
		updateProducts();
		updateItems();
	}

	/**
	 * Updates all of the items in the view to match the model.
	 */
	public void updateItems() {
		ArrayList<ItemData> itemsToDisplay = new ArrayList<ItemData>();
		ProductContainerData parent = view.getSelectedProductContainer();
		if (parent != null && parent.getTag() != null) {
			ProductContainer pc = (ProductContainer) parent.getTag();
			ProductData pd = view.getSelectedProduct();
			if (pd != null && pd.getTag() != null) {
				Iterator<Item> itemIterator = pc.getItemsIteratorForProduct((Product) pd
						.getTag());
				while (itemIterator.hasNext()) {
					ItemData id = DataWrapper.wrap(itemIterator.next());
					itemsToDisplay.add(id);
				}
			}
		}
		view.setItems(itemsToDisplay.toArray(new ItemData[itemsToDisplay.size()]));
	}

	/**
	 * Updates all of the ProductContainers in the view to match the model.
	 */
	public void updateProductContainers() {
		// TODO: Do we really need this? This one is less commonly used because only its child
		// class ProductContainerListener should need to update this...
	}

	/**
	 * Updates all of the products in the view to match the model.
	 */
	public void updateProducts() {
		ArrayList<ProductData> products = new ArrayList<ProductData>();

		ProductContainerData selectedContainer = view.getSelectedProductContainer();
		if (selectedContainer != null && selectedContainer.getTag() != null) {
			ProductContainer container = (ProductContainer) selectedContainer.getTag();
			Iterator<Product> iter = container.getProductsIterator();

			while (iter.hasNext()) {
				Product product = iter.next();
				int count = container.getItemsForProduct(product).size();
				ProductData productData = DataWrapper.wrap(product, count);
				products.add(productData);
			}
		}

		view.setProducts(products.toArray(new ProductData[products.size()]));
	}
}
