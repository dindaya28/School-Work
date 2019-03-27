
// Dinushan Dayarathna

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class main {

	public static void main(String[] args) {

		char[][] imgArray = new char[71][71];
		int rowIndex = 0;
		char alphabet = 'a';
		char[] alpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		File file = new File(args[0]);

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;

			System.out.println("Part 1: binary image.");

			while ((st = br.readLine()) != null) {
				for (int i = 0; i < 71; i++) {
					if (st.charAt(i) == '+') {
						System.out.print("1");
						imgArray[rowIndex][i] = '1';
					} else {
						System.out.print("0");
						imgArray[rowIndex][i] = '0';
					}
				}
				rowIndex++;
				System.out.print("\n");
			}

			br.close();
		} catch (Exception e) {
			System.out.println("Unable to open file.");
		}

		// Part 2
		for (int i = 0; i < 71; i++) {
			for (int j = 0; j < 71; j++) {
				if (imgArray[i][j] == '1') {
					findConnected(imgArray, i, j, alphabet);
					alphabet++;
				}
			}
		}

		System.out.println("\n\nPart 2: Component image");
		printPic(imgArray);

		System.out.println("\n\nPart 3: Component size");

		// Create the uandf to house the sets
		UnionFind uf = new UnionFind(71 * 71);
		int ufInt = 1;

		// Loop through the imgArray and create a set for each individual pixel and
		// union identical pixels
		for (int row = 0; row < 71; row++) {
			for (int col = 0; col < 71; col++) {
				LinkedList list1 = uf.findSet(ufInt);
				LinkedList list2 = uf.findSet((int) (imgArray[row][col] + 5041));

				list1.getHead().setRow(row);
				list1.getHead().setCol(col);

				if (list2 != null) {
					uf.unionSets(list2.getHead().getData(), list1.getHead().getData());
				} else {
					list1.getHead().setData(imgArray[row][col] + 5041);
				}
				ufInt++;
			}
		}

		uf.removeSet((char) (' ' + 5041));
		uf.finalSets();
		uf.sortArray();

		for (int i = 1; i <= uf.getSize(); i++) {
			System.out.println("Set " + i + ". Size = " + uf.array[i - 1].size() + ", Component = "
					+ (char) (uf.array[i - 1].getHead().getData() + 96));
		}

		// Part 4
		System.out.println("\n\nPart 4: Removed Components");

		char[] remove = new char[uf.getSize()];
		int index = 0;

		for (int i = 0; i < uf.getSize(); i++) {
			if (uf.array[i].size() <= 2) {
				remove[index] = ((char) (uf.array[i].getHead().getData() + 96));
				index++;
			}
		}
		for (int i = 0; i < index; i++) {
			LinkedList list = uf.findSet(remove[i] - 96);
			Node node = list.getHead();

			while (node != null) {
				imgArray[node.getRow()][node.getCol()] = ' ';
				node = node.getNext();
			}
		}

		printPic(imgArray);
	}

	public static void findConnected(char[][] array, int row, int col, char alpha) {
		if (array[row][col] != '1') {
			return;
		} else {
			array[row][col] = alpha;
			if (row == 0 && col == 0) {
				findConnected(array, row, col + 1, alpha);
				findConnected(array, row + 1, col, alpha);
			} else if (row == 0 && col == 70) {
				findConnected(array, row, col - 1, alpha);
				findConnected(array, row + 1, col, alpha);
			} else if (row == 70 && col == 0) {
				findConnected(array, row - 1, col, alpha);
				findConnected(array, row, col + 1, alpha);
			} else if (row == 70 && col == 70) {
				findConnected(array, row, col - 1, alpha);
				findConnected(array, row - 1, col, alpha);
			} else if (row == 0 && col != 70) {
				findConnected(array, row, col - 1, alpha);
				findConnected(array, row, col + 1, alpha);
				findConnected(array, row + 1, col, alpha);
			} else if (row != 70 && col == 0) {
				findConnected(array, row - 1, col, alpha);
				findConnected(array, row + 1, col, alpha);
				findConnected(array, row, col + 1, alpha);
			} else if (row == 70 && col != 70) {
				findConnected(array, row, col - 1, alpha);
				findConnected(array, row, col + 1, alpha);
				findConnected(array, row - 1, col, alpha);
			} else if (row != 70 && col == 70) {
				findConnected(array, row - 1, col, alpha);
				findConnected(array, row + 1, col, alpha);
				findConnected(array, row, col - 1, alpha);
			} else {
				findConnected(array, row - 1, col, alpha);
				findConnected(array, row, col - 1, alpha);
				findConnected(array, row, col + 1, alpha);
				findConnected(array, row + 1, col, alpha);
			}
		}
	}

	public static void printPic(char[][] array) {
		for (int i = 0; i < 71; i++) {
			for (int j = 0; j < 71; j++) {
				if (array[i][j] == '0') {
					System.out.print(' ');
				} else {
					System.out.print(array[i][j]);
				}
			}
			System.out.print("\n");
		}
	}
}
