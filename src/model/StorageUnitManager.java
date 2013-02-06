package model;

import java.util.Iterator;

public interface StorageUnitManager {

	public boolean isValidStorageUnitName(String name);
	public void add(StorageUnit storageUnit);
	public void remove(Product product);
	public void remove(ProductGroup productGroup);
	public void remove(StorageUnit storageUnit);
	public Iterator<StorageUnit> getStorageUnitsIterator();
	public void renameStorageUnit(StorageUnit storageUnit, String newStorageUnitName);
}
