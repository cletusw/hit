package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private static final String validBarcode = "testBarcode";
	private static final String validDescription = "testDescription";
	private static final int shelfLife = 3;
	private static final int threeMonthSupply = 4;
	private ProductManager productManager;
	private ProductQuantity size;
	private Product product;

	@Before
	public void setUp() throws Exception {
		productManager = new MockProductManager();
		size = new ProductQuantity(2.3f, Unit.GALLONS);
		product = new Product(validBarcode, validDescription, shelfLife, threeMonthSupply,
				size, productManager);

		// test invariants
		assertTrue(product.getBarcode() != null);
		assertTrue(product.getDescription() != null);
		assertTrue(product.getCreationDate() != null);
		assertTrue(product.getShelfLife() >= 0);
		assertTrue(product.getThreeMonthSupply() >= 0);
		assertTrue(product.getSize() != null);
	}

	@After
	public void tearDown() throws Exception {
		// test invariants
		assertTrue(product.getBarcode() != null);
		assertTrue(product.getDescription() != null);
		assertTrue(product.getCreationDate() != null);
		assertTrue(product.getShelfLife() >= 0);
		assertTrue(product.getThreeMonthSupply() >= 0);
		assertTrue(product.getSize() != null);
	}

	@Test
	public void testCompareTo() {
		Product sameProduct = new Product(product.getBarcode(), product.getDescription(), 0,
				0, size, productManager);
		Product differentProduct = new Product("DifferentBarcode", "DifferentDescription", 0,
				0, size, productManager);

		assertTrue(product.compareTo(sameProduct) == 0);
		assertTrue(product.compareTo(differentProduct) != 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreationDate() {
		Date createDate = product.getCreationDate();
		Date today = new Date();

		assertTrue(createDate.getYear() == today.getYear());
		assertTrue(createDate.getMonth() == today.getMonth());
		assertTrue(createDate.getDate() == today.getDate());
		assertTrue(createDate.getHours() == today.getHours());
		assertTrue(createDate.getMinutes() == today.getMinutes());
	}

	@Test
	public void testIsValidBarcode() {
		assertTrue(Product.isValidBarcode(validBarcode));
		assertFalse(Product.isValidBarcode(""));
		assertFalse(Product.isValidBarcode(null));
	}

	@Test
	public void testIsValidDescription() {
		assertTrue(Product.isValidDescription(validDescription));
		assertFalse(Product.isValidDescription(""));
		assertFalse(Product.isValidDescription(null));
	}

	@Test
	public void testIsValidShelfLife() {
		assertTrue(Product.isValidShelfLife(0));
		assertTrue(Product.isValidShelfLife(10));
		assertFalse(Product.isValidShelfLife(-5));
	}

	@Test
	public void testIsValidThreeMonthSupply() {
		assertTrue(Product.isValidThreeMonthSupply(0));
		assertTrue(Product.isValidThreeMonthSupply(10));
		assertFalse(Product.isValidThreeMonthSupply(-1));
	}

	@Test
	public void testProduct() {
		// test constructor post
		assertTrue(!product.getCreationDate().after(new Date()));
		assertTrue(product.getBarcode().equals(validBarcode));
		assertTrue(product.getDescription().equals(validDescription));
		assertTrue(product.getShelfLife() == shelfLife);
		assertTrue(product.getSize().equals(size));
		assertTrue(product.getThreeMonthSupply() == threeMonthSupply);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidBarcode() {
		new Product("", validDescription, 3, 3, size, productManager);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidDescription() {
		new Product(validBarcode, "", 3, 3, size, productManager);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidShelflife() {
		new Product(validBarcode, "", -1, 3, size, productManager);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidThreeMonthSupply() {
		new Product(validBarcode, "", 3, -1, size, productManager);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductNullBarcode() {
		new Product(null, "", 3, -1, size, productManager);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductNullDescription() {
		new Product(validBarcode, null, 3, 3, size, productManager);
	}
}
