package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConcreteProductManagerTest {
	private ConcreteProductManager manager;
	private final String barcode = "testBarcode";
	private final ProductQuantity productQuantity = new ProductQuantity(1, Unit.COUNT);

	@Before
	public void setUp() throws Exception {
		manager = new ConcreteProductManager();
	}

	@Test
	public void testManage() {
		Product product = new Product(barcode, "testDescription", 10, 10, productQuantity, manager);
		assertTrue(manager.contains(product));
		assertTrue(manager.getBy(barcode) == product);
	}

	@Test
	public void testUnmanage() {
		Product product = new Product(barcode, "testDescription", 10, 10, productQuantity, manager);
		manager.unmanage(product);
		assertFalse(manager.contains(product));
		assertFalse(manager.getBy(barcode) == product);
	}

}
