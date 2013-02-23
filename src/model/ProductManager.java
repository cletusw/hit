package model;

/**
 * @author Clayton Watts
 * 
 */

public interface ProductManager {
	public boolean contains(Product product);

	public boolean containsProduct(String productName);

	public Product getByBarcode(String barcodeScanned);

	public void manage(Product product);

	public void unmanage(Product product);
}
