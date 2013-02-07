package model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StorageUnitTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualStorageNames() {
		StorageUnit unit1 = new StorageUnit("Unit1");
		StorageUnit unit2 = new StorageUnit("Unit1");
		assertTrue(unit1.equals(unit2));
	}

	@Test
	public void testValidStorageUnitName() {
		new StorageUnit("Unit1");
	}

}
