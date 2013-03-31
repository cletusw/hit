package model.persistence;

import java.util.Observable;
import java.util.Observer;

import model.HomeInventoryTracker;

public abstract class InventoryDao implements Observer {

	public abstract void applicationClose(HomeInventoryTracker hit);

	public abstract HomeInventoryTracker LoadHomeInventoryTracker();

	@Override
	public abstract void update(Observable arg0, Object arg1);
}
