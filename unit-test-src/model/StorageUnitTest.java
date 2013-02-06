package model;

import static org.junit.Assert.*;

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

	@Test(expected=IllegalArgumentException.class)
	public void testNullStorageUnitName() {
		new StorageUnit(null);
	}
	
	@Test
	public void testValidStorageUnitName() {
		new StorageUnit("Unit1");
	}
	
	@Test
	public void testEqualStorageNames() {
		StorageUnit unit1 = new StorageUnit("Unit1");
		StorageUnit unit2 = new StorageUnit("Unit1");
		assertTrue(unit1.equals(unit2));
	}

}
