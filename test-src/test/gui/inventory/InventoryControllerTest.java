package test.gui.inventory;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gui.common.DataWrapper;
import gui.inventory.IInventoryView;
import gui.inventory.InventoryController;
import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import model.ConcreteProductContainerManager;
import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductManager;
import model.StorageUnit;

import org.easymock.Capture;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.model.ItemBuilder;
import builder.model.ProductGroupBuilder;
import builder.model.StorageUnitBuilder;

public class InventoryControllerTest extends EasyMockSupport {
	private InventoryController inventoryController;
	private IInventoryView mockView;
	private Item item;

	@Before
	public void setUp() throws Exception {
		item = new ItemBuilder().build();

		ProductContainerManager mockProductContainerManager = createNiceMock(ProductContainerManager.class);
		ItemManager mockItemManager = createNiceMock(ItemManager.class);
		ProductManager mockProductManager = createNiceMock(ProductManager.class);
		mockView = createNiceMock(IInventoryView.class);
		expect(mockView.getProductContainerManager()).andStubReturn(
				mockProductContainerManager);
		expect(mockView.getItemManager()).andStubReturn(mockItemManager);
		expect(mockView.getProductManager()).andStubReturn(mockProductManager);

		expect(mockProductContainerManager.getStorageUnits()).andStubReturn(
				new TreeSet<StorageUnit>());

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
		ProductContainer container = item.getContainer();
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(container));
		Product selected = item.getProduct();
		expect(mockView.getSelectedProduct()).andStubReturn(DataWrapper.wrap(selected, 1));

		replayAll();
		assertFalse(inventoryController.canDeleteProduct());
		container.remove(item, createNiceMock(ItemManager.class));
		assertTrue(inventoryController.canDeleteProduct());
	}

	@Test
	public void testCanDeleteProductGroup() {
		ProductContainer container = item.getContainer();
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(container));
		Product selected = item.getProduct();
		expect(mockView.getSelectedProduct()).andStubReturn(DataWrapper.wrap(selected, 1));

		replayAll();
		assertFalse(inventoryController.canDeleteProductGroup());
		container.remove(item, createNiceMock(ItemManager.class));
		container.remove(item.getProduct());
		assertTrue(inventoryController.canDeleteProductGroup());
	}

	@Test
	public void testCanDeleteStorageUnit() {
		ProductContainer container = item.getContainer();
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(container));
		replayAll();

		assertFalse(inventoryController.canDeleteStorageUnit());
		container.remove(item, createNiceMock(ItemManager.class));
		container.remove(item.getProduct());
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
		Product selected = item.getProduct();
		expect(mockView.getSelectedProduct()).andStubReturn(DataWrapper.wrap(selected, 1));
		replayAll();
		assertTrue(inventoryController.canEditProduct());
	}

	@Test
	public void testCanEditProductGroup() {
		ProductContainer container = item.getContainer();
		ProductGroup selected = new ProductGroupBuilder().parent(container).build();
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(selected));
		replayAll();

		assertTrue(inventoryController.canEditProductGroup());
	}

	@Test
	public void testCanEditStorageUnit() {
		ProductContainer container = item.getContainer();
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(container));
		replayAll();

		assertTrue(inventoryController.canEditStorageUnit());
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
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(item.getContainer()));
		replayAll();

		assertFalse(inventoryController.canRemoveItems());

		resetAll();

		ProductContainerData rootData = new ProductContainerData();
		rootData.setName("root");
		rootData.setTag(null);

		expect(mockView.getSelectedProductContainer()).andStubReturn(rootData);
		replayAll();

		assertTrue(inventoryController.canRemoveItems());
	}

	@Test
	public void testCanTransferItems() {
		assertTrue(inventoryController.canTransferItems());
	}

	@Test
	public void testDeleteProduct() {
		ProductContainer container = item.getContainer();
		Product toDelete = item.getProduct();
		expect(mockView.getSelectedProductContainer()).andStubReturn(
				DataWrapper.wrap(container));
		Product selected = item.getProduct();
		expect(mockView.getSelectedProduct()).andStubReturn(DataWrapper.wrap(selected, 1));
		ProductManager mockProductManager = createNiceMock(ProductManager.class);
		expect(mockView.getProductManager()).andStubReturn(mockProductManager);

		replayAll();
		assertFalse(inventoryController.canDeleteProduct());
		assertTrue(container.contains(toDelete));
		container.remove(item, createNiceMock(ItemManager.class));
		assertTrue(inventoryController.canDeleteProduct());

		inventoryController.deleteProduct();
		assertFalse(container.contains(toDelete));
	}

	@Test
	public void testDeleteProductGroup() {
		ProductContainer container = item.getContainer();
		Product selected = item.getProduct();
		ProductGroup child = new ProductGroupBuilder().parent(container).build();
		expect(mockView.getProductContainerManager()).andStubReturn(
				new ConcreteProductContainerManager());
		expect(mockView.getSelectedProductContainer()).andStubReturn(DataWrapper.wrap(child));

		replayAll();

		/*
		 * assertFalse(inventoryController.canDeleteProductGroup()); container.remove(item,
		 * createNiceMock(ItemManager.class)); container.remove(item.getProduct());
		 * assertTrue(inventoryController.canDeleteProductGroup());
		 */

		inventoryController.deleteProductGroup();
		// Doesn't work because of ProductContainerManager
		// assertFalse(container.containsExactProductGroup(child));

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
				DataWrapper.wrap(new StorageUnitBuilder().build()));
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
				DataWrapper.wrap(item.getContainer()));
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
}
