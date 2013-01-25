package model;

public class Product implements Comparable<Object> {
	private String barcode;
	
	public Product(String barcode) {
		this.barcode = barcode;
	}
	
	public String getBarcode() {
		return barcode;
	}

	@Override
	public int compareTo(Object o) {
		Product other = (Product) o;
		return barcode.compareToIgnoreCase(other.getBarcode());
	}

}
