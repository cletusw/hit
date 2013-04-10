package builder.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.Barcode;
import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainer;

public class ItemBuilder {
	private Product product = new ProductBuilder().build();
	private ProductContainer container = new StorageUnitBuilder().build();
	private Date entryDate = new Date();
	private Barcode barcode = null;
	private ItemManager manager = createNiceMock(ItemManager.class);

	public ItemBuilder barcode(Barcode barcode) {
		this.barcode = barcode;

		return this;
	}

	public Item build() {
		if (barcode == null)
			return new Item(product, container, entryDate, manager);
		else
			return new Item(barcode, product, container, entryDate, manager);
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
