package builder.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainer;

public class ItemBuilder {
	private Product product = new ProductBuilder().build();
	private ProductContainer container = new StorageUnitBuilder().build();
	private Date entryDate = new Date();
	private ItemManager manager = createNiceMock(ItemManager.class);

	public Item build() {
		return new Item(product, container, entryDate, manager);
	}

	public ItemBuilder container(ProductContainer container) {
		this.container = container;

		return this;
	}

	public ItemBuilder entryDate(Date entryDate) {
		this.entryDate = entryDate;

		return this;
	}

	public ItemBuilder manager(ItemManager manager) {
		this.manager = manager;

		return this;
	}

	public ItemBuilder product(Product product) {
		this.product = product;

		return this;
	}
}
