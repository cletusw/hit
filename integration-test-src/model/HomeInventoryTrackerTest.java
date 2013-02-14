package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HomeInventoryTrackerTest {
	private HomeInventoryTracker tracker;

	@Before
	public void setUp() throws Exception {
		tracker = new HomeInventoryTracker();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// Mess around with some Storage Units
		String storageUnitName = "Pantry";
		assertTrue(tracker.canAddStorageUnit(storageUnitName));
		StorageUnit storageUnit = new StorageUnit(storageUnitName,
				tracker.getProductContainerManager());
		assertFalse(tracker.canAddStorageUnit(storageUnitName));

		String newStorageUnitName = "Downstairs Pantry";
		assertTrue(tracker.canAddStorageUnit(newStorageUnitName));
		tracker.renameStorageUnit(storageUnit, newStorageUnitName);
		assertFalse(tracker.canAddStorageUnit(newStorageUnitName));

		assertTrue(tracker.canAddStorageUnit(storageUnitName));
		StorageUnit storageUnit2 = new StorageUnit(storageUnitName,
				tracker.getProductContainerManager());
		assertFalse(tracker.canAddStorageUnit(storageUnitName));

		// "Scan" a product barcode
		String barcodeScanned = "barcode " + 1;
		Product product = tracker.getProductByBarcode(barcodeScanned);
		assertTrue(product == null);

		// Fill in new Product information
		String description = "description " + 1;
		int shelfLife = 1;
		int threeMonthSupply = 1;
		ProductQuantity productQuantity = new ProductQuantity(1, Unit.COUNT);
		product = tracker.createProduct(barcodeScanned, description, shelfLife,
				threeMonthSupply, productQuantity);
		assertTrue(tracker.getProductByBarcode(barcodeScanned) != null);

		// Create new item
		assertTrue(storageUnit.getItemsSize() == 0);
		Date entryDate = new Date();
		Item item = tracker.addItem(product, entryDate, storageUnit);
		assertTrue(storageUnit.contains(item));

		// Move it
		tracker.move(storageUnit, storageUnit2, item);
		assertTrue(storageUnit2.contains(item));
		assertFalse(storageUnit.contains(item));

		// Remove it
		tracker.remove(item, storageUnit2);
		assertFalse(storageUnit2.contains(item));
	}
}
