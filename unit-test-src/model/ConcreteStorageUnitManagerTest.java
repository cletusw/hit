package model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcreteStorageUnitManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValidStorageUnitName() {
		ConcreteProductContainerManager manager = new ConcreteProductContainerManager();
		String storageUnitName = "Pantry";
		assertTrue(manager.isValidStorageUnitName(storageUnitName));
		StorageUnit storageUnit = new StorageUnit(storageUnitName, manager);
		String newStorageUnitName = "Downstairs Pantry";
		assertTrue(manager.isValidStorageUnitName(newStorageUnitName));
	}

}
