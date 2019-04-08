import java.util.LinkedList;

/**
 * 
 * @author Dinushan Dayarathna
 *
 *         Class will create a graph object to store the graph to be acted on by
 *         Dijkstra's algorithm
 *
 */
public class Graph {

	// Instance variables
	private int vertices;
	private LinkedList<Edge>[] adjacencyList;
	private Node[] nodes;

	/**
	 * Constructor, uses total number of vertices to implement a graph using an
	 * adjacency list of size numb vertices
	 * 
	 * @param vertices
	 */
	public Graph(int vertices) {
		this.vertices = vertices;
		this.adjacencyList = new LinkedList[vertices + 1];

		for (int i = 1; i <= vertices; i++) {
			adjacencyList[i] = new LinkedList<>();
		}
	}

	/**
	 * Method to add an edge to the graph
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @param cost
	 * 
	 *            inserts edge at the index of vertex1
	 */
	public void addEdge(int vertex1, int vertex2, int cost) {
		Edge edge = new Edge(vertex1, vertex2, cost);
		adjacencyList[vertex1].add(edge);
	}

	/**
	 * Method implements Dijkstra's algorithm to find the shortest path tree SPT
	 * within the graph from the source vertex Method uses a min-heap to accomplish
	 * the algorithm
	 * 
	 * @param source
	 */
	public void dijkstras(int source) {
		final int INFINITY = Integer.MAX_VALUE;
		this.nodes = new Node[vertices + 1];
		LinkedList<Node> spt = new LinkedList<>();

		// Initialize array of Node elements to have inifinity
		for (int i = 1; i <= vertices; i++) {
			nodes[i] = new Node(i, INFINITY);
		}

		// Source node will have distance of 0
		nodes[source].setDistance(0);

		// Initialize the heap
		Heap heap = new Heap(nodes, vertices);

		// Loop through the heap, popping the least weighted node and determining the
		// weight of its adjacent nodes
		while (!heap.is_empty()) {
			Node min = heap.delete_min();
			spt.add(min);

			// Check to determine if the last node has been removed from the heap
			// Indicates that the last node has been visited and therefore algorithm is
			// complete
			if (heap.is_empty()) {
				break;
			}

			LinkedList<Edge> adjacentEdges = new LinkedList<>();
			adjacentEdges = adjacencyList[min.getID()];

			// Update the weights of adjacent nodes using heap's built-in decrease-key
			// function
			for (int i = 0; i < adjacentEdges.size(); i++) {
				if (heap.in_heap(adjacentEdges.get(i).getV2())) {
					heap.decrease_key(adjacentEdges.get(i).getV2(),
							(min.getDistance() + adjacentEdges.get(i).getCost()));
					System.out.println("Edge: " + adjacentEdges.get(i).getV1() + ", " + adjacentEdges.get(i).getV2()
							+ ". Weight: " + adjacentEdges.get(i).getCost() + "\n");

				}
			}
		}
		// Loop through SPT to print out the order of the vertices produced through
		// Dijkstra's
		System.out.println("                    Vertices visited along the shortest path     \n");
		for (int i = 0; i < spt.size(); i++) {
			System.out.println("Vertex: " + spt.get(i).getID() + ", Weight: " + spt.get(i).getDistance() + "\n");
		}

		/*
		 * for (int i = spt.size(); i > 0; i--) { int j = i - 1; LinkedList<Edge> edges
		 * = new LinkedList<Edge>(); edges = adjacencyList[j]; for (int k = 0; k <
		 * edges.size(); k++) { if (edges.get(k).getV2() == i) { edges.get(k).print(); }
		 * } }
		 */

	}

	/**
	 * Prints out the graph using the adjacency list representation
	 */
	public void to_string() {
		String string = "";
		for (int i = 1; i <= vertices; i++) {
			LinkedList<Edge> edges = adjacencyList[i];
			string += "Vertex " + i + " connects to ";
			for (int j = 0; j < edges.size(); j++) {
				string += "[Vertex " + edges.get(j).getV2() + ", Weight " + edges.get(j).getCost() + "] ";
			}
			string += "\n\n";
		}

		System.out.println(string);
	}

}