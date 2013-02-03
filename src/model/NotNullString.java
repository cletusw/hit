package model;

/** Wrapper class to guarantee a string is not null and not empty
 * 
 * @author Matthew
 * @invariant string != null
 * @invariant !string.isEmpty()
 */
public class NotNullString {
	private String string;
	
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
		
		this.string = s;
	}
	
	/** Returns the string value of the notnullstring. 
	 * 
	 * @return String representation of the notnullstring.
	 * 
	 * @pre true
	 * @post true
	 */
	public String getValue(){
		return this.string;
	}
}
