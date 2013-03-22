package model;

import java.util.Date;
import java.util.Map;
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

	public Map<Product, Set<Item>> getRemovedItemsByProduct();

	public void manage(Item item);

	public void notifyObservers(Action action);

	public void remanage(Item item);

	public void undoManage(Item item);

	public void unmanage(Item item);

	void addObserver(Observer o);
}
