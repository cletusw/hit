package model;

import java.util.Iterator;

public class MockStorageUnitManager implements StorageUnitManager {

	@Override
	public boolean isValidStorageUnitName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<StorageUnit> getStorageUnitsIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String storageUnitName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(ProductGroup productGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(StorageUnit storageUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renameStorageUnit(String storageUnitName,
			String newStorageUnitName) {
		// TODO Auto-generated method stub
		
	}

}