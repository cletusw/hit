package test.model;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import model.Barcode;
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

public class ProductContainerTest {
	private ProductContainerManager pcManager;
	private ItemManager itemManager;
	private ProductManager productManager;
	private StorageUnit storageUnit1;
	private StorageUnit storageUnit2;
	private Product product1;
	private Product product2;
	private Item item1;
	private Item item1Copy;

	@Before
	public void setUp() throws Exception {
		pcManager = createMock(ProductContainerManager.class);
		itemManager = createMock(ItemManager.class);
		productManager = createMock(ProductManager.class);
		storageUnit1 = new StorageUnit("Cookie Jar", pcManager);
		storageUnit2 = new StorageUnit("Playdough Bin", pcManager);
		product1 = new Product("PROD1BARCODE", "Smelly socks", 0, 4, new ProductQuantity(1,
				Unit.COUNT), productManager);
		product2 = new Product("PROD2BARCODE00", "Green Jell-O", 365, 12, new ProductQuantity(
				3.5f, Unit.OUNCES), productManager);
		item1 = new Item(new Barcode("400000001968"), product1, storageUnit1, new Date(),
				itemManager);
		item1Copy = new Item(new Barcode("400000001968"), product1, storageUnit1, new Date(),
				itemManager);
	}

	@After
	public void tearDown() throws Exception {
		// Test the invariants!
		assertFalse(storageUnit1.getName() == null);
		assertFalse(storageUnit1.getName().equals(""));
		assertTrue(storageUnit1.getItemsSize() >= 0);
		assertTrue(storageUnit1.getProductsSize() >= 0);
		assertTrue(storageUnit1.getProductGroupsSize() >= 0);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddDuplicateItems() {
		storageUnit1.add(item1);
		storageUnit1.add(item1Copy);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddDuplicateItems2() {
		storageUnit1.add(item1);
		storageUnit1.add(item1Copy);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddDuplicateProducts() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		productGroup1.add(product1);

		Product product1Copy = new Product("PROD1BARCODE", "Pair of Smelly socks", 0, 4,
				new ProductQuantity(1, Unit.COUNT), productManager);

		productGroup1.add(product1Copy);
	}

	@Test
	public void testAddItem() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		productGroup2.add(product1);
		assertTrue(storageUnit1.add(item1));
		assertTrue(productGroup2.contains(item1));
	}

	@Test
	public void testAddItem2() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		assertTrue(storageUnit1.add(item1));
		assertFalse(productGroup2.contains(item1));
		assertFalse(productGroup1.contains(item1));
		assertTrue(storageUnit1.contains(item1));
	}

	@Test
	public void testCanRemove() {
		assertTrue(storageUnit1.canRemove());
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertTrue(productGroup1.canRemove());
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		productGroup2.add(product1);
		storageUnit1.add(item1);
		assertFalse(productGroup1.canRemove());
		assertFalse(storageUnit1.canRemove());
		productGroup2.remove(item1, itemManager);
		assertTrue(productGroup1.canRemove());
		assertTrue(storageUnit1.canRemove());
	}

