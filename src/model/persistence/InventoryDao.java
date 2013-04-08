package model.persistence;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import model.HomeInventoryTracker;

/**
 * The Abstract InventoryDao class observes the model and persists any necessary change when
 * changes to the model occur or the user session ends.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public abstract class InventoryDao implements Observer {

	/**
	 * Handles any data persistance necessary when the user session ends and the application is
	 * closed.
	 * 
	 * @param hit
	 *            The current state of the model that must be persisted
	 * 
	 * @pre hit != null
	 * @post hit data is persisted
	 */
	public abstract void applicationClose(HomeInventoryTracker hit) throws IOException;

	/**
	 * Creates a HomeInventoryTracker model from persisted data.
	 * 
	 * @return a fully initialized HomeInventoryTracker from persisted data.
	 * 
	 * @pre true
	 * @post HomeInventoryTracker is a full initialized model
	 */
	public abstract HomeInventoryTracker loadHomeInventoryTracker();

	/**
	 * Called when changes are made to the model. Updates the necessary persisted data.
	 */
	@Override
	public abstract void update(Observable arg0, Object arg1);
}
