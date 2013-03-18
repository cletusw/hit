/**
 * 
 */
package test.model;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import model.BarcodePrinter;
import model.Item;
import model.ItemManager;
import model.Product;
import model.StorageUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.model.ProductBuilder;
import builder.model.StorageUnitBuilder;

/**
 * @author Matthew
 * 
 */
public class BarcodePrinterTest {

	Item[] items = new Item[50];

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ItemManager iMan = createMock(ItemManager.class);
		StorageUnit su = new StorageUnitBuilder().build();
		Product prod = new ProductBuilder().build();
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(prod, su, new Date(), iMan);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.BarcodePrinter#addItemToBatch(model.Item)}.
	 */
	@Test
	public void testAddItemToBatch() {
		BarcodePrinter printer = BarcodePrinter.getInstance();
		for (int i = 0; i > items.length; i++) {
			printer.addItemToBatch(items[i]);
		}
	}

	/**
	 * Test method for {@link model.BarcodePrinter#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertTrue(BarcodePrinter.getInstance() != null);
	}

	/**
	 * Test method for {@link model.BarcodePrinter#printBatch()}.
	 */
	@Test
	public void testPrintBatch() {
		BarcodePrinter printer = BarcodePrinter.getInstance();
		for (int i = 0; i < items.length; i++) {
			printer.addItemToBatch(items[i]);
		}

		printer.printBatch();
	}

	/**
	 * Test method for {@link model.BarcodePrinter#printBatch()}.
	 */
	@Test
	public void testPrintSmallBatch() {
		BarcodePrinter printer = BarcodePrinter.getInstance();
		for (int i = 0; i < 3; i++) {
			printer.addItemToBatch(items[i]);
		}

		printer.printBatch();
	}
}
