/**
 * This class implements an ordered dictionary using a binary search tree
 * 
 * @author Dinushan Dayarathna
 *
 */
public class OrderedDictionary implements OrderedDictionaryADT {
	private Node root;

	/**
	 * Initialize an empty root to start a binary search tree based dictionary
	 */
	public void OrderedDictionay() {
		root = new Node();
	}

	/**
	 * Returns the Record object with key k, or it returns null if such a record is
	 * not in the dictionary
	 */
	public Record get(Pair k) {
		Node result = find(root, k);
		if (result.getRecord() != null)
			return result.getRecord();
		else
			return null;
	}

	/**
	 * Inserts r into the ordered dictionary. Throws a DictionaryException if a
	 * record with the same key as r is already in the dictionary.
	 */
	public void put(Record r) throws DictionaryException {
		// Case where the tree is empty and record will become the root
		if (root == null) {
			// Set the root node to insertion Record r
			root = new Node(r);
			// Create 2 empty nodes that will become the new leaf nodes
			Node newLeft = new Node();
			Node newRight = new Node();
			// Set new nodes' parent to insertion Record r
			newLeft.setParent(root);
			newRight.setParent(root);
			// Set children of Record r to new nodes
			root.setLeft(newLeft);
			root.setRight(newRight);
			return;
		}
		// Find the location r is supposed to be in
		Node result = find(root, r.getKey());
		// If Record r is already in the dictionary
		if (result.getRecord() != null) {
			throw new DictionaryException("Error: Entry already within dictionary.");
		}
		// Case that find returns an empty node where Record r is supposed to go
		else {
			// Set the empty node to insertion Record r
			result.setRecord(r);
			// Create 2 empty nodes that will become the new leaf nodes
			Node newLeft = new Node();
			Node newRight = new Node();
			// Set new nodes' parent to insertion Record r
			newLeft.setParent(result);
			newRight.setParent(result);
			// Set children of Record r to new nodes
			result.setLeft(newLeft);
			result.setRight(newRight);
			return;
		}
	}

	/**
	 * Removes the record with the key k from the dictionary. Throws a
	 * DictionaryException if the record is not in the dictionary.
	 */
	public void remove(Pair k) throws DictionaryException {
		Node remove = find(root, k);
		Node child = new Node();
		if (remove.isLeaf())
			throw new DictionaryException("Error: Entry not found in Dictionary.");
		// Case where remove is the only node in the tree
		else if (remove.getParent() == null && (remove.getLeft() == null && remove.getRight() == null)) {
			root = new Node();
			return;
		}
		// Case where remove is the last node in a branch
		else if (remove.getLeft() == null && remove.getRight() == null) {
			// If remove is the child on the right side
			if (remove.isRight()) {
				Node newNode = new Node();
				newNode.setParent(remove.getParent());
				remove.getParent().setRight(newNode);
			}
			// If remove is the child on the left side
			else {
				Node newNode = new Node();
				newNode.setParent(remove.getParent());
				remove.getParent().setLeft(newNode);
			}
		}
		// Case where remove has a left or a right subtree
		else if (remove.hasChild()) {
			// If the right child is a leaf
			if (remove.getRight() == null) {
				child = remove.getLeft();
			}
			// If the left child is a leaf
			else {
				child = remove.getRight();
			}
			// If remove is the root
			if (remove.getParent() == null) {
				child.setLeft(root.getLeft());
				child.setRight(root.getRight());
				root.setRecord(child.getRecord());
			}
			// If remove is the child on the right side
			else if (remove.getParent().getRight().getRecord() != null
					&& remove.getParent().getRight().getRecord().getKey().compareTo(remove.getRecord().getKey()) == 0) {
				remove.getParent().setRight(child);
			}
			// If remove is the child on the left side
			else {
				remove.getParent().setLeft(child);
			}
		} // Final case where result is an internal node surrounded by other internal
			// nodes
		else {
			Node smallest = smallestNode(remove.getRight());
			// Copy smallest to the location of remove
			smallest.setRight(remove.getRight());
			smallest.setLeft(remove.getLeft());
			remove.getRight().setParent(smallest);
			remove.getLeft().setParent(smallest);
			// Set the parent of smallest to now point to a leaf
			Node smallParent = smallest.getParent();
			smallParent.setLeft(new Node());
			smallest.setParent(remove.getParent());
			// Check if root again
			if (remove.getParent() == null) {
				root.setRecord(child.getRecord());
			}
			// If smallest is now the left child of the parent of remove
			else if (remove.getParent().getLeft().getRecord().getKey().compareTo(remove.getRecord().getKey()) == 0) {
				remove.getParent().setLeft(smallest);
			}
			// If the smallest is now the right child of the parent of remove
			else {
				remove.getParent().setRight(smallest);
			}
		}
	}

