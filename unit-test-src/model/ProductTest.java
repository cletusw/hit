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
	private final ProductManager productManager = new MockProductManager();
	private final int shelfLife = 3;
	private final int threeMonthSupply = 4;
	private final ProductQuantity size = new ProductQuantity(2.3f, Unit.GALLONS);
	private Product product;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		product = new Product(validBarcode, validDescription, shelfLife, threeMonthSupply, size, productManager);
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
		assertTrue(product.getShelfLife() == shelfLife);
		assertTrue(product.getCreationDate().equals(new Date()));
		assertTrue(product.getSize().equals(size));
		assertTrue(product.getThreeMonthSupply() == threeMonthSupply);
	}
	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductInvalidBarcode() {
		new Product("", validDescription, 3, 3, size, productManager);
	}

	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductNullBarcode() {
		new Product(null, "", 3, -1, size, productManager);
	}
	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductInvalidDescription() {
		new Product(validBarcode, "", 3, 3, size, productManager);
	}
	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductNullDescription() {
		new Product(validBarcode, null, 3, 3, size, productManager);
	}

	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductInvalidShelflife() {
		new Product(validBarcode, "", -1, 3, size, productManager);
	}
	
	/**
	 * Test method for {@link model.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testProductInvalidThreeMonthSupply() {
		new Product(validBarcode, "", 3, -1, size, productManager);
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
	 * Test method for {@link model.Product#isValidBarcode(java.lang.String)}.
	 */
	@Test
	public void testIsValidBarcode() {
		assertTrue(Product.isValidBarcode(validBarcode));
		assertFalse(Product.isValidBarcode(""));
		assertFalse(Product.isValidBarcode(null));
	}

	/**
	 * Test method for {@link model.Product#isValidDescription(java.lang.String)}.
	 */
	@Test
	public void testIsValidDescription() {
		assertTrue(Product.isValidDescription(validDescription));
		assertFalse(Product.isValidDescription(""));
		assertFalse(Product.isValidDescription(null));
	}

	/**
	 * Test method for {@link model.Product#isValidShelfLife(int)}.
	 */
	@Test
	public void testIsValidShelfLife() {
		assertTrue(Product.isValidShelfLife(0));
		assertTrue(Product.isValidShelfLife(10));
		assertFalse(Product.isValidShelfLife(-5));
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
		assertTrue(createDate.getDate() == today.getDate());
		assertTrue(createDate.getHours() == today.getHours());
		assertTrue(createDate.getMinutes() == today.getMinutes());
	}

	/**
	 * Test method for {@link model.Product#compareTo(java.lang.Object)}.
	 */
	@Test
	public void testCompareTo() {
		Product sameProduct = new Product(product.getBarcode(), product.getDescription(), 0, 0, size, productManager);
		Product differentProduct = new Product("DifferentBarcode", "DifferentDescription", 0, 0, size, productManager);
		
		assertTrue(product.compareTo(sameProduct) == 0);
		assertTrue(product.compareTo(differentProduct) != 0);
	}
}
