package builder.model;

import static org.easymock.EasyMock.createNiceMock;
import model.ProductContainer;
import model.ProductContainerManager;
import model.ProductGroup;
import model.ProductQuantity;
import model.Unit;

public class ProductGroupBuilder {
	private String name = "ProductGroup " + Long.toString(System.nanoTime());
	private ProductQuantity threeMonthSupply = new ProductQuantity(1, Unit.COUNT);
	private Unit groupUnit = Unit.COUNT;
	private ProductContainer parent = new StorageUnitBuilder().build();
	private ProductContainerManager manager = createNiceMock(ProductContainerManager.class);

	public ProductGroup build() {
		return new ProductGroup(name, threeMonthSupply, groupUnit, parent, manager);
	}

	public ProductGroupBuilder groupUnit(Unit groupUnit) {
		this.groupUnit = groupUnit;

		return this;
	}

	public ProductGroupBuilder manager(ProductContainerManager manager) {
		this.manager = manager;

		return this;
	}

	public ProductGroupBuilder name(String name) {
		this.name = name;

		return this;
	}

	public ProductGroupBuilder parent(ProductContainer parent) {
		this.parent = parent;

		return this;
	}

	public ProductGroupBuilder threeMonthSupply(ProductQuantity threeMonthSupply) {
		this.threeMonthSupply = threeMonthSupply;

		return this;
	}
}
