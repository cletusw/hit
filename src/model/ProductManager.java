package model;

import java.util.Observer;
import java.util.Set;

import model.undo.Command;

/**
 * @author Clayton Watts
 * 
 */
public interface ProductManager {
	public boolean contains(Product product);

	public boolean containsProduct(String productName);

	public Product getByBarcode(String barcodeScanned);

	public String getDescriptionForProduct(String barcode);

	public Command getPendingProductCommand();

	public Set<Product> getProducts();

	public void manage(Product product);

	public void notifyObservers(Action action);

	public void setPendingProductCommand(Command command);

	public void unmanage(Product product);

	void addObserver(Observer o);

	void delete(Product product);
}
