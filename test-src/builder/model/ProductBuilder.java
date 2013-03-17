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

	public void barcode(String barcode) {
		this.barcode = barcode;
	}

	public Product build() {
		return new Product(barcode, description, shelfLife, threeMonthSupply, productQuantity,
				productManager);
	}

	public void description(String description) {
		this.description = description;
	}

	public void productManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void productQuantity(ProductQuantity productQuantity) {
		this.productQuantity = productQuantity;
	}

	public void shelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	public void threeMonthSupply(int threeMonthSupply) {
		this.threeMonthSupply = threeMonthSupply;
	}
}
