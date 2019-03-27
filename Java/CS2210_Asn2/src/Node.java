/**
 * Class creates a linked list of type Configuration
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Node {

	// Instance variables
	private Configuration head;
	private Node next;

	// Initialize an empty node
	public Node() {
		head = null;
		next = null;
	}

	// Creates a node containing a configuration object
	public Node(Configuration config) {
		this.head = config;
		this.next = null;
	}

	// Returns the configuration object stored in the node
	public Configuration getConfig() {
		return head;
	}

	// Returns next
	public Node getNext() {
		return next;
	}

	// Sets the node to a different configuration object
	public void setHead(Configuration config) {
		head = config;
	}

	// Sets next to a new node
	public void setNext(Node newNode) {
		next = newNode;
	}

	// Returns true if the current node contains no configuration
	public boolean isEmpty() {
		if (head == null)
			return true;
		else
			return false;
	}

	// Returns true of next is not null
	public boolean hasNext() {
		if (next != null)
			return true;
		else
			return false;
	}
}
