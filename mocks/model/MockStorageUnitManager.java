package model;

import java.util.Iterator;

public class MockStorageUnitManager implements StorageUnitManager {

	@Override
	public void add(StorageUnit storageUnit) {
	}

	@Override
	public Iterator<StorageUnit> getStorageUnitsIterator() {
		return null;
	}

	@Override
	public boolean isValidStorageUnitName(String name) {
		return false;
	}

	@Override
	public void remove(Product product) {
	}

	@Override
	public void remove(ProductGroup productGroup) {
	}

	@Override
	public void remove(StorageUnit storageUnit) {
	}

	@Override
	public void renameStorageUnit(StorageUnit storageUnit, String newStorageUnitName) {
	}

}
