package test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.ConcreteProductManager;
import model.Product;
import model.ProductQuantity;
import model.Unit;

import org.junit.Before;
import org.junit.Test;

public class ConcreteProductManagerTest {
	private static final String barcode = "testBarcode";
	private ConcreteProductManager manager;
	private ProductQuantity productQuantity;
	private Product product;

	@Before
	public void setUp() throws Exception {
		manager = new ConcreteProductManager();
		productQuantity = new ProductQuantity(1, Unit.COUNT);
		product = new Product(barcode, "testDescription", 10, 10, productQuantity, manager);
	}

	@Test
	public void testManage() {
		assertTrue(manager.contains(product));
		assertTrue(manager.getByBarcode(barcode) == product);
	}

	@Test
	public void testUnmanage() {
		manager.unmanage(product);
		assertFalse(manager.contains(product));
		assertFalse(manager.getByBarcode(barcode) == product);
	}

}
