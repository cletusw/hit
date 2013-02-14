package model;

public class MockProductContainerManager implements ProductContainerManager {

	@Override
	public boolean isValidStorageUnitName(String name) {
		return false;
	}

	@Override
	public void manage(ProductContainer pc) {
	}

	@Override
	public void renameStorageUnit(StorageUnit storageUnit,
			String newStorageUnitName) {
	}

	@Override
	public void unmanage(ProductContainer pc) {
	}
}
