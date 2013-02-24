package test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Item;
import model.Product;
import model.StorageUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fixture.model.ItemFixture;
import fixture.model.ProductFixture;
import fixture.model.StorageUnitFixture;

public class ProductContainerTest {
	private StorageUnit storageUnit;
	private Item item;

	@Before
	public void setUp() throws Exception {
		storageUnit = new StorageUnitFixture();
		item = new ItemFixture(storageUnit);
	}

	@After
	public void tearDown() throws Exception {
		// Test the invariants!
		assertFalse(storageUnit.getName() == null);
		assertFalse(storageUnit.getName().equals(""));
		assertTrue(storageUnit.getItemsSize() >= 0);
		assertTrue(storageUnit.getProductsSize() >= 0);
		assertTrue(storageUnit.getProductGroupsSize() >= 0);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddDuplicateItems() {
		storageUnit.add(item);
		storageUnit.add(item);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddDuplicateProducts() {
		Product product = new ProductFixture();
		storageUnit.add(product);
		storageUnit.add(product);
	}

	// @Test
	// public void testAddItem() {
	// ProductGroup productGroup1 = new ProductGroupFixture(storageUnit);
	// ProductGroup productGroup2 = new ProductGroupFixture(productGroup1);
	// Item item1 = new ItemFixture();
	// productGroup2.add(item1.getProduct());
	// assertTrue(storageUnit.add(item1));
	// assertTrue(productGroup2.contains(item1));
	// }

	// @Test
	// public void testAddItem2() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// assertTrue(storageUnit.add(item));
	// assertFalse(productGroup2.contains(item));
	// assertFalse(productGroup1.contains(item));
	// assertTrue(storageUnit.contains(item));
	// }
	//
	// @Test
	// public void testCanRemove() {
	// assertTrue(storageUnit.canRemove());
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertTrue(productGroup1.canRemove());
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// productGroup2.add(product1);
	// storageUnit.add(item);
	// assertFalse(productGroup1.canRemove());
	// assertFalse(storageUnit.canRemove());
	// productGroup2.remove(item, itemManager);
	// assertTrue(productGroup1.canRemove());
	// assertTrue(storageUnit.canRemove());
	// }
	//
	// @Test
	// public void testCanRemoveProduct() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// productGroup1.add(product1);
	// assertTrue(productGroup1.canRemove(product1));
	// storageUnit.add(item);
	// assertFalse(productGroup1.canRemove(product1));
	// productGroup1.remove(item, itemManager);
	// assertTrue(productGroup1.canRemove(product1));
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testCanRemoveProductGroup() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// productGroup2.add(product1);
	// storageUnit.add(item);
	// assertFalse(productGroup1.canRemove());
	// assertFalse(productGroup2.canRemove());
	// productGroup1.remove(productGroup2);
	// }
	//
	// @Test
	// public void testGetCurrentSupply() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// productGroup2.add(product1);
	// storageUnit.add(item);
	// storageUnit.add(new Item(new Barcode("400000001920"), product1, storageUnit,
	// new Date(), itemManager));
	// assertTrue(storageUnit.getCurrentSupply(item.getProduct()).equals(
	// new ProductQuantity(2, Unit.COUNT)));
	// assertTrue(productGroup1.getCurrentSupply(item.getProduct()).equals(
	// new ProductQuantity(2, Unit.COUNT)));
	// Item item2 = new Item(new Barcode("400000001999"), product2, storageUnit, new Date(),
	// itemManager);
	// assertTrue(storageUnit.getCurrentSupply(item2.getProduct()).equals(
	// new ProductQuantity(0, Unit.OUNCES)));
	// }
	//
	// @Test
	// public void testGetName() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertTrue(productGroup1.getName().equals("Cookies"));
	// assertTrue(storageUnit.getName().equals("Cookie Jar"));
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testIllegalProductAdd() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertTrue(storageUnit.canAddProduct(product1.getBarcode()));
	// productGroup1.add(product1);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// assertFalse(productGroup2.canAddProduct(product1.getBarcode()));
	// productGroup2.add(product1);
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testIllegalProductAdd2() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// assertTrue(storageUnit.canAddProduct(product1.getBarcode()));
	// productGroup2.add(product1);
	// assertFalse(storageUnit.canAddProduct(product1.getBarcode()));
	// storageUnit.add(product1);
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testIllegalProductAdd3() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertTrue(storageUnit.canAddProduct(product1.getBarcode()));
	// storageUnit.add(product1);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// assertFalse(productGroup2.canAddProduct(product1.getBarcode()));
	// productGroup2.add(product1);
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testIllegalRemoveProduct() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// productGroup1.add(product1);
	// storageUnit.add(item);
	// productGroup1.remove(product1);
	// }
	//
	// @Test
	// public void testInvariants() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertFalse(productGroup1.getName() == null);
	// assertFalse(productGroup1.getName().equals(""));
	// assertTrue(productGroup1.getItemsSize() >= 0);
	// assertTrue(productGroup1.getProductsSize() >= 0);
	// assertTrue(productGroup1.getProductGroupsSize() >= 0);
	// }
	//
	// @Test
	// public void testMoveIntoContainer() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// productGroup1.add(product1);
	// storageUnit.add(item);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// productGroup1.moveIntoContainer(item, productGroup2);
	// assertTrue(productGroup2.contains(item));
	// assertFalse(productGroup1.contains(item));
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testMoveIntoContainerDuplicate() {
	// storageUnit.add(item);
	// storageUnit2.add(item);
	// storageUnit.moveIntoContainer(item, storageUnit2);
	// }
	//
	// @Test(expected = IllegalStateException.class)
	// public void testMoveIntoContainerFromEmpty() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, storageUnit2, pcManager);
	// productGroup1.moveIntoContainer(item, productGroup2);
	// }
	//
	// @Test
	// public void testProductGroupItems() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// productGroup1.add(item.getProduct());
	// assertEquals(0, productGroup1.getItemsSize());
	// assertFalse(productGroup1.contains(item));
	// }
	//
	// @Test
	// public void testProductGroupProductGroups() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertEquals(0, productGroup1.getProductGroupsSize());
	// String productGroup2Name = "Chocolate Chip Cookies";
	// assertTrue(productGroup1.canAddProductGroup(productGroup2Name));
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// assertFalse(productGroup1.canAddProductGroup(productGroup2Name));
	// assertTrue(productGroup1.contains(productGroup2));
	// assertEquals(1, productGroup1.getProductGroupsSize());
	// String productGroup3Name = "No-Bake Cookies";
	// assertFalse(productGroup1.containsProductGroup(productGroup3Name));
	// ProductGroup productGroup3 = new ProductGroup("No-Bake Cookies", new ProductQuantity(
	// 1, Unit.COUNT), Unit.KILOGRAMS, productGroup1, pcManager);
	// assertTrue(productGroup1.contains(productGroup3));
	// assertTrue(productGroup1.containsProductGroup(productGroup3.getName()));
	// assertFalse(productGroup1.canAddProductGroup(productGroup3.getName()));
	// assertEquals(2, productGroup1.getProductGroupsSize());
	// assertFalse(productGroup1.canAddProductGroup(productGroup2.getName()));
	// assertTrue(productGroup1.getProductGroup(productGroup3.getName())
	// .equals(productGroup3));
	// assertTrue(productGroup1.getProductGroup(productGroup2.getName())
	// .equals(productGroup2));
	// assertTrue(productGroup1.getProductGroup(productGroup1.getName()) == null);
	// }
	//
	// @Test
	// public void testProductGroupProducts() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// assertEquals(0, productGroup1.getProductsSize());
	// assertFalse(productGroup1.contains(product1));
	// productGroup1.add(product1);
	// assertTrue(productGroup1.contains(product1));
	// productGroup1.add(product2);
	// assertTrue(productGroup1.contains(product2));
	// assertTrue(productGroup1.containsProduct(product2.getBarcode()));
	// // Should not allow two top-level Products of the same name
	// assertEquals(2, productGroup1.getProductsSize());
	//
	// productGroup1.remove(product1);
	// assertEquals(1, productGroup1.getProductsSize());
	// assertTrue(productGroup1.getProductsIterator().next().equals(product2));
	//
	// assertTrue(productGroup1.getProduct(product2.getBarcode()).equals(product2));
	// assertTrue(productGroup1.getProduct(product1.getBarcode()) == null);
	// }
	//
	// @Test
	// public void testStorageUnitCanAddProduct() {
	// ProductGroup productGroup1 = new ProductGroup("Cookies", new ProductQuantity(1,
	// Unit.COUNT), Unit.KILOGRAMS, storageUnit, pcManager);
	// ProductGroup productGroup2 = new ProductGroup("Chocolate Chip Cookies",
	// new ProductQuantity(1, Unit.COUNT), Unit.KILOGRAMS, storageUnit2, pcManager);
	// ProductGroup productGroup3 = new ProductGroup("No-Bake Cookies", new ProductQuantity(
	// 1, Unit.COUNT), Unit.KILOGRAMS, productGroup2, pcManager);
	// productGroup3.add(product1);
	// assertTrue(storageUnit.canAddProduct(product1.getBarcode()));
	// assertTrue(productGroup1.canAddProduct(product1.getBarcode()));
	// assertFalse(storageUnit2.canAddProduct(product1.getBarcode()));
	// assertFalse(productGroup2.canAddProduct(product1.getBarcode()));
	// assertFalse(productGroup3.canAddProduct(product1.getBarcode()));
	// }
}
