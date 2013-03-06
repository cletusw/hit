package test.model;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import model.HomeInventoryTracker;
import model.PersistentStorageManager;
import model.ProductContainerManager;
import model.SerializationManager;
import model.StorageUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SerializationManagerTest {
	private static final String testTarget = "TestHITTracker.ser";

	@Before
	public void setUp() throws Exception {
		deleteDefaultFile();
	}

	@After
	public void tearDown() throws Exception {
		deleteDefaultFile();
	}

	@Test
	public void test() throws IOException {
		// Create instance of HIT
		HomeInventoryTracker hit = new HomeInventoryTracker();
		ProductContainerManager pcManager = hit.getProductContainerManager();

		String name1 = "StorageUnit1";
		String name2 = "StorageUnit2";
		new StorageUnit(name1, pcManager);
		new StorageUnit(name2, pcManager);
		assertTrue(pcManager.getStorageUnitByName(name1) != null);
		assertTrue(pcManager.getStorageUnitByName(name2) != null);

		PersistentStorageManager persistentStorageManager = new SerializationManager(
				testTarget);
		persistentStorageManager.save(hit);

		hit = null;
		pcManager = null;

		hit = persistentStorageManager.load();
		pcManager = hit.getProductContainerManager();
		assertTrue(pcManager.getStorageUnitByName(name1) != null);
		assertTrue(pcManager.getStorageUnitByName(name2) != null);
	}

	private void deleteDefaultFile() {
		File f = new File(testTarget);
		if (f.exists()) {
			f.delete();
		}
	}

}
