package model;

import java.util.Date;
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

	public Set<Item> getRemovedItems();

	public void manage(Item item);

	public void unmanage(Item item);

	void addObserver(Observer o);
}
