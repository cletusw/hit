package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BarcodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBarcodeConstructor() {
		Barcode b = new Barcode();
		assertTrue(b.toString() != null);
		assertTrue(b.toString().length() == 12);
		assertTrue(Barcode.isValidBarcode(b.toString()));

		Barcode c = new Barcode(b.toString());
		assertTrue(c.toString() != null);
		assertTrue(c.toString().equals(b.toString()));
		assertTrue(Barcode.isValidBarcode(c.toString()));
	}

	@Test
	public void testCompareTo() {
		Barcode b = new Barcode();
		Barcode c = new Barcode(b.toString());
		assertTrue(b.compareTo(c) == 0);
	}

	@Test
	public void testValidCodes() {
		// test null string
		// assertEquals(Barcode.isValidBarcode(null),false);

		// test empty string
		assertEquals(Barcode.isValidBarcode(""), false);

		// test string of wrong lengths
		assertEquals(Barcode.isValidBarcode("123"), false);
		assertEquals(Barcode.isValidBarcode("1234567899999"), false);

		// test string that doesn't begin with '4'
		assertEquals(Barcode.isValidBarcode("123456789999"), false);
		assertEquals(Barcode.isValidBarcode("016000660601"), false);

		// test string with characters in it
		assertEquals(Barcode.isValidBarcode("4abcdefg9123"), false);

		// test string with wrong check digit (final digit)
		assertEquals(Barcode.isValidBarcode("412345098212"), false);
		assertEquals(Barcode.isValidBarcode("421322231001"), false);
		assertEquals(Barcode.isValidBarcode("433211114520"), false);

		// test valid barcodes
		assertEquals(Barcode.isValidBarcode("412345098213"), true);
		assertEquals(Barcode.isValidBarcode("421322231000"), true);
		assertEquals(Barcode.isValidBarcode("433211114523"), true);
	}
}
