package model.persistence.factory;

import model.persistence.InventoryDao;

public interface DaoFactory {

	public InventoryDao getDao();

}
