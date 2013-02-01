package model;

import java.util.ArrayList;
import java.util.Collection;

public class ConreteItemManager implements ItemManager {
	private Collection<Item> items;
	
	public ConreteItemManager() {
		items = new ArrayList<Item>();
	}
	
	/** Adds the given item to this Manager's indexes
	 * @param item Item to manage
	 */
	@Override
	public void manage(Item item) {
		items.add(item);
	}
}
