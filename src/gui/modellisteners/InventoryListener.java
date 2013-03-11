package gui.modellisteners;

import gui.common.DataWrapper;
import gui.inventory.IInventoryView;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.ProductManager;
import model.StorageUnit;

public abstract class InventoryListener {
	protected IInventoryView view;

	public InventoryListener(IInventoryView view) {
		this.view = view;
	}

	// TODO: This code is duplicated from
	// InventoryController.productContainerSelectionChanged(). It should not be!!!
	// Proposed solution is to add a call to getController().productContainerSelectionChanged()
	// in InvetoryView.selectProductContainer() -- essentially when the views selection changes
	// programatically it will notify the controller. This will eliminate the need for this
	// method, but may have adverse side effects.
	/**
	 * Sets the context view information when a ProductContainer is created or edited.
	 * 
	 * @param currentContainer
	 *            - selected container used to get information for the view
	 */
	public void showContext(ProductContainer currentContainer) {
		if (currentContainer instanceof StorageUnit) {
			view.setContextGroup("");
			view.setContextSupply("");
			view.setContextUnit(currentContainer.getName());
		} else if (currentContainer instanceof ProductGroup) {
			ProductGroup group = (ProductGroup) currentContainer;
			StorageUnit root = view.getProductContainerManager().getRootStorageUnitForChild(
					group);
			if (root == null)
				return;
			view.setContextGroup(group.getName());
			view.setContextSupply(group.getThreeMonthSupply().toString());
			view.setContextUnit(root.getName());
		} else {
			view.setContextGroup("");
			view.setContextSupply("");
			view.setContextUnit("");
		}
	}

	/**
	 * Updates all of the items in the view to match the model.
	 */
	public void updateItems(boolean restoreSelected) {
		/*
		 * Item Table The table is sorted by Entry Date (ascending). The Expiration Date column
		 * contains the Item s expiration date, or empty if this is unspecified. The Storage
		 * Unit column contains the name of the Storage Unit that contains the Item. The
		 * Product Group column contains the name of the Product Group that contains the Item,
		 * or empty if the Item is not in a Product Group.
		 */

		ProductData selectedProduct = view.getSelectedProduct();
		ArrayList<ItemData> itemsToDisplay = new ArrayList<ItemData>();
		ItemData selectedItem = view.getSelectedItem();

		if (selectedProduct != null) {
			Product product = (Product) selectedProduct.getTag();
			ProductContainerData pcData = view.getSelectedProductContainer();
			if (pcData == null)
				throw new NullPointerException("Selected product container should not be null");
			ProductContainer container = (ProductContainer) pcData.getTag();

			Iterator<Item> itemIterator;

			if (container == null) { // Root container is selected
				itemIterator = product.getItemsIterator();
			} else {
				itemIterator = container.getItemsForProduct(product).iterator();
			}
			while (itemIterator.hasNext()) {
				ItemData id = DataWrapper.wrap(itemIterator.next());
				itemsToDisplay.add(id);
			}
		}
		view.setItems(itemsToDisplay.toArray(new ItemData[itemsToDisplay.size()]));
		if (restoreSelected && selectedItem != null)
			view.selectItem(selectedItem);
	}

	/**
	 * Updates all of the ProductContainers in the view to match the model.
	 */
	public void updateProductContainers(boolean restoreSelected) {
		// TODO: Do we really need this? This one is less commonly used because only its child
		// class ProductContainerListener should need to update this...
		updateProducts(restoreSelected);
	}

	/**
	 * Updates all of the products in the view to match the model.
	 */
	public void updateProducts(boolean restoreSelected) {
		/*
		 * Product Table The table is sorted by Description (ascending). The Count column
		 * displays the number of Items of that Product contained in the selected node.
		 * Specifically, if the root Storage Units node is selected, Count is the total number
		 * of Items of that Product in the entire system . If a Product Container (Storage Unit
		 * or Product Group) node is selected, Count is the number of Items of that Product
		 * contained in the selected Product Container.
		 */

		// MERGED from InventoryController
		// Load Products in selected ProductContainer
		List<ProductData> productDataList = new ArrayList<ProductData>();
		ProductContainerData selectedContainer = view.getSelectedProductContainer();
		ProductData selectedProduct = view.getSelectedProduct();

		if (selectedContainer != null) {
			ProductContainer selected = (ProductContainer) selectedContainer.getTag();
			if (selected != null) {
				Iterator<Product> productIterator = selected.getProductsIterator();
				while (productIterator.hasNext()) {
					Product p = productIterator.next();
					int count = selected.getItemsForProduct(p).size();
					productDataList.add(DataWrapper.wrap(p, count));
				}
				// Update contextView
				ProductContainer currentContainer = (ProductContainer) selectedContainer
						.getTag();
				if (currentContainer instanceof StorageUnit) {
					view.setContextUnit(selectedContainer.getName());
					view.setContextGroup("");
					view.setContextSupply("");
				} else if (currentContainer instanceof ProductGroup) {
					ProductGroup group = (ProductGroup) currentContainer;
					StorageUnit root = view.getProductContainerManager()
							.getRootStorageUnitForChild(group);
					view.setContextUnit(root.getName());
					view.setContextGroup(group.getName());
					view.setContextSupply(group.getThreeMonthSupply().toString());
				}
			} else {
				// Root "Storage units" node is selected; display all Products in system
				ProductManager manager = view.getProductManager();
				for (Product p : manager.getProducts()) {
					productDataList.add(DataWrapper.wrap(p, p.getItemCount()));
				}
				view.setContextUnit("All");
			}
		}
		view.setProducts(productDataList.toArray(new ProductData[productDataList.size()]));
		if (restoreSelected && selectedProduct != null)
			view.selectProduct(selectedProduct);
		updateItems(restoreSelected);
	}
}
