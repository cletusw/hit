package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Implements persistent storage using Java object serialization.
 * @author Seth Stewart
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * @invariant true
 */
public class SerializationManager implements PersistentStorageManager {
	public static final String defaultSerializedFileName = "HomeInventoryTracker.ser";
	
	/** Creates a new HomeInventoryTracker, reading it from persistent storage if available. Otherwise, it creates a new empty instance of the class. 
	 * @return a new instance of HomeInventoryTracker
	 * @pre true
	 * @post true
	 */
	public static HomeInventoryTracker create(String filename) {
		String target = assignTargetFilename(filename);
		
		HomeInventoryTracker tracker;
		PersistentStorageManager persistentStorageManager = new SerializationManager();
		try {
			tracker = persistentStorageManager.readObject(target);
		}
		catch(IOException e) {
			tracker = new HomeInventoryTracker();
		}
		return tracker;
	}
	
	/** Deserializes the Home Inventory Tracker from a file.
	 * @throws 	IOException if an error occurred reading from the serialized file.
	 * @pre true
	 * @post true
	 */
	public HomeInventoryTracker readObject(String filename) throws IOException {
		String target = assignTargetFilename(filename);
		
		HomeInventoryTracker hit;
		try {
			FileInputStream fileInputStream = new FileInputStream(target);
			ObjectInputStream objectReader = new ObjectInputStream(fileInputStream);
			hit = (HomeInventoryTracker) objectReader.readObject();
			objectReader.close();
		}
		catch (ClassNotFoundException e) {
			System.err.println("Could not locate class for deserialization");
			e.printStackTrace();
			return null;
		}
		return hit;
	}
	
	/** Serializes the Home Inventory Tracker to a file.
	 * @throws 	IOException if an error occurred writing to the serialized file.
	 * @pre hit != null
	 * @post true
	 */
	public void writeObject(HomeInventoryTracker hit,String filename) throws IOException {
		assert(hit != null);
		
		String target = assignTargetFilename(filename);

		FileOutputStream fileOutputStream = new FileOutputStream(target);
		ObjectOutputStream objectWriter = new ObjectOutputStream(fileOutputStream);
		
		objectWriter.writeObject(hit);
		objectWriter.flush();
		objectWriter.close();
	}
	
	private static String assignTargetFilename(String filename) {
		String target = null;
		if(filename == null || filename.equals(""))
			target = defaultSerializedFileName;
		else
			target = filename;
		
		return target;
	}
}
