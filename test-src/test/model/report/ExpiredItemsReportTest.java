package test.model.report;

import java.util.Arrays;
import java.util.Date;

import model.HomeInventoryTracker;
import model.Item;
import model.Product;
import model.StorageUnit;
import model.report.ExpiredItemsReport;
import model.report.builder.ReportBuilder;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.model.ItemBuilder;
import builder.model.ProductBuilder;
import builder.model.StorageUnitBuilder;

public class ExpiredItemsReportTest extends EasyMockSupport {
	private HomeInventoryTracker hit;
	private ExpiredItemsReport report;
	private ReportBuilder mockBuilder;

	@Before
	public void setUp() throws Exception {
		hit = new HomeInventoryTracker();
		report = new ExpiredItemsReport(hit.getProductContainerManager());
		mockBuilder = createMock(ReportBuilder.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnEmptyTree() {
		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithExpiredAndUnexpiredItems() {
		String description = "test description";
		Product product = new ProductBuilder().description(description).shelfLife(1).build();
		StorageUnit storageUnit = new StorageUnitBuilder().manager(
				hit.getProductContainerManager()).build();
		@SuppressWarnings("deprecation")
		Item item = new ItemBuilder().product(product).entryDate(new Date(2000 - 1900, 1, 1))
				.container(storageUnit).build();
		new ItemBuilder().product(product).entryDate(new Date()).container(storageUnit)
				.build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(description, storageUnit.getName(), "", item
				.getEntryDate().toString(), item.getExpirationDate().toString(), item
				.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithExpiredItem() {
		String description = "test description";
		Product product = new ProductBuilder().description(description).shelfLife(1).build();
		StorageUnit storageUnit = new StorageUnitBuilder().manager(
				hit.getProductContainerManager()).build();
		@SuppressWarnings("deprecation")
		Item item = new ItemBuilder().product(product).entryDate(new Date(2000 - 1900, 1, 1))
				.container(storageUnit).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(description, storageUnit.getName(), "", item
				.getEntryDate().toString(), item.getExpirationDate().toString(), item
				.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}
}
