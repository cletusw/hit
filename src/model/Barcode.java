package model;

/** Barcode class -- represents a product barcode.
 * 
 *  @author Matt Hess
 *  @version 1.0 -- Snell CS 340 Phase 1.0
 *  
 *  @invariant barcode.length() == 12
 */
public class Barcode extends NonNullString{

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
		super(s);
		if(!isValidBarcode(s))
			throw new IllegalArgumentException("Invalid barcode: " + s);
	}

	/** Default constructor -- shouldn't be used.
	 * 
	 * @pre true
	 * @post true
	 */
	protected Barcode() {
		throw new IllegalArgumentException("Invalid barcode: no string passed");
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
		return this.value;
	}

	/** Checks to see if a given barcode string is valid.
	 *
	 * @param s String barcode to check
	 * @return true if barcode is valid, false otherwise
	 * 

	 * @pre s.length() == 12
	 * @pre s.charAt(0) == '4'
	 * @pre for(char in s) Character.isDigit(char)
	 * @post true
	 * 
	 */
	public static boolean isValidBarcode(String s) {
		if(s == null || s.length() != 12 || s.charAt(0) != '4')
			return false;

		int total = 0;
		for(int i=0; i<=10; i+=2) {
			if(!Character.isDigit(s.charAt(i)))
				return false;
			
			total += Integer.parseInt(Character.toString(s.charAt(i)));
		}
		
		total *= 3;
		
		for(int i=1; i<=9; i+=2) {
			if(!Character.isDigit(s.charAt(i)))
				return false;
			
			total += Integer.parseInt(Character.toString(s.charAt(i)));
		}
		
		total %= 10;
		
		if(total != 0)
			total = 10 - total;
		
		return (total == Integer.parseInt(Character.toString(s.charAt(11))));
	}
	
	/**Compare this not-null string to another using String.compareTo(String other)
	 * 
	 * @param other NotNullString to compare
	 * @return the value 0 if the argument string is equal to this string; 
	 * a value less than 0 if this string is lexicographically less than 
	 * the string argument; and a value greater than 0 if this string is 
	 * lexicographically greater than the string argument.
	 */
	public int compareTo(Barcode other){
		return this.value.compareTo(other.value);
	}
}