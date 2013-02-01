/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Matthew
 *
 */
public class ProductTest {

	private final String validBarcode = "testBarcode";
	private final String validDescription = "testDescription";
	private final ProductManager productManager = new ProductManager();
	private Product product;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		product = new Product(validBarcode, validDescription, productManager);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testProduct() {
		assertTrue(product.getBarcode().equals(validBarcode));
		assertTrue(product.getDescription().equals(validDescription));
	}
	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductInvalidBarcode() {
		new Product("", validDescription, productManager);
	}
	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductInvalidDescription() {
		new Product(validBarcode, "", productManager);
	}

	/**
	 * Test method for {@link model.Product#isValidSize(float, model.Unit)}.
	 */
	@Test
	public void testIsValidSize() {
		// valid ProductQuantities
		assertTrue(product.isValidSize(1.2f, Unit.FLUID_OUNCES));
		assertTrue(product.isValidSize(0f, Unit.GALLONS));
		assertTrue(product.isValidSize(2, Unit.COUNT));
		
		// invalid ProductQuantities
		assertFalse(product.isValidSize(-4.2f, Unit.GRAMS));
		assertFalse(product.isValidSize(3.1f, Unit.COUNT));
	}

	/**
	 * Test method for {@link model.Product#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetValidDescription() {
		String newDescription = "newDescription";
		product.setDescription(newDescription);
		assertFalse(product.getDescription().equals(validDescription));
		assertTrue(product.getDescription().equals(newDescription));
	}
	
	/**
	 * Test method for {@link model.Product#setDescription(java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetEmptyDescription() {
		product.setDescription("");
	}
	
	/**
	 * Test method for {@link model.Product#setDescription(java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetNullDescription() {
		product.setDescription(null);
	}

	/**
	 * Test method for {@link model.Product#setShelfLife(int)}.
	 */
	@Test
	public void testSetShelfLife() {
		assertTrue(product.getShelfLife() == 0);
		
		int shelfLife = 5;
		product.setShelfLife(shelfLife);
		assertTrue(product.getShelfLife() == shelfLife);
	}
	
	/**
	 * Test method for {@link model.Product#setShelfLife(int)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetInvalidShelfLife() {
		product.setShelfLife(-1);
	}

	/**
	 * Test method for {@link model.Product#setThreeMonthSupply(int)}.
	 */
	@Test
	public void testThreeMonthSupply() {
		assertTrue(product.getThreeMonthSupply() == 0);
		
		int threeMonthSupply = 4;
		product.setThreeMonthSupply(threeMonthSupply);
		assertTrue(product.getThreeMonthSupply() == threeMonthSupply);
	}
	
	/**
	 * Test method for {@link model.Product#setThreeMonthSupply(int)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testInvalidThreeMonthSupply() {
		product.setThreeMonthSupply(-4);
	}

	/**
	 * Test method for {@link model.Product#isValidThreeMonthSupply(int)}.
	 */
	@Test
	public void testIsValidThreeMonthSupply() {
		assertTrue(product.isValidThreeMonthSupply(0));
		assertTrue(product.isValidThreeMonthSupply(10));
		assertFalse(product.isValidThreeMonthSupply(-1));
	}

	/**
	 * Test method for {@link model.Product#setBarcode(java.lang.String)}.
	 */
	@Test
	public void testSetBarcode() {
		String newBarcode = "newBarcode";
		product.setBarcode(newBarcode);
		assertFalse(product.getBarcode().equals(validBarcode));
		assertTrue(product.getBarcode().equals(newBarcode));
	}
	
	/**
	 * Test method for {@link model.Product#setBarcode(java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetEmptyBarcode() {
		String newBarcode = "";
		product.setBarcode(newBarcode);
	}
	
	/**
	 * Test method for {@link model.Product#setBarcode(java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testSetNullBarcode() {
		String newBarcode = null;
		product.setBarcode(newBarcode);
	}
	
	/**
	 * Test method for {@link model.Product#isValidBarcode(java.lang.String)}.
	 */
	@Test
	public void testIsValidBarcode() {
		assertTrue(product.isValidBarcode(validBarcode));
		assertFalse(product.isValidBarcode(""));
		assertFalse(product.isValidBarcode(null));
	}

	/**
	 * Test method for {@link model.Product#isValidDescription(java.lang.String)}.
	 */
	@Test
	public void testIsValidDescription() {
		assertTrue(product.isValidDescription(validDescription));
		assertFalse(product.isValidDescription(""));
		assertFalse(product.isValidDescription(null));
	}

	/**
	 * Test method for {@link model.Product#isValidShelfLife(int)}.
	 */
	@Test
	public void testIsValidShelfLife() {
		assertTrue(product.isValidShelfLife(0));
		assertTrue(product.isValidShelfLife(10));
		assertFalse(product.isValidShelfLife(-5));
	}

	/**
	 * Test method for {@link model.Product#getCreationDate()}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCreationDate() {
		Date createDate = product.getCreationDate();
		Date today = new Date();
		
		// TODO: Remove deprecated getters??
		assertTrue(createDate.getYear() == today.getYear());
		assertTrue(createDate.getMonth() == today.getMonth());
		assertTrue(createDate.getDate() == today.getDay());
		assertTrue(createDate.getHours() == today.getHours());
		assertTrue(createDate.getMinutes() == today.getMinutes());
	}

	/**
	 * Test method for {@link model.Product#compareTo(java.lang.Object)}.
	 */
	@Test
	public void testCompareTo() {
		Product sameProduct = new Product(product.getBarcode(), product.getDescription(), productManager);
		Product differentProduct = new Product("DifferentBarcode", "DifferentDescription", productManager);
		
		assertTrue(product.compareTo(sameProduct) == 0);
		assertTrue(product.compareTo(differentProduct) != 0);
	}

}
