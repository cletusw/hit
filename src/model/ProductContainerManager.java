package model;

public interface ProductContainerManager {

	public boolean isValidStorageUnitName(String storageUnitName);

	public void manage(ProductContainer pc);

	public void unmanage(ProductContainer pc);
}
