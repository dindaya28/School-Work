// Dinushan Dayarathna

public class Node {
	private int data, row, col;
	private Node next;
	private Node rep;

	public Node(int data) {
		this.data = data;
	}

	public boolean hasNext() {
		if (next == null)
			return false;
		else
			return true;
	}

	public void setNext(Node node) {
		this.next = node;
	}

	public Node getNext() {
		return next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getRep() {
		return rep;
	}

	public void setRep(Node node) {
		this.rep = node;
	}

	public void setRow(int i) {
		this.row = i;
	}

	public void setCol(int i) {
		this.col = i;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
