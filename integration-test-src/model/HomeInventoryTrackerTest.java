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
		
		String storageUnitName = "Pantry";
		assertTrue(tracker.canAddStorageUnit(storageUnitName));
		StorageUnit storageUnit = new StorageUnit(storageUnitName);
		tracker.addStorageUnit(storageUnit);
		String newStorageUnitName = "Downstairs Pantry";
		assertTrue(tracker.canAddStorageUnit(newStorageUnitName));
		tracker.renameStorageUnit(storageUnit, newStorageUnitName);
		
		String barcodeScanned = "barcode " + 1;
		Product product = tracker.getProductByBarcode(barcodeScanned);
		if (product == null) {
			String description = "description " + 1;
			int shelfLife = 1;
			int threeMonthSupply = 1;
			ProductQuantity productQuantity = new ProductQuantity(1, Unit.COUNT);
			product = tracker.createProduct(barcodeScanned, description, shelfLife, 
					threeMonthSupply, productQuantity);
		}
		
		Date entryDate = new Date();
		tracker.addItem(product, entryDate, newStorageUnitName);
		
		assertTrue(true);
	}

}
