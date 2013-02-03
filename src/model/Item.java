package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a physical item in the Home Inventory System.
 *
 * @invariant product != null
 * @invariant barcode != null
 * @invariant entryDate != null
 * @invariant expirationDate != null
 * @invariant container != null
 * 
 */
@SuppressWarnings("serial")
public class Item implements Comparable<Object>, Serializable {
	private Product product;
	private Barcode barcode;
	private Date entryDate;
	private Date expirationDate;
	private Date exitTime;
	private ProductContainer container;
	
	/** Constructs a new Item with the specified barcode, product, and container. Sets entryDate to now.
	 * @param barcode the Item's barcode
	 * @param product this Item's corresponding Product
	 * @param container the ProductContainer this Item is to be stored in
	 */
	public Item(Barcode barcode, Product product, ProductContainer container, ItemManager manager) {
		this(barcode, product, container, manager, new Date());
	}
	
	/** Constructs a new Item with the specified barcode, product, and container.
	 * @param barcode the Item's barcode
	 * @param product this Item's corresponding Product
	 * @param container the ProductContainer this Item is to be stored in
	 */
	public Item(Barcode barcode, Product product, ProductContainer container, ItemManager manager, Date entryDate){
		this.product = product;
		this.container = container;
		this.barcode = barcode;
		this.setEntryDate(entryDate);
		this.setExpirationDate();
		manager.manage(this);
	}
	
	/** Gets this Item's Product
	 * 
	 * @return this Item's Product
	 */
	public Product getProduct() {
		return product;
	}
	
	/** Gets this Item's barcode
	 * 
	 * @return this item's barcode
	 */
	public Barcode getBarcode() {
		return barcode;
	}
	
	/** Sets this Item's entry date
	 * 
	 * @param date the entry date of the Item
	 */
	private void setEntryDate(Date date) {
		// From the Data Dictionary:
		// Must be non-empty.  Cannot be in the 
		// future or prior to 1/1/2000.
		if(date == null){
			this.entryDate = new Date();
			return;
		}
		
		if(date.after(new Date()) || date.before(new Date(100, 0, 0, 0, 0, 0))){
			throw new IllegalArgumentException("Date must not be in future");
		}
		this.entryDate = date;
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
	
	/** Sets this Item's exit time. Must be between the enry date and the
	 * current time.
	 * 
	 * @param time the Item's exit time
	 */
	public void setExitTime(Date time) {
		// TODO: Check if item has been removed?
		if(time.before(this.entryDate) || time.after(new Date())){
			throw new IllegalArgumentException(time.toString() + " is before entryDate");
		}
		
		this.exitTime = time;
	}
	
	/*
	 * Set expiration date using this item's entry date and
	 * the product's shelf life
	 */
	@SuppressWarnings({ "deprecation" })
	private void setExpirationDate(){
		Date d = this.entryDate;
		this.expirationDate = new Date(d.getYear(), d.getMonth() + this.product.getShelfLife(), d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds());
	}
	
	/** Gets this Item's expiration date
	 * 
	 * @return the Item's expiration date
	 */
	public Date getExpirationDate() {
		return this.expirationDate;
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
		return barcode.compareTo(other.barcode);
	}
	
}