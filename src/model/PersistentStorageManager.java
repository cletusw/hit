package model;

import java.io.IOException;
/**
 * Defines an interface for classes that support read/write persistence operations for the HomeInventoryTracker class
 * @author Seth Stewart
 * @version 1.0 - Snell 340 Group 4 Phase 1
 */
public interface PersistentStorageManager {

	/**
	 * Writes the specified HomeInventoryTracker to persistent storage.
	 * @param hit the instance of HomeInventoryTracker to store
	 * @throw IOException if the write failed.
	 */
	public void writeObject(HomeInventoryTracker hit) throws IOException;
	
	/**
	 * Reads a HomeInventoryTracker from persistent storage.
	 * @return an instance of the HomeInventoryTracker class
	 * @throws IOException if the read failed.
	 */
	public HomeInventoryTracker readObject() throws IOException;
}
