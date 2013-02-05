package model;

import model.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductContainerTest {
	static ProductContainer testStorageUnit1;
	static ProductContainer testProductGroup1;
	static Product product1;
	static Product product2;
	static Item item1;
	static Item item2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testStorageUnit1 = new StorageUnit("CupboardTest", Unit.COUNT);
		testProductGroup1 = new ProductGroup("Cookies", new ProductQuantity(0, Unit.COUNT), Unit.KILOGRAMS);
		//product1 = new Product("PROD1BARCODE", "Smelly socks", 0, int tms, ProductQuantity pq, ProductManager manager) {);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void PGtestItems() {
		System.out.print("Testing ProductGroup Item logic...");
		//Product product1 = new Product
		/*
		Item one = new Item(new Barcode("001", "item1", null);
		Item two = new Item("item2");
		Item oneCopy = new Item("item1");
		ProductGroup pg1 = new ProductGroup("pg1",null);
		
		assertTrue(pg1.add(one));
		assertTrue(pg1.add(two));
		assertTrue(pg1.add(oneCopy));
		assertEquals(3,pg1.getItemsSize());
				
		pg1.removeItems("item1");
		assertEquals(1,pg1.getItemsSize());

		pg1.clearAllItems();
		assertEquals(0,pg1.getItemsSize());
		*/
		System.out.println("done.");
	}

	@Test
	public void PGtestProducts() {
		System.out.print("Testing ProductGroup Product logic...");
		/*Product one = new Product("prod1");
		Product two = new Product("prod2");
		Product oneCopy = new Product("prod1");
		ProductGroup pg1 = new ProductGroup("pg1",null);
		
		assertTrue(pg1.add(one));
		assertTrue(pg1.add(two));
		assertFalse(pg1.add(one));
		assertEquals(2,pg1.getProductsSize());
		
		assertFalse(pg1.add(oneCopy));
		
		Product three = new Product("prod3");
		assertTrue(pg1.add(three));
		
		assertTrue(pg1.removeProduct("prod2"));
		assertEquals(2,pg1.getProductsSize());
		
		pg1.clearAllProducts();
		assertEquals(0,pg1.getProductsSize());*/
		
		System.out.println("done.");
	}
	
	@Test
	public void PGtestProductGroups() {
		System.out.print("Testing ProductGroup ProductGroup logic...");
		System.out.println("done.");
	}
	
	@Test
	public void SUtestItems() {
		System.out.print("Testing StorageUnit Item logic...");
		System.out.println("done.");
	}
	
	@Test
	public void SUtestProducts() {
		System.out.print("Testing StorageUnit Product logic...");
		System.out.println("done.");
	}
	
	@Test
	public void SUtestProductGroups() {
		System.out.print("Testing StorageUnit ProductGroup logic...");
		System.out.println("done.");
	}
	
	@Test
	public void testProductContainer() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetUnit() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetItemsSize() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetProductsSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPGroupsSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProductGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentSupplyProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentSupply() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProductGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanAddProductGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItemItemManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveIntoContainer() {
		fail("Not yet implemented");
	}

	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProductContainer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProductGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearAllItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearAllProducts() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearAllProductGroups() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidName() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}	
}

