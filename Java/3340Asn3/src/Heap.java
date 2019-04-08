/**
 * Class implements a min-heap using an array of nodes
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Heap {

	// Instance variables
	private int maxSize, size;
	private Node[] nodes;
	private Node[] heap;

	/**
	 * Constructor, takes in an array of nodes and copies contents to heap
	 * Initializes heap to be of size n
	 * 
	 * @param keys
	 * @param n
	 */
	public Heap(Node[] keys, int n) {
		this.maxSize = n;
		heap = new Node[maxSize + 1];
		this.nodes = keys;
		heap[0] = null;
		size = 0;

		// Insert the nodes into the heap array
		for (int i = 1; i <= maxSize; i++) {
			insert(nodes[i]); // Takes in a node from the incoming key array and insert into heap
		}
	}

	// Re-adjusts the heap to be proper
	// Moves item down if value stored is larger than children's value
	public void heapify(int id) {
		if (!isLeaf(id)) {
			if (heap[id].getDistance() > heap[left(id)].getDistance()
					|| heap[id].getDistance() > heap[right(id)].getDistance()) {
				if (heap[right(id)].getDistance() > heap[left(id)].getDistance()) {
					swap(id, left(id));
					heapify(left(id));
				} else {
					swap(id, right(id));
					heapify(right(id));
				}
			}
		}
	}

	/**
	 * Method checks if the heap is empty
	 * 
	 * @return
	 */
	public boolean is_empty() {
		if (size == 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Returns true if the element whose id is id is in the heap
	 * 
	 * @param id
	 * @return
	 */
	public boolean in_heap(int id) {
		if (is_empty()) {
			return false;
		} else {
			// Check the nodes array to determine if the target id is within the heap
			for (int i = 1; i <= maxSize; i++) {
				if (heap[i] != null && heap[i].getID() == id) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Method sets the key of the element whose id is id to new key if its current
	 * key is greater than new key
	 * 
	 * @param id
	 * @param new_key
	 */
	public void decrease_key(int id, int new_key) {
		// Check the nodes array to determine if the target id is within the heap
		for (int i = 1; i <= maxSize; i++) {
			if (heap[i] != null && heap[i].getID() == id) {
				Node node = heap[i];
				if (node.getDistance() > new_key) {
					node.setDistance(new_key);
					bubble_up(i);
				}
			}
		}
	}

	/**
	 * Method returns the minimum key of the heap
	 * 
	 * @return
	 */
	public int min_key() {
		return heap[1].getDistance();
	}

	/**
	 * Method returns the id of the element with minimum key in the heap
	 * 
	 * @return
	 */
	public int min_id() {
		return heap[1].getID();
	}

	/**
	 * Method returns the key of the element whose id is id in the heap
	 * 
	 * @param id
	 * @return
	 */
	public int key(int id) {
		return nodes[id].getDistance();
	}

	/**
	 * Method deletes the element with minimum key from the heap. Re-adjusts the
	 * heap to maintain heap properties after deletion
	 * 
	 * @return
	 */
	public Node delete_min() {
		Node remove = heap[1];
		heap[1] = null;
		heap[1] = heap[size--];
		if (heap[1] != null) {
			heapify(1);
		}
		return remove;
	}

	/**
	 * Private method to insert a node into the heap
	 * 
	 * @param id
	 */
	private void insert(Node id) {
		if (is_empty()) {
			heap[1] = id;
			size++;
		} else {
			// increase the size of the heap and add the new node to the leaf of the heap
			heap[++size] = id;
			bubble_up(size);
		}
	}

	// Move a specific node up in the heap if it has a value less than its parent
	private void bubble_up(int id) {
		if (id == 1) {
			return;
		} else if (heap[id].getDistance() < heap[parent(id)].getDistance()) {
			swap(id, parent(id));
			bubble_up(parent(id));
		}
	}

	// Method returns index of parent to target id
	private int parent(int id) {
		return (id / 2);
	}

	// Method returns index of left child to current id
	private int left(int id) {
		return id * 2;
	}

	// Method returns index of right child to current id
	private int right(int id) {
		return (id * 2) + 1;
	}

	// Method used to swap two ids in the heap
	private void swap(int id_1, int id_2) {
		Node temp;
		temp = heap[id_1];
		heap[id_1] = heap[id_2];
		heap[id_2] = temp;
	}

	// Method checks if given position in the heap is a leaf
	private boolean isLeaf(int id) {
		if (id >= (size / 2) && id <= size) {
			return true;
		} else
			return false;
	}
}
