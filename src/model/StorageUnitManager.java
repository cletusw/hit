package model;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

@SuppressWarnings("serial")
public class StorageUnitManager implements Serializable {
	private Set<StorageUnit> rootStorageUnits;

	/** Constructor
	 * 
	 */
	public StorageUnitManager() {
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
		return !name.equals("") && !rootStorageUnits.contains(name);
	}
	
	/** Creates a new StorageUnit and adds it to the system.
	 * 
	 *  @pre isValidStorageUnitName(storageUnitName)
	 *  @post true
	 */
	public void add(String storageUnitName) {
		rootStorageUnits.add(new StorageUnit(storageUnitName));
	}
	
	/** Removes a given Product from all Storage Units.
	 * 
	 * @param product		the Product to remove
	 */
	public void remove(Product product) {
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
}
