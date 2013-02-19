package model;

import static org.junit.Assert.assertTrue;

import mocks.MockProductContainerManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StorageUnitTest {
	private ProductContainerManager mockManager = new MockProductContainerManager();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualStorageNames() {

		StorageUnit unit1 = new StorageUnit("Unit1", mockManager);
		StorageUnit unit2 = new StorageUnit("Unit1", mockManager);
		assertTrue(unit1.equals(unit2));
	}

	@Test
	public void testValidStorageUnitName() {
		new StorageUnit("Unit1", mockManager);
	}
}
