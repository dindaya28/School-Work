
/**
 * Class creates and uses a HuffmanTree to decode and encode characters
 * 
 * @author Dinushan
 *
 */
public class HuffmanCoder {

	// Instance variables
	private HuffmanTree huffTree;
	private ArrayUnorderedList<EncodingData> encodingList = new ArrayUnorderedList<EncodingData>();

	/**
	 * Constructor, creates a new HuffmanTree based on the input list and its
	 * associated encoding data
	 * 
	 * @param pairsList
	 *            ArrayOrderedList
	 */
	public HuffmanCoder(ArrayOrderedList<HuffmanPair> pairsList) {
		huffTree = new HuffmanTree(pairsList);
		buildEncodingList(huffTree.getRoot(), "");
	}

	/**
	 * Private method that builds the encoding list containing the characters and
	 * their associated compresison code Based on the HuffmanTree created in the
	 * constructor
	 * 
	 * @param node
	 * @param encoding
	 */
	private void buildEncodingList(BinaryTreeNode<HuffmanPair> node, String encoding) {
		if (node.isLeaf()) {
			EncodingData data = new EncodingData(node.getElement().getCharacter(), encoding);
			encodingList.addToFront(data);
		} else {
			buildEncodingList(node.getLeft(), encoding + "0");
			buildEncodingList(node.getRight(), encoding + "1");
		}
	}

	/**
	 * Method traverses the tree to look for a leaf node containing a character will
	 * take a left with code 0 and a right with code 1
	 * 
	 * @param code
	 *            String of bits that correspond to the the char found at the leaf
	 * @return character at the leaf node
	 */
	public char decode(String code) {
		char result = 0;
		BinaryTreeNode<HuffmanPair> root = huffTree.getRoot();
		if (root.isLeaf())
			return 0;
		else {
			for (int i = 0; i < code.length(); i++) {
				char ch = code.charAt(i);
				if (ch == '0' && !root.isLeaf()) {
					root = root.getLeft();
				} else if (ch == '1' && !root.isLeaf()) {
					root = root.getRight();
				} else if (root.isLeaf()) {
					return 0;
				}
			}
			result = root.getElement().getCharacter();
			return result;
		}
	}

	/**
	 * Method scans the encodingList instance variable to find the character given
	 * in the parameter and returns its associated compression bit code
	 * 
	 * @param c
	 *            target character
	 * @return target compression code
	 * @throws ElementNotFoundException
	 */
	public String encode(char c) throws ElementNotFoundException {
		String result = "";
		for (int i = 0; i < encodingList.size(); i++) {
			EncodingData node = encodingList.removeFirst();
			if (node.getSymbol() == c) {
				result = node.getEncoding();
				encodingList.addToRear(node);
			} else
				encodingList.addToRear(node);
		}
		if (result.equals(""))
			throw new ElementNotFoundException("Element not found");
		return result;
	}

	/**
	 * Method returns a string representation of the encodingList containing
	 * EncodingData objects
	 * 
	 * @return String
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < encodingList.size(); i++) {
			EncodingData node = encodingList.removeFirst();
			str += node.toString();
			encodingList.addToRear(node);
		}
		return str;
	}

}
