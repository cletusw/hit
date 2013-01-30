package model;

/** Product Group class. Inherits from ProductContainer.
 * 
 *  @author - Matt Hess
 *  @version 1.0 - Snell 340 Group 4 Phase 1
 *  
 *  @invariant container != null
 */
public class ProductGroup extends ProductContainer {
	private ProductQuantity threeMonthSupply;
	private ProductContainer container;
	
	/** Constructor
	 * 
	 * @param pcName - String name of the product group.
	 * @param tmSupply - the three-month supply (ProductQuantity) to be specified for this object
	 * 
	 * @pre true
	 * @post container != null
	 */
	public ProductGroup(String pcName,ProductQuantity tmSupply) {
		super(pcName);
		
		threeMonthSupply = tmSupply;
	}
	
	/** Default Constructor
	 * 
	 * @pre true
	 * @post container != null
	 * 
	 */
	public ProductGroup() {
		super();
		threeMonthSupply = null;
	}
	
	@Override
	/** Equals override. Note that the object is explicitly
	 *  cast as a ProductGroup object. 
	 * 
	 * @param o - the object to be compared to this one
	 * 
	 * @pre o != null
	 * @pre o instanceof ProductGroup
	 * @pre getName() != null
	 * @post true
	 * 
	 */
	public boolean equals(Object o) {
		ProductGroup pg = (ProductGroup) o;
		
		return getName().equals(pg.getName());
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
	 * @post this.threeMonthSupply == threeMonthSupply
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
	 */
	public boolean isValidThreeMonthSupply(ProductQuantity threeMonthSupply) {
		// From the Data Dictionary:
		// The magnitude can be any non-negative float value.  Zero means "unspecified".  
		// The unit of measurement can be any of the following: count, pounds, ounces, 
		// grams, kilograms, gallons, quarts, pints, fluid ounces, liters.  If the unit of 
		// measurement is “count”, the magnitude must be an integer (i.e., no fraction). 
		return true;
	}

}
