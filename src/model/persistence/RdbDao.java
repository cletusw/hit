package model.persistence;

import java.util.Observable;

import model.HomeInventoryTracker;

/**
 * Observes the model and persists all changes made to it an a local MySQL database
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class RdbDao extends InventoryDao {

	@Override
	public void applicationClose(HomeInventoryTracker hit) {
		// TODO Auto-generated method stub

	}

	@Override
	public HomeInventoryTracker LoadHomeInventoryTracker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
