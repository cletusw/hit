package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Action.ActionType;
import model.undo.Command;

import common.ObservableWithPublicNotify;

/**
 * @author Clayton Watts
 * 
 */
@SuppressWarnings("serial")
public class ConcreteProductManager extends ObservableWithPublicNotify implements
		ProductManager, Serializable {
	private final Set<Product> products;
	private final Map<String, Product> barcodesToProducts;
	private Command pendingProductCommand;

	/**
	 * Creates an empty ConcreteProductManager
	 */
	public ConcreteProductManager() {
		products = new TreeSet<Product>();
		barcodesToProducts = new TreeMap<String, Product>();
		pendingProductCommand = null;
	}

	/**
	 * Checks if the identified Product exists in the home inventory system.
	 * 
	 * @param product
	 *            The Product to check
	 * @return true if the product exists in the home inventory system, false otherwise.
	 * 
	 * @pre product != null
	 */
	@Override
	public boolean contains(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Null Product product");
		}

		return products.contains(product);
	}

	/**
	 * Checks if the identified Product exists in the home inventory system.
	 * 
	 * @param productName
	 *            The barcode of the Product to check
	 * @return true if the product exists in the home inventory system, false otherwise.
	 * 
	 * @pre product != null
	 */
	@Override
	public boolean containsProduct(String productName) {
		return barcodesToProducts.containsKey(productName);
	}

	/**
	 * This method does all the work of unmanaging the product as well as removing it
	 * completely from the model.
	 * 
	 * @param product
	 *            Product to remove
	 */
	@Override
	public void delete(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Null Product product");
		}

		products.remove(product);
		barcodesToProducts.remove(product.getBarcode());
		notifyObservers(new Action(product, ActionType.DELETE));
		notifyObservers(new Action(product, ActionType.DEEP_DELETE));
	}

	/**
	 * Looks up a product by the given barcode
	 * 
	 * @param barcode
	 *            The barcode of the product to return
	 * @return The product with the given barcode
	 * 
	 * @pre barcode != null
	 */
	@Override
	public Product getByBarcode(String barcode) {
		if (barcode == null) {
			throw new IllegalArgumentException("Null Barcode barcode");
		}

		return barcodesToProducts.get(barcode);
	}

	@Override
	public String getDescriptionForProduct(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the pending command for Product manipulation. Used by the GUI for undo / redo state
	 * across multiple controllers.
	 * 
	 * @return the Command to be executed that affects the ProductManager, null if none
	 * @pre true
	 * @post true
	 */
	@Override
	public Command getPendingProductCommand() {
		return pendingProductCommand;
	}

	/**
	 * Gets all Products in the System.
	 * 
	 * @return an *unmodifiable* set of products
	 */
	@Override
	public Set<Product> getProducts() {
		return Collections.unmodifiableSet(products);
	}

	/**
	 * Adds the given product to this Manager's indices
	 * 
	 * @param product
	 *            Product to manage
	 * 
	 * @pre product != null
	 * @post contains(product) == true
	 * @post getBy(product.getBarcode()) == product
	 */
	@Override
	public void manage(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Null Product product");
		}

		products.add(product);
		barcodesToProducts.put(product.getBarcode(), product);

		notifyObservers(new Action(product, ActionType.CREATE));
	}

	/**
	 * Sets the pending command for Product manipulation. Used by the GUI for undo / redo state
	 * across multiple controllers.
	 * 
	 * @param command
	 *            the Command to be executed by a controller
	 * @pre true
	 * @post true
	 */
	@Override
	public void setPendingProductCommand(Command command) {
		pendingProductCommand = command;
	}

	/**
	 * Removes the given product from this Manager's indices
	 * 
	 * @param product
	 *            Product to unmanage
	 * 
	 * @pre product != null
	 * @post contains(product) == false
	 * @post getBy(product.getBarcode()) == null
	 */
	@Override
	public void unmanage(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Null Product product");
		}
		// Don't ever delete products from system???
		/*
		 * products.remove(product); barcodesToProducts.remove(product.getBarcode());
		 */
		notifyObservers(new Action(product, ActionType.DELETE));
	}
}
