package model;

import java.util.Iterator;

public interface ProductContainerManager {
	public StorageUnit getStorageUnitByName(String name);

	public Iterator<StorageUnit> getStorageUnitIterator();

	public boolean isValidStorageUnitName(String storageUnitName);

	public void manage(ProductContainer pc);

	public void unmanage(ProductContainer pc);
}