	/**
	 * Returns the successor of k; returns null if the given key has no successor.
	 */
	public Record successor(Pair k) {
		// Check to see if tree is empty
		if (root.isLeaf()) {
			return null;
		}
		// Get the location that the Pair k is supposed to be stored in the tree
		Node current = find(root, k);
		Node prev;

		// Case if current is a leaf
		if (current.isLeaf()) {
			current = root;
			prev = current;
			while (current.hasChild() && current.getRecord().getKey().compareTo(k) != 0) {
				// if k > current then go right
				if (current.getRecord().getKey().compareTo(k) < 0) {
					prev = current;
					current = current.getRight();
				}
				// if k < current then go left
				else {
					prev = current;
					current = current.getLeft();
				}
			}
			// Reached case where current is either a leaf or contains the same pair as k
			// Now need to compare the previous node to determine if it is the successor
			current = prev;
			if (current.getRecord().getKey().compareTo(k) > 0) {// && current.getRight().isLeaf()) {
				return current.getRecord();
			}
		}
		// if right child of current is not a leaf
		if (!current.getRight().isLeaf()) {
			return (smallestNode(current.getRight())).getRecord();
		}
		// if right child of current is a leaf
		else {
			Node parent = current.getParent();

			// Traverse the tree from bottom to top starting at the parent of current, while
			// current is the right child
			while (parent.getParent() != null
					&& current.getRecord().getKey().compareTo(parent.getRecord().getKey()) > 0) {
				current = parent;
				parent = parent.getParent();
			}
			// Check if parent is now the root and current is still the right child
			// This case, Pair k has no successor
			if (parent.getParent() == null && current.getRecord().getKey().compareTo(parent.getRecord().getKey()) > 0)
				return null;
			// Other case where parent is now the node that contains the successor to Pair k
			else {
				return parent.getRecord();
			}
		}
	}

	/**
	 * Returns the predecessor of k (largest key smaller than k); returns null if
	 * the given key has no successor
	 */
	public Record predecessor(Pair k) {
		// Check to see if tree is empty
		if (root.isLeaf()) {
			return null;
		}
		// Get the location that the Pair k is supposed to be stored in the tree
		Node current = find(root, k);
		Node prev;

		// Case if current is a leaf, when k is not in the tree
		if (current.isLeaf()) {
			current = root;
			prev = current;
			while (current.hasChild() && current.getRecord().getKey().compareTo(k) != 0) {
				// if k > current then go right
				if (current.getRecord().getKey().compareTo(k) < 0) {
					prev = current;
					current = current.getRight();
				}
				// if k < current then go left
				else {
					prev = current;
					current = current.getLeft();
				}
			}
			// Reached case where current is either a leaf or contains the same pair as k
			// Now need to compare the previous node to determine if it is the successor
			current = prev;
			if ((current.getRecord().getKey().compareTo(k) < 0) && current.getRight().isLeaf()) {
				return current.getRecord();
			}
		}
		// if left child of current is not a leaf
		if (!current.getLeft().isLeaf()) {
			return (largestNode(current.getLeft())).getRecord();
		}
		// if right child of current is a leaf
		else {
			Node parent = current.getParent();

			// Traverse the tree from bottom to top starting at the parent of current, while
			// current is the left child
			while (parent.getParent() != null && current.isLeft()) {
				current = parent;
				parent = parent.getParent();
			}
			// Check if parent is now the root and current is still the left child
			// This case, Pair k has no predecessor
			if (parent.getParent() == null && current.isLeft())
				return null;
			// Other case where parent is now the node that contains the successor to Pair k
			else {
				return parent.getRecord();
			}
		}
	}

	/**
	 * Returns the record with the smallest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
	 */
	public Record smallest() {
		return smallestNode(root).getRecord();
	}

	/**
	 * Returns the record with the largest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
	 */
	public Record largest() {
		return largestNode(root).getRecord();
	}

	/**
	 * Helper method that finds the largest node in a subtree
	 * 
	 * @param r
	 * @return
	 */
	private Node largestNode(Node r) {
		if (r.isLeaf())
			return r.getParent();
		else {
			Node largest = r;
			while (!largest.isLeaf()) {
				largest = largest.getRight();
			}
			return largest.getParent();
		}
	}

	/**
	 * Helper method that finds the smallest node in a subtree
	 * 
	 * @param r
	 * @return
	 */
	private Node smallestNode(Node r) {
		if (r.isLeaf())
			return r.getParent();
		else {
			Node smallest = r;
			while (!smallest.isLeaf()) {
				smallest = smallest.getLeft();
			}
			return smallest.getParent();
		}
	}

	/**
	 * Helper method that traverses the tree and returns the node where Pair item is
	 * supposed to be stored
	 * 
	 * @param r
	 * @param k
	 * @return
	 */
	private Node find(Node r, Pair k) {
		if (r.getRecord() == null)
			return r;
		else {
			if (r.getRecord().getKey().compareTo(k) == 0) {
				return r;
			} else if (r.getRecord().getKey().compareTo(k) > 0) {
				return find(r.getLeft(), k);
			} else
				return find(r.getRight(), k);
		}
	}
}
