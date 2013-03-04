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
	}
}
