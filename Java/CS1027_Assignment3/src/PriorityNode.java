/**
 * Class for implementing nodes containing a data item and associated priority
 * for implementation in DLPriorityQueue
 * 
 * @author Dinushan Dayarathna
 *
 */
public class PriorityNode<T> {

	// Instance variables for the class
	private T dataItem;
	private PriorityNode<T> next;
	private PriorityNode<T> previous;
	private double priority;

	/**
	 * Constructor for class PriorityNode<T> that takes in specific parameters to
	 * create a node
	 * 
	 * @param data
	 * @param prio
	 */
	public PriorityNode(T data, double prio) {
		dataItem = data;
		priority = prio;
	}

	/**
	 * Base constructor for PriorityNode<T> that doesn't take in parameters and
	 * creates a null node
	 */
	public PriorityNode() {
		dataItem = null;
		priority = 0;
	}

	/**
	 * Method returns the priority
	 * 
	 * @return priority
	 */
	public double getPriority() {
		return priority;
	}

	/**
	 * Method returns the data item
	 * 
	 * @return dataItem
	 */
	public T getDataItem() {
		return dataItem;
	}

	/**
	 * Method returns the next node that this node is pointing to
	 * 
	 * @return next
	 */
	public PriorityNode<T> getNext() {
		return next;
	}

	/**
	 * Method returns the previous node that this node is pointing to
	 * 
	 * @return previous
	 */
	public PriorityNode<T> getPrevious() {
		return previous;
	}

	/**
	 * Method sets the instance variable dataItem to item specified in the parameter
	 * 
	 * @param item
	 */
	public void setDataItem(T item) {
		dataItem = item;
	}

	/**
	 * Method sets the instance variable priority to priority specified in the
	 * parameter
	 * 
	 * @param prio
	 */
	public void setPriority(double prio) {
		priority = prio;
	}

	/**
	 * Method sets the instance variable next to the node specified in the parameter
	 * 
	 * @param node
	 */
	public void setNext(PriorityNode<T> node) {
		next = node;
	}

	/**
	 * Method sets the instance variable previous to the node specified in the
	 * parameter
	 * 
	 * @param node
	 */
	public void setPrevious(PriorityNode<T> node) {
		previous = node;
	}

}
