
// Dinushan Dayarathna

import java.util.Comparator;

public class LinkedList {
	private Node head;
	private Node tail;

	/**
	 * Initializes empty linked list
	 */
	public LinkedList(Node head, Node tail) {
		this.head = head;
		this.tail = tail;
		head.setRep(head);
		tail.setRep(head);
	}

	public void setHead(Node node) {
		this.head = node;
	}

	public void setTail(Node node) {
		this.tail = node;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	public int size() {
		int size = 1;

		Node node = head;

		if (head == tail) {
			size = 1;
		} else {
			while (node.hasNext()) {
				size++;
				node = node.getNext();
			}
		}

		return size;
	}

	public void deleteList() {
		head = null;
		tail = null;
	}
}

// Help with sorting the array in UnionFind
class sort implements Comparator<LinkedList> {
	public int compare(LinkedList a, LinkedList b) {
		return b.size() - a.size();
	}
}