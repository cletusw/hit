package model.undo;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import model.Product;

/**
 * Manager that takes care of Doing, Undoing, and Redoing Commands
 * 
 * @author clayton
 */
public class UndoManager {
	private final Stack<Command> undoStack;
	private final Stack<Command> redoStack;

	public UndoManager() {
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}

	/**
	 * Returns true if there are commands available for redoing.
	 * 
	 * @return true if there are commands available for redoing. false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	public boolean canRedo() {
		return !redoStack.isEmpty();
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
		return !undoStack.isEmpty();
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
	 * @return the Command that was executed.
	 */
	public Command execute(Command command) {
		if (command == null)
			throw new IllegalArgumentException("Null command");
		command.execute();
		undoStack.push(command);
		redoStack.clear();
		return command;
	}

	/**
	 * Redoes the last Undone command.
	 * 
	 * @pre canRedo()
	 * @post canUndo()
	 * @return the Command that was redone.
	 */
	public Command redo() {
		if (!canRedo()) {
			throw new IllegalStateException("Cannot redo");
		}
		Command command = redoStack.pop();
		command.execute();
		undoStack.push(command);
		return command;
	}

	/**
	 * Undoes the last executed command.
	 * 
	 * @pre canUndo()
	 * @post canRedo()
	 * @return the Command that was undone.
	 */
	public Command undo() {
		if (!canUndo()) {
			throw new IllegalStateException("Cannot undo");
		}
		Command command = undoStack.pop();
		command.undo();
		redoStack.push(command);
		return command;
	}
}
