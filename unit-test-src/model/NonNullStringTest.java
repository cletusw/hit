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
	public void validConstructorTest(){
		NonNullString one = new NonNullString("one");
		assertTrue(one.value.equals("one"));
		
		NonNullString two = new NonNullString("two");
		assertTrue(two.value.equals("two"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testNullConstructor(){
		new NonNullString(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEmptyStringConstructor(){
		new NonNullString("");
	}
	
	@Test
	public void testCompareTo(){
		NonNullString one = new NonNullString("one");
		NonNullString two = new NonNullString("two");
		NonNullString copyOfOne = new NonNullString("one");
		assertTrue(one.compareTo(two) != 0);
		assertTrue(one.compareTo(two.getValue()) != 0);
		assertTrue(one.compareTo(copyOfOne) == 0);
		assertTrue(one.compareTo(copyOfOne.getValue()) == 0);
	}

}
