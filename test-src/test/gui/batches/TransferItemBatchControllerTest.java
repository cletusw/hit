package test.gui.batches;

import gui.batches.TransferItemBatchController;
import gui.common.DataWrapper;
import gui.common.IView;
import model.ProductContainerManager;
import model.StorageUnit;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransferItemBatchControllerTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ProductContainerManager mockProductContainerManager = createNiceMock(ProductContainerManager.class);
		StorageUnit source = new StorageUnit("Source", mockProductContainerManager);
		StorageUnit destination = new StorageUnit("Destination", mockProductContainerManager);

		IView mockView = createNiceMock(IView.class);
		new TransferItemBatchController(mockView, DataWrapper.wrap(destination));
	}

}
