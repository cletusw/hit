package model;

import java.util.Observer;

/**
 * @author Clayton Watts
 * 
 */

public interface ProductManager {
	public boolean contains(Product product);

	public boolean containsProduct(String productName);

	public Product getByBarcode(String barcodeScanned);

	void addObserver(Observer o);
	
	public void editProduct(Product product, String newDescription, ProductQuantity newQuantity, int newShelfLife, int newTms);
	
	public void manage(Product product);

	public void unmanage(Product product);
}
