package model;

/**
 * Definition of acceptable units
 */
public enum Unit {
	COUNT, POUNDS, OUNCES, GRAMS, KILOGRAMS, GALLONS, QUARTS, PINTS, FLUID_OUNCES, LITERS;
	
	/**
	 * Print unit with only first letter capitalized
	 */
	@Override 
	public String toString() {
	   String s = super.toString();
	   return s.substring(0, 1) + s.substring(1).toLowerCase();
	 }
}
