package test.gui.batches;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gui.batches.ITransferItemBatchView;
import gui.batches.TransferItemBatchController;
import gui.common.DataWrapper;
import model.Item;
import model.ItemManager;
import model.StorageUnit;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.model.ItemBuilder;
import builder.model.StorageUnitBuilder;

public class TransferItemBatchControllerTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StorageUnit source = new StorageUnitBuilder().build();
		StorageUnit destination = new StorageUnitBuilder().build();
		Item item1 = new ItemBuilder().container(source).build();

		ITransferItemBatchView mockView = createNiceMock(ITransferItemBatchView.class);
		ItemManager mockItemManager = createNiceMock(ItemManager.class);

		expect(mockView.getUseScanner()).andStubReturn(true);
		expect(mockView.getBarcode()).andStubReturn(item1.getBarcode());
		expect(mockView.getItemManager()).andStubReturn(mockItemManager);
		expect(mockItemManager.getItemByItemBarcode(item1.getBarcode())).andStubReturn(item1);
		replayAll();

		TransferItemBatchController controller = new TransferItemBatchController(mockView,
				DataWrapper.wrap(destination));

		assertTrue(source.contains(item1));
		assertFalse(destination.contains(item1));
		controller.transferItem();
		assertFalse(source.contains(item1));
		assertTrue(destination.contains(item1));
	}

}
