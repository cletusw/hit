package model;

import java.util.Collection;
import java.io.*;

/** HomeInventoryTracker: Home�Inventory�Tracker�(HIT)�is�a�system�for�tracking�home�storage�inventories. 
 * @author Seth Stewart
 * @version 1.0
 */
@SuppressWarnings("serial")
public class HomeInventoryTracker implements Serializable {
	private Collection<StorageUnit> rootStorageUnits;
	private Collection<Item> removedItems;
	private ItemManager itemManager;
	
	/** Initializes the HomeInventoryTracker. */
	public HomeInventoryTracker() {
		itemManager = new ItemManager();
	}
	
	/** Determines whether the specified Storage Unit name is valid for adding a new Storage Unit.
	 * 
	 * @param name 	The name to be tested
	 * @return true if name is valid, false otherwise
	 */
	public boolean isValidStorageUnitName(String name) {
		// From the Data Dictionary: Must be non-empty. Must be unique among all Storage Units.
		return true;
	}
	
	/** Deletes the identified Product from the home inventory system.
	 * @param product		The Product to be deleted
	 * @return				true if the product was deleted, false otherwise.
	 */
	public boolean deleteProduct(Product product) {
		// @TODO: Implement me!
		return false;
	}
	
	/** Adds the identified Item to a given ProductContainer
	 * @param item			The Item to be added
	 * @param container		The ProductContainer you are adding the Item to
	 * @return				true if the item was added to the container, false otherwise.
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
	
}