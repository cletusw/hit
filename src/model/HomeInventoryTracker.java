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
	private Collection<Item> removedItems;
	private ItemManager itemManager;
	private ProductManager productManager;
	
	/** Initializes the HomeInventoryTracker. 
	 * @pre true
	 * @post removedItems.size() == 0
	 * @post rootStorageUnits.size() == 0
	 */
	public HomeInventoryTracker() {
		rootStorageUnits = new ArrayList<StorageUnit>();
		removedItems = new TreeSet<Item>();
		itemManager = new ConreteItemManager();
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
		return true;
	}
	
	/** Removes the identified Item from a given ProductContainer
	 * @param item			The Item to be removed
	 * @param container		The ProductContainer to remove the item from
	 * @return				true if the item was removed from the container, false otherwise.
	 * 
	 * @pre item != null
	 * @post !containsItem(item)
	 */
	public boolean remove(Item item) {
		// @TODO: Implement me!
		//container.remove(item);
		return false;	
	}
	
	/** Moves the identified Item from one ProductContainer to another
	 * @param source		The ProductContainer you are moving the item from
 	 * @param destination	The ProductContainer you are moving the item to
	 * @param item			The Item to be moved
	 * @return				true if the item was moved from the source container to the destination, false otherwise.
	 * 
	 * @pre source != null && destination != null && item != null
	 * @pre source.contains(item)
	 * @post destination.contains(item) && !source.contains(item)
	 */
	public boolean move(ProductContainer source, ProductContainer destination, Item item) {
		assert(source != null);
		assert(destination != null);
		assert(item != null);
		//assert(source.contains(item));
		
		// @TODO: Implement me!
		return false;
	}
	
	/** Deletes the identified Product from the home inventory system.
	 * @param product		The Product to be deleted
	 * @return				true if the product was deleted, false otherwise.
	 * 
	 * @pre product != null
	 * @post !containsProduct(product)
	 */
	public boolean remove(Product product) {
		// @TODO: Implement me!
		return false;
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
		// Ask the Product Manager!
		return false;
	}
	
	/** Adds the identified Item to a given ProductContainer
	 * @param item			The Item to be added
	 * @param container		The ProductContainer you are adding the Item to
	 * @return				true if the item was added to the container, false otherwise.
	 * 
	 * @pre item != null && container != null
	 * @post container.contains(item)
	 */
	public boolean add(Item item, ProductContainer container) {
		// @TODO: Implement me!
		//container.add(item);
		return false;
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
	
	// DO we need these? (And for Items?)
	public boolean canRemove(Product product) {
		return true;
	}
	
	public void remove(ProductContainer container) {
	
	}
	
	public boolean canDeleteProductContainer(ProductContainer container) {
		return true;
	}
	
	public boolean canAddStorageUnit(String storageUnitName) {
		// Must be unique
		return true;
	}
	
}
