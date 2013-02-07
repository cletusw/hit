package model;

public class MockProductManager implements ProductManager {
	@Override
	public boolean contains(Product product) {
		return false;
	}

	@Override
	public Product getByBarcode(String barcodeScanned) {
		return null;
	}

	@Override
	public void manage(Product product) {
	}

	@Override
	public void unmanage(Product product) {
	}
}
