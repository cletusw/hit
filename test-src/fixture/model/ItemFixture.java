package fixture.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.Item;
import model.ItemManager;
import model.Product;
import model.ProductContainer;
import builder.model.ProductBuilder;

@SuppressWarnings("serial")
public class ItemFixture extends Item {
	public ItemFixture() {
		this(new StorageUnitFixture());
	}

	public ItemFixture(Product product, ProductContainer productContainer) {
		super(product, productContainer, new Date(), createNiceMock(ItemManager.class));
	}

	public ItemFixture(ProductContainer productContainer) {
		this(new ProductBuilder().build(), productContainer);
	}
}
