package undo;

/**
 * Class that encapsulates an undo-able action.
 * 
 * @author clayton
 */
public interface Command {
	/**
	 * Executes the action that this Command represents.
	 */
	public void execute();

	/**
	 * Executes the inverse of the action that this Command represents.
	 */
	public void unexecute();
}
