package model;

public class MockProductManager implements ProductManager {
	@Override
	public void manage(Product product) {
	}

	@Override
	public void unmanage(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product getByBarcode(String barcodeScanned) {
		// TODO Auto-generated method stub
		return null;
	}
}
