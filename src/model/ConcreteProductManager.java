package model;

import java.io.Serializable;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Action.ActionType;

/**
 * @author Clayton Watts
 * 
 */
@SuppressWarnings("serial")
public class ConcreteProductManager extends Observable implements ProductManager, Serializable {
	private final Set<Product> products;
	private final Map<String, Product> barcodesToProducts;

	/**
	 * Creates an empty ConcreteProductManager
	 */
	public ConcreteProductManager() {
		products = new TreeSet<Product>();
		barcodesToProducts = new TreeMap<String, Product>();
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
		setChanged();
		Action a = new Action(product, ActionType.CREATE);
		notifyObservers(a);
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

		products.remove(product);
		barcodesToProducts.remove(product.getBarcode());
	}

	
	/**
	 * Modifies the given product with the given data and notifies observers when
	 * change is made
	 * 
	 */
	@Override
	public void editProduct(Product product, String newDescription, ProductQuantity newQuantity, int newShelfLife, int newTms) {
		this.products.remove(product);
		this.barcodesToProducts.remove(product.getBarcode());
		product.setDescription(newDescription);
		product.setProductQuantity(newQuantity);
		product.setShelfLife(newShelfLife);
		product.setThreeMonthSupply(newTms);
		this.products.add(product);
		this.barcodesToProducts.put(product.getBarcode(), product);
		setChanged();
		Action action = new Action(product, ActionType.EDIT);
		notifyObservers(action);
	}
}
