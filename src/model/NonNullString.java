package model;

import java.io.Serializable;

/** Wrapper class to guarantee a string is not null and not empty
 * 
 * @author Matthew Matheson
 * @invariant value != null
 * @invariant !value.isEmpty()
 */
@SuppressWarnings("serial")
public class NonNullString implements Serializable, Comparable {
	protected String value;
	
	protected NonNullString(){
		
	}
	
	/** Constructor. Must be given a non-null, non-empty string.
	 * 
	 * @param s non-null, non-empty string.
	 * 
	 * @pre true
	 * @post value == s
	 * @post value != null
	 */
	public NonNullString(String s){
		if(s == null || s.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		this.value = s;
	}
	
	/** Returns the string value of the notnullstring. 
	 * 
	 * @return String representation of the notnullstring.
	 * 
	 * @pre true
	 * @post true
	 */
	public String getValue(){
		return this.value;
	}

	/**Compare this not-null string to another using String.compareTo(String other)
	 * 
	 * @param other NotNullString to compare, or String
	 * @return the value 0 if the argument string is equal to this string; 
	 * a value less than 0 if this string is lexicographically less than 
	 * the string argument; and a value greater than 0 if this string is 
	 * lexicographically greater than the string argument.
	 * 
	 * @pre other must be an instance of NonNullString of String
	 * @post true
	 */
	public int compareTo(Object other) {
		assert(other instanceof NonNullString || other instanceof String);
		if(other instanceof NonNullString){
			return this.value.compareTo(((NonNullString) other).getValue());
		}
		else if(other instanceof String){
			return this.value.compareTo((String)other);
		}
		return -1;
	}
}
