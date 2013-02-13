package model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MockItemManager implements ItemManager {
	@Override
	public Set<Item> getItemsByProduct(Product product) {
		return new TreeSet<Item>();
	}

	@Override
	public void manage(Item item) {
	}

	@Override
	public Iterator<Item> removedItemsIterator() {
		return null;
	}

	@Override
	public void unmanage(Item item) {
	}
}
