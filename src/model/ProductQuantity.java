package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Maintain a quantity along with an enumerated unit.
 */
public class ProductQuantity {
	
	/** Non-negative value for quantity. If units are count, must be integer.  */
	private float quantity;
	
	/**  */
	private Unit units;
	
	/**
	 * ProductQuantity constructor, sets quantity and unit.
	 * @param q
	 * @param u
	 */
	public ProductQuantity(float q, Unit u){
		throw new NotImplementedException();
	}
	
	/**
	 * getter for quantity
	 * @return
	 */
	public float getQuantity(){
		return this.quantity;
	}
	
	/**
	 * assign quantity
	 * @param q - float if unit is not COUNT, integer otherwise
	 */
	public void setQuantity(float q){
		
	}
	
	/**
	 * get units associated with this quantity
	 * @return
	 */
	public Unit getUnits(){
		return this.units;
	}
	
	/**
	 * Add two quantities. Must have matching units.
	 * @param quantityToAdd - ProductQuantity to add to this ProductQuantity, must have non-negative quantity
	 */
	public void add(ProductQuantity quantityToAdd){
		if(quantityToAdd.units != this.units){
			throw new IllegalArgumentException("Cannot add quantities with different units!");
		}
		throw new NotImplementedException();
	}
	
	/**
	 * Subtract two quantities. Must have matching units, result must be non-negative.
	 * @param quantityToSubtract - ProductQuantity to subtract from this ProductQuantity, must have non-negative quantity 
	 */
	public void subtract(ProductQuantity quantityToSubtract){
		if(quantityToSubtract.units != this.units){
			throw new IllegalArgumentException("Cannot subtract quantities with different units!");
		}
		throw new NotImplementedException();
	}
}
