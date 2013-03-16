package builder.model;

import static org.easymock.EasyMock.createNiceMock;
import model.Product;
import model.ProductManager;
import model.ProductQuantity;
import model.Unit;

public class ProductBuilder {
	private String barcode = "ProductBarcode " + Long.toString(System.nanoTime());
	private String description = "Description";
	private int shelfLife = 1;
	private int threeMonthSupply = 1;
	private ProductQuantity productQuantity = new ProductQuantity(1, Unit.COUNT);
	private ProductManager productManager = createNiceMock(ProductManager.class);

	public ProductBuilder() {
	}

	public Product build() {
		return new Product(barcode, description, shelfLife, threeMonthSupply, productQuantity,
				productManager);
	}
}
