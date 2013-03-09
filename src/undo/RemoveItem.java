package undo;

import model.Item;
import model.ItemManager;
import model.ProductContainer;

/**
 * 
 * @author mgh14
 *
 * @invariant manager != null
 * @invariant toRemove != null
 * 
 */
public class RemoveItem implements Command {
	private ItemManager manager;
	private Item toRemove;
	private ProductContainer itemContainer;
	
	/**
	 * Constructor.
	 * 
	 * @param itemBarcode
	 * @param manager
	 * 
	 * @pre itemBarcode != null
	 * @pre manager.getItemByItemBarcode(itemBarcode) != null
	 * @pre manager.getItemByItemBarcode(itemBarcode).getContainer() != null
	 * @pre manager != null
	 * @post true
	 * 
	 */
	public RemoveItem(String itemBarcode,ItemManager manager) {
		this.manager = manager;
		
		toRemove = manager.getItemByItemBarcode(itemBarcode);
		itemContainer = toRemove.getContainer();
	}
	
	/**
	 * Removes an item from the model based on command data from the constructor
	 * 
	 * @pre true
	 * @post toRemove.getContainer() == null
	 * @post manager.removedItems.contains(toRemove)
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Undoes the remove item action performed previously on toRemove
	 * 
	 * @pre execute() has been called
	 * @pre !itemContainer.contains(toRemove)
	 * @post itemContainer.contains(toRemove)
	 * @post toRemove.getContainer().equals(itemContainer)
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
