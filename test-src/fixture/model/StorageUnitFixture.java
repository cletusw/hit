package fixture.model;

import static org.easymock.EasyMock.createNiceMock;
import model.ProductContainerManager;
import model.StorageUnit;

@SuppressWarnings("serial")
public class StorageUnitFixture extends StorageUnit {
	public StorageUnitFixture() {
		super("StorageUnit " + Long.toString(System.nanoTime()),
				createNiceMock(ProductContainerManager.class));
	}
}
