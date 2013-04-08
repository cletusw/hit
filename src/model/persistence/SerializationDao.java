package model.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;

import model.HomeInventoryTracker;

/**
 * On application close, persists the model by serializing the HomeInventoryTracker class
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 */
public class SerializationDao extends InventoryDao {

	private static final String defaultSerializedFilename = "HomeInventoryTracker.ser";

	private final String serializedFilename;

	public SerializationDao() {
		serializedFilename = defaultSerializedFilename;
	}

	public SerializationDao(String serializedFilename) {
		this.serializedFilename = serializedFilename;
	}

	@Override
	public void applicationClose(HomeInventoryTracker hit) throws IOException {
		if (hit == null) {
			throw new NullPointerException("Null HomeInventoryTracker hit");
		}

		FileOutputStream fileOutputStream = new FileOutputStream(serializedFilename);
		ObjectOutputStream objectWriter = new ObjectOutputStream(fileOutputStream);

		objectWriter.writeObject(hit);
		objectWriter.flush();
		objectWriter.close();
	}

	@Override
	public HomeInventoryTracker loadHomeInventoryTracker() {
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
	public void update(Observable arg0, Object arg1) {
		// If I were a rich man...
	}

}