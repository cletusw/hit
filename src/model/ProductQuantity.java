package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** Maintain a quantity (volume) along with an enumerated unit.
 * 
 * @author Matt Matheson
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * 
 */
public class ProductQuantity {

	// Member variables
	private float quantity;
	private Unit units;
	
	/** Constructor
	 * @param quantity Quantity, must be non-negative. If Unit is COUNT, must be integer.
	 * @param units Unit
	 */
	public ProductQuantity(float quantity, Unit units){
		this.units = units;
		setQuantity(quantity);
	}
	
	/** Checks if float q and Unit u can be combined to create a valid
	 * Product Group. q must be non-negative. If u is COUNT, q must be
	 * an integer.
	 * 
	 * @param quantity float quantity to test.
	 * @param units Unit to test.
	 * @return true if quantity, units can be combined to make a valid ProductQuantity,
	 * false otherwise 
	 * 
	 */
	public static boolean isValidProductQuantity(float quantity, Unit units){
		if(quantity < 0){
			return false;
		}
		
		if(units == Unit.COUNT && quantity != Math.round(quantity)){
			return false;
		}
		
		return true;
	}
	
	/** Attribute getter for quantity
	 * 
	 * @return The float quantity associated with this ProductQuantity
	 */
	public float getQuantity(){
		return this.quantity;
	}
	
	/** Attribute setter for quantity. This setter enforces that if the units
	 * are COUNT, the quantity must be
	 *  
	 * @param quantity Float if units is not COUNT, integer otherwise
	 * @throws IllegalArgumentException If units are COUNT and quantity is not an integer 
	 */
	public void setQuantity(float quantity) throws IllegalArgumentException{
		if(!isValidProductQuantity(quantity, this.units)){
			throw new IllegalArgumentException("Units are COUNT so quantity must be integer. Given " + quantity);
		}
		this.quantity = quantity;
	}
	
	/** Attribute getter for Unit associated with this quantity
	 * 
	 * @return Unit Enum of this ProductQuantity
	 * 
	 */
	public Unit getUnits(){
		return this.units;
	}
	
	/** Adds a ProductQuantity to this ProductQuantity.
	 * To add two ProductQuantities, their Unit must match.
	 * 
	 * @param quantityToAdd - ProductQuantity to add to this ProductQuantity
	 * 
	 * @throws IllegalArgumentException If this ProductQuantity's Unit does not
	 * match incoming ProductQuantity Unit
	 * 
	 */
	public void add(ProductQuantity quantityToAdd) throws IllegalArgumentException{
		if(quantityToAdd.units != this.units){
			throw new IllegalArgumentException("Cannot add quantities with different units!");
		}
		throw new NotImplementedException();
	}
	
	/** Subtracts a ProductQuantity from this ProductQuantity. The two ProductQuantities
	 * Must have matching units, and the result result must be non-negative.
	 * 
	 * @param quantityToSubtract - ProductQuantity to subtract from this ProductQuantity, must have non-negative quantity
	 *  
	 * @throws IllegalArgumentException If this ProductQuantity's Unit does not
	 * match incoming ProductQuantity Unit
	 */
	public void subtract(ProductQuantity quantityToSubtract){
		if(quantityToSubtract.units != this.units){
			throw new IllegalArgumentException("Cannot subtract quantities with different units!");
		}
		throw new NotImplementedException();
	}
	
	
	/** Gets a legible string representation of this ProductQuantity 
	 * in the format <quantity> <Unit>
	 * 
	 * @return String representation of ProductQuantity
	 */
	public String toString(){
		if (units == Unit.COUNT) {
			return Math.round(quantity) + " " + units.toString();
		}
		else {
			return quantity + " " + units.toString();
		}
	}
}
