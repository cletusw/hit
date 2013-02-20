package mocks;

import model.ProductContainer;
import model.ProductContainerManager;

public class MockProductContainerManager implements ProductContainerManager {

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
