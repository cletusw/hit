package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Action.ActionType;

import common.ObservableWithPublicNotify;

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
public class ConcreteItemManager extends ObservableWithPublicNotify implements ItemManager,
		Serializable {
	private final Set<Item> items;
	private final Map<Product, Set<Item>> removedItems;
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
		removedItems = new TreeMap<Product, Set<Item>>();
		productsToItems = new TreeMap<Product, Set<Item>>();
	}

	@Override
	public void editItem(Item item, Date newEntryDate) {
		items.remove(item);
		Set<Item> localItems = productsToItems.remove(item.getProduct());
		localItems.remove(item);
		item.setEntryDate(newEntryDate);
		localItems.add(item);
		productsToItems.put(item.getProduct(), localItems);
		items.add(item);

		notifyObservers(new Action(item, ActionType.EDIT));
	}

	@Override
	/**
	 * Method that retrieves all items in the system by the item's barcode
	 * 
	 * @param barcode the barcode to match item barcodes with
	 * @throws IllegalArgumentException if the barcode parameter is null
	 * 
	 * @pre for(Item item : items) item.getBarcode() != null
	 * @post true
	 */
	public Item getItemByItemBarcode(String barcode) {
		if (barcode == null)
			throw new IllegalArgumentException("Comparator barcode should not be null");

		Iterator<Item> it = items.iterator();
		while (it.hasNext()) {
			Item current = it.next();
			String currentBarcode = current.getBarcode();
			if (currentBarcode == null)
				throw new NullPointerException("Item should never have a null barcode");

			if (currentBarcode.equals(barcode))
				return current;
		}

		return null;
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

		Set<Item> items = productsToItems.get(product);
		if (items == null) {
			items = new TreeSet<Item>();
		}

		return items;
	}

	/**
	 * Gets all of the removed items in the system.
	 * 
	 * @return an *unmodifiable* Set of all of the removed Items
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public Map<Product, Set<Item>> getRemovedItemsByProduct() {
		return Collections.unmodifiableMap(removedItems);
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

		notifyObservers(new Action(item, ActionType.CREATE));
	}

	/**
	 * 
	 * @param item
	 * 
	 * @pre item != null
	 * @pre removedItems.contains(item)
	 * @post !removedItems.contains(item)
	 * @post items.contains(item)
	 * @post productsToItems.contains(item.getBarcode())
	 */
	@Override
	public void remanage(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Null Item item");
		}

		if (removedItems.get(item.getProduct()) == null
				|| !removedItems.get(item.getProduct()).contains(item))
			throw new IllegalStateException("removedItems does not contain this item");

		removedItems.get(item.getProduct()).remove(item);
		manage(item);

		notifyObservers(new Action(item, ActionType.CREATE));
	}

	/**
	 * Undo the management of an Item without adding it to the RemovedItems list.
	 * 
	 * @param item
	 *            the Item to unmanage
	 * @pre true
	 * @post !items.contains(item) && !removedItems.contains(item);
	 */
	@Override
	public void undoManage(Item item) {
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
		notifyObservers(new Action(item, ActionType.DELETE));
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
		undoManage(item);
		Set<Item> items = new TreeSet<Item>();
		if (removedItems.containsKey(item.getProduct()))
			items = removedItems.get(item.getProduct());
		items.add(item);
		removedItems.put(item.getProduct(), items);

		notifyObservers(new Action(item, ActionType.DELETE));
	}

}
