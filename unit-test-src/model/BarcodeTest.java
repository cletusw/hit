package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class BarcodeTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidCodes() {
		// test null string
		//assertEquals(Barcode.isValidBarcode(null),false);
		
		// test empty string
		assertEquals(Barcode.isValidBarcode(""),false);
		
		// test string of wrong lengths
		assertEquals(Barcode.isValidBarcode("123"),false);
		assertEquals(Barcode.isValidBarcode("1234567899999"),false);
		
		// test string that doesn't begin with '4'
		assertEquals(Barcode.isValidBarcode("123456789999"),false);
		assertEquals(Barcode.isValidBarcode("016000660601"),false);
		
		// test string with characters in it
		assertEquals(Barcode.isValidBarcode("4abcdefg9123"),false);
		
		// test string with wrong check digit (final digit)
		assertEquals(Barcode.isValidBarcode("412345098212"),false);
		assertEquals(Barcode.isValidBarcode("421322231001"),false);
		assertEquals(Barcode.isValidBarcode("433211114520"),false);
		
		// test valid barcodes
		assertEquals(Barcode.isValidBarcode("412345098213"),true);
		assertEquals(Barcode.isValidBarcode("421322231000"),true);
		assertEquals(Barcode.isValidBarcode("433211114523"),true);
	}
	
	@Test
	public void TestBarcodeConstructor(){
		Barcode b = new Barcode();
		assertTrue(b.getValue() != null);
		assertTrue(b.getValue().length() == 12);
		assertTrue(Barcode.isValidBarcode(b.getValue()));
		
		Barcode c = new Barcode(b.getValue());
		assertTrue(c.getValue() != null);
		assertTrue(c.getValue().equals(b.getValue()));
		assertTrue(Barcode.isValidBarcode(c.getValue()));
	}
	
	@Test
	public void testCompareTo(){
		Barcode b = new Barcode();
		Barcode c = new Barcode(b.getValue());
		assertTrue(b.compareTo(c) == 0);
	}
}
