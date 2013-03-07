package test.model;

import static org.junit.Assert.assertTrue;

import model.ConcreteProductContainerManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcreteProductContainerManagerTest {

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
		String newStorageUnitName = "Downstairs Pantry";
		assertTrue(manager.isValidStorageUnitName(newStorageUnitName));
	}

}
