package test.model.undo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.undo.Command;
import model.undo.UndoManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UndoManagerTest {
	private class EmptyCommand implements Command {

		@Override
		public void execute() {
		}

		@Override
		public void undo() {
		}

	}

	private UndoManager manager;
	private Command command;

	@Before
	public void setUp() throws Exception {
		manager = new UndoManager();
		command = new EmptyCommand();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalStateException.class)
	public void testCanRedoAfterExecute() {
		manager.execute(command);
		assertFalse(manager.canRedo());
		manager.redo();
	}

	@Test(expected = IllegalStateException.class)
	public void testCanRedoAfterUndoAndExecute() {
		manager.execute(command);
		manager.undo();
		assertTrue(manager.canRedo());
		manager.execute(command);
		assertFalse(manager.canRedo());
		manager.redo();
	}

	@Test(expected = IllegalStateException.class)
	public void testCanRedoEmpty() {
		assertFalse(manager.canRedo());
		manager.redo();
	}

	@Test(expected = IllegalStateException.class)
	public void testCanUndo() {
		assertFalse(manager.canUndo());
		manager.execute(command);
		assertTrue(manager.canUndo());
		manager.undo();
		assertFalse(manager.canUndo());
		manager.redo();
		assertTrue(manager.canUndo());
		manager.undo();
		assertFalse(manager.canUndo());
		manager.undo();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExecute() {
		manager.execute(null);
	}

	@Test
	public void testRedo() {
		assertFalse(manager.canRedo());
		manager.execute(command);
		assertFalse(manager.canRedo());
		manager.undo();
		assertTrue(manager.canRedo());
		manager.redo();
		assertFalse(manager.canRedo());
	}

	@Test
	public void testUndo() {
		assertFalse(manager.canUndo());
		manager.execute(command);
		assertTrue(manager.canUndo());
		manager.undo();
		assertFalse(manager.canUndo());
	}

}
