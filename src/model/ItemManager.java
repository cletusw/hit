package model;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Clayton Watts
 * 
 */
public interface ItemManager {
	public Set<Item> getItemsByProduct(Product product);

	public void manage(Item item);

	public Iterator<Item> removedItemsIterator();

	public void unmanage(Item item);
}
