package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a physical item in the Home Inventory System.
 *
 */
@SuppressWarnings("serial")
public class Item implements Comparable<Object>, Serializable {
	private Product product;
	private String barcode;
	private Date entryDate;
	private Date exitTime;
	private ProductContainer container;
	
	/** Constructs a new Item with the specified barcode, product, and container
	 * @param barcode the Item's barcode
	 * @param product this Item's corresponding Product
	 * @param container the ProductContainer this Item is to be stored in
	 */
	public Item(String barcode, Product product, ProductContainer container, ItemManager manager) {
		setBarcode(barcode);
		manager.manage(this);
	}
	
	/** Sets this item's Product using the given Product
	 * 
	 * @param p	the Product which corresponds to this Item
	 */
	public void setProduct(Product p) {
		// From the Data Dictionary: must be non-empty
		product = p;
	}

	/** Gets this Item's Product
	 * 
	 * @return this Item's Product
	 */
	public Product getProduct() {
		return product;
	}
	
	//Sets this Item's internal barcode (assigned by HomeInventoryTracker from constructor)
	private void setBarcode(String bc) {
		// From the Data Dictionary: Must be a valid UPC barcode and unique among all Items. HIT assigns these barcodes.
		barcode = bc;
	}
	
	/** Gets this Item's barcode
	 * 
	 * @return this item's barcode
	 */
	public String getBarcode() {
		return barcode;
	}
	
	/** Sets this Item's entry date
	 * 
	 * @param date the entry date of the Item
	 */
	public void setEntryDate(Date date) {
		// From the Data Dictionary:
		// Must be non-empty.  Cannot be in the 
		// future or prior to 1/1/2000. 
		entryDate = date;
	}
	
	/** Gets this Item's entry date
	 * 
	 * @return this item's entry date
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	
	/** Gets this Item's exit time
	 * 
	 * @return the Item's exit time
	 */
	public Date getExitTime() {
		return exitTime;
	}
	
	/** Sets this Item's exit time
	 * 
	 * @param time the Item's exit time
	 */
	public void setExitTime(Date time) {
		// From the Data Dictionary:
		// This attribute is defined only if the Item 
		// has been removed from storage. 
		// Cannot be in the future or prior to 12 
		// AM on the Item’s Entry Date. 
	}
	
	/** Gets this Item's expiration date
	 * 
	 * @return the Item's expiration date
	 */
	public Date getExpirationDate() {
		// This attribute is defined only if the 
		// Product’s Shelf Life attribute has been 
		// specified. 
		
		//TODO: Compute this dynamically from the Product, the current date, and the Item's creation Date.
		return new Date();
	}
	
	/** Sets this Item's parent container to c
	 * 
	 * @param c the Item's parent ProductContainer
	 */
	public void setContainer(ProductContainer c) {
		container = c;
	}
	
	/** Gets this Item's parent container
	 * 
	 * @return this Item's parent ProductContainer
	 */
	public ProductContainer getContainer() {
		// From the Data Dictionary: 
		// Empty if the Item has been removed 
		// from storage.  Non-empty if the Item has 
		// not been removed from storage.  
		// (Before it is removed, an Item is 
		// contained in one Product Container.  
		// After it is removed, it is contained in no 
		// Product Containers.) 
		return container;
	}
	
	@Override
	/** Compares this Item to another object
	 * @param o the object to compare this Item to
	 * @return zero if the items are equal, otherwise a number greater than or less than zero
	 */
	public int compareTo(Object o) {
		Item other = (Item) o;
		return barcode.compareToIgnoreCase(other.getBarcode());
	}
	
}