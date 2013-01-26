package model;

public class Item implements Comparable<Object> {
	private String barcode;
	
	public Item(String iBarcode) {
		barcode = iBarcode;
	}
	
	@Override
	public int compareTo(Object o) {
		Item other = (Item) o;
		
		return -1;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
}