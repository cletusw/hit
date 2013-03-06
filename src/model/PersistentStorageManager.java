package model;

import java.io.IOException;

/**
 * Defines an interface for classes that support read/write persistence operations for the
 * HomeInventoryTracker class
 * 
 * @author Seth Stewart
 * @version 1.0 - Snell 340 Group 4 Phase 1
 */
public interface PersistentStorageManager {
	/**
	 * Creates a new HomeInventoryTracker, reading it from persistent storage if available.
	 * Otherwise, it creates a new empty instance of the class.
	 * 
	 * @return a new instance of HomeInventoryTracker
	 * @pre true
	 * @post true
	 */
	public HomeInventoryTracker load();

	/**
	 * Persists the given Home Inventory Tracker to storage.
	 * 
	 * @param hit
	 *            the instance of HomeInventoryTracker to store
	 * @throws IOException
	 *             if an error occurred writing to the serialized file.
	 * 
	 * @pre hit != null
	 * @post true
	 */
	public void save(HomeInventoryTracker hit) throws IOException;
}
