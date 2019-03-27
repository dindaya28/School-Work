import java.io.IOException;

/**
 * Class that finds the shortest path from the UWO Store to a Customer
 * 
 * @author Dinushan
 *
 */
public class FindShortestPath {

	/**
	 * Method that determines if there are any Tower cells surrounding a cell
	 * 
	 * @param cell
	 * @return boolean
	 */
	private static boolean interference(MapCell cell) {
		boolean val = false;
		for (int i = 0; i < 6; i++) {
			try {
				if (cell.getNeighbour(i) != null) {
					MapCell nCell = cell.getNeighbour(i);
					if (nCell.isTower())
						val = true;
				}
			} catch (InvalidNeighbourIndexException e) {
				System.out.println("Number of adjacent cells is out of bounds!");
			}
		}
		return val;
	}

	/**
	 * Main method that determines the shortest path
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			// Create an empty priority queue
			DLPriorityQueue<MapCell> queue = new DLPriorityQueue();
			try {
				// Create a map object and get the starting cell
				Map map = new Map(args[0]);
				MapCell cell = map.getUWOstore();

				// enqueue the starting cell into the priority queue
				queue.enqueue(cell, 0);
				cell.markEnqueued();

				// While loop runs until either the customer is found or there are no more nodes
				// to search through and so no path is found
				while (!queue.isEmpty() && !cell.isCustomer()) {

					cell = queue.getSmallest();
					cell.markDequeued();

					if (cell.isCustomer())
						break;
					else if (interference(cell)) {
						continue;
					}

					// For loop analyzes the cell surrounding cell and determines a viable option
					// based on distance from the start and its priority
					for (int i = 0; i < 6; i++) {

						if (cell.getNeighbour(i) != null && !cell.getNeighbour(i).isNoFlying()
								&& !cell.getNeighbour(i).isMarkedDequeued() && !cell.getNeighbour(i).isTower()) {
							MapCell neighbour = cell.getNeighbour(i);
							int startDistance = 1 + cell.getDistanceToStart();

							// Determines the next cell to add to the queue based on its distance from the
							// start
							if (neighbour.getDistanceToStart() > startDistance) {
								neighbour.setDistanceToStart(startDistance);
								neighbour.setPredecessor(cell);
								double priority = neighbour.getDistanceToStart() + neighbour.euclideanDistToDest(map);

								if (neighbour.isMarkedEnqueued() && queue.getPriority(neighbour) > priority) {
									queue.changePriority(neighbour, priority);

								} else {
									queue.enqueue(neighbour, priority);
									neighbour.markEnqueued();
								}
							}
						}
					}
				}
				if (queue.isEmpty()) {
					System.out.println("No path could be found");
				} else if (cell.isCustomer()) {
					int distance = cell.getDistanceToStart();
					System.out.println("Path found in " + distance + " cells");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid map file");
			} catch (IOException e) {
				System.out.println("Invalid Input File");
			} catch (InvalidMapException e) {
				System.out.println(e + "\n" + "Path not found");
			} catch (EmptyPriorityQueueException e) {
				System.out.println(e + "\n" + "Path not found");
			} catch (InvalidDataItemException e) {
				System.out.println(e + "\n" + "Path not found");
			} catch (InvalidNeighbourIndexException e) {
				System.out.println("The number of neighbours exceeds the parameters");
			}
		}
	}
}