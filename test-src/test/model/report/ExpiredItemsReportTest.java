package test.model.report;

import java.util.Arrays;
import java.util.Date;

import model.ConcreteProductContainerManager;
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
	private StorageUnit storageUnit;
	private Product perishableProduct;

	@Before
	public void setUp() throws Exception {
		hit = new HomeInventoryTracker();
		report = new ExpiredItemsReport(hit.getProductContainerManager());
		mockBuilder = createMock(ReportBuilder.class);

		storageUnit = new StorageUnitBuilder().manager(hit.getProductContainerManager())
				.build();
		perishableProduct = new ProductBuilder().shelfLife(1).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnEmptyTree() {
		ExpiredItemsReport report = new ExpiredItemsReport(
				new ConcreteProductContainerManager());

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
		Item expiredItem = new ItemBuilder().product(perishableProduct).entryDate(JAN1ST2000)
				.container(storageUnit).build();
		new ItemBuilder().product(perishableProduct).entryDate(new Date())
				.container(storageUnit).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(perishableProduct.getDescription(), storageUnit
				.getName(), "", expiredItem.getEntryDate().toString(), expiredItem
				.getExpirationDate().toString(), expiredItem.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithExpiredItem() {
		Item expiredItem = new ItemBuilder().product(perishableProduct).entryDate(JAN1ST2000)
				.container(storageUnit).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(perishableProduct.getDescription(), storageUnit
				.getName(), "", expiredItem.getEntryDate().toString(), expiredItem
				.getExpirationDate().toString(), expiredItem.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithExpiredItemsOfDifferingProducts() {
		Product perishableProduct2 = new ProductBuilder().description("Product2").shelfLife(1)
				.build();
		Product perishableProduct1 = new ProductBuilder().description("Product1").shelfLife(1)
				.build();
		Item expiredItem2 = new ItemBuilder().product(perishableProduct2)
				.entryDate(JAN1ST2000).container(storageUnit).build();
		Item expiredItem1 = new ItemBuilder().product(perishableProduct1)
				.entryDate(JAN1ST2000).container(storageUnit).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(perishableProduct1.getDescription(),
				storageUnit.getName(), "", expiredItem1.getEntryDate().toString(),
				expiredItem1.getExpirationDate().toString(), expiredItem1.getBarcode()));
		mockBuilder.addTableRow(Arrays.asList(perishableProduct2.getDescription(),
				storageUnit.getName(), "", expiredItem2.getEntryDate().toString(),
				expiredItem2.getExpirationDate().toString(), expiredItem2.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}

	@Test
	public void testOnStorageUnitWithUnexpiredItem() {
		new ItemBuilder().product(perishableProduct).entryDate(new Date())
				.container(storageUnit).build();

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
		StorageUnit storageUnit2 = new StorageUnitBuilder()
				.manager(hit.getProductContainerManager()).name("StorageUnit 2").build();
		StorageUnit storageUnit1 = new StorageUnitBuilder()
				.manager(hit.getProductContainerManager()).name("StorageUnit 1").build();
		Item expiredItem2 = new ItemBuilder().product(perishableProduct).entryDate(JAN1ST2000)
				.container(storageUnit2).build();
		Item expiredItem1 = new ItemBuilder().product(perishableProduct).entryDate(JAN1ST2000)
				.container(storageUnit1).build();

		// Expect:
		mockBuilder.addDocumentTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.addTableRow(Arrays.asList(perishableProduct.getDescription(),
				storageUnit1.getName(), "", expiredItem1.getEntryDate().toString(),
				expiredItem1.getExpirationDate().toString(), expiredItem1.getBarcode()));
		mockBuilder.addTableRow(Arrays.asList(perishableProduct.getDescription(),
				storageUnit2.getName(), "", expiredItem2.getEntryDate().toString(),
				expiredItem2.getExpirationDate().toString(), expiredItem2.getBarcode()));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}
}
