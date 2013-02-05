package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NonNullStringTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// Test null string
		try {
			new NonNullString(null);
			assert(false);
		} catch(IllegalArgumentException e) {
			
		}
		
		// Test empty string
		try {
			new NonNullString("");
			assert(false);
		} catch(IllegalArgumentException e) {
			
		}
		
		// Test equal strings
		NonNullString third = new NonNullString("third");
		NonNullString fourth = new NonNullString("third");
		assertEquals((third.compareTo(fourth) == 0),true);
		
		// Test unequal strings
		NonNullString fifth = new NonNullString("fifth");
		assertEquals(third.equals(fifth),false);
		
	}

}
