/**
 * Class implements an Edge object to be stored in an adjacency list
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Edge {

	private int vertex1, vertex2, cost;

	// Constructor, followed by getter and setter methods
	public Edge(int v1, int v2, int cost) {
		this.vertex1 = v1;
		this.vertex2 = v2;
		this.cost = cost;
	}

	public int getV1() {
		return this.vertex1;
	}

	public int getV2() {
		return this.vertex2;
	}

	public int getCost() {
		return this.cost;
	}

	public void setV1(int v1) {
		this.vertex1 = v1;
	}

	public void setV2(int v2) {
		this.vertex1 = v2;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void print() {
		System.out.println("Edge: " + vertex1 + ", " + vertex2 + ". Weight: " + cost + "\n");
	}
}
