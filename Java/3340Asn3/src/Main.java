import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Main method to create a graph from text file input and run Dijkstra's
 * 
 * @author Dinushan Dayarathna
 *
 */
public class Main {

	public static void main(String[] args) {

		if (args[0] != null) {
			File file = new File(args[0]);

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));

				String string = br.readLine();
				int capacity = Integer.parseInt(string);

				// Create graph of size capacity, which will house all of the nodes
				Graph graph = new Graph(capacity);

				while ((string = br.readLine()) != null) {
					int vertex1 = Character.getNumericValue((string.charAt(0)));
					int vertex2 = Character.getNumericValue((string.charAt(2)));
					int cost = Integer.parseInt(string.substring(5));

					// Add edges to the graph corresponding to the nodes
					graph.addEdge(vertex1, vertex2, cost);
				}
				br.close();

				System.out.println("                     Graph Construction                     \n");
				graph.to_string();

				System.out.println("                     Edges visited as Dijkstra's runs                     \n");
				graph.dijkstras(1);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}