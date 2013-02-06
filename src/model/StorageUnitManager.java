package model;

import java.util.Iterator;

public interface StorageUnitManager {

	public boolean isValidStorageUnitName(String name);
	public void add(String storageUnitName);
	public void remove(Product product);
	public void remove(ProductGroup productGroup);
	public void remove(StorageUnit storageUnit);
	public Iterator<StorageUnit> getStorageUnitsIterator();
	public void renameStorageUnit(String storageUnitName, String newStorageUnitName);
}
