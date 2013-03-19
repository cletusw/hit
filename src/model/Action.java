package model;

public class Action {
	public enum ActionType {
		CREATE, EDIT, DELETE, MOVE
	}

	private final Object object;
	private final ActionType action;

	public Action(Object o, ActionType a) {
		object = o;
		action = a;
	}

	public ActionType getAction() {
		return action;
	}

	public Object getObject() {
		return object;
	}
}
