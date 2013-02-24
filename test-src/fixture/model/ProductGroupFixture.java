package fixture.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductQuantity;
import model.Unit;

@SuppressWarnings("serial")
public class ProductGroupFixture extends ProductGroup {
	public ProductGroupFixture() {
		this(new StorageUnitFixture());
	}

	public ProductGroupFixture(ProductContainer parent) {
		super("ProductGroup " + (new Date()).toString(), new ProductQuantity(1, Unit.COUNT),
				Unit.COUNT, parent, createNiceMock(ProductContainerManager.class));
	}
}