	@Test
	public void testCanRemoveProduct() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		productGroup1.add(product1);
		assertTrue(productGroup1.canRemove(product1));
		storageUnit1.add(item1);
		assertFalse(productGroup1.canRemove(product1));
		productGroup1.remove(item1, itemManager);
		assertTrue(productGroup1.canRemove(product1));
	}

	@Test(expected = IllegalStateException.class)
	public void testCanRemoveProductGroup() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		productGroup2.add(product1);
		storageUnit1.add(item1);
		assertFalse(productGroup1.canRemove());
		assertFalse(productGroup2.canRemove());
		productGroup1.remove(productGroup2);
	}

	@Test
	public void testGetCurrentSupply() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		productGroup2.add(product1);
		storageUnit1.add(item1);
		storageUnit1.add(new Item(new Barcode("400000001920"), product1, storageUnit1,
				new Date(), itemManager));
		assertTrue(storageUnit1.getCurrentSupply(item1.getProduct()).equals(
				new ProductQuantity(2, Unit.COUNT)));
		assertTrue(productGroup1.getCurrentSupply(item1.getProduct()).equals(
				new ProductQuantity(2, Unit.COUNT)));
		Item item2 = new Item(new Barcode("400000001999"), product2, storageUnit1, new Date(),
				itemManager);
		assertTrue(storageUnit1.getCurrentSupply(item2.getProduct()).equals(
				new ProductQuantity(0, Unit.OUNCES)));
	}

	@Test
	public void testGetName() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertTrue(productGroup1.getName().equals("Cookies"));
		assertTrue(storageUnit1.getName().equals("Cookie Jar"));
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalProductAdd() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertTrue(storageUnit1.canAddProduct(product1.getBarcode()));
		productGroup1.add(product1);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		assertFalse(productGroup2.canAddProduct(product1.getBarcode()));
		productGroup2.add(product1);
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalProductAdd2() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		assertTrue(storageUnit1.canAddProduct(product1.getBarcode()));
		productGroup2.add(product1);
		assertFalse(storageUnit1.canAddProduct(product1.getBarcode()));
		storageUnit1.add(product1);
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalProductAdd3() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertTrue(storageUnit1.canAddProduct(product1.getBarcode()));
		storageUnit1.add(product1);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		assertFalse(productGroup2.canAddProduct(product1.getBarcode()));
		productGroup2.add(product1);
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalRemoveProduct() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		productGroup1.add(product1);
		storageUnit1.add(item1);
		productGroup1.remove(product1);
	}

	@Test
	public void testInvariants() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertFalse(productGroup1.getName() == null);
		assertFalse(productGroup1.getName().equals(""));
		assertTrue(productGroup1.getItemsSize() >= 0);
		assertTrue(productGroup1.getProductsSize() >= 0);
		assertTrue(productGroup1.getProductGroupsSize() >= 0);
	}

	@Test
	public void testMoveIntoContainer() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		productGroup1.add(product1);
		storageUnit1.add(item1);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		productGroup1.moveIntoContainer(item1, productGroup2);
		assertTrue(productGroup2.contains(item1));
		assertFalse(productGroup1.contains(item1));
	}

	@Test(expected = IllegalStateException.class)
	public void testMoveIntoContainerDuplicate() {
		storageUnit1.add(item1);
		storageUnit2.add(item1);
		storageUnit1.moveIntoContainer(item1, storageUnit2);
	}

	@Test(expected = IllegalStateException.class)
	public void testMoveIntoContainerFromEmpty() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, storageUnit2, pcManager);
		productGroup1.moveIntoContainer(item1, productGroup2);
	}

	@Test
	public void testProductGroupItems() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		productGroup1.add(item1.getProduct());
		assertEquals(0, productGroup1.getItemsSize());
		assertFalse(productGroup1.contains(item1));
	}

	@Test
	public void testProductGroupProductGroups() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertEquals(0, productGroup1.getProductGroupsSize());
		String productGroup2Name = "Chocolate Chip Cookies";
		assertTrue(productGroup1.canAddProductGroup(productGroup2Name));
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		assertFalse(productGroup1.canAddProductGroup(productGroup2Name));
		assertTrue(productGroup1.contains(productGroup2));
		assertEquals(1, productGroup1.getProductGroupsSize());
		String productGroup3Name = "No-Bake Cookies";
		assertFalse(productGroup1.containsProductGroup(productGroup3Name));
		ProductGroup productGroup3 = new ProductGroup("No-Bake Cookies", new ProductQuantity(
				1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
		assertTrue(productGroup1.contains(productGroup3));
		assertTrue(productGroup1.containsProductGroup(productGroup3.getName()));
		assertFalse(productGroup1.canAddProductGroup(productGroup3.getName()));
		assertEquals(2, productGroup1.getProductGroupsSize());
		assertFalse(productGroup1.canAddProductGroup(productGroup2.getName()));
		assertTrue(productGroup1.getProductGroup(productGroup3.getName())
				.equals(productGroup3));
		assertTrue(productGroup1.getProductGroup(productGroup2.getName())
				.equals(productGroup2));
		assertTrue(productGroup1.getProductGroup(productGroup1.getName()) == null);
	}

	@Test
	public void testProductGroupProducts() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		assertEquals(0, productGroup1.getProductsSize());
		assertFalse(productGroup1.contains(product1));
		productGroup1.add(product1);
		assertTrue(productGroup1.contains(product1));
		productGroup1.add(product2);
		assertTrue(productGroup1.contains(product2));
		assertTrue(productGroup1.containsProduct(product2.getBarcode()));
		// Should not allow two top-level Products of the same name
		assertEquals(2, productGroup1.getProductsSize());

		productGroup1.remove(product1);
		assertEquals(1, productGroup1.getProductsSize());
		assertTrue(productGroup1.getProductsIterator().next().equals(product2));

		assertTrue(productGroup1.getProduct(product2.getBarcode()).equals(product2));
		assertTrue(productGroup1.getProduct(product1.getBarcode()) == null);
	}

	@Test
	public void testStorageUnitCanAddProduct() {
		ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
				Unit.COUNT), Unit.KILOGRAMS, storageUnit1, pcManager);
		ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
				new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, storageUnit2, pcManager);
		ProductGroup productGroup3 = new ProductGroup("No-Bake Cookies", new ProductQuantity(
				1, Unit.COUNT), Unit.KILOGRAMS, productGroup2, pcManager);
		productGroup3.add(product1);
		assertTrue(storageUnit1.canAddProduct(product1.getBarcode()));
		assertTrue(productGroup1.canAddProduct(product1.getBarcode()));
		assertFalse(storageUnit2.canAddProduct(product1.getBarcode()));
		assertFalse(productGroup2.canAddProduct(product1.getBarcode()));
		assertFalse(productGroup3.canAddProduct(product1.getBarcode()));
	}
}
