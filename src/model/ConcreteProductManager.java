package model;

import java.util.ArrayList;
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
}
