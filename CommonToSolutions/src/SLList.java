/**
 * @author Eric Dunbar
 * @date 15/5/2016
 * @assignment 1
 * @question 2A & 1A
 * @title SLList, includes node swapping
 * @description Implementation of a SLList. Includes code for question 2, swap two adjacent elements
 *              in a list by adjusting only the links (and not the data) using... a singly-linked
 *              list (6 marks). Description: An SLList (singly-linked list) is a sequence of Nodes.
 *              Each node u stores a data value u.x and a reference u.next to the next node in the
 *              sequence. For the last node w in the sequence, w.next = null
 * 
 * @param <E>
 *
 */
public class SLList<T extends Comparable<T>> {

	// LIST OF LINKS:

	// Note: future effort... implement PriorityQueue
	// http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-b25/java/util/LinkedList.java#LinkedList.Node
	// http://stackoverflow.com/questions/20255911/what-does-the-e-in-java-mean
	// http://stackoverflow.com/questions/5526955/in-java-whats-the-difference-between-e-t
	// http://www.coderanch.com/t/617025/java/java/compareTo-generic-objects
	// http://stackoverflow.com/questions/3777315/t-cannot-be-resolved-to-a-type

	// COMPARABLE solution
	// *******************************************************************************************
	// http://stackoverflow.com/questions/25437682/use-a-linked-list-to-implement-a-priority-queue
	// *******************************************************************************************

	/**
	 * Track the element for each node with this class
	 */
	class Node {
		public T elementData;
		public Node next;
	}

	/*
	 * For efficiency, an SLList uses variables head and tail to keep track of the first and last
	 * node in the sequence, as well as an integer n to keep track of the length of the list:
	 */
	Node head; // first node in sequence
	Node tail; // last node in sequence
	int n; // length of sequence

	// STACK OPERATIONS
	/*
	 * An SLList can efficiently implement the Stack operations push() and pop() by adding and
	 * removing elements at the head of the sequence.
	 * 
	 * The push() operation simply creates a new node u with data value element, sets u.next to the
	 * old head of the list and makes u the new HEAD of the list. Finally, it increments n since the
	 * size of the SLList has increased by one.
	 * 
	 * Sources\: http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in- java/ and
	 * Morin
	 */

	/**
	 * Implement push operation. Adds element to the stack.
	 * 
	 * @param element data
	 * @return element data given to method
	 */
	public T push(T element) {
		Node u = new Node(); // create a Node
		u.elementData = element; // set data value
		u.next = head; // set next Node to the previous head Node
		head = u; // set new head Node
		if (n == 0)
			tail = u; // list was empty so now head = u = tail

		n++; // increment size of SLList
		return u.elementData; // why does the Object get returned?????
	}

	/*
	 * The pop() operation, after checking that the SLList is not empty, removes the HEAD by setting
	 * head = head.next and decrementing n. A special case occurs when the last element is being
	 * removed, in which case tail is set to null.
	 */
	public T pop() {
		if (n == 0)
			return null; // SLList is already empty

		T element = head.elementData; // get the element to be returned
		head = head.next; // advance the head by one Node
		n--; // decrement size of SLList
		if (n == 0)
			tail = null; // set tail to null if SLList is empty (not a DLList)
		return element; // return the Object
	}

	// QUEUE OPERATIONS

	/*
	 * Removals are done from the HEAD of the list, and are identical to the pop() operation.
	 */

	public T remove() {
		return pop();
	}

	/*
	 * Additions are done at the TAIL of the list. In most cases, this is done by setting tail.next
	 * = u, where u is the newly created node that contains x. However, a special case occurs when n
	 * = 0, in which case tail = head = null. In this case, both tail and head are set to u.
	 */

	/**
	 * Adds the element of type T to the TAIL of a singly-linked list.
	 * 
	 * @param element
	 * @return boolean if successful
	 */
	public boolean add(T element) {
		Node u = new Node(); // create the new Node
		u.elementData = element; // set the element of the new Node
		if (n == 0) // SLList is empty.
			head = u; // set head to the new Node
		else // SLList isn't empty.
			tail.next = u; // set the new tail as the next Node for the old tail

		tail = u; // set the new Node as the tail

		n++; // increase the size of the SLList

		return true; // when would false get returned?
	}

	/**
	 * Swap two adjacent elements in a list by adjusting only the links (and not the data) using a
	 * singly-linked list (6 marks).
	 * 
	 * @param index Index of first of two nodes
	 */
	public void swapWithNextNode(int index) {
		swapWithNextNode(getNode(index));
	}

	/**
	 * Swap two adjacent elements in a list by adjusting only the links (and not the data) using a
	 * singly-linked list (6 marks).
	 * 
	 * @param swapNextNode the node that is to be swapped with the following node
	 */
	public void swapWithNextNode(Node swapNextNode) {
		// throw an exception if list is empty?
		Node base = new Node();
		base.next = head;

		if (this.size() == 0) {
			throw new IndexOutOfBoundsException("Empty list");
		}
		for (int i = 0; i < this.size(); i++) {
			if (base.next == swapNextNode) {
				if (base.next == tail)
					throw new IndexOutOfBoundsException("Cannot swap tail with a following node");

				// is the node to swap head?
				boolean isHead = (base.next == head);

				// use variables to improve readability
				Node nodeIn1 = base.next;
				Node nodeIn2 = base.next.next;
				Node nodeIn3 = base.next.next.next; // null if nodeIn2 == tail

				// perform the swap
				base.next = nodeIn2;
				nodeIn1.next = nodeIn3;
				nodeIn2.next = nodeIn1;

				if (nodeIn2 == tail)
					tail = nodeIn1; // ensure tail points to the new tail

				if (isHead)
					head = nodeIn2; // ensure head points to the new head
				return;
			}
			base = base.next;
		}
	}

	/**
	 * How many elements are stored in the SLList?
	 * @return number of elements
	 */
	public int size() {
		return n;
	}

	/**
	 * Given an index return the pointer to the node
	 * @param i index
	 * @return pointer to node
	 */
	public Node getNode(int i) {
		Node returnNode = head;
		for (int idx = 0; idx < i; idx++)
			returnNode = returnNode.next;
		return returnNode;
	}
}
