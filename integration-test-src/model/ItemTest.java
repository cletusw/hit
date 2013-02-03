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
	private final Product product = new Product("validBarcode", "A product", productManager);
	private final ProductGroup productGroup = new ProductGroup();
	private final Date entryDateLastMonth = new Date(113, 0, 1, 12, 45, 45);
	
	private Item item;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item = new Item(validUPCABarcode, product, productGroup, itemManager, entryDateLastMonth);
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
		
		assertTrue(entry.getYear() == now.getYear());
		assertTrue(entry.getMonth() == now.getMonth());
		assertTrue(entry.getDay() == now.getDay());
		assertTrue(entry.getHours() == now.getHours());
		assertTrue(entry.getMinutes() == now.getMinutes());
		
		assertTrue(expiration.getYear() == now.getYear());
		assertTrue(expiration.getMonth() == now.getMonth());
		assertTrue(expiration.getDay() == now.getDay());
		assertTrue(expiration.getHours() == now.getHours());
		assertTrue(expiration.getMinutes() == now.getMinutes());
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
	public void testSetExitTime() {
		Date exitTime = new Date();
		item.setExitTime(exitTime);
		assertTrue(item.getExitTime().equals(exitTime));
	}
	
	/**
	 * Test method for {@link model.Item#setExitTime(java.util.Date)}.
	 */
	@SuppressWarnings("deprecation")
	@Test (expected=IllegalArgumentException.class)
	public void testSetFutureExitTime() {
		Date exitTime = new Date(115, 10, 15, 12, 45, 45);
		item.setExitTime(exitTime);
	}
	
	/**
	 * Test method for {@link model.Item#setExitTime(java.util.Date)}.
	 */
	@SuppressWarnings("deprecation")
	@Test (expected=IllegalArgumentException.class)
	public void testSetOldExitTime() {
		item.setExitTime(new Date(110, 10, 15, 12, 45, 45));
	}

	/**
	 * Test method for {@link model.Item#setContainer(model.ProductContainer)}.
	 */
	@Test
	public void testSetContainer() {
		ProductGroup newPG = new ProductGroup("NewPG", new ProductQuantity(2.1f, Unit.FLUID_OUNCES));
		item.setContainer(newPG);
		assertTrue(item.getContainer().equals(newPG));
		assertFalse(item.getContainer().equals(productGroup));
	}
	
	@Test
	public void testSetExpirationDate(){
		fail("Item.setExpirationDate not yet implemented");
	}
	
	@Test
	public void testSetBadExpirationDate(){
		fail("Item.setExpirationDate not yet implemented");
	}

	/**
	 * Test method for {@link model.Item#compareTo(java.lang.Object)}.
	 */
	@Test
	public void testCompareTo() {
		Item sameItem = new Item(validUPCABarcode, product, productGroup, itemManager);
		Item newItem = new Item(new Barcode("412345688919"), new Product("abc", "abcd", productManager), new ProductGroup(), itemManager);
		assertTrue(item.compareTo(sameItem) == 0);
		assertTrue(item.compareTo(newItem) != 0);
	}

}
