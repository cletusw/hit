package model;

/** Barcode class -- represents a product barcode.
 * 
 *  @author Matt Hess
 *  @version 1.0 -- Snell CS 340 Phase 1.0
 *  
 *  @invariant s != null
 *  @invariant s.length() > 0
 */
public class Barcode {
	private String barcode;
	
	/** Constructor
	 * 
	 * @param s
	 * @throws IllegalArgumentException
	 * 
	 * @pre s != null
	 * @pre !s.equals("")
	 * @post barcode.equals(s)
	 * @post isValidBarcode(barcode)
	 * 
	 */
	public Barcode(String s) throws IllegalArgumentException {
		if(!isValidBarcode(s))
			throw new IllegalArgumentException("Invalid barcode: " + s);
		
		barcode = s;
	}
	
	/** Default constructor -- shouldn't be used.
	 * 
	 * @pre true
	 * @post true
	 */
	protected Barcode() {
		
	}
	
	/** Returns the string value of the barcode. 
	 * 
	 * @return String representation of the barcode.
	 * 
	 * @pre true
	 * @post true
	 * 
	 */
	public String getValue() {
		return barcode;
	}
	
	/** Checks to see if a given barcode string is valid.
	 *
	 * @param s String barcode to check
	 * @return true if barcode is valid, false otherwise
	 * 
	 * @pre s!=null
	 * @pre !s.equals("")
	 * 
	 * @post true
	 */
	private boolean isValidBarcode(String s) {
		if(s == null || s.equals(""))
			return false;
		
		return false;
	}

}
