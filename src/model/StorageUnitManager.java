package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StorageUnitManager {
	private Collection<StorageUnit> rootStorageUnits;
	
	/** Constructor
	 * 
	 */
	public StorageUnitManager() {
		rootStorageUnits = new ArrayList<StorageUnit>();	
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
		return !name.equals("") && !rootStorageUnits.contains(new StorageUnit(name, Unit.COUNT));
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
	
	/** Removes a given ProductContainer from the system
	 * 
	 * @param container		the ProductContainer to remove
	 * @pre container != null
	 * @post !contains(container)
	 */
	public void remove(ProductContainer container) {
		for (StorageUnit storageUnit : rootStorageUnits) {
			if (storageUnit.equals(container)) {
				rootStorageUnits.remove(storageUnit);
				return;
			}
		}
		for (StorageUnit storageUnit : rootStorageUnits) {
			storageUnit.remove(container);
		}
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
