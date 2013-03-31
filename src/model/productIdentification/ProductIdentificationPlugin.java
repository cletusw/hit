package model.productIdentification;

import model.Product;

public abstract class ProductIdentificationPlugin {
	private ProductIdentificationPlugin successor;

	public abstract String getDescriptionForProduct(Product product);

	public ProductIdentificationPlugin getSuccessor() {
		return successor;
	}

	public void setSuccessor(ProductIdentificationPlugin plugin) {
		successor = plugin;
	}

}
