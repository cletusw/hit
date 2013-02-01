package model;
import java.util.TreeMap;
import java.util.Map;


/**
 * Definition of acceptable units.
 */
public enum Unit {
	COUNT, POUNDS, OUNCES, GRAMS, KILOGRAMS, GALLONS, QUARTS, PINTS, FLUID_OUNCES, LITERS;
	
	public static final Map<Unit, UnitType> typeMap = new TreeMap<Unit, UnitType>();
	static {
		typeMap.put(Unit.COUNT, UnitType.COUNT);
		typeMap.put(Unit.POUNDS, UnitType.WEIGHT);
		typeMap.put(Unit.OUNCES, UnitType.WEIGHT);
		typeMap.put(Unit.GRAMS, UnitType.WEIGHT);
		typeMap.put(Unit.KILOGRAMS, UnitType.WEIGHT);
		typeMap.put(Unit.GALLONS, UnitType.VOLUME);
		typeMap.put(Unit.QUARTS, UnitType.VOLUME);
		typeMap.put(Unit.PINTS, UnitType.VOLUME);
		typeMap.put(Unit.FLUID_OUNCES, UnitType.VOLUME);
		typeMap.put(Unit.LITERS, UnitType.VOLUME);
	}
	
	/**
	 * Get the string representation of the Unit.
	 * 
	 * @return String representation of this Unit with first letter capitalized.
	 */
	@Override 
	public String toString() {
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	 }
}
