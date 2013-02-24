package model;

import java.util.Observer;

/**
 * @author Clayton Watts
 * 
 */

public interface ProductManager {
	public boolean contains(Product product);

	public boolean containsProduct(String productName);

	public void editProduct(Product product, String newDescription,
			ProductQuantity newQuantity, int newShelfLife, int newTms);

	public Product getByBarcode(String barcodeScanned);

	public void manage(Product product);

	public void unmanage(Product product);

	void addObserver(Observer o);
}
