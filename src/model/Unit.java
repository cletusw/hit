package model;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * Definition of acceptable units.
 */
public enum Unit implements Serializable {
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
	
	/** Retrieves the conversion factor to convert a quantity of one type of unit to another.
	 * 
	 * @param convertFrom - originalUnits
	 * @param convertTo - new units
	 * @return float conversion factor
	 * 
	 * @pre true
	 * @post true
	 */
	public static float getConversionFactor(Unit convertFrom, Unit convertTo){
		if(convertFrom.equals(convertTo))
			return 1f;
		
		if(typeMap.get(convertFrom).equals(typeMap.get(convertTo))){
			switch(typeMap.get(convertFrom)){
			case COUNT:
				return 1;
			case VOLUME:
				return getVolumeConversionFactor(convertFrom, convertTo);
			case WEIGHT:
				return getWeightConversionFactor(convertFrom, convertTo);
			default:
				throw new NotImplementedException();
			}
		}
		else{
			throw new IllegalArgumentException("Units must match UnitType to convert");
		}
	}
	
	/**
	 * Determines whether a specified integer is a valid count.
	 * @param toTest	the integer to test
	 * @return 			true if toTest is a valid count, false otherwise.
	 * 
	 * @pre true
	 * @post true
	 */
	public static boolean isValidCount(int toTest) {
		return toTest >= 0;
	}
	
	private static float convertFromFluidOunces(Unit convertTo){
		switch(convertTo){
			// FLUID_OUNCES -> GALLONS
			case GALLONS:
				return 0.0078125f;
				
			// FLUID_OUNCES -> QUARTS
			case QUARTS:
				return 0.03125f;
				
			// FLUID_OUNCES -> PINTS
			case PINTS:
				return 0.0625f;
				
			// FLUID_OUNCES -> LITERS
			case LITERS:
				return 0.0295735f;
				
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromGallons(Unit convertTo){
		switch(convertTo){
			// GALLONS -> QUARTS
			case QUARTS:
				return 4;
			
			// GALLONS -> PINTS
			case PINTS:
				return 8;
			
			// GALLONS -> FLUID_OUNCES
			case FLUID_OUNCES:
				return 128;
			
			// GALLONS -> LITERS
			case LITERS:
				return 3.78541f;
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromGrams(Unit convertTo){
		switch(convertTo){
			// GRAMS -> POUNDS
			case POUNDS:
				return 0.00220462f;
				
			// GRAMS -> OUNCES
			case OUNCES:
				return 0.035274f;
				
			// GRAMS -> KILOGRAMS
			case KILOGRAMS:
				return 0.001f;
				
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromKilograms(Unit convertTo){
		switch(convertTo){
			// KILOGRAMS -> POUNDS
			case POUNDS:
				return 2.20462f;
				
			// KILOGRAMS -> OUNCES
			case OUNCES:
				return 35.274f;
				
			// KILOGRAMS -> GRAMS
			case GRAMS:
				return 1000f;
				
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromLiters(Unit convertTo){
		switch(convertTo){
			// LITERS -> GALLONS
			case GALLONS:
				return 0.264172f;
				
			// LITERS -> QUARTS
			case QUARTS:
				return 1.05669f;
				
			// LITERS -> PINTS
			case PINTS:
				return 2.11338f;
				
			// LITERS -> FLUID_OUNCES
			case FLUID_OUNCES:
				return 33.814f;
		
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromOunces(Unit convertTo){
		switch(convertTo){
			// OUNCES -> POUNDS
			case POUNDS:
				return 0.0625f;
				
			// OUNCES -> GRAMS
			case GRAMS:
				return 28.3495f;
				
			// OUNCES -> KILOGRAMS
			case KILOGRAMS:
				return 0.0283495f;
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromPints(Unit convertTo){
		switch(convertTo){
			// PINTS -> GALLONS
			case GALLONS:
				return .125f;
			
			// PINTS -> QUARTS
			case QUARTS:
				return .5f;
			
			// PINTS -> FLUID_OUNCES
			case FLUID_OUNCES:
				return 16f;
			
			// PINTS -> LITERS;
			case LITERS:
				return 0.473176f;
			
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromPounds(Unit convertTo){
		switch(convertTo){
			// POUNDS -> OUNCES
			case OUNCES:
				return 16f;
			
			// POUNDS -> GRAMS
			case GRAMS:
				return 453.592f;
			
			// POUNDS -> KILOGRAMS
			case KILOGRAMS:
				return 0.453592f;
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float convertFromQuarts(Unit convertTo){
		switch(convertTo){
			// QUARTS -> GALLONS
			case GALLONS:
				return .25f;
			
			// QUARTS -> PINTS	
			case PINTS:
				return 2f;
			
			// QUARTS -> FLUID_OUNCES	
			case FLUID_OUNCES:
				return 32f;
			
			// QUARTS -> LITERS
			case LITERS:
				return 0.946353f;
			
			default:
				throw new NotImplementedException();
		}
	}
	
	private static float getVolumeConversionFactor(Unit convertFrom, Unit convertTo){
		switch(convertFrom){

		// GALLONS ->
		case GALLONS:
			return convertFromGallons(convertTo);
				
		// QUARTS ->	
		case QUARTS:
			return convertFromQuarts(convertTo);
		
		// PINTS ->
		case PINTS:
			return convertFromPints(convertTo);
			
		// FLUID_OUNCES ->
		case FLUID_OUNCES:
			return convertFromFluidOunces(convertTo);

		// LITERS ->
		case LITERS:
			return convertFromLiters(convertTo);
		default:
			throw new NotImplementedException();
		}
	}
	
	private static float getWeightConversionFactor(Unit convertFrom, Unit convertTo){
		switch(convertFrom){
		
		// POUNDS -> 
		case POUNDS:
			return convertFromPounds(convertTo);
			
		// OUNCES ->
		case OUNCES:
			return convertFromOunces(convertTo);
			
		// GRAMS -> 
		case GRAMS:
			return convertFromGrams(convertTo);
			
		// KILOGRAMS ->
		case KILOGRAMS:
			return convertFromKilograms(convertTo);
		default:
			throw new NotImplementedException();
		}
	}
	/**
	 * Get the string representation of the Unit.
	 * 
	 * @return String representation of this Unit with first letter capitalized.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override 
	public String toString() {
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	 }
}
