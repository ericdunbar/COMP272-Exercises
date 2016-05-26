import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/*
 * 2. Swap two adjacent elements in a list by adjusting only the links (and not the data) using...
 * 
 * a. singly-linked list (6 marks).
 * 
 */

/**
 * Description: An SLList (singly-linked list) is a sequence of Nodes. Each node u stores a data
 * value u.x and a reference u.next to the next node in the sequence. For the last node w in the
 * sequence, w.next = null
 * 
 * @Date 15/5/2016
 * @author Eric Dunbar
 * @param <E>
 *
 */
public class SLList<T extends Comparable<T>> {

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

	private class Node {
		T elementData;
		Node next;
	}

	/*
	 * For efficiency, an SLList uses variables head and tail to keep track of the first and last
	 * node in the sequence, as well as an integer n to keep track of the length of the sequence:
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
	 * Sources: http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in- java/ and
	 * Morin
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
	 * 2. Swap two adjacent elements in a list by adjusting only the links (and not the data)
	 * using...
	 * 
	 * a. singly-linked list (6 marks).
	 */
	public void swapWithNextNode(Node swapNextNode) {
		// throw an exception if list is empty?
		Node base = new Node();
		base.next = head;

		for (int i = 0; i < this.size(); i++) {
			if (base.next == swapNextNode) {
				if (base.next == tail)
					return; // throw exception?

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

		// what happens if the node isn't found? Throw an exception?
	}

	/**
	 * Search for, removes and returns the smallest element in the singly-linked list. Ties are
	 * arbitrarily broken by removing the "oldest" or "first in" element.
	 * 
	 * @param <T>
	 * 
	 * @return T representing the smallest element
	 */
	public T deleteMin() {
		//TODO move deleteMin into its own class
		if (n == 0)
			return null; // nothing to delete
		// this should throw an error, don't you think?
		else if (n == 1)
			return pop(); // one item to delete
		else if (n == 2)
			System.out.println("A problem exists... fix me");

		Node currentNode = head;
		Node minimumIsNext = new Node();
		minimumIsNext.next = head; // start by assuming head is minimum
		Node tailInTwoNodes = new Node();

		// find the minimum element
		for (int i = 0; i < n - 1; i++) {
			if (minimumIsNext.next.elementData.compareTo(currentNode.next.elementData) > 0) {
				// we've found a new smallest element
				minimumIsNext = currentNode;
			}
			if (n - 2 == i)
				tailInTwoNodes = currentNode; // record node two back from TAIL

			currentNode = currentNode.next; // advance to the next node
		}

		if (minimumIsNext.next == head)
			return remove(); // the HEAD element is the minimum
		else if (minimumIsNext.next == tail) {
			// TAIL is minimum
			T element = minimumIsNext.next.elementData;

			// remove tail from the list
			tail = minimumIsNext;

			if (n == 2) {
				head = tail;
				tail.next = head;
			} else {
				tail.next = tailInTwoNodes;
				tailInTwoNodes.next = tail;
			}

			n--;
			System.out.print("TAIL is min ");
			return element;
		} else if (minimumIsNext.next.next == tail) {
			// TAIL comes after minimum
			T element = minimumIsNext.next.elementData;

			// remove node n-2 from the list
			tail.next = minimumIsNext;
			minimumIsNext.next = tail;

			n--;
			System.out.print("TAIL is one after min ");
			return element;
		} else
			return removeNextNode(minimumIsNext);
	}

	private T removeNextNode(SLList<T>.Node removeNextNode) {
		if (removeNextNode == tail || removeNextNode.next == tail) {
			throw new NoSuchElementException(
					"Cannot remove nodes at size()-1 or size()-2 using this method.");
		} else if (n == 0) {
			throw new EmptyStackException(); // should be
												// EmptyLinkedListException but
												// close enough
		}

		T element = removeNextNode.next.elementData; // get the element to be
														// returned

		removeNextNode.next = removeNextNode.next.next; // remove the next node

		n--; // decrement size of the SLList

		return element; // return the Object
	}

	public int size() {
		return n;
	}

	public Node getNode(int i) {
		Node returnNode = head;
		for (int idx = 0; idx < i; idx++)
			returnNode = returnNode.next;
		return returnNode;
	}

	private static void swapNodeDemo() {
		SLList<String> mySwapQueue = new SLList<>();

		System.out.println(mySwapQueue.size());
		System.out.println(mySwapQueue.add("First"));
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Second");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("third");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Fourth");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Fifth");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Sixth");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Seventh");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Eighth");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Ninth");
		System.out.println(mySwapQueue.size());
		mySwapQueue.add("Tenth");
		System.out.println(mySwapQueue.size());
		for (int idx = 0; idx < mySwapQueue.size(); idx++) {
			System.out.println(idx + ": " + mySwapQueue.getNode(idx).elementData);
		}

		int j = 10;
				
		mySwapQueue.swapWithNextNode(mySwapQueue.getNode(j));

		System.out.println(mySwapQueue.getNode(j));
		
		for (int idx = 0; idx < mySwapQueue.size(); idx++) {
			System.out.println(idx + ": " + mySwapQueue.getNode(idx).elementData);
		}

	}

	@SuppressWarnings("unused")
	private static void priorityQueueDemo() {
		SLList<String> myPriorityQueue = new SLList<>();

		System.out.println("Fi".compareTo("Fifth"));

		System.out.println(myPriorityQueue.size());
		System.out.println(myPriorityQueue.add("First"));
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Second");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Tenth");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Fourth");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Fifth");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Sixth");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Seventh");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Eighth");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Ninth");
		System.out.println(myPriorityQueue.size());
		myPriorityQueue.add("Third");
		System.out.println(myPriorityQueue.size());

		while (myPriorityQueue.size() > 0) {
			System.out.print(myPriorityQueue.size() + ", ");
			System.out.println(myPriorityQueue.deleteMin() + ", " + myPriorityQueue.size());
		}
		System.out.println(myPriorityQueue.size());
		System.out.println(myPriorityQueue.deleteMin());
		System.out.println(myPriorityQueue.size());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("START SLL TESTING");

		swapNodeDemo();

		System.out.println("END   SLL TESTING");
		System.out.println(-2 >>> 3);

	}
}
