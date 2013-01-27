package model;

import java.io.Serializable;
import java.util.Collection;

import java.util.Date;

/**
 * A representation of a product within the Home Inventory System
 *
 */
public class Product implements Comparable<Object>, Serializable {
	private String barcode;
	private Date creationDate;
	private String description;
	private int shelfLife;
	private int threeMonthSupply;
	private Collection<ProductContainer> productContainers;
	private ProductQuantity size;
	
	/** 
	 * Constructs a Product using the given barcode and description
	 * @param barcode
	 */
	public Product(String barcode, String description) {
		this.barcode = barcode;
		this.description = description;
	}
	
	/** Gets this Product's size
	 * 
	 * @return this Product's size
	 */
	public ProductQuantity getSize() {
		return size;
	}

	/** Sets this Product's size
	 * 
	 * @param size the size to set for the Product
	 */
	public void setSize(ProductQuantity size) {
		this.size = size;
	}
	
	/** Checks to determine if the given parameters constitute a valid Product size.
	 * 
	 * @param q	the magnitude of the Product size to check
	 * @param u the Unit of the Product size to check
	 * @return true if the parameters constitute a valid Product size, false otherwise.
	 */
	public boolean isValidSize(float q, Unit u) {
		// From the Data Dictionary: The magnitude can be any positive float value (zero is not allowed).
		//  if the unit of measurement is "count", the magnitude must be "1".
		if (u.equals(Unit.COUNT) && q != 1)
			return false;
		return q > 0;
	}

	/** Gets this Product's description
	 * 
	 * @return this Product's description
	 */
	public String getDescription() {
		return description;
	}

	/** Sets this Product's description to the specified string
	 * 
	 * @param description the description to apply to the Product
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Gets this Product's shelf life
	 * 
	 * @return this Product's shelf life
	 */
	public int getShelfLife() {
		return shelfLife;
	}

	/** Sets this Product's shelf life
	 * 
	 * @param shelfLife this Product's shelf life
	 */
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	/**
	 * Gets this Product's three-month supply.
	 * @return this Product's three-month supply.
	 */
	public int getThreeMonthSupply() {
		return threeMonthSupply;
	}

	/**
	 * Sets this Product's three-month supply to the indicated amount
	 * @param threeMonthSupply the number (count) of this product required for a three-month supply
	 */
	public void setThreeMonthSupply(int threeMonthSupply) {
		this.threeMonthSupply = threeMonthSupply;
	}

	/**
	 * Determines whether given integer is a valid three-month supply.
	 * @param tms the integer to check
	 * @return true if the integer is a valid three-month supply, false otherwise.
	 */
	public boolean isValidThreeMonthSupply(int tms) {
		// From the Data Dictionary: Must be non-negative
		return tms >= 0;
	}
	
	/**
	 * Sets this Product's barcode to the specified value
	 * @param barcode the Product's barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	/**
	 * Determines whether the given string is a valid barcode.
	 * @param barcode the string to test
	 * @return true if the barcode is valid, false otherwise.
	 */
	public boolean isValidBarcode(String barcode) {
		// From the Data Dictionary: must be non-empty
		return (!barcode.equals(""));
	}
	
	/**
	 * Determines whether the given string is a valid description
	 * @param desc the string to test
	 * @return true if the string is valid, false otherwise.
	 */
	public boolean isValidDescription(String desc) {
		// From the Data Dictionary: must be non-empty
		return (!desc.equals(""));
	}
	
	/**
	 * Determines whether the given integer is a valid shelf life.
	 * @param sl the integer to test
	 * @return true if the shelf life is valid, false otherwise.
	 */
	public boolean isValidShelfLife(int sl) {
		// From the Data Dictionary: Must be non-negative
		return sl >= 0;
	}
	
	/**
	 * Adds this Product to the specified container.
	 * The container is responsible for checking that this is a valid operation.
	 * @param container the container to add this Product to
	 */
	public void addToContainer(ProductContainer container) {
		// From the Data Dictionary: At most one ProductContainer in a StorageUnit may contain a particular Product.
	}
	
	/**
	 * Removes this Product from the specified container.
	 * @param container the container to remove this Product from.
	 */
	public void removeFromContainer(ProductContainer container) {
		// From the Data Dictionary: 
	}
	
	/** 
	 * Gets this Product's barcode
	 * @return this Product's barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/** 
	 * Gets this Product's creation date
	 * @return this Product's creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	@Override
	/**
	 * Compares this Product to another Object.
	 */
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
