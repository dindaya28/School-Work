/**
 * Class creates the board game on which the game will be played Uses an table
 * to store the values located on each square of the board
 * 
 * @author Dinushan
 *
 */
public class BoardGame {

	// Instance variables
	private char[][] gameBoard;
	private int size;
	private int empty;
	private int max;
	private int numEmpty;

	/**
	 * Initializes an empty board of specified size, only allowing specified number
	 * of empty spaces
	 * 
	 * @param board_size
	 * @param empty_positions
	 * @param max_levels
	 */
	public BoardGame(int board_size, int empty_positions, int max_levels) {
		gameBoard = new char[board_size][board_size];
		// Ensure that all spaces on the board are considered empty by initializing them
		// as 'g'
		for (int i = 0; i < board_size; i++)
			for (int j = 0; j < board_size; j++)
				gameBoard[i][j] = 'g';
		this.size = board_size;
		this.empty = empty_positions;
		this.max = max_levels;
		this.numEmpty = board_size * board_size;
	}

	/**
	 * Initializes an empty HashDictionary to store the hash codes
	 * 
	 * @return
	 */
	public HashDictionary makeDictionary() {
		HashDictionary dict = new HashDictionary(size);
		return dict;
	}

	/**
	 * Returns the score of the string stored in gameBoard Uses the getScore method
	 * of HashDictionary to return either a -1 if the string is not in the
	 * dictionary, or the score if it is found in the dictionary
	 * 
	 * @param dict
	 * @return
	 */
	public int isRepeatedConfig(HashDictionary dict) {
		String gameBoardString = gameBoard_toString();
		int score = (dict.getScore(gameBoardString));
		return score;
	}

	/**
	 * Method creates a Configuration object using the string representation of the
	 * gameBoard and adds it to the dictionary
	 * 
	 * @param dict
	 * @param score
	 */
	public void putConfig(HashDictionary dict, int score) {
		// Creates a string representation of the gameBoard
		String gameBoardString = gameBoard_toString();
		Configuration config = new Configuration(gameBoardString, score);
		try {
			dict.put(config);
		} catch (DictionaryException e) {
			e.getMessage();
		}
	}

