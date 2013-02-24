package fixture.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.ProductContainerManager;
import model.StorageUnit;

@SuppressWarnings("serial")
public class StorageUnitFixture extends StorageUnit {
	public StorageUnitFixture() {
		super("StorageUnit " + (new Date()).toString(),
				createNiceMock(ProductContainerManager.class));
	}
}
