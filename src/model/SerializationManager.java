package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

/**
 * Implements persistent storage using Java object serialization.
 * @author Seth Stewart
 * @version 1.0 - Snell 340 Group 4 Phase 1
 */
public class SerializationManager implements PersistentStorageManager {
	public static final String defaultSerializedFileName = "HomeInventoryTracker.ser";
	
	/** Creates a new HomeInventoryTracker, reading it from persistent storage if available. Otherwise, it creates a new empty instance of the class. 
	 * @return a new instance of HomeInventoryTracker
	 *  
	 */
	public static HomeInventoryTracker create() {
		HomeInventoryTracker tracker;
		PersistentStorageManager persistentStorageManager = new SerializationManager();
		try {
			tracker = persistentStorageManager.readObject();
		}
		catch(IOException e) {
			tracker = new HomeInventoryTracker();
		}
		return tracker;
	}
	
	/** Deserializes the Home Inventory Tracker from a file.
	 * @throws 	IOException if an error occurred reading from the serialized file.
	 */
	public HomeInventoryTracker readObject() throws IOException {
		HomeInventoryTracker hit;
		try {
			FileInputStream fileInputStream = new FileInputStream(defaultSerializedFileName);
			ObjectInputStream objectReader = new ObjectInputStream(fileInputStream);
			//instance = (HomeInventoryTracker) objectReader.readObject(); // Static object cannot be written or read
			hit = (HomeInventoryTracker) objectReader.readObject();
			objectReader.close();
		}
		//catch (IOException e) {
		//	System.err.println("Could not open the serialized file " + serializedFileName + " for reading");
		//	e.printStackTrace();
		//}
		catch (ClassNotFoundException e) {
			System.err.println("Could not locate class for deserialization");
			e.printStackTrace();
			return null;
		}
		return hit;
	}
	
	/** Serializes the Home Inventory Tracker to a file.
	 * @throws 	IOException if an error occurred writing to the serialized file.
	 */
	public void writeObject(HomeInventoryTracker hit) throws IOException {
		//try {
			FileOutputStream fileOutputStream = new FileOutputStream(defaultSerializedFileName);
			ObjectOutputStream objectWriter = new ObjectOutputStream(fileOutputStream);
			//objectWriter.writeObject(instance);
			objectWriter.writeObject(hit);
			objectWriter.flush();
			objectWriter.close();
		//}
		//catch (IOException e) {
		//	System.err.println("Error writing to serialization file " + serializedFileName);
		//	e.printStackTrace();
		//}
	}
}
