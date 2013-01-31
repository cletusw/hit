package model;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Clayton Watts
 *
 */
public class ProductQuantityTest {
	// Test fixtures
	private ProductQuantity fluid;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fluid = new ProductQuantity(3.2f, Unit.FLUID_OUNCES);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.ProductQuantity#ProductQuantity(float, model.Unit)}.
	 */
	@Test
	public void testProductQuantity() {
		assertTrue(fluid.getQuantity() == 3.2f);
		assertTrue(fluid.getUnits() == Unit.FLUID_OUNCES);
	}

	/**
	 * Test method for {@link model.ProductQuantity#isValidProductQuantity(float, model.Unit)}.
	 */
	@Test
	public void testIsValidProductQuantity() {
		assertTrue(ProductQuantity.isValidProductQuantity(3.2f, Unit.FLUID_OUNCES));
		assertTrue(ProductQuantity.isValidProductQuantity(0f, Unit.FLUID_OUNCES));
		assertFalse(ProductQuantity.isValidProductQuantity(-1.4f, Unit.FLUID_OUNCES));
		assertTrue(ProductQuantity.isValidProductQuantity(3, Unit.COUNT));
		assertFalse(ProductQuantity.isValidProductQuantity(3.1f, Unit.COUNT));
	}

	/**
	 * Test method for {@link model.ProductQuantity#setQuantity(float)}.
	 */
	@Test
	public void testSetQuantity() {
		fluid.setQuantity(0f);
		assertTrue(fluid.getQuantity() == 0f);
		
		ProductQuantity count = new ProductQuantity(0, Unit.COUNT);
		count.setQuantity(1);
		assertTrue(count.getQuantity() == 1);
		
		thrown.expect(IllegalArgumentException.class);
		count.setQuantity(1.2f);
	}

	/**
	 * Test method for {@link model.ProductQuantity#add(model.ProductQuantity)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.ProductQuantity#subtract(model.ProductQuantity)}.
	 */
	@Test
	public void testSubtract() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.ProductQuantity#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
