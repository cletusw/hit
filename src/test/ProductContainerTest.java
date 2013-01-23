package test;

import model.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductContainerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void PGtestItems() {
		System.out.print("Testing ProductGroup Item logic...");
		Item one = new Item("item1");
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
		
		System.out.println("done.");
	}

	@Test
	public void PGtestProducts() {
		System.out.print("Testing ProductGroup Product logic...");
		Product one = new Product("prod1");
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
		assertEquals(0,pg1.getProductsSize());
		
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
}

