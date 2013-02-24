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
	public void editItem(Item item, Date newEntryDate);

	public Item getItemByItemBarcode(String barcode);

	public Set<Item> getItemsByProduct(Product product);

	public void manage(Item item);

	public Iterator<Item> removedItemsIterator();

	public void unmanage(Item item);

	void addObserver(Observer o);
}
