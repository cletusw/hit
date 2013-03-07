package test.model;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import model.ConcreteProductContainerManager;
import model.ProductContainerManager;
import model.StorageUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StorageUnitTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidStorageUnitName() {
		ProductContainerManager mockManager = createMock(ProductContainerManager.class);
		new StorageUnit("Unit1", mockManager);
	}
	
	@Test
	public void testEditStorageUnit(){
		ProductContainerManager manager = new ConcreteProductContainerManager();
		StorageUnit unit = new StorageUnit("Unit1", manager);
		assertTrue(unit.getName().equals("Unit1"));
		unit.edit("Test");
		assertTrue(unit.getName().equals("Test"));
		unit.edit("");
		assertTrue(unit.getName().equals("Test"));
		unit.edit(null);
		assertTrue(unit.getName().equals("Test"));
	}
}
