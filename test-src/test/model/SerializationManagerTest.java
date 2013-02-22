package test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import model.HomeInventoryTracker;
import model.ProductContainerManager;
import model.SerializationManager;
import model.StorageUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SerializationManagerTest {
	private String testTarget = "TestHITTracker.ser";

	private void deleteDefaultFile() {
		File f = new File(testTarget);
		if (f.exists()) {
			f.delete();
		}
	}

	@Before
	public void setUp() throws Exception {
		deleteDefaultFile();
	}

	@After
	public void tearDown() throws Exception {
		deleteDefaultFile();
	}

	@Test
	public void test() {
		// Create instance of HIT
		HomeInventoryTracker hit = SerializationManager.create("");
		ProductContainerManager pcManager = hit.getProductContainerManager();

		String name1 = "StorageUnit1";
		String name2 = "StorageUnit2";
		new StorageUnit(name1, pcManager);
		new StorageUnit(name2, pcManager);

		try {
			hit.write(testTarget);
		} catch (IOException e) {
			fail("IOException when attempting to serialize HomeInventoryTracker: "
					+ e.getMessage());
		}

		hit = SerializationManager.create(testTarget);
		assertFalse(hit.canAddStorageUnit(name1));
		assertFalse(hit.canAddStorageUnit(name2));
	}

}
