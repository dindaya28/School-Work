import java.util.Iterator;

/**
 * Class creates a Huffman Tree based on the input type by extending
 * LinkedBinaryTree
 * 
 * @author Dinushan
 *
 */
public class HuffmanTree extends LinkedBinaryTree<HuffmanPair> implements Comparable<HuffmanTree> {

	/**
	 * Base constructor creates an empty tree
	 */
	public HuffmanTree() {
		super();
	}

	/**
	 * Constructor creates a tree with only a root
	 * 
	 * @param element
	 *            of type HuffmanPair
	 */
	public HuffmanTree(HuffmanPair element) {
		super(element);
	}

	/**
	 * Creates a tree with the root as the first parameter and leaves containing the
	 * second and third parameters
	 * 
	 * @param element
	 *            type HuffmanPair
	 * @param leftSubtree
	 *            type HuffmanTree
	 * @param rightSubtree
	 *            type HuffmanTree
	 */
	public HuffmanTree(HuffmanPair element, HuffmanTree leftSubtree, HuffmanTree rightSubtree) {
		super(element);
		super.getRoot().setLeft(leftSubtree.getRoot());
		super.getRoot().setRight(rightSubtree.getRoot());
	}

	/**
	 * Constructor that creates a tree using the nodes found within the parameter
	 * array Maintains an ordered tree using the ArrayOrdededList buildList
	 * 
	 * @param pairsList
	 *            arrayOrderedList
	 */
	public HuffmanTree(ArrayOrderedList<HuffmanPair> pairsList) {
		ArrayOrderedList<HuffmanTree> buildList = new ArrayOrderedList<HuffmanTree>();
		// converting HuffmanPairs of pairsList to HuffmanTrees to be stored in
		// buildList
		if (pairsList.size() != 0) {
			while (pairsList.size() != 0) {
				HuffmanPair pair = pairsList.removeFirst();
				HuffmanTree tree = new HuffmanTree(pair);
				buildList.add(tree);
			}
		}
		// making the final tree using the trees stored inside buildList
		while (buildList.size() > 1) { // have to do a check later on to see if there is only one node in the tree
			HuffmanTree left = buildList.removeFirst();
			HuffmanTree right = buildList.removeFirst();
			int freq1 = left.getRoot().getElement().getFrequency();
			int freq2 = right.getRoot().getElement().getFrequency();
			HuffmanPair element = new HuffmanPair(freq1 + freq2);
			HuffmanTree tree = new HuffmanTree(element, left, right);
			buildList.add(tree);
		}
		// Creating a tree with the root set to the final tree built from buildList
		super.setRoot(buildList.removeFirst().getRoot());
	}

	/**
	 * Compares the frequency value of otherTree to this tree
	 * 
	 * @param HuffmanTree
	 * @return integer
	 */
	public int compareTo(HuffmanTree otherTree) {
		return this.getRoot().getElement().getFrequency() - otherTree.getRoot().getElement().getFrequency();
	}

	/**
	 * Returns a string representation of the tree through a preorder traversal
	 * 
	 * @return String
	 */
	public String toString() {
		String s = "";

		Iterator<HuffmanPair> list;

		list = iteratorPreOrder();
		while (list.hasNext()) {
			s += list.next().toString();
		}
		return s;
	}
}
