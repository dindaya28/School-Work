/**
 * This class represents the key attribute of a record in the ordered dictionary
 * Each key has two parts: word and type
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Pair {
	private String word = "";
	private String type;

	/**
	 * Initializes a Pair containing a word and a type
	 * 
	 * @param word
	 * @param type
	 */
	public Pair(String word, String type) {
		this.word = word.toLowerCase();
		this.type = type.toLowerCase();
	}

	/**
	 * Returns the word stored in this Pair
	 * 
	 * @return
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Returns the type stored in this Pair
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Compares the word and type of this Pair and another Pair. Returns an integer:
	 * -1 if this Pair is less than comparison Pair, 0 if the two Pairs are
	 * identical, 1 if the this Pair is greater than comparison Pair
	 * 
	 * @param k
	 * @return
	 */
	public int compareTo(Pair k) {
		int value = 1; // if none of the loops are entered then the default will be this Pair word is
						// greater than comparison Pair

		if (this.word.equals(k.getWord())) { // Check for identical words from Pairs
			if (this.type.equals(k.getType())) // Check for identical types from Pairs
				value = 0;
			else if (this.type.compareTo(k.getType()) < 0) // Check if this Pair type is less than comparison Pair
				value = -1;
			else if (this.type.compareTo(k.getType()) > 0) // Check if this Pair type is greater than comparison Pair
				value = 1;
		} else if (this.word.compareTo(k.getWord()) < 0) // Check if this Pair word is less than comparison Pair
			value = -1;

		return value;
	}
}
