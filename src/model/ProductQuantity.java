package model;

import java.io.Serializable;

/**
 * Maintain a quantity (volume) along with an enumerated unit.
 * 
 * @author Matt Matheson
 * @version 1.0 - Snell 340 Group 4 Phase 1
 * 
 * @invariant quantity >= 0.0
 * @invariant units != null
 */
@SuppressWarnings("serial")
public class ProductQuantity implements Serializable {

	/**
	 * Checks if float q and Unit u can be combined to create a valid Product Group. q must be
	 * non-negative. If u is COUNT, q must be an integer.
	 * 
	 * @param quantity
	 *            float quantity to test.
	 * @param units
	 *            Unit to test.
	 * @return true if quantity, units can be combined to make a valid ProductQuantity, false
	 *         otherwise
	 * 
	 * @pre true
	 * @post true
	 */
	public static boolean isValidProductQuantity(float quantity, Unit units) {
		if (quantity < 0) {
			return false;
		}

		if (units == Unit.COUNT && quantity != Math.round(quantity)) {
			return false;
		}

		return true;
	}

	// Member variables
	private float quantity;
	private Unit units;
	private UnitType unitType;

	/**
	 * Constructor
	 * 
	 * @param q
	 *            Quantity, must be non-negative. If Unit is COUNT, must be integer.
	 * @param u
	 *            Unit
	 * 
	 * @pre quantity >= 0.0
	 * 
	 * @post units.equals(u)
	 * @post quantity.equals(q)
	 * @post isValidProductQuantity(this.units, this.quantity)
	 */
	public ProductQuantity(float q, Unit u) {
		if (q < 0.0) {
			throw new IllegalArgumentException("q less than 0.0");
		}

		units = u;
		unitType = Unit.typeMap.get(units);
		setQuantity(q);
	}

	/**
	 * Adds a ProductQuantity to this ProductQuantity. If their UnitTypes don't match, no
	 * effect will take place. If their units don't match but the UnitTypes do, the incoming
	 * ProductQuantity will be converted to a matching unit and added to this product quantity.
	 * This ProductQuantity's units will not be modified.
	 * 
	 * @param otherQuantity
	 *            - ProductQuantity to add to this ProductQuantity
	 * 
	 * @pre otherQuantity != null
	 * @pre unitType.equals(otherQuantity.unitType)
	 * @post quantity.equals(quantity + quantityToAdd.quantity)
	 */
	public void add(ProductQuantity otherQuantity) throws IllegalArgumentException {
		if (otherQuantity == null)
			throw new IllegalArgumentException("Cannot add a null productQuantity");
		if (!unitType.equals(otherQuantity.unitType)) {
			throw new IllegalArgumentException(
					"Cannot add quantities with different unit types.");
		}

		float conversionFactor = Unit.getConversionFactor(otherQuantity.units, units);
		quantity += (otherQuantity.quantity * conversionFactor);
	}

	/**
	 * Determines whether this ProductQuantity is equal to another.
	 * 
	 * @param other
	 *            the Object to test for equality
	 * @return true if the objects are equal, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) 
			return false;
		if (!(other instanceof ProductQuantity))
			return super.equals(other);
		ProductQuantity pq = (ProductQuantity) other;
		return quantity == pq.quantity && units == pq.units;
	}

	/**
	 * Attribute getter for quantity
	 * 
	 * @return The float quantity associated with this ProductQuantity
	 * 
	 * @pre true
	 * @post true
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * Attribute getter for Unit associated with this quantity
	 * 
	 * @return Unit Enum of this ProductQuantity
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public Unit getUnits() {
		return units;
	}

	/**
	 * Attribute setter for quantity. This setter enforces that if the units are COUNT, the
	 * quantity must be an Integer
	 * 
	 * @param q
	 *            Float if units is not COUNT, integer otherwise
	 * @throws IllegalArgumentException
	 *             If units are COUNT and quantity is not an integer
	 * 
	 * @pre isValidProductQuantity(q, this.units)
	 * @post quantity.equals(q)
	 */
	public void setQuantity(float q) throws IllegalArgumentException {
		if (!isValidProductQuantity(q, units)) {
			throw new IllegalArgumentException("Invalid quantity: " + quantity);
		}
		quantity = q;
	}

	/**
	 * Subtracts a ProductQuantity from this ProductQuantity. The two ProductQuantities Must
	 * have matching units, and the result result must be non-negative.
	 * 
	 * @param quantityToSubtract
	 *            - ProductQuantity to subtract from this ProductQuantity, must have
	 *            non-negative quantity
	 * 
	 * @pre otherQuantity != null
	 * @pre unitType.equals(otherQuantity.unitType)
	 * @pre quantity - otherQuantity.quantity >= 0.0
	 * @post quantity.equals(quantity - otherQuantity.quantity)
	 */
	public void subtract(ProductQuantity otherQuantity) {
		if (otherQuantity == null) {
			throw new NullPointerException("Null ProductQuantity otherQuantity");
		}
		if (!unitType.equals(otherQuantity.unitType)) {
			throw new IllegalArgumentException(
					"Cannot add quantities with different unit types.");
		}

		float conversionFactor = Unit.getConversionFactor(otherQuantity.units, units);
		if (quantity - (otherQuantity.quantity * conversionFactor) < 0) {
			throw new IllegalArgumentException("Subtraction result would be negative");
		}

		quantity -= (otherQuantity.quantity * conversionFactor);
	}

	/**
	 * Gets a legible string representation of this ProductQuantity in the format [quantity]
	 * [Unit]
	 * 
	 * @return String representation of ProductQuantity
	 * @pre true
	 * @post true
	 */
	@Override
	public String toString() {
		if (units == Unit.COUNT) {
			return Math.round(quantity) + " " + units.toString();
		} else {
			return quantity + " " + units.toString();
		}
	}
}
