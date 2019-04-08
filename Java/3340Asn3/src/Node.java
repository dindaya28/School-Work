/**
 * Class implements a Node object to be stored in the heap
 * 
 * @author Dinushan
 *
 */
public class Node {

	// Instance variables
	// Distance = key
	private int id, distance;

	// Constructor, followed by getter and setter methods
	public Node(int id, int distance) {
		this.id = id;
		this.distance = distance;
	}

	public int getID() {
		return this.id;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}