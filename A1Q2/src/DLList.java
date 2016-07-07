/**
 * @author Eric Dunbar
 * @date 17/5/2016
 * @assignment 1
 * @question 2B
 * @title DLList node swapping
 * @description  2. Swap two adjacent elements in a list by adjusting only the links (and not the data) using...
 * a doubly-linked list (6 marks). In a DLList (doubly-linked list) each node u stores a data
 * value, x and has references to both the node u.next that follows it and the
 * node u.prev that precedes it.
 * 
 * Source: ODS by PM

 * @param <E>
 *
 */
public class DLList<T> implements Comparable<T> {

	/*
	 * "CompareTo with Generic Objects!" (Beginning Java Forum at Coderanch).
	 * July 31, 2013. Accessed May 17, 2016.
	 * http://www.coderanch.com/t/617025/java/java/compareTo-generic-objects.
	 * 
	 * "GC: LinkedList - Java.util.LinkedList (.java) - GrepCode Class Source."
	 * 2013. Accessed May 17, 2016.
	 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/
	 * 8u40-b25/ java/util/LinkedList.java#LinkedList.Node.
	 * 
	 * "In Java What's the Difference between ?, E, T." - Stack Overflow. April
	 * 3, 2011. Accessed May 17, 2016.
	 * http://stackoverflow.com/questions/5526955/in-java-whats-the-difference-
	 * between-e- t.
	 * 
	 * "<T> Cannot Be Resolved to a Type." Java. September 23, 2010. Accessed
	 * May 17, 2016.
	 * http://stackoverflow.com/questions/3777315/t-cannot-be-resolved-to-a-
	 * type.
	 * 
	 * "What Does the <E> in Java Mean?" Syntax. November 28, 2013. Accessed May
	 * 17, 2016.
	 * http://stackoverflow.com/questions/20255911/what-does-the-e-in-java-mean.
	 */

	/*
	 * *************************************************************************
	 * ********** COMPARABLE solution
	 * *************************************************************************
	 * ********** "Use a Linked List to Implement a Priority Queue." Java.
	 * August 22, 2014. Accessed May 17, 2016.
	 * http://stackoverflow.com/questions/25437682/use-a-linked-list-to-
	 * implement-a- priority-queue.
	 * *************************************************************************
	 * **********
	 */

	/**
	 * DLList Node implemented with prev and next
	 *
	 */
	class Node {
		T elementData;
		Node prev, next;
	}

	/*
	 * Dummy node. This is a node that does not contain any data, but acts as a
	 * placeholder so that there are no special nodes; every node has both a
	 * next and a prev, with dummy acting as the node that follows the last node
	 * in the list and that precedes the first node in the list. In this way,
	 * the nodes of the list are (doubly-)linked into a cycle.
	 */

	Node dummy; // first and last node in sequence
	int n; // length of sequence
	final boolean testing;

	/**
	 * Create a doubly-linked list using a dummy node to solve head and tail
	 * link problems.
	 * 
	 * Source: ODS by PM
	 */
	DLList() {
		dummy = new Node();
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
		testing = false;
	}

	/**
	 * To find the node with index in a DLList start at the head of the list
	 * (dummy.next) and work forward, or start at the tail of the list
	 * (dummy.prev) and work backward. This reaches the ith node in O(1 + min{i,
	 * n-i}) time.
	 * 
	 * Source: ODS by PM
	 * 
	 * "GC: LinkedList - Java.util.LinkedList (.java) - GrepCode Class Source."
	 * GC: LinkedList - Java.util.LinkedList (.java) - GrepCode Class Source.
	 * 2006. Accessed May 17, 2016.
	 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-
	 * b14/java/
	 * util/LinkedList.java#LinkedList.addBefore(java.lang.Object,java.util.
	 * LinkedList. Entry).
	 * 
	 * 
	 * @param index
	 * @return
	 */
	Node getNode(int index) {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Index: " + index + "; List size: " + n);

		Node target = null;
		if (index < n / 2) { // search forwards
			target = dummy.next;
			for (int j = 0; j < index; j++)
				target = target.next;
		} else { // search backwards
			target = dummy;
			for (int j = n; j > index; j--)
				target = target.prev;
		}
		return (target);
	}

	/**
	 * Return the value at the ith index. The running time of this operation is
	 * dominated by the time it takes to find the ith node, and is therefore O(1
	 * + min{i, n-i}).
	 * 
	 * Source: ODS by PM
	 * 
	 * @param index
	 * @return
	 */
	T get(int index) {
		return getNode(index).elementData;
	}

