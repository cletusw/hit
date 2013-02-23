package test.gui.inventory;

import static org.junit.Assert.assertTrue;
import gui.inventory.ProductContainerData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductContainerDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSortedIndex() {
		ProductContainerData root = new ProductContainerData();
		root.setName("Root");
		ProductContainerData childA = new ProductContainerData();
		childA.setName("childA");
		ProductContainerData childC = new ProductContainerData();
		childC.setName("childC");
		ProductContainerData childE = new ProductContainerData();
		childE.setName("childE");

		root.addChild(childA);
		root.addChild(childC);
		root.addChild(childE);
		assertTrue(root.getSortedIndex("childB") == 1);
		assertTrue(root.getSortedIndex("childD") == 2);
		assertTrue(root.getSortedIndex("childF") == 3);
		assertTrue(root.getSortedIndex("best") == 0);
		assertTrue(root.getSortedIndex("worst") == 3);
	}

}
