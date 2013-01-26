package model;

/** Product Group class. Inherits from ProductContainer.
 * 
 *  @author - Matt Hess
 *  @version 1.0 - Snell 340 Group 4 Phase 1
 */
public class ProductGroup extends ProductContainer {
	private ProductQuantity threeMonthSupply;
	
	/** Constructor
	 * 
	 * @param pcName - String name of the product group.
	 * @param tmSupply - the three-month supply (ProductQuantity) to be specified for this object
	 * 
	 */
	public ProductGroup(String pcName,ProductQuantity tmSupply) {
		super(pcName);
		
		threeMonthSupply = tmSupply;
	}
	
	/** Default Constructor
	 * 
	 */
	public ProductGroup() {
		super();
		threeMonthSupply = null;
	}
	
	/** Attribute getter - threeMonthSupply
	 * 
	 * @return ProductQuantity - the three-month supply
	 */
	public ProductQuantity getThreeMonthSupply() {
		return threeMonthSupply;
	}

	@Override
	/** Equals override. Note that the object is explicitly
	 *  cast as a ProductGroup object. 
	 * 
	 * @param o - the object to be compared to this one
	 * 
	 */
	public boolean equals(Object o) {
		ProductGroup pg = (ProductGroup) o;
		
		return getName().equals(pg.getName());
	}

}
