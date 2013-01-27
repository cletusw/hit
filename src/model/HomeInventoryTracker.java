package model;

import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;

/** HomeInventoryTracker: Home Inventory Tracker (HIT) is a system for tracking home storage inventories. 
 * @author Seth Stewart
 * @version 1.0
 */
public class HomeInventoryTracker implements Serializable {
	private static final long serialVersionUID = 0;
	private Collection<StorageUnit> rootStorageUnits;
	private Collection<Item> removedItems;
	private static final String defaultSerializedFileName = "HomeInventoryTracker.ser";
	private String serializedFileName;
	private static HomeInventoryTracker instance;
	
	/** Singleton access method: Returns the instance of the HomeInventoryTracker.
	 * 
	 * @return 			the instance of HomeInventoryTracker
	 */
	public static HomeInventoryTracker getInstance() {
		if (instance == null)
			instance = new HomeInventoryTracker();
		return instance;
	}
	
	/** Initializes the HomeInventoryTracker. */
	private HomeInventoryTracker() {
		serializedFileName = defaultSerializedFileName;
		try {
			deserialize();
		}
		catch(IOException e) {
			rootStorageUnits = new ArrayList<StorageUnit>();
			removedItems = new TreeSet<Item>();
		}
	}
	
	/** Deserializes the Home Inventory Tracker from a file.
	 * @throws 	IOException if an error occurred reading from the serialized file.
	 */
	public void deserialize() throws IOException {
		try {
			FileInputStream fileInputStream = new FileInputStream(serializedFileName);
			ObjectInputStream objectReader = new ObjectInputStream(fileInputStream);
			//instance = (HomeInventoryTracker) objectReader.readObject(); // Static object cannot be written or read
			rootStorageUnits = (Collection<StorageUnit>) objectReader.readObject();
			removedItems = (Collection<Item>) objectReader.readObject();
			objectReader.close();
		}
		//catch (IOException e) {
		//	System.err.println("Could not open the serialized file " + serializedFileName + " for reading");
		//	e.printStackTrace();
		//}
		catch (ClassNotFoundException e) {
			System.err.println("Could not locate class for deserialization");
			e.printStackTrace();
		}
	}
	
	/** Serializes the Home Inventory Tracker to a file.
	 * @throws 	IOException if an error occurred writing to the serialized file.
	 */
	public void serialize() throws IOException {
		//try {
			FileOutputStream fileOutputStream = new FileOutputStream(serializedFileName);
			ObjectOutputStream objectWriter = new ObjectOutputStream(fileOutputStream);
			//objectWriter.writeObject(instance);
			objectWriter.writeObject(rootStorageUnits);
			objectWriter.writeObject(removedItems);
			objectWriter.flush();
			objectWriter.close();
		//}
		//catch (IOException e) {
		//	System.err.println("Error writing to serialization file " + serializedFileName);
		//	e.printStackTrace();
		//}
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