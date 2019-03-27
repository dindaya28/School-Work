/**
 * This class represents a record in the ordered dictionary Each record consists
 * of two parts: a key and the data associated with the key.
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Record {
	private Pair key;
	private String data;

	/**
	 * Initializes a record containing a key and data
	 * 
	 * @param key
	 * @param data
	 */
	public Record(Pair key, String data) {
		this.key = key;
		this.data = data;
	}

	/**
	 * Returns the key of this Record
	 * 
	 * @return
	 */
	public Pair getKey() {
		return key;
	}

	/**
	 * Returns the data item in this Record
	 * 
	 * @return
	 */
	public String getData() {
		return data;
	}

}
