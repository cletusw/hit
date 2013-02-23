package test.gui.inventory;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertTrue;
import gui.inventory.IInventoryView;
import gui.inventory.InventoryController;
import gui.inventory.InventoryView;
import gui.main.GUI;
import model.ConcreteProductContainerManager;
import model.ItemManager;
import model.ProductContainerManager;
import model.ProductManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryControllerTest {
	private InventoryController inventoryController;

	@Before
	public void setUp() throws Exception {
		ItemManager itemManager = createMock(ItemManager.class);
		ProductManager productManager = createMock(ProductManager.class);
		// ProductContainerManager productContainerManager = EasyMock
		// .createMock(ProductContainerManager.class);
		ProductContainerManager productContainerManager = new ConcreteProductContainerManager();
		String[] args = { "", "" };
		IInventoryView view = new InventoryView(new GUI(args), itemManager, productManager,
				productContainerManager); // createMock(IInventoryView.class);
		inventoryController = new InventoryController(view, itemManager, productManager,
				productContainerManager);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddItems() {
		inventoryController.addItems();
	}

	@Test
	public void testAddProductGroup() {
		inventoryController.addProductGroup();
	}

	@Test
	public void testAddProductToContainer() {
		// TODO
	}

	@Test
	public void testAddStorageUnit() {
		inventoryController.addStorageUnit();
	}

	@Test
	public void testCanAddItems() {
		assertTrue(inventoryController.canAddItems());
	}

	@Test
	public void testCanAddProductGroup() {
		assertTrue(inventoryController.canAddProductGroup());
	}

	@Test
	public void testCanAddStorageUnit() {
		assertTrue(inventoryController.canAddStorageUnit());
	}

	@Test
	public void testCanDeleteProduct() {
		// TODO
	}

	@Test
	public void testCanDeleteProductGroup() {
		// TODO
	}

	@Test
	public void testCanDeleteStorageUnit() {
		// TODO
	}

	@Test
	public void testCanEditItem() {
		assertTrue(inventoryController.canEditItem());
	}

	@Test
	public void testCanEditProduct() {
		assertTrue(inventoryController.canEditProduct());
	}

	@Test
	public void testCanEditProductGroup() {
		assertTrue(inventoryController.canEditProductGroup());
	}

	@Test
	public void testCanEditStorageUnit() {
		assertTrue(inventoryController.canEditStorageUnit());
	}

	@Test
	public void testCanRemoveItem() {
		assertTrue(inventoryController.canRemoveItem());
	}

	@Test
	public void testCanRemoveItems() {
		assertTrue(inventoryController.canRemoveItems());
	}

	@Test
	public void testCanTransferItems() {
		assertTrue(inventoryController.canTransferItems());
	}

	@Test
	public void testDeleteProduct() {
		// TODO
	}

	@Test
	public void testDeleteProductGroup() {
		// TODO
	}

	@Test
	public void testDeleteStorageUnit() {
		// TODO
	}

	@Test
	public void testEditItem() {
		// TODO
	}

	@Test
	public void testEditProduct() {
		// TODO
	}

	@Test
	public void testEditProductGroup() {
		// TODO
	}

	@Test
	public void testEditStorageUnit() {
		// TODO
	}

	@Test
	public void testItemSelectionChanged() {
		// TODO
	}

	@Test
	public void testMoveItemToContainer() {
		// TODO
	}

	@Test
	public void testProductContainerSelectionChanged() {
		// TODO
	}

	@Test
	public void testProductSelectionChanged() {
		// TODO
	}

	@Test
	public void testRemoveItem() {
		// TODO
	}

	@Test
	public void testRemoveItems() {
		// TODO
	}

	@Test
	public void testTransferItems() {
		// TODO
	}

}
