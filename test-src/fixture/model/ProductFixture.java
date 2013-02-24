package fixture.model;

import static org.easymock.EasyMock.createNiceMock;
import model.Product;
import model.ProductManager;
import model.ProductQuantity;
import model.Unit;

@SuppressWarnings("serial")
public class ProductFixture extends Product {
	public ProductFixture() {
		super("ProductBarcode " + Long.toString(System.nanoTime()), "Description", 1, 1,
				new ProductQuantity(1, Unit.COUNT), createNiceMock(ProductManager.class));
	}
}
