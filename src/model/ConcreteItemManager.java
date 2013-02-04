package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ConcreteItemManager implements ItemManager {
	private Collection<Item> items;
	private Collection<Item> removedItems;
	private Map<Product, Collection<Item>> productsToItems;
	
	public ConcreteItemManager() {
		items = new ArrayList<Item>();
		removedItems = new TreeSet<Item>();
		productsToItems = new TreeMap<Product, Collection<Item>>();
	}
	
	/** Adds the given item to this Manager's indexes
	 * @param item Item to manage
	 */
	@Override
	public void manage(Item item) {
		items.add(item);
	}
	
	/** Adds the specified item to a productContainer
	 * @param item		The Item to add
	 * @param container	The ProductContainer to add the Item to
	 * 
	 * @pre item != null
	 * @post !container.contains(item)
	 */
	@Override
	public void unmanage(Item item) {
		assert(item != null);
		item.remove();
		removedItems.add(item);
	}	

	/** Returns an Iterator to access all of the removed items.
	 * @return	an Iterator allowing access to all of the removed Items
	 */
	public Iterator<Item> removedItemsIterator() {
		return removedItems.iterator();
	}

	/** Determines whether a given product has items in the system.
	 * @param product		The Product to test
	 * @return				true if the specified product has items that use it, false otherwise
	 * 
	 * @pre product != null
	 * @post true
	 */
	public boolean productHasItems(Product product) {
		return productsToItems.get(product).isEmpty();
	}

}
