package model;

/** Storage Unit class. Inherits from Product Container.
 * 
 *  @author - Matt Hess
 *  @version 1.0 - Snell 340 Group 4 Phase 1
 *  
 */

@SuppressWarnings("serial")
public class StorageUnit extends ProductContainer {
	
	/** Constructor
	 * 
	 * @param pcName - String name of the storage unit.
	 * @param unit - Unit for counting in this storage unit.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public StorageUnit(String pcName) {
		super(pcName);
	}
	
	/** Determines whether or not the specified Product Group can be added to this Storage Unit.
	 * @param pGroup the Product Group to test
	 * @return true if able to add, false otherwise
	 * 
	 * @pre pGroups != null
	 * @post true
	 */
	public boolean canAddProductGroup(ProductGroup pGroup) {
		// From the Data Dictionary: A Storage Unit cannot have two top-level 
		// Product Groups of the same name.
		return (getProductGroup(pGroup.getName()) == null);
	}	
}
