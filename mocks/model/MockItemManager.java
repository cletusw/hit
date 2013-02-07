package model;

import java.util.Iterator;

public class MockItemManager implements ItemManager {
	@Override
	public void manage(Item item) {
	}

	@Override
	public boolean productHasItems(Product product) {
		return false;
	}

	@Override
	public Iterator<Item> removedItemsIterator() {
		return null;
	}

	@Override
	public void unmanage(Item item) {
	}
}
