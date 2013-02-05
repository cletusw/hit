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

	@Test
	public void test() {
		// test null name storage unit
		try {
			new StorageUnit(null,Unit.COUNT);
			assertFalse(true);
		} catch(IllegalArgumentException e) {}
		
		// test null unit storage unit
		try {
			new StorageUnit("Unit1",null);
			assertFalse(true);
		} catch(IllegalArgumentException e) {}
		
		// test equality of (legitimate) storage units
		StorageUnit unit1 = new StorageUnit("Unit1",Unit.GALLONS);
		StorageUnit unit2 = new StorageUnit("Unit1",Unit.GALLONS);
		assertTrue(unit1.equals(unit2));

		StorageUnit unit3 = new StorageUnit("Unit3",Unit.GALLONS);
		assertFalse(unit1.equals(unit3));
		
		StorageUnit unit4 = new StorageUnit("Unit1",Unit.OUNCES);
		assertTrue(unit4.equals(unit1));
	}

}
