package model;

import java.util.Date;
import java.util.Iterator;
import java.util.Observer;
import java.util.Set;

/**
 * @author Clayton Watts
 * 
 */
public interface ItemManager {
	public Set<Item> getItemsByProduct(Product product);

	public void manage(Item item);

	public Iterator<Item> removedItemsIterator();
	
	public void editItem(Item item, Date newEntryDate);
	
	void addObserver(Observer o);

	public void unmanage(Item item);
}
