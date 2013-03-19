package test.model.report;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import model.HomeInventoryTracker;
import model.report.NMonthSupplyReport;
import model.report.builder.ReportBuilder;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NMonthSupplyReportTest extends EasyMockSupport {
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
