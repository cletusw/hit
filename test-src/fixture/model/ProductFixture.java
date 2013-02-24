package fixture.model;

import static org.easymock.EasyMock.createNiceMock;

import java.util.Date;

import model.Product;
import model.ProductManager;
import model.ProductQuantity;
import model.Unit;

@SuppressWarnings("serial")
public class ProductFixture extends Product {
	public ProductFixture() {
		super("ProductBarcode " + (new Date()).toString(), "Description", 1, 1,
				new ProductQuantity(1, Unit.COUNT), createNiceMock(ProductManager.class));
	}
}
