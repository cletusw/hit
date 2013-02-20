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
	/**
	 * Determines if the given string is non-null and non-empty
	 * 
	 * @param s
	 *            String to test
	 * @return true if s is non-null and non-empty; false otherwise
	 */
	public static boolean isValid(String s) {
		return s != null && !s.isEmpty();
	}

	private String value;

	/**
	 * Constructor. Must be given a non-null, non-empty string.
	 * 
	 * @param s
	 *            non-null, non-empty string.
	 * 
	 * @pre isValid(s)
	 * @post toString() == s
	 */
	public NonEmptyString(String s) {
		if (!isValid(s)) {
			throw new IllegalArgumentException("Empty or null string");
		}

		value = s;
	}

	/**
	 * Compare this NonEmptyString to another using String.compareTo(String other)
	 * 
	 * @param other
	 *            Another NonEmptyString or String to compare to
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
	 * Compare this NonEmptyString to another
	 * 
	 * @param o
	 *            Another NonEmptyString or String to compare to
	 * @return true if this NonEmptyString and the one provided have the same String contents
	 * 
	 * @pre true
	 * @post true
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof NonEmptyString) {
			NonEmptyString other = (NonEmptyString) o;
			return value.equals(other.value);
		} else if (o instanceof String) {
			String other = (String) o;
			return value.equals(other);
		} else {
			return super.equals(o);
		}
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
