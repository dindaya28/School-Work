/**
 * Class creates a hash dictionary Using an array that points to a linked list
 * Array stores hash codes respective to the nodes that they point to
 * 
 * @author Dinushan Dayarathna
 *
 */
public class HashDictionary implements DictionaryADT {

	// Instance variables
	private Node[] dict;
	private int M;

	/**
	 * Hash function used to assign hash codes to the incoming config strings Takes
	 * into account the length of the string and repeatedly applies the function
	 * until a unique hash code is produced
	 * 
	 * @param config
	 * @return hash code
	 */
	private int hashKey(String config) {
		int sum = (int) config.charAt(config.length() - 1);
		for (int i = 0; i < config.length(); i++) {
			// using 139 prime number here as it produces the least amount of collisions
			sum = ((sum * 139) + (int) config.charAt(i)) % M;
		}
		return sum % M;
	}

	/**
	 * Initializes a dictionary that points to an empty node at each point in the
	 * array
	 * 
	 * @param size
	 */
	public HashDictionary(int size) {
		dict = new Node[size];
		for (int i = 0; i < size; i++) {
			dict[i] = new Node();
		}
		this.M = size;
	}

	/**
	 * Method adds a Configuration object into the dictionary by assigning it a hash
	 * code The hash code is used to assign it a specific location The Configuration
	 * object is then stored within the node that is associated with the hash code
	 * location
	 * 
	 * @param Configuration
	 * @return integer
	 */
	public int put(Configuration data) throws DictionaryException {
		// Uses the hash method to assign a hash key to the data type
		int hashCode = hashKey(data.getStringConfiguration());

		Node currentNode = dict[hashCode];
		/*
		 * Check to see if a collision occurs If collision occurs, the linked list at
		 * that hash code is extended to add another configuration
		 */
		if (!currentNode.isEmpty()) {
			do {
				if (currentNode.getConfig().getStringConfiguration().equals(data.getStringConfiguration()))
					throw new DictionaryException("Pair exists already");
				if (currentNode.hasNext())
					currentNode = currentNode.getNext();
			} while (currentNode.hasNext());

			currentNode.setHead(data);
			currentNode.setNext(new Node());
			return 1;
		}
		// Add the configuration object to the linked list at the specified hash code
		// location when no collisions occur
		else {
			currentNode.setHead(data);
			currentNode.setNext(new Node());
			return 0;
		}
	}

	/**
	 * Method removes a configuration data type by looking up its assigned hash code
	 * 
	 */
	public void remove(String data) throws DictionaryException {
		Node item = null;
		Node item2 = null;
		// Determines hash code to look for the location of the stored data
		int hashCode = hashKey(data);
		// Check if the data entry is stored
		if (dict[hashCode].isEmpty())
			throw new DictionaryException("Data item not found");
		else {
			while (dict[hashCode].hasNext()) {
				item = dict[hashCode];
				item2 = dict[hashCode].getNext();
				if (item.getConfig().getStringConfiguration().equals(data)) {
					item.setNext(item2.getNext());
				}
			}
		}
	}

	/**
	 * Returns the score of a particular configuration object Looks up assigned hash
	 * code to determine location of object
	 */
	public int getScore(String config) {
		int score = 0;
		// Determines hash code to look for the location of the stored data
		int hashCode = hashKey(config);
		Node currentNode = dict[hashCode];
		if (currentNode.isEmpty())
			score = -1;
		else {
			do {
				if (currentNode.getConfig().getStringConfiguration().equals(config)) {
					score = currentNode.getConfig().getScore();
				}
				if (currentNode.hasNext())
					currentNode = currentNode.getNext();
			} while (currentNode.hasNext());
		}
		return score;
	}

}
