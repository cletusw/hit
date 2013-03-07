package model;

import java.util.Observer;
import java.util.Set;

/**
 * @author Clayton Watts
 * 
 */
public interface ProductManager {
	public boolean contains(Product product);

	public boolean containsProduct(String productName);

	public Product getByBarcode(String barcodeScanned);

	public Set<Product> getProducts();

	public void manage(Product product);

	public void unmanage(Product product);

	void addObserver(Observer o);
	
	public void notifyObservers(Action action);
}
