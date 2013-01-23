package model;

public class Item implements Comparable<Object> {
	private String name;
	
	public Item(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Object o) {
		Item other = (Item) o;
		return name.compareToIgnoreCase(other.getName());
	}
	
}