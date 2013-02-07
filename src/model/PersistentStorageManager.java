package model;

import java.io.IOException;
/**
 * Defines an interface for classes that support read/write persistence operations 
 * for the HomeInventoryTracker class
 * @author Seth Stewart
 * @version 1.0 - Snell 340 Group 4 Phase 1
 */
public interface PersistentStorageManager {

	/**
	 * Reads a HomeInventoryTracker from persistent storage.
	 * @param filename The filename of the file to read.
	 * @return an instance of the HomeInventoryTracker class
	 * @throws IOException if the read failed.
	 */
	public HomeInventoryTracker readObject(String filename) throws IOException;
	
	/**
	 * Writes the specified HomeInventoryTracker to persistent storage.
	 * @param hit the instance of HomeInventoryTracker to store
	 * @param filename the String filename to write to
	 * @throw IOException if the write failed.
	 */
	public void writeObject(HomeInventoryTracker hit,String filename) throws IOException;
}
