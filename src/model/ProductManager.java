package model;

/**
 * @author Clayton Watts
 *
 */

public interface ProductManager {
	public void manage(Product product);
	public void unmanage(Product product);
	public boolean contains(Product product);
}
