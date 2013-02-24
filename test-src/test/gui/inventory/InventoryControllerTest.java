package test.gui.inventory;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gui.common.DataWrapper;
import gui.inventory.IInventoryView;
import gui.inventory.InventoryController;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductManager;
import model.ProductQuantity;
import model.StorageUnit;
import model.Unit;

import org.easymock.Capture;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryControllerTest extends EasyMockSupport {
	private InventoryController inventoryController;
	private IInventoryView mockView;
	private ItemManager itemManager;
	private StorageUnit storageUnit;
	private ProductGroup productGroup;
	private Product product;
	private Item item;

	@Before
	public void setUp() throws Exception {
		setUpModelFixtures();

		ProductContainerManager mockProductContainerManager = createNiceMock(ProductContainerManager.class);
		ItemManager mockItemManager = createNiceMock(ItemManager.class);
		ProductManager mockProductManager = createNiceMock(ProductManager.class);
		mockView = createNiceMock(IInventoryView.class);
		expect(mockView.getProductContainerManager()).andStubReturn(
				mockProductContainerManager);
		expect(mockView.getItemManager()).andStubReturn(mockItemManager);
		expect(mockView.getProductManager()).andStubReturn(mockProductManager);

		Iterator<StorageUnit> storageUnitIterator = (new ArrayList<StorageUnit>()).iterator();
		expect(mockProductContainerManager.getStorageUnitIterator()).andStubReturn(
				storageUnitIterator);

		replayAll();

		inventoryController = new InventoryController(mockView);

		resetAll();
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
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(storageUnit));
		replayAll();

		assertFalse(inventoryController.canDeleteStorageUnit());
		storageUnit.remove(item, itemManager);
		assertTrue(inventoryController.canDeleteStorageUnit());
	}

	@Test
	public void testCanEditItemWithItemSelected() {
		ItemData itemData = DataWrapper.wrap(item);
		expect(mockView.getSelectedItem()).andStubReturn(itemData);
		replayAll();

		assertTrue(inventoryController.canEditItem());
	}

	@Test
	public void testCanEditItemWithNothingSelected() {
		assertFalse(inventoryController.canEditItem());
	}

	@Test
	public void testCanEditProduct() {
		// assertTrue(inventoryController.canEditProduct());
	}

	@Test
	public void testCanEditProductGroup() {
		// assertTrue(inventoryController.canEditProductGroup());
	}

	@Test
	public void testCanEditStorageUnit() {
		// assertTrue(inventoryController.canEditStorageUnit());
	}

	@Test
	public void testCanRemoveItem() {
		ItemData itemData = DataWrapper.wrap(item);
		expect(mockView.getSelectedItem()).andStubReturn(itemData);
		replayAll();

		assertTrue(inventoryController.canRemoveItem());
	}

	@Test
	public void testCanRemoveItems() {
		// assertTrue(inventoryController.canRemoveItems());
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
	public void testProductContainerSelectionChangedEmptyContainer() {
		Capture<ProductData[]> productListCapture = new Capture<ProductData[]>();

		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(productGroup));
		mockView.setProducts(capture(productListCapture));
		replay(mockView);

		inventoryController.productContainerSelectionChanged();

		List<ProductData> productList = Arrays.asList(productListCapture.getValue());
		assertTrue(productList.size() == 0);
	}

	@Test
	public void testProductContainerSelectionChangedNonEmptyContainer() {
		Capture<ProductData[]> productListCapture = new Capture<ProductData[]>();

		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(storageUnit));
		mockView.setProducts(capture(productListCapture));
		replay(mockView);

		inventoryController.productContainerSelectionChanged();

		List<ProductData> productList = Arrays.asList(productListCapture.getValue());
		assertTrue(productList.size() == 1);
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

	private void setUpModelFixtures() {
		ProductManager productManager = createNiceMock(ProductManager.class);
		itemManager = createNiceMock(ItemManager.class);
		ProductContainerManager mockProductContainerManager = createNiceMock(ProductContainerManager.class);

		storageUnit = new StorageUnit("Test Storage Unit", mockProductContainerManager);

		productGroup = new ProductGroup("Test Product Group", new ProductQuantity(1,
				Unit.COUNT), Unit.COUNT, storageUnit, mockProductContainerManager);

		product = new Product("Test Barcode", "Test Description", 1, 1, new ProductQuantity(1,
				Unit.COUNT), productManager);

		item = new Item(product, storageUnit, itemManager);

		storageUnit.add(item);
	}

}
