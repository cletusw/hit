package model;

/** Wrapper class to guarantee a string is not null and not empty
 * 
 * @author Matthew
 * @invariant string != null
 * @invariant !string.isEmpty()
 */
public class NotNullString {
	protected String value;
	
	protected NotNullString(){
		
	}
	
	/** Constructor. Must be given a non-null, non-empty string.
	 * 
	 * @param s non-null, non-empty string.
	 */
	public NotNullString(String s){
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
	 * @param other NotNullString to compare
	 * @return the value 0 if the argument string is equal to this string; 
	 * a value less than 0 if this string is lexicographically less than 
	 * the string argument; and a value greater than 0 if this string is 
	 * lexicographically greater than the string argument.
	 */
	public int compareTo(NotNullString other) {
		return value.compareTo(other.getValue());
	}
}
