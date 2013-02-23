package model;

public class Action {
	public enum ActionType {
		CREATE, EDIT, DELETE
	}

	private Object object;
	private ActionType action;

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
