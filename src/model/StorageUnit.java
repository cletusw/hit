package model;

/**
 * Storage Unit class. Inherits from Product Container.
 * 
 * @author - Matt Hess
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * 
 */

@SuppressWarnings("serial")
public class StorageUnit extends ProductContainer {

	/**
	 * Constructor
	 * 
	 * @param pcName
	 *            - String name of the storage unit.
	 * @param unit
	 *            - Unit for counting in this storage unit.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public StorageUnit(String pcName, ProductContainerManager manager) {
		super(pcName, manager);
		this.manager.manage(this);
	}

	/**
	 * Defines equality with another ProductContainer descendant.
	 * 
	 * @param o
	 *            - the object to be compared to.
	 * @returns True if the objects are equal, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof ProductContainer) {
			ProductContainer other = (ProductContainer) o;
			return getName().equals(other.getName());
		} else {
			return super.equals(o);
		}
	}
}
