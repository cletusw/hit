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

	public ProductBuilder barcode(String barcode) {
		this.barcode = barcode;

		return this;
	}

	public Product build() {
		return new Product(barcode, description, shelfLife, threeMonthSupply, productQuantity,
				productManager);
	}

	public ProductBuilder description(String description) {
		this.description = description;

		return this;
	}

	public ProductBuilder productManager(ProductManager productManager) {
		this.productManager = productManager;

		return this;
	}

	public ProductBuilder productQuantity(ProductQuantity productQuantity) {
		this.productQuantity = productQuantity;

		return this;
	}

	public ProductBuilder shelfLife(int shelfLife) {
		this.shelfLife = shelfLife;

		return this;
	}

	public ProductBuilder threeMonthSupply(int threeMonthSupply) {
		this.threeMonthSupply = threeMonthSupply;

		return this;
	}
}
