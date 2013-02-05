package model;

import java.util.Date;
import java.io.*;

/** HomeInventoryTracker: Home Inventory Tracker (HIT) is a system for tracking home storage inventories. 
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
	private ItemManager itemManager;
	private ProductManager productManager;
	private StorageUnitManager storageUnitManager;
	
	/** Initializes the HomeInventoryTracker. 
	 * @pre true
	 * @post itemManager != null
	 * @post productManager != null
	 */
	public HomeInventoryTracker() {
		itemManager = new ConcreteItemManager();
		productManager = new ConcreteProductManager();
		storageUnitManager = new StorageUnitManager();
	}
	
	/**
	 * Writes this instance of HomeInventoryTracker to persistent storage.
	 * 
	 * @param filename The filename to write to.
	 * @throws IOException if the write failed.
	 * 
	 * @pre filename != null
	 * @pre !filename.equals("")
	 * @post File f(filename).exists()
	 */
	public void write(String filename) throws IOException {
		PersistentStorageManager persistentStorageManager = new SerializationManager();
		persistentStorageManager.writeObject(this,filename);
	}
	
	/** Determines whether the specified Storage Unit name is valid for adding a new Storage Unit.
	 * 
	 * @param name 	The name to be tested
	 * @return true if name is valid, false otherwise
	 * 
	 * @pre name != null
	 * @post true
	 */
	public boolean isValidStorageUnitName(String name) {
		return storageUnitManager.isValidStorageUnitName(name);
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
	public void remove(Item item, ProductContainer container) throws IllegalStateException {
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
		storageUnitManager.remove(product);
	}
	
	/** Removes the identified Product from the specified ProductContainer
	 * @param product		The Product to be removed
	 * @param container		The ProductContainer to remove the Product from
	 * 
	 * @pre product != null
	 * @post !contains(product)
	 */
	public void removeFromContainer(Product product, ProductContainer container) throws IllegalStateException {
		container.remove(product);
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
	 * @param container			The ProductContainer to add the Product to
	 * @param product			The Product to be added
	 * @return					true if the item was added to the container, false otherwise.
	 * 
	 * @pre container != null
	 * @pre product != null
	 * @post container.contains(product)
	 */
	public void addProductToContainer(Product product, ProductContainer container) {
		assert(container != null);
		assert(product != null);
		container.add(product);
	}
	
	/** Removes a ProductGroup from the system
	 * 
	 * @param productGroup the ProductGroup to remove
	 * 
	 * @pre productGroup != null
	 * @pre productGroup.canRemove()
	 */
	public void remove(ProductGroup productGroup) {
		storageUnitManager.remove(productGroup);
	}
	
	/**
	 * Removes a Storage Unit from the system
	 * 
	 * @param storageUnit The Storage Unit to remove
	 * 
	 * @pre storageUnit != null
	 * @pre storageUnit.canRemove()
	 */
	public void remove(StorageUnit storageUnit) {
		storageUnitManager.remove(storageUnit);
	}
	
	/**
	 * Determines whether a storage unit with the given name can be added to the system.
	 * @param storageUnitName	the name of the storage unit to test
	 * @return	 				true if it can be added, false otherwise
	 * 
	 * @pre true
	 * @post true
	 */
	public boolean canAddStorageUnit(String storageUnitName) {
		return storageUnitManager.isValidStorageUnitName(storageUnitName);
	}

	/**
	 * Add a Storage Unit to the system with the specified name
	 * @param storageUnitName Name of the Storage Unit to create
	 * 
	 * @pre canAddStorageUnit(storageUnitName)
	 * @post true
	 */
	public void addStorageUnit(String storageUnitName) {
		assert(canAddStorageUnit(storageUnitName));
		
		storageUnitManager.add(storageUnitName);
	}

	/**
	 * Rename a Storage Unit
	 * @param storageUnitName Name of the Storage Unit to rename
	 * @param newStorageUnitName New name to be given to Storage Unit
	 * 
	 * @pre canAddStorageUnit(newStorageUnitName)
	 * @post true
	 */
	public void renameStorageUnit(String storageUnitName, String newStorageUnitName) {
		assert(canAddStorageUnit(newStorageUnitName));
		
		storageUnitManager.renameStorageUnit(storageUnitName, newStorageUnitName);
	}

	public Product getProductByBarcode(String barcodeScanned) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product createProduct(String barcode, String description, int shelfLife, int threeMonthSupply, ProductQuantity productQuantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addItem(Product product, Date entryDate, String newStorageUnitName) {
		// TODO Auto-generated method stub
		
	}
	
}
