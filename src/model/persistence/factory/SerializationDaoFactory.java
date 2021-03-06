package model.persistence.factory;

import model.persistence.InventoryDao;
import model.persistence.SerializationDao;

/**
 * Creates and maintains a SerializationDao object to be used for persistence.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 * @invariant InventoryDao != null
 */
public class SerializationDaoFactory implements DaoFactory {

	@Override
	public InventoryDao getDao() {
		return new SerializationDao();
	}
}
