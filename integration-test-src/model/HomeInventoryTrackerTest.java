package model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HomeInventoryTrackerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		HomeInventoryTracker tracker = new HomeInventoryTracker();
		
		// Mess around with some Storage Units
		String storageUnitName = "Pantry";
		assertTrue(tracker.canAddStorageUnit(storageUnitName));
		StorageUnit storageUnit = new StorageUnit(storageUnitName);
		tracker.addStorageUnit(storageUnit);
		String newStorageUnitName = "Downstairs Pantry";
		assertTrue(tracker.canAddStorageUnit(newStorageUnitName));
		tracker.renameStorageUnit(storageUnit, newStorageUnitName);
		
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
	}

}
