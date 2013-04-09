package test.model;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import model.Product;
import model.ProductManager;
import model.ProductQuantity;
import model.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.model.ProductBuilder;

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
		productManager = createMock(ProductManager.class);
		size = new ProductQuantity(2.3f, Unit.GALLONS);
		product = new ProductBuilder().barcode(validBarcode).description(validDescription)
				.productQuantity(size).productManager(productManager).shelfLife(shelfLife)
				.threeMonthSupply(threeMonthSupply).build();

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
		Product sameProduct = new ProductBuilder().barcode(validBarcode)
				.description(validDescription).productQuantity(size)
				.productManager(productManager).shelfLife(shelfLife)
				.threeMonthSupply(threeMonthSupply).build();
		Product differentProduct = new ProductBuilder().barcode("DifferentBarcode")
				.description("DifferentDescription").productQuantity(size)
				.productManager(productManager).build();

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

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidProductQuantity() {
		new ProductBuilder().productQuantity(new ProductQuantity(3, Unit.COUNT)).build();
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
	public void testIsValidProductQuantity() {
		assertTrue(Product.isValidProductQuantity(new ProductQuantity(1, Unit.COUNT)));
		assertFalse(Product.isValidProductQuantity(new ProductQuantity(3, Unit.COUNT)));
		ProductQuantity pq = new ProductQuantity(15, Unit.LITERS);
		product = new ProductBuilder().productQuantity(pq).build();
		assertTrue(product.getProductQuantity().equals(pq));
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
		new ProductBuilder().barcode("").build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidDescription() {
		new ProductBuilder().description("").build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidShelflife() {
		new ProductBuilder().shelfLife(-1).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidThreeMonthSupply() {
		new ProductBuilder().threeMonthSupply(-1).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductNullBarcode() {
		new ProductBuilder().barcode(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIllegalShelfLife() {
		new ProductBuilder().shelfLife(-1).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIllegalThreeMonthSupply() {
		new ProductBuilder().threeMonthSupply(-1).build();
	}

	@Test
	public void testSetShelfLife() {
		assertTrue(product.getShelfLife() == shelfLife);
		product = new ProductBuilder().shelfLife(0).build();
		assertTrue(product.getShelfLife() == 0);
		product = new ProductBuilder().shelfLife(100).build();
		assertTrue(product.getShelfLife() == 100);
	}

	@Test
	public void testSetThreeMonthSupply() {
		assertTrue(product.getThreeMonthSupply() == threeMonthSupply);
		product = new ProductBuilder().threeMonthSupply(0).build();
		assertTrue(product.getThreeMonthSupply() == 0);
		product = new ProductBuilder().threeMonthSupply(5).build();
		assertTrue(product.getThreeMonthSupply() == 5);
	}
}
