
// Dinushan Dayarathna

import java.util.Arrays;

public class UnionFind {

	public LinkedList array[];
	private int size;
	private int state;
	private LinkedList iSet = null;
	private LinkedList jSet = null;

	/**
	 * Increases the size of the uandf array by 1
	 */
	private void increaseSize() {
		LinkedList[] temp = new LinkedList[size + 1];

		for (int i = 0; i < size; i++) {
			temp[i] = array[i];
		}

		size++;
		array = temp;
	}

	/**
	 * Decreases the size of the uandf array by 1
	 */
	private void decreaseSize() {
		LinkedList[] temp = new LinkedList[size - 1];
		int index = 0;

		for (int i = 0; i < size; i++) {
			if (array[i] != null) {
				temp[index] = array[i];
				index++;
			}
		}

		size--;
		array = temp;
	}

	/**
	 * Adds a set to the uandf array
	 * 
	 * @param set
	 */
	private void add(LinkedList set) {
		for (int i = 0; i < size; i++) {
			if (array[i] == null) {
				array[i] = set;
				return;
			}
		}
	}

	private int setLocation(LinkedList set) {
		int loc = 0;
		for (int i = 0; i < size; i++) {
			if (set == array[i]) {
				loc = i;
			}
		}
		return loc;
	}

	// Helper method to get the size of the array
	public int getSize() {
		return size;
	}

	/**
	 * Constructs an union-find data type with n elements
	 * 
	 * @param n
	 */
	public UnionFind(int n) {
		this.size = n;
		this.state = 1;
		array = new LinkedList[n];

		for (int i = 0; i < n; i++) {
			Node node = new Node(i + 1);
			LinkedList set = new LinkedList(node, node);
			array[i] = set;
		}

		/*
		 * for (int i = 0; i < n; i++) { Node node = new Node(i + 1); LinkedList<T> list
		 * = new LinkedList<T>(node, node); array[i] = list; }
		 */
	}

	/**
	 * Creates a new set whose only member and representative is i
	 * 
	 * @param i
	 */
	public void makeSet(int i) {
		Node node = new Node(i);
		LinkedList list = new LinkedList(node, node);
		if (state == 1) {
			add(list);
		}
	}

	/**
	 * Unites the dynamic sets that contains i and j, into a new set that is the
	 * union of these two sets
	 * 
	 * @param i
	 * @param j
	 */
	public void unionSets(int i, int j) {
		if (state == 1) {

			iSet = findSet(i);
			jSet = findSet(j);

			if (iSet.size() >= jSet.size()) {
				iSet.getTail().setNext(jSet.getHead());
				Node temp = jSet.getHead();

				for (int k = 0; k < jSet.size(); k++) {
					temp.setRep(iSet.getHead());
					temp = temp.getNext();
				}
				iSet.setTail(jSet.getTail());
				array[setLocation(jSet)] = null;
				jSet.deleteList();
			} else {
				jSet.getTail().setNext(iSet.getHead());
				Node temp = jSet.getHead();

				for (int k = 0; k < iSet.size(); k++) {
					temp.setRep(jSet.getHead());
					temp = temp.getNext();
				}

				jSet.setTail(iSet.getTail());
				array[setLocation(iSet)] = null;
				iSet.deleteList();
			}

			decreaseSize();
		}
	}

	/**
	 * Returns the representative of the set containing i
	 * 
	 * @param i
	 */
	public LinkedList findSet(int i) {
		LinkedList set = null;

		for (int j = 0; j < size; j++) {
			if (array[j].getHead().getData() == i) {
				set = array[j];
			}
		}

		return set;
	}

	/**
	 * Returns the total number of current sets, finalizes the current sets, and
	 * resets the representatives of the sets so that integers from 1 to finalSets
	 * will be used as representatives
	 * 
	 * @return
	 */
	public int finalSets() {
		state = 0;
		for (int i = 0; i < size; i++) {
			if (array[i].getHead().getData() == (i + 1)) {
				continue;
			} else {
				array[i].getHead().setData(i + 1);
			}
		}

		return size;
	}

	// Helper method to sort the array
	public void sortArray() {
		Arrays.sort(array, new sort());
	}

	/**
	 * Helper method to remove any unwanted sets from the array
	 * 
	 * @param c
	 */
	public void removeSet(char c) {
		if (state == 1) {
			LinkedList set = findSet(c);
			int setLoc = setLocation(set);

			LinkedList[] temp = new LinkedList[size - 1];
			int tempLoc = 0;

			for (int i = 0; i < size; i++) {
				if (i == setLoc) {
					continue;
				} else {
					temp[tempLoc] = array[i];
					tempLoc++;
				}
			}
			array = temp;
			size--;
		}
	}
}
