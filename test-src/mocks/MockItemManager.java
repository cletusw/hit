package mocks;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import model.Item;
import model.ItemManager;
import model.Product;

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
