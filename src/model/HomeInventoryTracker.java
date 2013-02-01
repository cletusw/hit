package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.io.*;

/** HomeInventoryTracker: Home Inventory Tracker (HIT) is a system for tracking home storage inventories. 
 * @author Seth Stewart
 * @version 1.0
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
	 * @post 
	 */
	public boolean isValidStorageUnitName(String name) {
		// From the Data Dictionary: Must be non-empty. Must be unique among all Storage Units.
		return true;
	}
	
	/** Deletes the identified Product from the home inventory system.
	 * @param product		The Product to be deleted
	 * @return				true if the product was deleted, false otherwise.
	 * 
	 * @pre product != null
	 * @post !containsProduct(product)
	 */
	public boolean deleteProduct(Product product) {
		// @TODO: Implement me!
		return false;
	}
	
	/**
	 * Checks if the identified Product exists in the home inventory system.
	 * @param product 		The Product to check
	 * @return				true if the product exists in the home inventory system, false otherwise.
	 * 
	 * @pre product != null
	 * @post  
	 */
	public boolean containsProduct(Product product) {
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
	public boolean addItem(Item item, ProductContainer container) {
		// @TODO: Implement me!
		//container.add(item);
		return false;
	}
	
	/** Removes the identified Item from a given ProductContainer
	 * @param item			The Item to be removed
	 * @param container		The ProductContainer to remove the item from
	 * @return				true if the item was removed from the container, false otherwise.
	 * 
	 * @pre item != null
	 * @post !containsItem(item)
	 */
	public boolean removeItem(Item item) {
		// @TODO: Implement me!
		//container.remove(item);
		return false;	
	}
	
	/** Moves the identified Item from one ProductContainter to another
	 * @param source		The ProductContainer you are moving the item from
 	 * @param destination	The ProductContainer you are moving the item to
	 * @param item			The Item to be moved
	 * @return				true if the item was moved from the source container to the destination, false otherwise.
	 */
	public boolean moveItem(ProductContainer source, ProductContainer destination, Item item) {
		// @TODO: Implement me!
		return false;
	}
	
	/** Adds the identified Product to a given ProductContainer
	 * @param source			The ProductContainer to add the Product to
	 * @param product			The Product to be added
	 * @return					true if the item was added to the container, false otherwise.
	 */
	public boolean addProductToContainer(ProductContainer source, Product product) {
		// @TODO: Implement me!
		return false;
	}
	
	public void remove(Item item) {
		
	}
	
	public void remove(Product product) {
		
	}
	
	public void remove(ProductContainer container) {
	
	}
	
	// DO we need these? (And for Items?)
	public boolean canRemoveProduct(Product product) {
		return true;
	}
	
	public boolean canDeleteProductContainer(ProductContainer container) {
		return true;
	}
	
	public boolean canAddStorageUnit(String storageUnitName) {
		// Must be unique
		return true;
	}
	
}
