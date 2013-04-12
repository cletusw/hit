package test.model;

import static org.junit.Assert.assertTrue;
import model.ConcreteProductManager;
import model.Product;

import org.junit.Before;
import org.junit.Test;

import builder.model.ProductBuilder;

public class ConcreteProductManagerTest {
	private ConcreteProductManager manager;
	private Product product;

	@Before
	public void setUp() throws Exception {
		manager = new ConcreteProductManager();
		product = new ProductBuilder().productManager(manager).build();
	}

	@Test
	public void testManage() {
		assertTrue(manager.contains(product));
		assertTrue(manager.getByBarcode(product.getBarcode()) == product);
	}

	@Test
	public void testUnmanage() {
		manager.unmanage(product);
		assertTrue(manager.contains(product));
		assertTrue(manager.getByBarcode(product.getBarcode()) == product);
	}
}
