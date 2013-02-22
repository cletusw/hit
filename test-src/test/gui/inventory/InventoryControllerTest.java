package test.gui.inventory;

import gui.inventory.IInventoryView;
import gui.inventory.InventoryController;
import model.ItemManager;
import model.ProductContainerManager;
import model.ProductManager;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryControllerTest {
	private InventoryController inventoryController;

	@Before
	public void setUp() throws Exception {
		IInventoryView view = EasyMock.createMock(IInventoryView.class);
		ItemManager itemManager = EasyMock.createMock(ItemManager.class);
		ProductManager productManager = EasyMock.createMock(ProductManager.class);
		ProductContainerManager productContainerManager = EasyMock
				.createMock(ProductContainerManager.class);
		inventoryController = new InventoryController(view, itemManager, productManager,
				productContainerManager);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddItems() {
	}

	@Test
	public void testAddProductGroup() {
	}

	@Test
	public void testAddProductToContainer() {
	}

	@Test
	public void testAddStorageUnit() {
	}

	@Test
	public void testCanAddItems() {
	}

	@Test
	public void testCanAddProductGroup() {
	}

	@Test
	public void testCanAddStorageUnit() {
	}

	@Test
	public void testCanDeleteProduct() {
	}

	@Test
	public void testCanDeleteProductGroup() {
	}

	@Test
	public void testCanDeleteStorageUnit() {
	}

	@Test
	public void testCanEditItem() {
	}

	@Test
	public void testCanEditProduct() {
	}

	@Test
	public void testCanEditProductGroup() {
	}

	@Test
	public void testCanEditStorageUnit() {
	}

	@Test
	public void testCanRemoveItem() {
	}

	@Test
	public void testCanRemoveItems() {
	}

	@Test
	public void testCanTransferItems() {
	}

	@Test
	public void testDeleteProduct() {
	}

	@Test
	public void testDeleteProductGroup() {
	}

	@Test
	public void testDeleteStorageUnit() {
	}

	@Test
	public void testEditItem() {
	}

	@Test
	public void testEditProduct() {
	}

	@Test
	public void testEditProductGroup() {
	}

	@Test
	public void testEditStorageUnit() {
	}

	@Test
	public void testItemSelectionChanged() {
	}

	@Test
	public void testMoveItemToContainer() {
	}

	@Test
	public void testProductContainerSelectionChanged() {
	}

	@Test
	public void testProductSelectionChanged() {
	}

	@Test
	public void testRemoveItem() {
	}

	@Test
	public void testRemoveItems() {
	}

	@Test
	public void testTransferItems() {
	}

}
