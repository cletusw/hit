package model;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

@SuppressWarnings("serial")
public class ConcreteStorageUnitManager implements Serializable, StorageUnitManager {
	private Set<StorageUnit> rootStorageUnits;

	/** Constructor
	 * 
	 */
	public ConcreteStorageUnitManager() {
		rootStorageUnits = new TreeSet<StorageUnit>();	
	}
	
	/** Determines whether the specified Storage Unit name is valid for adding a new Storage Unit.
	 * 
	 * @param name 	The name to be tested
	 * @return true if name is valid, false otherwise
	 * 
	 * @pre true
	 * @post true
	 */
	public boolean isValidStorageUnitName(String name) {
		if(name == null || name.equals(""))
			return false;
		
		// From the Data Dictionary: Must be non-empty. Must be unique among all Storage Units.
		return !name.equals("") && !rootStorageUnits.contains(new StorageUnit(name));
	}
	
	/** Creates a new StorageUnit and adds it to the system.
	 * 
	 * @param storageUnit The Storage Unit to add
	 * 
	 * @pre isValidStorageUnitName(storageUnitName)
	 * @post true
	 */
	public void add(StorageUnit storageUnit) {
		assert(isValidStorageUnitName(storageUnit.getName()));
		
		rootStorageUnits.add(storageUnit);
	}
	
	/** Removes a given Product from all Storage Units.
	 * 
	 * @param product The Product to remove
	 * 
	 * @pre product.canRemove()
	 * @post for(storageUnit in rootStorageUnits) storageUnit::products.size() == 
	 * 		storageUnit::products.size()@pre - 1
	 */
	public void remove(Product product) {
		assert(product.canRemove());
		
		for (StorageUnit storageUnit : rootStorageUnits) {
			storageUnit.remove(product);
		}
	}
	
	/** Removes a ProductGroup from the system
	 * 
	 * @param productGroup the ProductGroup to remove
	 * 
	 * @pre productGroup != null
	 * @pre productGroup.canRemove()
	 * @post for(storageUnit in rootStorageUnits) storageUnit::products.size() ==
	 * 		storageUnit::products.size()@pre - 1
	 */
	public void remove(ProductGroup productGroup) {
		assert(productGroup != null);
		assert(productGroup.canRemove());
		
		for (StorageUnit storageUnit : rootStorageUnits) {
			storageUnit.remove(productGroup);
		}
	}
	
	/**
	 * Removes a Storage Unit from the system
	 * 
	 * @param storageUnit The Storage Unit to remove
	 * 
	 * @pre storageUnit != null
	 * @pre storageUnit.canRemove()
	 * @post rootStorageUnits.size() == rootStorageUnits.size()@post - 1
	 * 
	 */
	public void remove(StorageUnit storageUnit) {
		assert(storageUnit != null);
		assert(storageUnit.canRemove());
		
		rootStorageUnits.remove(storageUnit);
	}
	
	/** Returns an Iterator over the Storage Units.
	 * @return an Iterator for accessing the root Storage Units
	 * 
	 *  @pre true
	 *  @post true
	 */
	public Iterator<StorageUnit> getStorageUnitsIterator() {
		return rootStorageUnits.iterator();
	}

	/**
	 * Rename a Storage Unit
	 * @param storageUnit The Storage Unit to rename
	 * @param newStorageUnitName New name to be given to Storage Unit
	 * 
	 * @pre rootStorageUnits.contains(storageUnitName)
	 * @pre isValidStorageUnitName(newStorageUnitName)
	 * @post !rootStorageUnits.contains(storageUnitName)
	 * @post rootStorageUnits.contains(newStorageUnitName)
	 */
	public void renameStorageUnit(StorageUnit storageUnit, String newStorageUnitName) {
		assert(rootStorageUnits.contains(storageUnit));
		assert(isValidStorageUnitName(newStorageUnitName));
		
		storageUnit.setName(newStorageUnitName);
	}
}
