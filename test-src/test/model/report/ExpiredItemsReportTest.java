package test.model.report;

import model.ProductContainerManager;
import model.report.ExpiredItemsReport;

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
	public void test() {
		ProductContainerManager productContainerManager = createNiceMock(ProductContainerManager.class);
		new ExpiredItemsReport(productContainerManager);
	}
}
