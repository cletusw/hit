package model.visitor;

public interface InventoryVisitable {

	/**
	 * Allows an object to be notified of a visitor, as well as what to do when visited.
	 * 
	 * @param visitor
	 *            InventoryVisitor visiting the current object
	 * 
	 * @pre visitor != null
	 * @post true
	 */
	public void accept(InventoryVisitor visitor);
}
