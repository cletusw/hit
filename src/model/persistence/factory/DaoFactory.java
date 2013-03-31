package model.persistence.factory;

import model.persistence.InventoryDao;

/**
 * 
 * @author Matthew
 * 
 */
public interface DaoFactory {

	/**
	 * 
	 * @return
	 */
	public InventoryDao getDao();

}
