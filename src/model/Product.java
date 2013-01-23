package model;

public class Product implements Comparable<Object> {
	private String name;
	
	public Product(String pName) {
		name = pName;
	}
	
	public String getName() {
		return name;
	}
	

	@Override
	public int compareTo(Object o) {
		Product other = (Product) o;
		return name.compareToIgnoreCase(other.getName());
	}

}
