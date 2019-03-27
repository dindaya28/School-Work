/**
 * This class represents the nodes to be stored in the binary search tree Each
 * node contains a reference to a record, left and right child, and the node's
 * parent
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Node {
	private Record record;
	private Node leftChild;
	private Node rightChild;
	private Node parent;

	/**
	 * Initializes an empty node
	 */
	public Node() {
		this.record = null;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

	/**
	 * Initializes an node containing a record, still requires user to set the
	 * left,right, and parents
	 * 
	 * @param record
	 */
	public Node(Record record) {
		this.record = record;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

	/**
	 * Sets record to the specified record
	 * 
	 * @param record
	 */
	public void setRecord(Record record) {
		this.record = record;
	}

	/**
	 * Sets the leftChild to the specified node
	 * 
	 * @param node
	 */
	public void setLeft(Node node) {
		this.leftChild = node;
	}

	/**
	 * Sets the rightChild to the specified node
	 * 
	 * @param node
	 */
	public void setRight(Node node) {
		this.rightChild = node;
	}

	/**
	 * Sets the parent to the specified node
	 * 
	 * @param node
	 */
	public void setParent(Node node) {
		this.parent = node;
	}

	/**
	 * Returns the record stored in this Node
	 * 
	 * @return
	 */
	public Record getRecord() {
		return record;
	}

	/**
	 * Returns the node stored in leftChild of this Node
	 * 
	 * @return
	 */
	public Node getLeft() {
		return leftChild;
	}

	/**
	 * Returns the node stored in rightChild of this Node
	 * 
	 * @return
	 */
	public Node getRight() {
		return rightChild;
	}

	/**
	 * Returns the node stored in parent of this Node
	 * 
	 * @return
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Returns true if node stores a null record, else false
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		if (this.record == null)
			return true;
		else
			return false;
	}

	/**
	 * Returns true if this node has at least one child
	 * 
	 * @return boolean
	 */
	public boolean hasChild() {
		if (this.leftChild != null || this.rightChild != null)
			return true;
		else
			return false;
	}

	/**
	 * Returns true if this node is the right child of this parent, else false
	 * 
	 * @return
	 */
	public boolean isRight() {
		if (this.parent.getRight() != null && this.parent.getRight().getRecord() == this.record) {
			return true;
		} else
			return false;
	}

	/**
	 * Returns true if this node is the left child of this parent, else false
	 * 
	 * @return
	 */
	public boolean isLeft() {
		if (this.parent.getLeft() != null && this.parent.getLeft().getRecord() == this.record) {
			return true;
		} else
			return false;
	}
}
