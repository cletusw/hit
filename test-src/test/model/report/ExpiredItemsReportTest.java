package test.model.report;

import java.util.Arrays;

import model.HomeInventoryTracker;
import model.report.ExpiredItemsReport;
import model.report.builder.ReportBuilder;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExpiredItemsReportTest extends EasyMockSupport {
	private ExpiredItemsReport report;
	private ReportBuilder mockBuilder;

	@Before
	public void setUp() throws Exception {
		HomeInventoryTracker hit = new HomeInventoryTracker();
		report = new ExpiredItemsReport(hit.getProductContainerManager());
		mockBuilder = createMock(ReportBuilder.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnEmptyTree() {
		// Expect:
		mockBuilder.addTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));
		mockBuilder.endTable();

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}
}
