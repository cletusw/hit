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

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnEmptyTree() {
		HomeInventoryTracker hit = new HomeInventoryTracker();
		ExpiredItemsReport report = new ExpiredItemsReport(hit.getProductContainerManager());

		ReportBuilder mockBuilder = createMock(ReportBuilder.class);
		// Expect:
		mockBuilder.addTitle("Expired Items");
		mockBuilder.startTable(Arrays.asList("Description", "Storage Unit", "Product Group",
				"Entry Date", "Expire Date", "Item Barcode"));

		replayAll();

		report.construct(mockBuilder);

		verifyAll();
	}
}
