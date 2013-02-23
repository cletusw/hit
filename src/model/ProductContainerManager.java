package model;

import java.util.Iterator;
import java.util.Observer;

public interface ProductContainerManager {
	public StorageUnit getRootStorageUnitByName(String productContainerName);

	public StorageUnit getStorageUnitByName(String name);

	public Iterator<StorageUnit> getStorageUnitIterator();

	public boolean isValidStorageUnitName(String storageUnitName);

	public void manage(ProductContainer pc);

	public void setStorageUnitName(String name, StorageUnit su);

	public void unmanage(ProductContainer pc);

	void addObserver(Observer o);

	boolean isValidProductGroupName(String productGroupName);
}
