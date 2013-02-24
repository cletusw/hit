package fixture.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.Item;
import model.ItemManager;

@SuppressWarnings("serial")
public class ItemFixture extends Item {
	public ItemFixture() {
		super(new ProductFixture(), new StorageUnitFixture(), new Date(),
				createNiceMock(ItemManager.class));
	}
}
