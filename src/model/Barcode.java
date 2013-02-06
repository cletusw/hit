package model;

import java.util.Date;
import java.util.Random;

/** Barcode class -- represents a product barcode.
 * 
 *  @author Matt Hess
 *  @version 1.0 -- Snell CS 340 Phase 1.0
 *  
 *  @invariant value.length() == 12
 */
@SuppressWarnings("serial")
public class Barcode extends NonNullString{

	/** Constructor
	 * 
	 * @param s
	 * @throws IllegalArgumentException
	 * 
	 * @pre s != null
	 * @pre !s.equals("")
	 * @post barcode.equals(s)
	 * @post isValidBarcode(barcode) == true
	 * 
	 */
	public Barcode(String s) throws IllegalArgumentException {
		super(s);
		if(!isValidBarcode(s))
			throw new IllegalArgumentException("Invalid barcode: " + s);
	}

	/** Default constructor - generates a new, unique UPC-A barcode
	 * 
	 * @pre true
	 * @post getValue() != null
	 * @post isValidBarcode(barcode) == true
	 */
	public Barcode() {
		this(generateBarcode());
	}
	
	private static String generateBarcode(){
		Random randomGenerator = new Random();
		String code = "4";
		for(int i=0; i<10; i++) 
			code += Integer.toString(randomGenerator.nextInt(10));
		
		int oddDigits = 3 * (Character.getNumericValue(code.charAt(0)) + 
				Character.getNumericValue(code.charAt(2)) + 
				Character.getNumericValue(code.charAt(4)) + 
				Character.getNumericValue(code.charAt(6)) + 
				Character.getNumericValue(code.charAt(8)) + 
				Character.getNumericValue(code.charAt(10)));
		
		int evenDigits = (Character.getNumericValue(code.charAt(1)) + 
				Character.getNumericValue(code.charAt(3)) + 
				Character.getNumericValue(code.charAt(5)) + 
				Character.getNumericValue(code.charAt(7)) +
				Character.getNumericValue(code.charAt(9)));
		
		int total = oddDigits + evenDigits;
		total = total % 10;
		int checkDigit = -1;
		if(total == 0)
			checkDigit = 0;
		else
			checkDigit = 10 - total;
		code = code + checkDigit;
		
		if(isValidBarcode(String.valueOf(code)))
			return String.valueOf(code);
		throw new IllegalArgumentException("Unable to create valid barcode");
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
	 * @pre s != null
	 * @pre s.length() == 12
	 * @pre s.charAt(0) == '4'
	 * @pre for(char in s) Character.isDigit(char)
	 * @post true
	 * 
	 */
	public static boolean isValidBarcode(String s) {
		assert(s != null);
		assert(s.length() == 12);
		assert(s.charAt(0) == '4');
		for(int i=0; i<s.length(); i++) {
			assert(Character.isDigit(s.charAt(i)));
		}
		
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
	 * 
	 * @pre other != null
	 * @post true
	 */
	public int compareTo(Barcode other){
		assert(other != null);
		
		return this.value.compareTo(other.value);
	}
}