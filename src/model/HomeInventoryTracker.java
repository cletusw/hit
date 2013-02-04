package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.io.*;

/** HomeInventoryTracker: Home Inventory Tracker (HIT) is a system for tracking home storage inventories. 
 * @author Seth Stewart
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * 
 * @invariant removedItems != null
 * @invariant rootStorageUnits != null
 * @invariant itemManager != null
 * @invariant productManager != null
 */
@SuppressWarnings("serial")
public class HomeInventoryTracker implements Serializable {
	private Collection<StorageUnit> rootStorageUnits;
	private ItemManager itemManager;
	private ProductManager productManager;
	
	/** Initializes the HomeInventoryTracker. 
	 * @pre true
	 * @post removedItems.size() == 0
	 * @post rootStorageUnits.size() == 0
	 */
	public HomeInventoryTracker() {
		rootStorageUnits = new ArrayList<StorageUnit>();
		itemManager = new ConcreteItemManager();
		productManager = new ConcreteProductManager();
	}
	
	/**
	 * Writes this instance of HomeInventoryTracker to persistent storage.
	 * @throws IOException if the write failed.
	 * @pre true
	 * @post true
	 */
	public void write() throws IOException {
		PersistentStorageManager persistentStorageManager = new SerializationManager();
		persistentStorageManager.writeObject(this);
	}
	
	/** Determines whether the specified Storage Unit name is valid for adding a new Storage Unit.
	 * 
	 * @param name 	The name to be tested
	 * @return true if name is valid, false otherwise
	 * 
	 * @pre true
	 * @post true
	 */
	public boolean isValidStorageUnitName(String name) {
		// From the Data Dictionary: Must be non-empty. Must be unique among all Storage Units.
		return !name.equals("") && !rootStorageUnits.contains(new StorageUnit(name));
	}
	
	/** Adds the identified Item to a given ProductContainer
	 * @param item			The Item to be added
	 * @param container		The ProductContainer you are adding the Item to
	 * 
	 * @pre item != null && container != null
	 * @post container.contains(item)
	 */
	public void add(Item item, ProductContainer container) {
		container.add(item);
	}
	
	/** Removes the identified Item from a given ProductContainer
	 * @param item			The Item to be removed
	 * @param container		The ProductContainer to remove the item from
	 * 
	 * @pre item != null && container != null
	 * @post !containsItem(item)
	 */
	public void remove(Item item, ProductContainer container) {
		container.remove(item, itemManager);
	}
	
	/** Moves the identified Item from one ProductContainer to another
	 * @param source		The ProductContainer you are moving the item from
 	 * @param destination	The ProductContainer you are moving the item to
	 * @param item			The Item to be moved
	 * 
	 * @pre source != null && destination != null && item != null
	 * @pre source.contains(item)
	 * @post destination.contains(item) && !source.contains(item)
	 */
	public void move(ProductContainer source, ProductContainer destination, Item item) {
		assert(source != null);
		assert(destination != null);
		assert(item != null);
		assert(source.contains(item));
		source.remove(item, null);
		destination.add(item);
	}
	
	/** Deletes the identified Product from the home inventory system.
	 * @param product		The Product to be deleted
	 * 
	 * @pre product != null
	 * @post !contains(product)
	 */
	public void remove(Product product) throws IllegalStateException {
		if (!canRemove(product))
			throw new IllegalStateException("Cannot remove product from the system; it still has items that refer to it");
		productManager.unmanage(product);
		for (StorageUnit storageUnit:rootStorageUnits) {
			storageUnit.remove(product);
		}
	}
	
	/** Deletes the identified Product from the home inventory system.
	 * @param product		The Product to be deleted
	 * 
	 * @pre product != null
	 * @post !contains(product)
	 */
	public void removeFromContainer(Product product, ProductContainer container) throws IllegalStateException {
		if (!canRemove(product))
			throw new IllegalStateException("Cannot remove product from the system; it still has items that refer to it");
		//TODO
		//productManager.remove(product);
	}
	
	/**
	 * Determines whether the specified product can be removed.
	 * @param product		The Product to test
	 * @return				true if the Product can be safely removed, false otherwise.
	 * 
	 * @pre product != null
	 * @post true
	 */
	public boolean canRemove(Product product) {
		// From the Data Dictionary: A Product can be removed from the system only if
		//    the system contains no Items of the Product
		return !itemManager.productHasItems(product);
	}
	
	/**
	 * Checks if the identified Product exists in the home inventory system.
	 * @param product 		The Product to check
	 * @return				true if the product exists in the home inventory system, false otherwise.
	 * 
	 * @pre product != null
	 * @post true
	 */
	public boolean contains(Product product) {
		return productManager.contains(product);
	}
	
	/** Adds the identified Product to a given ProductContainer
	 * @param source			The ProductContainer to add the Product to
	 * @param product			The Product to be added
	 * @return					true if the item was added to the container, false otherwise.
	 * 
	 * @pre source != null && product != null
	 * @post source.contains(product)
	 */
	public boolean addProductToContainer(ProductContainer source, Product product) {
		assert(source != null);
		assert(product != null);
		// @TODO: Implement me!
		return false;
	}
	
	/**
	 * Removes the specified ProductContainer.
	 * @param container		The ProductContainer to remove
	 * @pre container != null
	 * @post !contains(container)
	 */
	public void remove(ProductContainer container) {
	
	}
	
	/**
	 * Determines whether the specified ProductContainer can be removed.
	 * @param container		The ProductContainer to test
	 * @return				true if it is safe to remove the container, false otherwise.
	 * 
	 * @pre container != null
	 * @post true
	 */
	public boolean canRemove(ProductContainer container) {
		return container.getItemsSize() == 0;
	}
	
	/**
	 * Determines whether a storage unit with the given name can be added to the system.
	 * @param storageUnitName	the name of the storage unit to test
	 * @return	 				true if it can be added, false otherwise
	 * 
	 * @pre storageUnitName != null
	 * @post true
	 */
	public boolean canAddStorageUnit(String storageUnitName) {
		// Must be unique
		return true;
	}
	
}
