import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * This class implements the user interface and it contains the main method
 * 
 * @author Dinushan Dayarathna
 *
 */
public class UserInterface {
	public static void main(String[] args) {
		// Initialize an empty dictionary
		OrderedDictionary dictionary = new OrderedDictionary();
		String filename = args[0];

		// Try opening file and adding contents to dictionary
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String word = in.readLine();
			String data;
			String type;
			while (word != null) {
				data = in.readLine();
				// Check for type of record, this case if it's a sound file
				if (data.endsWith(".wav") || data.endsWith(".mid")) {
					type = "audio";
				}
				// Check to see if record is a picture
				else if (data.endsWith(".gif") || data.endsWith(".jpg")) {
					type = "image";
				}

				// Everything else is text type
				else
					type = "text";

				// Create a Pair object containing the word and type
				Pair pair = new Pair(word, type);

				// Create a Record object containing the pair and associated data
				Record rec = new Record(pair, data);

				// Add record to the dictionary
				try {
					dictionary.put(rec);
				} catch (DictionaryException e) {
					System.out.println(e);
				}
				word = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Unable to open file: " + filename);
		}

		// Section takes user input, processes, and returns strings based on user input
		StringReader keyboard = new StringReader();
		StringTokenizer st;
		String typ = "", word = "", data = "", cmd = "";
		String line = "";

		// Ask user for input
		line = keyboard.read("Enter next command: ");
		// Initialize the StringTokenizer
		st = new StringTokenizer(line);

		int count = 0;
		boolean restart = false;

		while (!line.equals("finish")) {
			// Reset variables
			typ = "";
			word = "";
			data = "";
			cmd = "";
			count = 0;

			// Check for amount of entries in the user command
			count = st.countTokens();

			// Case if the user did not enter anything, will ask them to enter a command
			// again
			if (count == 0)
				restart = true;
			else {
				for (int i = 0; i < count; i++) {
					// First token is the command
					if (i == 0)
						cmd = st.nextToken();

					// Second token is the word
					else if (i == 1)
						word = st.nextToken();

					// Third token is the type
					else if (i == 2)
						typ = st.nextToken();

					// Anything more is part of the data
					else if (i >= 3)
						data += st.nextToken().toString() + " ";
				}
			}

			if (!restart) {
				// If the dictionary contains the given word following the get command, it will
				// be processed here
				if (cmd.equals("get")) {
					Pair pair;
					Record rec;
					int found = 0;

					// 3 types that can be found in the dictionary
					String type1 = "text";
					String type2 = "audio";
					String type3 = "image";

					// Check for a record containing the word and a text type
					// Create a Pair object containing the word and type1
					pair = new Pair(word, type1);

					// Determine if the pair is stored in the dictionary
					rec = dictionary.get(pair);
					if (rec != null) {
						System.out.println(rec.getData().toString());
						found++;
					}

					// Check for a record containing the word and audio type
					// Create a Pair object containing the word and type2
					pair = new Pair(word, type2);

					// Determine if the pair is stored in the dictionary
					rec = dictionary.get(pair);
					if (rec != null) {
						// Try and play the sound file
						try {
							SoundPlayer player = new SoundPlayer();
							player.play(rec.getData());
							found++;
						} catch (MultimediaException e) {
							System.out.println(e);
						}
					}

					// Check for a record containing the word and image type
					// Create a Pair object containing the word and type3
					pair = new Pair(word, type3);

					// Determine if the pair is stored in the dictionary
					rec = dictionary.get(pair);
					if (rec != null) {
						// Try and display the image file
						try {
							PictureViewer image = new PictureViewer();
							image.show(rec.getData());
							found++;
						} catch (MultimediaException e) {
							System.out.println(e);
						}
					}
					// If no entries containing the target word were found then successor and
					// predecessor will be printed
					if (found == 0) {
						Record successor = dictionary.successor(pair);
						Record predecessor = dictionary.predecessor(pair);
						System.out.println("The word " + word + " is not in the dictionary.");
						if (predecessor != null) {
							System.out.println("Preceding word: " + predecessor.getKey().getWord().toString());
						} else {
							System.out.println("Preceding word: ");
						}
						if (successor != null) {
							System.out.println("Following word: " + successor.getKey().getWord().toString());
						} else {
							System.out.println("Following word: ");
						}
					}
				}
				// If the dictionary contains the word and type following the delete command, it
				// will be removed
				else if (cmd.equals("delete")) {
					// Create a Pair object containing the word and type
					Pair pair = new Pair(word, typ);
					try {
						dictionary.remove(pair);
					} catch (DictionaryException e) {
						System.out.println("No record in the ordered dictionary has key (" + word + ", " + typ + ")");
					}
				}
				// Insert the word, type, and data into the dictionary if not already contained
				else if (cmd.equals("put")) {
					// Create new pair containing word and type
					Pair pair = new Pair(word, typ);

					// Create record containing pair and data
					Record rec = new Record(pair, data);
					try {
						dictionary.put(rec);
					} catch (DictionaryException e) {
						System.out.println("A record with the given key (" + word + ", " + typ
								+ ") is already in the ordered dictionary");
					}
				} else if (cmd.equals("list")) {
					// Create pairs containing the prefix and each type
					Pair check = new Pair(word, "text");
					Pair check1 = new Pair(word, "audio");
					Pair check2 = new Pair(word, "image");
					// Check to see if the prefix and associated type is within the dictionary
					Record getWord = dictionary.get(check);
					Record getWord1 = dictionary.get(check1);
					Record getWord2 = dictionary.get(check2);

					if (getWord != null) {
						System.out.println(getWord.getKey().getWord().toString());
					} else if (getWord1 != null) {
						System.out.println(getWord1.getKey().getWord().toString());
					} else if (getWord2 != null) {
						System.out.println(getWord2.getKey().getWord().toString());
					}

					else {
						// Determine length of prefix
						int wordLength = word.length();

						// Count the number of times a word starting with the prefix was found
						int added = 0;

						// Resulting string containing the words starting with the prefix
						String result = "";

						// Break down the prefix, starting with the first letter
						// After each loop extend the prefix to include the next letter in the string
						// Searching for the successor to each prefix, which will be words that start
						// with the prefix
						for (int i = 1; i < wordLength + 1; i++) {
							Pair checkPrefix = new Pair(word.substring(0, i), "text");
							Record successor = dictionary.successor(checkPrefix);
							if (successor != null) {
								if (successor.getKey().getWord().startsWith(word.substring(0, i))) {
									result += successor.getKey().getWord().toString() + ", ";
									added++;
								}
							}
						}
						// Check to see if there are any words that start with the prefix by using the
						// added variable
						if (added == 0) {
							System.out.println("No words in the ordered dictionary start with prefix " + word);
						}

						// Print out the list of words that start with the prefix
						else {
							System.out.println(result.substring(0, result.length() - 2));
						}
					}
				}
				// Print the record with the smallest key in the ordered dictionary
				else if (cmd.equals("smallest")) {
					Record smallest = dictionary.smallest();
					if (smallest != null) {
						System.out.println("(" + smallest.getKey().getWord().toString() + ", "
								+ smallest.getKey().getType().toString() + ") " + smallest.getData().toString());
					}
				}
				// Print the record with the largest key in the ordered dictionary
				else if (cmd.equals("largest")) {
					Record largest = dictionary.largest();
					if (largest != null) {
						System.out.println("(" + largest.getKey().getWord().toString() + ", "
								+ largest.getKey().getType().toString() + ") " + largest.getData().toString());
					}
				} else {
					System.out.println(
							"Invalid command. Valid commands: get, delete, put, list, smallest, largest, or finish.");
				}
			}
			// Ask user for input
			line = keyboard.read("Enter next command: ");
			// Initialize the StringTokenizer
			st = new StringTokenizer(line);
		}
		System.out.println("Program terminated.");
	}
}
