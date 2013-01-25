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
		String otherBarcode = "";
		
		if (o instanceof Product) {
			otherBarcode = ((Product) o).getBarcode();
		}
		else {
			otherBarcode = (String) o;
		}
		
		return barcode.compareToIgnoreCase(otherBarcode);
	}

}