	/**
	 * First find the ith node, then set its value, and finally return the old
	 * value. The running time of this operation is dominated by the time it
	 * takes to find the ith node, and is therefore O(1 + min{i, n-i}).
	 * 
	 * Source: ODS by PM
	 * 
	 * @param index
	 * @param x
	 * @return
	 */
	T set(int index, T x) {
		Node u = getNode(index);
		T oldElement = u.elementData;
		u.elementData = x;
		return oldElement;
	}

	/*
	 * ADDING AND REMOVING
	 * 
	 * With a reference to node w in a DLList, insert node u before w by setting
	 * u.next = w, u.prev = w.prev, and then adjusting u.prev.next and
	 * u.next.prev. (See Figure 3.3 in ODS) The dummy node eliminates the need
	 * to worry about w.prev or w.next not existing.
	 * 
	 * Source: 3.2.1 ODS by PM and
	 * 
	 * "GC: LinkedList - Java.util.LinkedList (.java) - GrepCode Class Source."
	 * GC: LinkedList - Java.util.LinkedList (.java) - GrepCode Class Source.
	 * 2006. Accessed May 17, 2016.
	 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-
	 * b14/java/
	 * util/LinkedList.java#LinkedList.addBefore(java.lang.Object,java.util.
	 * LinkedList. Entry).
	 * 
	 */

	/**
	 * Insert the element in a new Node in front of Node w.
	 * 
	 * Source: ODS by PM and
	 * 
	 * @param w
	 * @param element
	 * @return
	 */
	Node addBefore(Node w, T element) {
		Node newNode = new Node();
		newNode.elementData = element;
		newNode.prev = w.prev;
		newNode.next = w;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		n++;
		return newNode;
	}

	/**
	 * Insert element in front of index. Add(i, x) runs in O(1 + min{i, n-i})
	 * time.
	 * 
	 * Source: ODS by PM and
	 * 
	 * @param index
	 * @param element
	 */
	public void add(int index, T element) {
		addBefore(getNode(index), element);
	}

	/**
	 * Add an element to the head of the list.
	 * 
	 * @param x
	 * @return
	 */
	public boolean add(T x) {
		addBefore(dummy, x);
		return true;
	}

	// REMOVING
	/*
	 * The only expensive part of this operation is finding the ith node using
	 * getNode(i), so remove(i) runs in O(1 + min{i, n-i}) time.
	 */

	/**
	 * Adjust pointers at w.next and w.prev so they skip w. The dummy node
	 * eliminates the need to consider any special cases.
	 * 
	 * Source: ODS by PM
	 * 
	 * @param w
	 * @return
	 */
	T remove(Node w) {
		T result = w.elementData;
		w.prev.next = w.next;
		w.next.prev = w.prev;
		n--;
		return result;
	}

	/**
	 * remove node at index
	 * 
	 * @param index
	 * @return
	 */
	T remove(int index) {
		Node w = getNode(index);
		remove(w);
		return w.elementData;
	}

	/**
	 * Return the size() of the list.
	 * 
	 * @return size of list as integer
	 */
	public int size() {
		return n;
	}

	/**
	 * Display the elements contained in the previous, current and next nodes.
	 * 
	 * @param y
	 *            current node
	 */
	public void printSummary(Node y) {
		System.out.printf("Swap summary: -1: %4s (0: %4s); 0: %4s; +1: %4s (0: %4s); +2: %4s (+1: %4s) %n",
				y.prev.elementData, y.prev.next.elementData, y.elementData, y.next.elementData, y.next.prev.elementData,
				y.next.next.elementData, y.next.next.prev.elementData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String listSummary = "";
		String separator = "";
		Node current = dummy.next;

		for (int i = 0; i < this.size(); i++) {
			String classType;
			try {
				classType = current.elementData.getClass().toString();
			} catch (Exception e) {
				classType = "";
			}
			if (classType.equals("class java.lang.Double")) {
				listSummary += separator + String.format("%4.2f", current.elementData);
			} else
				listSummary += separator + current.elementData;
			current = current.next;
			separator = ", ";
		}
		return "DLList [" + listSummary + "]";
	}

	/**
	 * Swap the following node with the current node
	 * @param y First of two nodes to be swapped
	 */
	public void swapWithNextNode(Node y) {
		// Future: move into own class

		if (y.next == dummy || y == dummy)
			throw new IndexOutOfBoundsException("Cannot swap with dummy node.");

		Node after = y.next.next;

		if (testing) {
			printSummary(y);
		}
		y.prev.next = y.next;
		y.next.prev = y.prev;
		y.next.next.prev = y;
		y.next.next = y;
		y.prev = y.next;
		y.next = after;

		if (testing) {
			printSummary(y);
			System.out.println();
		}

	}

	@Override
	public int compareTo(T arg0) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
