package model;

import java.util.Observer;
import java.util.Set;

public interface ProductContainerManager {
	public void editProductGroup(ProductContainer parent, String oldName, String newName,
			ProductQuantity newTMS);

	public StorageUnit getRootStorageUnitByName(String productContainerName);

	public StorageUnit getRootStorageUnitForChild(ProductContainer child);

	public StorageUnit getStorageUnitByName(String name);

	public Set<StorageUnit> getStorageUnits();

	public boolean isValidStorageUnitName(String storageUnitName);

	public void manage(ProductContainer pc);

	public void setStorageUnitName(String name, StorageUnit su);

	public void unmanage(ProductContainer pc);

	void addObserver(Observer o);
}
