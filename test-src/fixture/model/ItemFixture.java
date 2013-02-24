package fixture.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.Item;
import model.ItemManager;
import model.ProductContainer;

@SuppressWarnings("serial")
public class ItemFixture extends Item {
	public ItemFixture() {
		this(new StorageUnitFixture());
	}

	public ItemFixture(ProductContainer productContainer) {
		super(new ProductFixture(), productContainer, new Date(),
				createNiceMock(ItemManager.class));
	}
}
