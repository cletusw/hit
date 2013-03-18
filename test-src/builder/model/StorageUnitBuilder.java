package builder.model;

import static org.easymock.EasyMock.createNiceMock;
import model.ProductContainerManager;
import model.StorageUnit;

public class StorageUnitBuilder {
	private String name = "StorageUnit " + Long.toString(System.nanoTime());
	private ProductContainerManager manager = createNiceMock(ProductContainerManager.class);

	public StorageUnit build() {
		return new StorageUnit(name, manager);
	}

	public StorageUnitBuilder manager(ProductContainerManager manager) {
		this.manager = manager;

		return this;
	}

	public StorageUnitBuilder name(String name) {
		this.name = name;

		return this;
	}
}
