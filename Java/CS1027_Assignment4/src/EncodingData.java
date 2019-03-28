/**
 * Class creates and modifies object containing a character and associated bit
 * code
 * 
 * @author Dinushan Dayarathna
 *
 */
public class EncodingData {

	// Instance variables
	private char symbol;
	private String encoding;

	/**
	 * Constructor
	 * 
	 * @param sym
	 *            of type character
	 * @param encode
	 *            of type String
	 */
	public EncodingData(char sym, String encode) {
		symbol = sym;
		encoding = encode;
	}

	/**
	 * Getter method for the symbol variable
	 * 
	 * @return character
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Getter method for the encoding variable
	 * 
	 * @return string
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Setter method for the symbol variable
	 * 
	 * @param sym
	 *            type char
	 */
	public void setSymbol(char sym) {
		symbol = sym;
	}

	/**
	 * Setter method for the encoding variable
	 * 
	 * @param encode
	 */
	public void setEncoding(String encode) {
		encoding = encode;
	}

	/**
	 * determines if the parameter's symbol is equal to this symbol
	 * 
	 * @param type
	 *            object
	 */
	public boolean equals(Object obj) {

		EncodingData other = (EncodingData) obj;
		if (symbol != other.symbol)
			return false;
		else
			return true;
	}

	/**
	 * Method returns a string representation of the 2 instance variables contained
	 * within this object
	 * 
	 * @return type string
	 */
	public String toString() {
		String str = "";
		str = str + Character.toString(symbol) + " - " + encoding;
		return str;
	}

}
