/**
 * Class that allows user to create a stack using an array
 * 
 * @author Dinushan
 *
 * @param <T>
 */
public class MyStack<T> implements MyStackADT<T> {

	/**
	 * Class instance variables
	 */
	private T[] arrayStack;
	private int numItems;
	private int maximumCapacity;

	/**
	 * Constructor for the class
	 */
	public MyStack() {
		numItems = 0;
		maximumCapacity = 1000;
		arrayStack = (T[]) (new Object[10]);
	}

	/**
	 * Constructor for the class with parameters
	 * 
	 * @param initialCapacity
	 * @param maxCap
	 */
	public MyStack(int initialCapacity, int maxCap) {
		numItems = 0;
		maximumCapacity = maxCap;
		arrayStack = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * Method that increases the capacity of the array by a factor of 3
	 * 
	 * @param stack
	 * @return
	 */
	private T[] increaseBy3(T[] stack) {
		T[] newArrayStack = (T[]) (new Object[arrayStack.length * 3]);
		for (int i = 0; i < numItems; i++) {
			newArrayStack[i] = arrayStack[i];
		}
		return newArrayStack;
	}

	/**
	 * Method that increases the capacity of the array by 100
	 * 
	 * @param stack
	 * @return stack
	 */
	private T[] add100(T[] stack) {
		T[] newArrayStack = (T[]) (new Object[arrayStack.length + 100]);
		for (int i = 0; i < numItems; i++) {
			newArrayStack[i] = arrayStack[i];
		}
		return newArrayStack;
	}

	/**
	 * Method that adds an item to the top of the stack
	 */
	public void push(T dataItem) throws OverflowException {
		if (numItems == arrayStack.length && arrayStack.length < 60) {
			arrayStack = increaseBy3(arrayStack);
		}
		if (numItems == arrayStack.length && arrayStack.length >= 60) {
			arrayStack = add100(arrayStack);
		}
		if (arrayStack.length > maximumCapacity) {
			throw new OverflowException("Maximum capacity reached!");
		}
		arrayStack[numItems] = dataItem;
		numItems++;
	}

	/**
	 * Method that removes and return the item at the top of the stack
	 * 
	 * @return T
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Stack is empty!");
		}
		numItems--;
		T item = arrayStack[numItems];
		arrayStack[numItems] = null;
		return item;
	}

	/**
	 * Method that returns but doesn't remove the item at the top of the stack
	 * 
	 * @return T
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Stack is empty!");
		}
		numItems--;
		T item = (T) (arrayStack[numItems]);
		numItems++;
		return item;
	}

	/**
	 * Returns true of the stack is empty return boolean
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		else
			return false;
	}

	/**
	 * returns the number of items in the stack return int
	 * 
	 * @return int
	 */
	public int size() {
		return numItems;
	}

	/**
	 * returns a string representation of items in the stack return String
	 * 
	 * @return String
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < numItems; i++) {
			str = str + arrayStack[i].toString() + "\n";
		}
		return str;
	}
}
