package model;

import java.util.Iterator;

public class MockItemManager implements ItemManager {
	@Override
	public void manage(Item item) {
	}

	@Override
	public boolean productHasItems(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Item> removedItemsIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unmanage(Item item) {
		// TODO Auto-generated method stub

	}
}
