package undo;

import model.Item;
import model.ProductContainer;

/**
 * Encapsulates the reversible action of transferring an Item between ProductContainers.
 * 
 * @author clayton
 * 
 * @invariant getTransferredItem() != null
 */
public class TransferItem implements Command {
	private Item transferredItem;

	/**
	 * Constructs a TransferItem command with the given dependencies.
	 * 
	 * @param toTransfer
	 *            Item to transfer
	 * @param source
	 *            ProductContainer source
	 * @param destination
	 *            ProductContainer destination
	 * 
	 * @pre toTransfer != null
	 * @pre source != null
	 * @pre destination != null
	 * @post true
	 */
	public TransferItem(Item toTransfer, ProductContainer source, ProductContainer destination) {
		transferredItem = toTransfer;
	}

	/**
	 * Transfer an Item based on the source and destination provided on construction.
	 * 
	 * @pre getTransferredItem() != null
	 * @post true
	 */
	@Override
	public void execute() {
	}

	/**
	 * Returns the Item transferred by the execute method of this Command.
	 * 
	 * @return the Item transferred by the execute method of this Command.
	 */
	public Item getTransferredItem() {
		return transferredItem;
	}

	/**
	 * Return an item previously transferred by the execute method.
	 * 
	 * @pre getTransferredItem() != null
	 * @post true
	 */
	@Override
	public void undo() {
	}
}
