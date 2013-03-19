package test.model.report;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.ConcreteProductContainerManager;
import model.ConcreteProductManager;
import model.HomeInventoryTracker;
import model.report.NMonthSupplyReport;
import model.report.builder.ReportBuilder;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NMonthSupplyReportTest extends EasyMockSupport {
	private static List<String> productsHeaders(int months) {
		return Arrays.asList("Description", "Barcode", Integer.toString(months)
				+ "-Month Supply", "Current Supply");
	}

	private HomeInventoryTracker hit;
	private NMonthSupplyReport report;
	private ReportBuilder mockBuilder;

	@Before
	public void setUp() throws Exception {
		hit = new HomeInventoryTracker();
		report = new NMonthSupplyReport(hit.getProductManager(),
				hit.getProductContainerManager());
		mockBuilder = createMock(ReportBuilder.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnEmptyTreeFor3Months() {
		NMonthSupplyReport report = new NMonthSupplyReport(new ConcreteProductManager(),
				new ConcreteProductContainerManager());

		// Expect:
		mockBuilder.addDocumentTitle("3-Month Supply Report");
		mockBuilder.startTable(productsHeaders(3));

		replayAll();

		report.construct(mockBuilder, 3);

		verifyAll();
	}

	@Test
	public void testOnEmptyTreeFor4Months() {
		NMonthSupplyReport report = new NMonthSupplyReport(new ConcreteProductManager(),
				new ConcreteProductContainerManager());

		// Expect:
		mockBuilder.addDocumentTitle("4-Month Supply Report");
		mockBuilder.startTable(productsHeaders(4));

		replayAll();

		report.construct(mockBuilder, 4);

		verifyAll();
	}

	@Test
	public void testRunTime() {
		report.construct(mockBuilder, 3);
		Date firstRunTime = report.getLastRunTime();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		report.construct(mockBuilder, 3);
		assertTrue(firstRunTime.before(report.getLastRunTime()));
	}
}