	/**
	 * Adds a new move to the gameBoard
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
		numEmpty--;
	}

	/**
	 * Method checks whether the position is empty
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean positionIsEmpty(int row, int col) {
		if (gameBoard[row][col] == 'g')
			return true;
		else
			return false;
	}

	/**
	 * Method checks whether the position is taken by the computer's tile
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileOfComputer(int row, int col) {
		if (gameBoard[row][col] == 'o')
			return true;
		else
			return false;
	}

	/**
	 * Method checks whether the position is taken by the human's tile
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileOfHuman(int row, int col) {
		if (gameBoard[row][col] == 'b')
			return true;
		else
			return false;
	}

	/**
	 * Method determines whether there are n number of the specified tile in a row,
	 * column, or diagonal to determine if there is a winner Returns false if there
	 * is no winner found
	 * 
	 * @param symbol
	 * @return
	 */
	public boolean wins(char symbol) {
		// Scan through every row and column to determine if there are n in a row of the
		// same tile
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (gameBoard[row][col] == symbol) {
					// Uses the checkColumn method to determine if the columns contain a winner
					if (checkColumn(col, symbol) == true)
						return true;
					// Uses the checkRow method to determine if the rows contain a winner
					else if (checkRow(row, symbol) == true)
						return true;
					// Uses the checkDiagonalLeft to determine if the diagonal from the top left to
					// bottom right contains a winner
					else if (checkDiagonalLeft(symbol) == true)
						return true;
					// Uses the checkDiagonalRight to determine if the diagonal from the top right
					// to the bottom left contains a winner
					else if (checkDiagonalRight(symbol) == true)
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method determines if the gameBoard ended in a draw, with neither player able
	 * to win
	 * 
	 * @param symbol
	 * @param empty_positions
	 * @return
	 */
	public boolean isDraw(char symbol, int empty_positions) {
		boolean check = false;
		if (!wins(symbol)) {
			// Check to determine if the number of empty tiles is equal to number of allowed
			// empty tiles
			if (empty_positions == 0 && numEmpty == 0)
				check = true;
			// Check to see if none of the empty tiles have an adjacent tile labeled by
			// symbol
			else if (empty_positions > 0 && numEmpty == empty_positions && checkEmptyPositions(symbol)) {
				check = true;
			}
		}

		return check;
	}

	/**
	 * Method evaluates the board to determine who is the winner, loser, or
	 * undetermined Assigns 3 if computer is the winner, 0 if human is the winner, 2
	 * if the board is a draw, or 1 if undetermined
	 * 
	 * @param symbol
	 * @param empty_positions
	 * @return
	 */
	public int evalBoard(char symbol, int empty_positions) {
		int eval = 0;
		if (symbol == 'o') {
			if (wins(symbol))
				eval = 3;
		} else if (symbol == 'b') {
			if (wins(symbol))
				eval = 0;
		} else if (isDraw(symbol, empty_positions))
			eval = 2;
		else
			eval = 1;
		return eval;
	}

	/**
	 * Method checks every empty position on the board to determine if it is
	 * surrounded by the specified symbol
	 * 
	 * @param symbol
	 * @return
	 */
	private boolean checkEmptyPositions(char symbol) {
		for (int row = 0; row < size; row++)
			for (int col = 0; col < size; col++)
				// Uses the checkAdjacentTiles method to compare surrounding tiles
				if (positionIsEmpty(row, col) && checkAdjacentTiles(row, col, symbol)) {
					return false;
				}

		return true;
	}

	/**
	 * Method checks surrounding tiles, excluding those that are considered off the
	 * board, to determine if the specified symbol is adjacent to the inputted tile
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 * @return
	 */
	private boolean checkAdjacentTiles(int row, int col, char symbol) {
		boolean check = false;

		for (int r = row - 1; r <= 1; r++)
			for (int c = col - 1; c <= 1; c++)
				if (r != 0 && c != 0)
					// Uses checkBounds to determine if the tiles surrounding the specified tile are
					// within the bounds of the board
					check |= checkBounds(r, c, symbol);

		return check;
	}

	/**
	 * Method determines if the entered tile is within the bounds of the board and
	 * whether the tile contains the specified symbol
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 * @return
	 */
	private boolean checkBounds(int row, int col, char symbol) {
		if (row >= size || col >= size)
			return false;
		else if (row < 0 || col < 0)
			return false;
		else if (gameBoard[row][col] == symbol)
			return true;
		else
			return false;
	}

	/**
	 * Method converts the characters associated at each tile of gameBoard to a
	 * string
	 * 
	 * @return
	 */
	private String gameBoard_toString() {
		String boardString = "";
		// Uses a nested for loop to go through each tile to add it to the boardString
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				boardString += gameBoard[row][col];
			}
		}
		return boardString;
	}

	/**
	 * Method checks to see if there are n of the same tiles in a column at the
	 * specified column
	 * 
	 * @param col
	 * @param symbol
	 * @return
	 */
	private boolean checkColumn(int col, char symbol) {
		int count = 0;
		// Uses a for loop to change the row to check for the same symbol
		for (int i = 0; i < size; i++) {
			if (gameBoard[i][col] == symbol)
				count++;
			else
				break;
		}
		// returns true if the number of tiles in the column are the same as the size of
		// the board
		return (count == size);
	}

	/**
	 * Method checks to see if there are n of the same tiles in a row at the
	 * specified row
	 * 
	 * @param row
	 * @param symbol
	 * @return
	 */
	private boolean checkRow(int row, char symbol) {
		int count = 0;
		// Uses a for loop to change the column to check for the same symbol
		for (int i = 0; i < size; i++) {
			if (gameBoard[row][i] == symbol)
				count++;
			else
				break;
		}
		// returns true if the number of tiles in the row are the same as the size of
		// the board
		return (count == size);
	}

	/**
	 * Method checks to determine if the specified symbol spans the main diagonal of
	 * the board, from top left to bottom right, to indicate a win
	 * 
	 * @param symbol
	 * @return
	 */
	private boolean checkDiagonalLeft(char symbol) {
		int count = 0;
		// Uses a loop to traverse the main diagonal from left to right of the board
		for (int i = 0; i < size; i++) {
			if (gameBoard[i][i] == symbol)
				count++;
			else
				break;
		}
		return (count == size);
	}

	/**
	 * Method checks to determine if the specified symbol spans the main diagonal of
	 * the board, from top right to bottom left, to indicate a win
	 * 
	 * @param symbol
	 * @return
	 */
	private boolean checkDiagonalRight(char symbol) {
		int count = 0;
		int col = size - 1;
		// Uses a loop that increments by one and a column count the decrements by 1 to
		// traverse the diagonal from top left to bottom right
		for (int i = 0; i < size; i++) {
			if (gameBoard[i][col] == symbol) {
				count++;
				col--;
			} else
				break;
		}
		return (count == size);
	}

}
