package test.gui.inventory;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gui.inventory.IInventoryView;
import gui.inventory.InventoryController;
import gui.inventory.ProductContainerData;

import java.util.ArrayList;
import java.util.Iterator;

import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductManager;
import model.ProductQuantity;
import model.StorageUnit;
import model.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryControllerTest {
	private InventoryController inventoryController;
	private IInventoryView mockView;

	private StorageUnit storageUnit;
	private ProductGroup productGroup;
	private Product product;
	private Item item;

	@Before
	public void setUp() throws Exception {
		ProductManager productManager = createNiceMock(ProductManager.class);
		ItemManager itemManager = createNiceMock(ItemManager.class);
		ProductContainerManager mockProductContainerManager = createNiceMock(ProductContainerManager.class);

		// Fixtures
		storageUnit = new StorageUnit("Test Storage Unit", mockProductContainerManager);
		productGroup = new ProductGroup("Test Product Group", new ProductQuantity(1,
				Unit.COUNT), Unit.COUNT, storageUnit, mockProductContainerManager);
		product = new Product("Test Barcode", "Test Description", 1, 1, new ProductQuantity(1,
				Unit.COUNT), productManager);
		item = new Item(product, storageUnit, itemManager);

		reset(mockProductContainerManager);
		mockView = createNiceMock(IInventoryView.class);
		expect(mockView.getProductContainerManager()).andStubReturn(
				mockProductContainerManager);
		Iterator<StorageUnit> storageUnitIterator = (new ArrayList<StorageUnit>()).iterator();
		expect(mockProductContainerManager.getStorageUnitIterator()).andStubReturn(
				storageUnitIterator);
		replay(mockView);
		replay(mockProductContainerManager);

		inventoryController = new InventoryController(mockView);

		reset(mockProductContainerManager);
		reset(mockView);
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
		ProductContainerData storageUnitData = new ProductContainerData();
		storageUnitData.setTag(storageUnit);

		expect(mockView.getSelectedProductContainer()).andStubReturn(storageUnitData);
		replay(mockView);

		assertTrue(inventoryController.canDeleteStorageUnit());
		storageUnit.add(item);
		assertFalse(inventoryController.canDeleteStorageUnit());
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
