package common;

import java.util.Observable;

import model.Action;

public abstract class ObservableWithPublicNotify extends Observable {
	public void notifyObservers(Action action) {
		setChanged();
		super.notifyObservers(action);
	}
}
