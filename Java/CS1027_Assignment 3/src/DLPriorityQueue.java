/**
 * Class for implementing a doubly linked queue of PriorityNodes
 * 
 * @author Dinushan Dayarathna
 *
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

	// Instance variables
	private PriorityNode<T> front;
	private PriorityNode<T> rear;
	private int count;

	/**
	 * Constructor for the class: creates an empty queue
	 */
	public DLPriorityQueue() {
		count = 0;
		front = rear = null;
	}

	/**
	 * Method that removes a node from the queue regardless of its location
	 * 
	 * @param item
	 */
	private void dequeueSmallest(T item) {
		PriorityNode<T> current = front;
		while (current != null) {
			if (current.getDataItem().equals(item)) {
				if (numItems() == 1) {
					rear = front = null;
					count--;
				} else if (rear.equals(current)) {
					rear = current.getPrevious();
					current.getPrevious().setNext(null);
					count--;
				} else if (front.equals(current)) {
					dequeue();
				} else {
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
					count--;
				}
			}
			current = current.getNext();
		}
	}

	/**
	 * Method adds a node to end of the queue
	 */
	public void enqueue(T dataItem, double priority) { // check if working
		PriorityNode<T> node = new PriorityNode<T>(dataItem, priority);

		if (isEmpty())
			front = node;
		else {
			node.setPrevious(rear);
			rear.setNext(node);
		}

		rear = node;
		count++;
	}

	/**
	 * Method removes a node from the front of the queue
	 */
	public T dequeue() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("queue is empty");
		T result = front.getDataItem();
		if (numItems() == 1) {
			front = rear = null;
		} else {
			front.getNext().setPrevious(null);
			front = front.getNext();
		}
		count--;
		return result;
	}

	/**
	 * Method returns the priority of the specified data item
	 * 
	 * @param dataItem
	 * @return priority
	 * @throws InvalidDataItemException
	 */
	public double getPriority(T dataItem) throws InvalidDataItemException {
		PriorityNode<T> current = front;
		double priority = 0.0;
		while (current != null) {
			if (current.getDataItem().equals(dataItem)) {
				priority = current.getPriority();
				return priority;
			}
			current = current.getNext();
		}
		throw new InvalidDataItemException("Data Item not found");
	}

	/**
	 * Method changes the priority of the specified data item to the new priority
	 */
	public void changePriority(T dataItem, double newPriority) throws InvalidDataItemException {
		PriorityNode<T> current = front;
		while (current != null) {
			if (current.getDataItem().equals(dataItem)) {
				current.setPriority(newPriority);
				return;
			}
			current = current.getNext();
		}
		if (current == null)
			throw new InvalidDataItemException("Data Item not found");
	}

	/**
	 * Method removes and returns the data item with the smallest priority from the
	 * queue
	 */
	public T getSmallest() throws EmptyPriorityQueueException {
		PriorityNode<T> current = front;
		double smallest;
		T item;

		if (isEmpty())
			throw new EmptyPriorityQueueException("Queue is empty");

		smallest = current.getPriority();
		item = current.getDataItem();
		current = current.getNext();

		while (current != null) {
			if (current.getPriority() < smallest) {
				smallest = current.getPriority();
				item = current.getDataItem();
			}
			current = current.getNext();

		}
		dequeueSmallest(item);
		return item;
	}

	/**
	 * Method checks to see if the queue is empty
	 */
	public boolean isEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	/**
	 * Method returns the number of nodes in the queue
	 */
	public int numItems() {
		return count;
	}

	/**
	 * Method returns the nodes in the queue with their data items and corresponding
	 * priorities
	 */
	public String toString() { // update this method so that it doesn't use toString from PriorityNode
		PriorityNode<T> current = front;
		String str = "";
		if (isEmpty())
			return null;
		while (current != null) {
			str = str.concat(
					"Data item: " + current.getDataItem().toString() + " Priority: " + current.getPriority() + "\n");
			current = current.getNext();
		}
		return str;
	}

}
