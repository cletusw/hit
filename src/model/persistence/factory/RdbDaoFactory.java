package model.persistence.factory;

import model.persistence.InventoryDao;
import model.persistence.RdbDao;

/**
 * Creates and maintains an RdbDao object to be used for persistence.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 * @invariant InventoryDao != null
 */
public class RdbDaoFactory implements DaoFactory {

	@Override
	public InventoryDao getDao() {
		return new RdbDao();
	}

}
