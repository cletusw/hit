package model;

import java.io.Serializable;

/**
 * Wrapper class to guarantee a string is not null and not empty
 * 
 * @author Matthew Matheson
 * @invariant toString() != null
 * @invariant !toString().isEmpty()
 */
@SuppressWarnings("serial")
public class NonEmptyString implements Serializable, Comparable<Object> {
	private String value;

	/**
	 * Constructor. Must be given a non-null, non-empty string.
	 * 
	 * @param s
	 *            non-null, non-empty string.
	 * 
	 * @pre s != null
	 * @pre !s.isEmpty()
	 * @post toString() == s
	 */
	public NonEmptyString(String s) {
		if (s == null || s.isEmpty()) {
			throw new IllegalArgumentException("Empty or null string");
		}

		value = s;
	}

	protected NonEmptyString() {

	}

	/**
	 * Compare this NonEmptyString to another using String.compareTo(String other)
	 * 
	 * @param other
	 *            Another NonNullString or String to compare to
	 * @return the value 0 if the argument string is equal to this string; a value less than 0
	 *         if this string is lexicographically less than the string argument; and a value
	 *         greater than 0 if this string is lexicographically greater than the string
	 *         argument.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public int compareTo(Object other) {
		return value.compareTo(other.toString());
	}

	/**
	 * Returns the string representation of this NonEmptyString.
	 * 
	 * @return String representation of this NonEmptyString.
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public String toString() {
		return value;
	}
}
