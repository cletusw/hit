package model;

import java.io.Serializable;

public enum UnitType implements Serializable {
	VOLUME, WEIGHT, COUNT;

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
