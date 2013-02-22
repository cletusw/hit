package mocks;

import model.ProductContainer;
import model.ProductContainerManager;
import model.StorageUnit;

public class MockProductContainerManager implements ProductContainerManager {

	@Override
	public StorageUnit getStorageUnitByName(String name) {
		return null;
	}

	@Override
	public boolean isValidStorageUnitName(String name) {
		return false;
	}

	@Override
	public void manage(ProductContainer pc) {
	}

	@Override
	public void unmanage(ProductContainer pc) {
	}
}
