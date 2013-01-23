package model;

/** Class Description
 * 
 *  @author - Matt Hess
 *  @version 1.0 - Snell 340 Group 4 Phase 1
 */

public class StorageUnit extends ProductContainer {
	
	/** Constructor
	 * 
	 * @param pcName - String name of the product group.
	 * 
	 */
	public StorageUnit(String pcName) {
		super(pcName);
	}
	
	/** Default Constructor
	 * 
	 */
	public StorageUnit() {
		super();
	}

	@Override
	/** Equals override. Note that the object is explicitly
	 *  cast as a ProductGroup object. 
	 * 
	 * @param o - the object to be compared to this one
	 * 
	 */

	public boolean equals(Object o) {
		StorageUnit sUnit = (StorageUnit) o;
		
		return getName().equals(sUnit.getName());
	}
}
