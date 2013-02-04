package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;

/**
 * @author Clayton Watts
 *
 */
public class ConcreteProductManager implements ProductManager {
	private Collection<Product> products;
	
	public ConcreteProductManager() {
		products = new ArrayList<Product>();
	}
	
	/** Adds the given product to this Manager's indexes
	 * @param product Product to manage
	 */
	@Override
	public void manage(Product product) {
		products.add(product);
	}
	
	@Override
	public void unmanage(Product product) {
		products.remove(product);
	}
	
	/**
	 * Checks if the identified Product exists in the home inventory system.
	 * @param product 		The Product to check
	 * @return				true if the product exists in the home inventory system, false otherwise.
	 * 
	 * @pre product != null
	 * @post true
	 */
	public boolean contains(Product product) {
		return products.contains(product);
	}
	
}
