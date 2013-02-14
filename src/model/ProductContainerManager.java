package model;

public interface ProductContainerManager {

	public boolean isValidStorageUnitName(String storageUnitName);

	public void manage(ProductContainer pc);

	public void renameStorageUnit(StorageUnit storageUnit,
			String newStorageUnitName);

	public void unmanage(ProductContainer pc);
}
