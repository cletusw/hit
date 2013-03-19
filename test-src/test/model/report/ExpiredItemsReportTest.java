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
	@SuppressWarnings("deprecation")
	private static final Date JAN1ST2000 = new Date(2000 - 1900, 1, 1);
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
		Product product = new ProductBuilder().shelfLife(1).build();
		StorageUnit storageUnit = new StorageUnitBuilder().manager(
				hit.getProductContainerManager()).build();
		Item item = new ItemBuilder().product(product).entryDate(JAN1ST2000)
				.container(storageUnit).build();
		new ItemBuilder().product(product).entryDate(new Date()).container(storageUnit)
				.build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(product.getDescription(), storageUnit.getName(),
				"", item.getEntryDate().toString(), item.getExpirationDate().toString(),
				item.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithExpiredItem() {
		Product product = new ProductBuilder().shelfLife(1).build();
		StorageUnit storageUnit = new StorageUnitBuilder().manager(
				hit.getProductContainerManager()).build();
		Item item = new ItemBuilder().product(product).entryDate(JAN1ST2000)
				.container(storageUnit).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(product.getDescription(), storageUnit.getName(),
				"", item.getEntryDate().toString(), item.getExpirationDate().toString(),
				item.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithUnexpiredItem() {
		Product product = new ProductBuilder().shelfLife(1).build();
		StorageUnit storageUnit = new StorageUnitBuilder().manager(
				hit.getProductContainerManager()).build();
		new ItemBuilder().product(product).entryDate(new Date()).container(storageUnit)
				.build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnTwoStorageUnitsWithExpiredItems() {
		Product product = new ProductBuilder().shelfLife(1).build();
		StorageUnit storageUnit2 = new StorageUnitBuilder()
				.manager(hit.getProductContainerManager()).name("StorageUnit 2").build();
		StorageUnit storageUnit1 = new StorageUnitBuilder()
				.manager(hit.getProductContainerManager()).name("StorageUnit 1").build();
		Item item2 = new ItemBuilder().product(product).entryDate(JAN1ST2000)
				.container(storageUnit2).build();
		Item item1 = new ItemBuilder().product(product).entryDate(JAN1ST2000)
				.container(storageUnit1).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(product.getDescription(),
				storageUnit1.getName(), "", item1.getEntryDate().toString(), item1
						.getExpirationDate().toString(), item1.getBarcode()));
		mockBuilder.addTableRow(Arrays.asList(product.getDescription(),
				storageUnit2.getName(), "", item2.getEntryDate().toString(), item2
						.getExpirationDate().toString(), item2.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}
}
