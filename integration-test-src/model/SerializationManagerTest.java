package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SerializationManagerTest {
	private String testTarget = "TestHITTracker.ser";

	@Before
	public void setUp() throws Exception {
		deleteDefaultFile();
	}

	@After
	public void tearDown() throws Exception {
		deleteDefaultFile();
	}
	
	private void deleteDefaultFile() {
		File f = new File(testTarget);
		if(f.exists()) {
			f.delete();
		}
	}

	@Test
	public void test() {
		// Create instance of HIT
		HomeInventoryTracker hit = SerializationManager.create("");
		
		String name1 = "StorageUnit1";
		String name2 = "StorageUnit2";
		hit.addStorageUnit(name1);
		hit.addStorageUnit(name2);
		
		try {
			hit.write(testTarget);
		} catch (IOException e) {
			assertFalse(true);
		}
		
		hit = SerializationManager.create(testTarget);
		assertFalse(hit.canAddStorageUnit(name1));
		assertFalse(hit.canAddStorageUnit(name2));
	}

}
