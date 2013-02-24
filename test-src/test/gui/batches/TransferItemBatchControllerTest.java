package test.gui.batches;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Item;
import model.ItemManager;
import model.StorageUnit;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fixture.model.ItemFixture;
import fixture.model.StorageUnitFixture;
import gui.batches.ITransferItemBatchView;
import gui.batches.TransferItemBatchController;
import gui.common.DataWrapper;

public class TransferItemBatchControllerTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StorageUnit source = new StorageUnitFixture();
		StorageUnit destination = new StorageUnitFixture();
		Item item1 = new ItemFixture(source);

		ITransferItemBatchView mockView = createNiceMock(ITransferItemBatchView.class);
		ItemManager mockItemManager = createNiceMock(ItemManager.class);

		TransferItemBatchController controller = new TransferItemBatchController(mockView,
				DataWrapper.wrap(destination));

		expect(mockView.getBarcode()).andStubReturn(item1.getBarcode());
		expect(mockView.getItemManager()).andStubReturn(mockItemManager);
		expect(mockItemManager.getItemByItemBarcode(item1.getBarcode())).andStubReturn(item1);
		replayAll();

		assertTrue(source.contains(item1));
		assertFalse(destination.contains(item1));
		controller.transferItem();
		assertFalse(source.contains(item1));
		assertTrue(destination.contains(item1));
	}

}
