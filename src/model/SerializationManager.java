package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Implements persistent storage using Java object serialization.
 */
public class SerializationManager implements PersistentStorageManager {
	private static final String defaultSerializedFilename = "HomeInventoryTracker.ser";

	private String serializedFilename;

	public SerializationManager() {
		serializedFilename = defaultSerializedFilename;
	}

	public SerializationManager(String serializedFilename) {
		this.serializedFilename = serializedFilename;
	}

	@Override
	public HomeInventoryTracker load() {
		HomeInventoryTracker hit;

		try {
			FileInputStream fileInputStream = new FileInputStream(serializedFilename);
			ObjectInputStream objectReader = new ObjectInputStream(fileInputStream);
			hit = (HomeInventoryTracker) objectReader.readObject();
			objectReader.close();
		} catch (Exception e) {
			hit = new HomeInventoryTracker();
		}

		return hit;
	}

	@Override
	public void save(HomeInventoryTracker hit) throws IOException {
		if (hit == null) {
			throw new NullPointerException("Null HomeInventoryTracker hit");
		}

		FileOutputStream fileOutputStream = new FileOutputStream(serializedFilename);
		ObjectOutputStream objectWriter = new ObjectOutputStream(fileOutputStream);

		objectWriter.writeObject(hit);
		objectWriter.flush();
		objectWriter.close();
	}
}
