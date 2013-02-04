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
public class ItemTest {

	private final Barcode validUPCABarcode = new Barcode("494180175762");
	private final ItemManager itemManager = new MockItemManager();
	private final ProductManager productManager = new MockProductManager();
	private final ProductQuantity pq = new ProductQuantity(2.2f, Unit.FLUID_OUNCES);
	private final Product product = new Product("validBarcode", "A product", 3, 3, pq, productManager);
	private final ProductGroup productGroup = new ProductGroup("Test product group", pq, Unit.GALLONS);
	private final Date entryDateLastMonth = new Date(113, 0, 1, 12, 45, 45);
	
	private Item item;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item = new Item(validUPCABarcode, product, productGroup, entryDateLastMonth, itemManager);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Item#Item(java.lang.String, model.Product, model.ProductContainer)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testItem() {
		assertTrue(item.getBarcode().equals(validUPCABarcode));
		assertTrue(item.getProduct().compareTo(product) == 0);
		assertTrue(item.getContainer().equals(productGroup));
		
		assertTrue(item.getExitTime() == null);
		
		Date now = new Date();
		Date expiration = item.getExpirationDate();
		Date entry = item.getEntryDate();
		assertTrue(expiration != null);
		assertTrue(entry != null);
		
		assertTrue(entry.getYear() == entryDateLastMonth.getYear());
		assertTrue(entry.getMonth() == entryDateLastMonth.getMonth());
		assertTrue(entry.getDate() == entryDateLastMonth.getDate());
		assertTrue(entry.getHours() == entryDateLastMonth.getHours());
		assertTrue(entry.getMinutes() == entryDateLastMonth.getMinutes());
	}

	/**
	 * Test method for {@link model.Item#setProduct(model.Product)}.
	 *
	@Test
	public void testSetProduct() {
		Product newProduct = new Product("newBarcode", "newDescription", productManager);
		item.setProduct(newProduct);
		assertTrue(item.getProduct().compareTo(newProduct) == 0);
		assertFalse(item.getProduct().compareTo(product) == 0);
	}
	*/

	/**
	 * Test method for {@link model.Item#setEntryDate(java.util.Date)}.
	 *
	@SuppressWarnings("deprecation")
	@Test
	public void testSetEntryDate() {
		Date entryDate = new Date(110, 10, 15, 12, 45, 45);
		item.setEntryDate(entryDate);
		assertTrue(item.getEntryDate().equals(entryDate));
	}
	*/

	/**
	 * Test method for {@link model.Item#setEntryDate(java.util.Date)}.
	 *
	@SuppressWarnings("deprecation")
	@Test (expected=IllegalArgumentException.class)
	public void testSetBadEntryDate() {
		item.setEntryDate(new Date(115, 10, 15, 12, 45, 45));
	}
	*/

	/**
	 * Test method for {@link model.Item#setExitTime(java.util.Date)}.
	 */
	@Test
	public void testRemove() {
		Date exitTime = new Date();
		item.remove();
		assertTrue(item.getContainer() == null);
		assertTrue(item.getExitTime().equals(exitTime));
	}
	
	/**
	 * Test method for {@link model.Item#compareTo(java.lang.Object)}.
	 */
	@Test
	public void testCompareTo() {
		Item sameItem = new Item(validUPCABarcode, product, productGroup, itemManager);
		Item newItem = new Item(new Barcode("412345688919"), new Product("abc", "abcd", 3, 3, pq, productManager), productGroup, itemManager);
		assertTrue(item.compareTo(sameItem) == 0);
		assertTrue(item.compareTo(newItem) != 0);
	}

}
