package model;

/**
 * Storage Unit class. Inherits from Product Container.
 * 
 * @author - Matt Hess
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * 
 */

public class StorageUnit extends ProductContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		manager.manage(this);
	}
}
