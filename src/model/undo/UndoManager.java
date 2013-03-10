package model.undo;

import java.util.Stack;

/**
 * Manager that takes care of Doing, Undoing, and Redoing Commands
 * 
 * @author clayton
 */
public class UndoManager {
	private Stack<Command> undoStack;
	private Stack<Command> redoStack;

	/**
	 * Returns true if there are commands available for redoing.
	 * 
	 * @return true if there are commands available for redoing. false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	public boolean canRedo() {
		return true;
	}

	/**
	 * Returns true if there are commands available for undoing.
	 * 
	 * @return true if there are commands available for undoing. false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	public boolean canUndo() {
		return true;
	}

	/**
	 * Executes a command and stores it for possible undoing. Also clears away any built-up
	 * potential redos.
	 * 
	 * @param command
	 *            The Command to execute and store.
	 * 
	 * @pre command != null
	 * @post canUndo()
	 * @post !canRedo()
	 */
	public void execute(Command command) {
	}

	/**
	 * Redoes the last Undone command.
	 * 
	 * @pre canRedo()
	 * @post canUndo()
	 */
	public void redo() {
	}

	/**
	 * Undoes the last executed command.
	 * 
	 * @pre canUndo()
	 * @post canRedo()
	 */
	public void undo() {
	}
}
