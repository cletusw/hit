package model.persistence.factory;

import model.persistence.InventoryDao;

/**
 * Creates and maintains InventoryDao object for the application session based on the type of
 * persistence selected on startup
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 * @invariant InventoryDao != null
 */
public interface DaoFactory {

	/**
	 * Get the InventoryDao for the session
	 * 
	 * @return Initialized InventoryDao object
	 * 
	 * @pre true
	 * @post true
	 */
	public InventoryDao getDao();

}
