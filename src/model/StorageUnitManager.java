package model;

import java.util.Iterator;

public interface StorageUnitManager {

	public void add(StorageUnit storageUnit);

	public Iterator<StorageUnit> getStorageUnitsIterator();

	public boolean isValidStorageUnitName(String name);

	public void remove(Product product);

	public void remove(ProductGroup productGroup);

	public void remove(StorageUnit storageUnit);

	public void renameStorageUnit(StorageUnit storageUnit, String newStorageUnitName);
}
