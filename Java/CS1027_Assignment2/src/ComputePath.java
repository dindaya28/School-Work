/**
 * Class that computes a path from initial cell to destination cell
 * 
 * @author Dinushan Dayarathna
 *
 */
public class ComputePath {
	/**
	 * Instance variable
	 */
	Map cityMap;

	/**
	 * Constructor for the class
	 * 
	 * @param filename
	 */
	public ComputePath(String filename) {
		try {
			cityMap = new Map(filename);
		} catch (Exception e) {
			System.out.println("Invalid file");
		}
	}

	/**
	 * Method returns the initial cell
	 * 
	 * @return MapCell
	 */
	private MapCell getStart() {
		return cityMap.getStart();
	}

	/**
	 * Main method that determines the easiest path to the destination cell
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			// creation of a stack and pushing the initial cell into the stack
			MyStack<MapCell> stack = new MyStack();
			ComputePath map = new ComputePath(args[0]);
			stack.push(map.getStart());
			MapCell currentCell = stack.peek();
			currentCell.markInStack();
			// Runs code until either destination is found or the stack becomes empty
			try {
				while (!stack.isEmpty() && !currentCell.isCustomer()) {
					boolean val = map.interference(currentCell);
					if (val) {
						MapCell cutCell = stack.pop();
						cutCell.markOutStack();
						currentCell = stack.peek();
					}
					currentCell = map.nextCell(currentCell);
					if (currentCell != null) {
						currentCell.markInStack();
						stack.push(currentCell);
					} else
						stack.pop().markOutStack();
					currentCell = stack.peek();
				}

				// if stack becomes empty, ArrayStack will throw an error indicating that no
				// destination was found
			} catch (EmptyStackException e) {
				System.out.println("Unable to find a viable path");
			}

			if (currentCell != null)
				if (currentCell.isCustomer()) {
					System.out.println("Path found to customer!");
					System.out.println(stack.size() + " cells to reach customer.");
				}
			// System.exit(0);
		}
	}

	/**
	 * Method that determines if the cells neighboring the current cell are Tower
	 * cells
	 * 
	 * @param cell
	 * @return boolean
	 */
	private boolean interference(MapCell cell) {
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
	 * Method that determines the next best cell to travel to: Customer > Free >
	 * High Altitude > Thief
	 * 
	 * @param cell
	 * @return
	 */
	private MapCell nextCell(MapCell cell) { // might have to add try/catch to ensure the exceptions are caught
		MapCell bestCell = null;
		for (int i = 5; i >= 0; i--) {
			if (cell.getNeighbour(i) != null) {

				// Specific algorithm checks if it is customer cell first then free, then high
				// altitude and finally thief
				// chooses the free cell with the lowest index before any other cell
				try {
					MapCell neighbour = cell.getNeighbour(i);
					if (neighbour.isCustomer() && !neighbour.isMarked())
						return bestCell = cell.getNeighbour(i);

					else if (neighbour.isFree() && !neighbour.isMarked())
						bestCell = cell.getNeighbour(i);

					else if (neighbour.isHighAltitude() && !neighbour.isMarked()) {
						if (bestCell == null)
							bestCell = cell.getNeighbour(i);
						else if (bestCell.isThief())
							bestCell = cell.getNeighbour(i);
					}

					else if (neighbour.isThief() && !neighbour.isMarked()) {
						if (bestCell == null)
							bestCell = cell.getNeighbour(i);
						else if (!bestCell.isFree())
							bestCell = cell.getNeighbour(i);
						else if (!bestCell.isHighAltitude())
							bestCell = cell.getNeighbour(i);
					}

				} catch (InvalidNeighbourIndexException e) {
					System.out.println("Number of adjacent cells is out of bounds!");
				}
			}
		}
		return bestCell;
	}
}
