package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcreteItemManagerTest {
	private ConcreteProductManager pm;
	private ConcreteItemManager cm;
	private Product pOne;
	private Product pTwo;
	private ProductGroup pg1;

	@Before
	public void setUp() throws Exception {
		pm = new ConcreteProductManager();
		pOne = new Product("barcode1", "Description1", new Date(), 5, 3, new ProductQuantity(
				5, Unit.GALLONS), pm);
		pTwo = new Product("barcode2", "Description2", new Date(), 1, 1, new ProductQuantity(
				1, Unit.COUNT), pm);
		pm.manage(pOne);
		pm.manage(pTwo);

		pg1 = new ProductGroup("Pg1", new ProductQuantity(3, Unit.COUNT), Unit.COUNT,
				new StorageUnit("Su1"));

		cm = new ConcreteItemManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAndRemoveItems() {
		// add item
		Item one = new Item(pOne, pg1, cm);
		assertTrue(cm.productHasItems(pOne));

		// re-add same item (which shouldn't be added), then remove it
		cm.manage(one);
		cm.unmanage(one);
		assertFalse(cm.productHasItems(pOne));

		// restore original item
		cm.manage(one);
		assertTrue(cm.productHasItems(pOne));

		// add different item of the same product type
		Item two = new Item(pOne, pg1, cm);
		// add different item of different product type
		Item three = new Item(pTwo, pg1, cm);
		assertTrue(cm.productHasItems(pTwo));

		// test unmanage functions
		cm.unmanage(one);
		assertTrue(cm.productHasItems(pOne)); // still has one item of type pOne
		cm.unmanage(two);
		assertFalse(cm.productHasItems(pOne));

		cm.unmanage(three);
		assertFalse(cm.productHasItems(pTwo));

		// re-manage Item one
		cm.manage(one);
		assertTrue(cm.productHasItems(pOne));

		// check removed items data structure
		Iterator<Item> removed = cm.removedItemsIterator();
		int counter = 0;
		while (removed.hasNext()) {
			Item current = removed.next();
			if (!current.equals(one) && !current.equals(two) && !current.equals(three))
				assertFalse(true);
			else
				counter++;
		}
		assertEquals(counter, 3);
	}

}
