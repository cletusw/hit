package generators;

import java.util.Date;

import model.Item;
import model.ItemManager;
import model.Product;
import builder.model.ItemBuilder;

public class ItemGenerator {
	private final ItemManager manager;

	public ItemGenerator(ItemManager manager) {
		this.manager = manager;
	}

	public Item createItemAtRandomTime(Product product) {
		Date date = product.getCreationDate();
		double creationTime = date.getTime();
		Date now = new Date();
		long time = (long) (Math.random() * (now.getTime() - creationTime) + creationTime);
		date = new Date(time);
		ItemBuilder itemBuilder = new ItemBuilder();
		Item item = itemBuilder.entryDate(date).product(product).manager(manager).build();
		return item;
	}

	public Item createItemBeforeDate(Product product, Date date) {
		if (date.before(product.getCreationDate()))
			throw new IllegalArgumentException(
					"Cannot create an item before its product was created");
		Date creationDate = product.getCreationDate();
		double creationTime = creationDate.getTime();
		long time = (long) (Math.random() * (date.getTime() - creationTime) + creationTime);
		date = new Date(time);
		ItemBuilder itemBuilder = new ItemBuilder();
		Item item = itemBuilder.entryDate(date).product(product).manager(manager).build();
		return item;
	}
}
