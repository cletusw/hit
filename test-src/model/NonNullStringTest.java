package model;

import static org.junit.Assert.assertTrue;

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
	public void testCompareTo() {
		NonEmptyString one = new NonEmptyString("one");
		NonEmptyString two = new NonEmptyString("two");
		NonEmptyString copyOfOne = new NonEmptyString("one");
		assertTrue(one.compareTo(two) != 0);
		assertTrue(one.compareTo(two.toString()) != 0);
		assertTrue(one.compareTo(copyOfOne) == 0);
		assertTrue(one.compareTo(copyOfOne.toString()) == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyStringConstructor() {
		new NonEmptyString("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullConstructor() {
		new NonEmptyString(null);
	}

	@Test
	public void validConstructorTest() {
		NonEmptyString one = new NonEmptyString("one");
		assertTrue(one.toString().equals("one"));

		NonEmptyString two = new NonEmptyString("two");
		assertTrue(two.toString().equals("two"));
	}

}
