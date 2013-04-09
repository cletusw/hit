package test.model.report;

import generators.ProductGenerator;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.ConcreteItemManager;
import model.ConcreteProductContainerManager;
import model.ConcreteProductManager;
import model.HomeInventoryTracker;
import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainerManager;
import model.ProductManager;
import model.StorageUnit;
import model.report.ProductStatisticsReport;
import model.report.builder.ReportBuilder;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.model.ProductBuilder;

public class ProductStatisticsReportTest extends EasyMockSupport {

	private static List<String> productsHeaders(int months) {
		return Arrays.asList("Description", "Barcode", "Size", Integer.toString(months)
				+ "-Month Supply", "Supply: Cur/Avg", "Supply: Min/Max", "Supply Used/Added",
				"Shelf Life", "Used Age: Avg/Max", "Cur Age: Avg/Max");
	}

	private HomeInventoryTracker hit;
	private ProductStatisticsReport report;
	private ReportBuilder mockBuilder;

	@Before
	public void setUp() throws Exception {
		hit = new HomeInventoryTracker();
		report = new ProductStatisticsReport(hit.getItemManager(), hit.getProductManager());
		mockBuilder = createMock(ReportBuilder.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * * Creates Items at random dates within the time frame of the report (must be included)
	 */

	@Test
	public void testEmptyReport() {
		ProductStatisticsReport report = new ProductStatisticsReport(
				new ConcreteItemManager(), new ConcreteProductManager());

		// Expect:
		mockBuilder.addDocumentTitle("Product Report (3 Months)");

		mockBuilder.startTable(productsHeaders(3));

		replayAll();

		report.construct(mockBuilder, 3);

		verifyAll();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testOneProductOneItem() {
		ProductManager productManager = new ConcreteProductManager();
		ItemManager itemManager = new ConcreteItemManager();
		ProductContainerManager productContainerManager = new ConcreteProductContainerManager();
		ProductStatisticsReport report = new ProductStatisticsReport(itemManager,
				productManager);
		int months = 3;
		Date now = new Date();
		Date date = new Date(now.getTime() - 5000);
		Product product = new ProductBuilder().productManager(productManager)
				.threeMonthSupply(3).build();
		Item item = new Item(product, new StorageUnit("SU", productContainerManager), date,
				itemManager);
		// public Product(String barcode, String description, int shelfLife, int tms,
		// ProductQuantity pq, ProductManager manager) {

		// Expect:
		mockBuilder.addDocumentTitle("Product Report (3 Months)");

		mockBuilder.startTable(productsHeaders(3));
		// desc,barc,pq,nms,s:cur/av,min/max,used/added,shelfLife,usedAge:av/max,curAge:av/max
		mockBuilder.addTableRow(asTableRow(product, months, 1, 1, 1, 0, 1, 0d, 0d, 1, 1));
		// asTableRow(Product product, int months, double avgSupply,
		// int minSupply, int maxSupply, int usedSupply, int addedSupply, double
		// avgUsedAge, double maxUsedAge, double curAvgAge, double curMaxAge) {

		replayAll();

		report.construct(mockBuilder, 3);

		verifyAll();
	}

	@Test
	public void testProductsInPeriod() {
		ProductManager productManager = new ConcreteProductManager();
		ProductStatisticsReport report = new ProductStatisticsReport(
				new ConcreteItemManager(), productManager);

		ProductGenerator productGenerator = new ProductGenerator(productManager);
		for (int months = 1; months <= 100; months *= 10) {
			Set<Product> productsInPeriod = new TreeSet<Product>();
			for (int i = 0; i < 1; i++) {
				// productsInPeriod.add(productGenerator.createProductInDateRange(0, months));
			}
			// TODO explicitly test edge case

			// Expect:
			mockBuilder.addDocumentTitle("Product Report (3 Months)");

			mockBuilder.startTable(productsHeaders(3));
			// for (Product product : productsInPeriod)
			// TODO Compute these manually, as done in the PSReport.
			// desc,barc,pq,nms,s:cur/av,min/max,used/added,shelfLife,usedAge:av/max,curAge:av/max
			// mockBuilder.addTableRow(asTableRow(product, months, 1d, 0, 1, 0, 1, 0d, 0d,
			// 0d, 0d));
			//

			replayAll();

			report.construct(mockBuilder, 3);

			verifyAll();
		}
	}

	/*
	 * Creates some additional Items beyond the report�s time frame and removes them before the
	 * report period begins; checks that they are not included in the report
	 * 
	 * Creates Items beyond the report�s time frame and does not remove them, checks that they
	 * are included in the report
	 * 
	 * Chooses some subset of the included items to remove at random times during the reporting
	 * period and checks that the report counts these items as removed within the specified
	 * period
	 * 
	 * Check that current supply level is correct
	 * 
	 * Check that average supply level is correct
	 * 
	 * Check that minimum supply level is correct
	 * 
	 * Check that maximum supply level is correct
	 * 
	 * Check that used supply is correct
	 * 
	 * Check that added supply is correct
	 * 
	 * Check that average used age is correct
	 * 
	 * Check that maximum used age is correct
	 * 
	 * Check that average current age is correct
	 * 
	 * Check that maximum current age is correct
	 */

	/**
	 * Returns a List of String that represents how this Product should appear in the
	 * ProductStatistics table.
	 * 
	 * @param product
	 *            Product to represent as row in the ProductStatistics table
	 * @return a List of String that represents how this Product should appear in the
	 *         ProductStatistics table
	 */
	private List<String> asTableRow(Product product, int months, double avgSupply,
			int minSupply, int maxSupply, int usedSupply, int addedSupply, double avgUsedAge,
			double maxUsedAge, double curAvgAge, double curMaxAge) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Arrays.asList(product.getDescription(), product.getBarcode(), product
				.getProductQuantity().toString(), Integer.toString(product
				.getThreeMonthSupply()), Integer.toString(product.getCurrentSupply()) + "/"
				+ twoDForm.format(avgSupply),
				Integer.toString(minSupply) + "/" + Integer.toString(maxSupply), ""
						+ usedSupply + "/" + addedSupply, "" + product.getShelfLife()
						+ " months",
				twoDForm.format(avgUsedAge) + "/" + twoDForm.format(maxUsedAge),
				twoDForm.format(curAvgAge) + "/" + twoDForm.format(curMaxAge));
	}
}
