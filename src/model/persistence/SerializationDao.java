package model.persistence;

import java.util.Observable;

import model.HomeInventoryTracker;

/**
 * On application close, persists the model by serializing the HomeInventoryTracker class
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 */
public class SerializationDao extends InventoryDao {

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
