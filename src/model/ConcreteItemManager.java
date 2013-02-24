package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Action.ActionType;

/**
 * Class that manages Items
 * 
 * @author Clayton Watts
 * @version 1.0 -- CS 340 Snell Phase 1
 * 
 * @invariant items != null
 * @invariant removedItems != null
 * @invariant productsToItems != null
 * 
 */
@SuppressWarnings("serial")
public class ConcreteItemManager extends Observable implements ItemManager, Serializable {
	private final Set<Item> items;
	private final Set<Item> removedItems;
	private final Map<Product, Set<Item>> productsToItems;

	/**
	 * Constructor
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public ConcreteItemManager() {
		items = new TreeSet<Item>();
		removedItems = new TreeSet<Item>();
		productsToItems = new TreeMap<Product, Set<Item>>();
	}

	/**
	 * Gets all the items of a given Product from the system
	 * 
	 * @param product
	 *            The Product whose Items we want
	 * @return Set<Item> All the items in the system of Product product
	 * 
	 * @pre product != null
	 */
	@Override
	public Set<Item> getItemsByProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Null Product product");
		}

		return productsToItems.get(product);
	}

	/**
	 * Adds the given item to this Manager's indexes
	 * 
	 * @param item
	 *            Item to manage
	 * 
	 * @pre item != null
	 * @post items.contains(item)
	 * @post productsToItems.get(item.getProduct()).contains(item)
	 */
	@Override
	public void manage(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Null Item item");
		}

		// System.out.println("Managing item " + item.getBarcode() + " "
		// + item.getProduct().getDescription());

		Set<Item> found = productsToItems.get(item.getProduct());
		if (found != null)
			found.add(item);
		else {
			TreeSet<Item> prItems = new TreeSet<Item>();
			prItems.add(item);
			productsToItems.put(item.getProduct(), prItems);
		}

		items.add(item);

		setChanged();
		Action action = new Action(item, ActionType.CREATE);
		this.notifyObservers(action);
	}

	/**
	 * Returns an Iterator to access all of the removed items.
	 * 
	 * @return an Iterator allowing access to all of the removed Items
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public Iterator<Item> removedItemsIterator() {
		return removedItems.iterator();
	}

	/**
	 * Adds the specified item to a productContainer
	 * 
	 * @param item
	 *            The Item to add
	 * @param container
	 *            The ProductContainer to add the Item to
	 * 
	 * @pre item != null
	 * @pre items.contains(item)
	 * @pre productsToItems.get(item.getProduct()).contains(item)
	 * @post removedItems.contains(item)
	 * @post !items.contains(item)
	 * 
	 */
	@Override
	public void unmanage(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Item to unmanage can't be null.");
		// System.out.println("Unmanaging item " + item.getBarcode() + " "
		// + item.getProduct().getDescription());
		if (!items.contains(item) || !productsToItems.get(item.getProduct()).contains(item)) {
			throw new IllegalStateException("unmanage() being called before manage()");
		}

		items.remove(item);

		Set<Item> found = productsToItems.get(item.getProduct());
		found.remove(item);
		if (found.isEmpty())
			productsToItems.remove(item.getProduct());

		item.remove();
		removedItems.add(item);
		setChanged();
		Action action = new Action(item, ActionType.DELETE);
		this.notifyObservers(action);
	}

}
