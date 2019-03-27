
public class ArrayCode {
	private CodePair [] arrayOfCodes;
	private int arraySize;
	private int numCodePairs;
	
	private CodePair[] doubleCapacity(CodePair[] array) {
		CodePair[] newArrayOfCodes = new CodePair [arraySize*2];
		for (int i = 0; i < numCodePairs; i++) {
			newArrayOfCodes[i] = array[i];
		}	
		return newArrayOfCodes;
	}
	
	private CodePair[] expandCapacity(CodePair[] array) {
		CodePair[] newArrayOfCodes = new CodePair [arraySize+20];
		for (int i = 0; i < numCodePairs; i++) {
			newArrayOfCodes[i] = array[i];
		}
		return newArrayOfCodes;
	}
	
	private CodePair[] decreaseCapacity(CodePair[] array) {
		CodePair[] newArrayOfCodes = new CodePair [arraySize/2];
		for (int i = 0; i < numCodePairs; i++) {
			newArrayOfCodes[i] = array[i];
		}
		return newArrayOfCodes;
	}
	
	public ArrayCode(int size) {
		arraySize = size;
		numCodePairs = 0;
		arrayOfCodes = new CodePair [arraySize];
	}
	
	public void add (CodePair pair) {
		if (arraySize == numCodePairs) {
			if (arraySize <= 100) {
				arrayOfCodes = doubleCapacity(arrayOfCodes);
			}else {
				arrayOfCodes = expandCapacity(arrayOfCodes);
			}
		}else {
			arrayOfCodes[numCodePairs] = pair;
		}
	}
	
	public void remove (CodePair pairToRemove) {
		int i = 0;
		while ((i<numCodePairs) && !(arrayOfCodes[i].getCharacter() == pairToRemove.getCharacter())) {
			i++;
		}
		arrayOfCodes[i] = arrayOfCodes[numCodePairs - 1];
		arrayOfCodes[numCodePairs - 1] = null;
		numCodePairs--;
		
		if (numCodePairs < (arraySize/4)) {
			arrayOfCodes = decreaseCapacity(arrayOfCodes);
		}
	}
	
	public int findCode(String code) {
		int i = 0;
		while ((i<numCodePairs) && !(arrayOfCodes[i].getCode() == code)) {
			i++;
		}
		if (i == numCodePairs) return -1;
		else {
			return i;
		}
	}
	
	public int findCharacter(char c) {
		int i = 0;
		while ((i<numCodePairs) && !(arrayOfCodes[i].getCharacter() == c)) {
			i++;
		}
		if (i == numCodePairs) return -1;
		else {
			return i;
		}
	}
	
	public String getCode(int i) {
		if (i <= numCodePairs && i >= 0) {
			return arrayOfCodes[i].getCode();
		}else {
			return null;
		}
	}
	
	public char getCharacter(int i) {
		if (i <= numCodePairs && i >= 0) {
			return arrayOfCodes[i].getCharacter();
		}else {
			return 0;
		}
	}
	
	public int getSize() {
		return arraySize;
	}
	
	public int getNumPairs() {
		return numCodePairs;
	}
}