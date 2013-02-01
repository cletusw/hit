package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Clayton Watts
 *
 */

public class ProductManager {
	private Collection<Product> products;
	
	public ProductManager() {
		products = new ArrayList<Product>();
	}
	
	/** Adds the given product to this Manager's indexes
	 * @param product Product to manage
	 */
	public void manage(Product product) {
		products.add(product);
	}
}
