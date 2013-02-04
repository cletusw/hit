package model;

import java.util.Iterator;

/**
 * @author Clayton Watts
 *
 */
public interface ItemManager {
	public void manage(Item item);
	public void unmanage(Item item);
	public Iterator<Item> removedItemsIterator();
	public boolean productHasItems(Product product);
}
