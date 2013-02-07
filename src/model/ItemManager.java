package model;

import java.util.Iterator;

/**
 * @author Clayton Watts
 * 
 */
public interface ItemManager {
	public void manage(Item item);

	public boolean productHasItems(Product product);

	public Iterator<Item> removedItemsIterator();

	public void unmanage(Item item);
}
