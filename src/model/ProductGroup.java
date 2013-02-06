package model;

import java.util.Iterator;

/** Product Group class. Inherits from ProductContainer.
 * 
 *  @author - Matt Hess
 *  @version 1.0 - Snell 340 Group 4 Phase 1
 *  
 *  @invariant container != null
 */
@SuppressWarnings("serial")
public class ProductGroup extends ProductContainer {
	private ProductQuantity threeMonthSupply;
	private ProductContainer container;
	private Unit groupUnit;
	
	/** Constructor
	 * 
	 * @param pcName - String name of the product group.
	 * @param tmSupply - the three-month supply (ProductQuantity) to be specified for this object
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public ProductGroup(String pcName, ProductQuantity tmSupply, Unit groupUnit) {
		super(pcName);
		
		threeMonthSupply = tmSupply;
		this.groupUnit = groupUnit;
	}
	
	/** Method that calculates and returns the amount of a product group in this container.
	 * 
	 * @return ProductQuantity - the current supply of this container
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public ProductQuantity getCurrentSupply() {
		Iterator<Product> it = getProductsIterator();
		ProductQuantity total = new ProductQuantity(0, groupUnit);
		while(it.hasNext()) {
			try {
				ProductQuantity pSupply = getCurrentSupply(it.next());
				total.add(pSupply);
			} catch(IllegalArgumentException e) {
				continue;
			}
		}
		
		return total;
	}
	
	/** Attribute getter - threeMonthSupply
	 * 
	 * @return ProductQuantity - the three-month supply
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public ProductQuantity getThreeMonthSupply() {
		return threeMonthSupply;
	}
	
	/**
	 * Gets this ProductGroup's parent container
	 * @return this ProductGroup's container
	 * 
	 * @pre container != null
	 * @post container != null
	 * 
	 */
	public ProductContainer getContainer() {
		return container;
	}
	
	/**
	 * Sets this ProductGroup's parent container to the specified container.
	 * @param container the new parent container
	 * 
	 * @pre container != null
	 * @post this.container == container
	 */
	public void setContainer(ProductContainer container) {
		// From the Data Dictionary: 
		// Must be non-empty.  A Product Group is 
		// always contained within one Product 
		// Container. 
		this.container = container;
	}
	
	/**
	 * Sets this ProductGroup's three-month supply
	 * @param threeMonthSupply - the new quantity for the three-month supply
	 * 
	 * @pre true
	 * @post this.threeMonthSupply.equals(threeMonthSupply)
	 */
	public void setThreeMonthSupply(ProductQuantity threeMonthSupply) {
		this.threeMonthSupply = threeMonthSupply;
	}
	
	/**
	 * Determines whether a given ProductQuantity is a valid three-month supply
	 * @param threeMonthSupply - the ProductQuantity to test
	 * @return true if the ProductQuantity is valid, false otherwise.
	 * 
	 * @pre threeMonthSupply != null
	 * @post true
	 * 
	 */
	public boolean isValidThreeMonthSupply(ProductQuantity threeMonthSupply) {
		// From the Data Dictionary:
		// The magnitude can be any non-negative float value.  Zero means "unspecified".  
		// The unit of measurement can be any of the following: count, pounds, ounces, 
		// grams, kilograms, gallons, quarts, pints, fluid ounces, liters.  If the unit of 
		// measurement is “count”, the magnitude must be an integer (i.e., no fraction). 
		return ProductQuantity.isValidProductQuantity(threeMonthSupply.getQuantity(),
				threeMonthSupply.getUnits());
	}
}
