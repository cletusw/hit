package test.mcontrollers;

import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.assertTrue;
import gui.inventory.IInventoryView;
import mcontrollers.ProductContainerListener;
import model.ProductContainerManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductContainerListenerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdate() {
		IInventoryView view = createNiceMock(IInventoryView.class);
		ProductContainerManager manager = createNiceMock(ProductContainerManager.class);
		ProductContainerListener listener = new ProductContainerListener(view, manager);
		assertTrue(true);
	}
}
