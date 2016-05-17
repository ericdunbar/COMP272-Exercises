import java.util.AbstractQueue;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * 2. Swap two adjacent elements in a list by adjusting only the links (and not the data) using...
 * 
 * a. doubly-linked list (6 marks).
 * 
 */

/**
 * Description: An SLList (singly-linked list) is a sequence of Nodes. Each node u stores a data
 * value u.x and a reference u.next to the next node in the sequence. For the last node w in the
 * sequence, w.next = null. A DLList (doubly-linked list) is very similar to an SLList except that
 * each node u in a DLList has references to both the node u:next that follows it and the node
 * u:prev that precedes it.
 * 
 * Source: ODS by PM
 * 
 * @Date 17/5/2016
 * @author Eric Dunbar
 * @param <E>
 *
 */
public class DLList<T extends Comparable<T>> {

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
		Node prev, next;
	}

	/*
	 * Dummy node. This is a node that does not contain any data, but acts as a placeholder so that
	 * there are no special nodes; every node has both a next and a prev, with dummy acting as the
	 * node that follows the last node in the list and that precedes the first node in the list. In
	 * this way, the nodes of the list are (doubly-)linked into a cycle
	 */

	Node dummy; // first node in sequence
	int n; // length of sequence

	/**
	 * Create a doubly-linked list using a dummy node to solve head and tail link problems.
	 * 
	 * Source: ODS by PM
	 */
	DLList() {
		dummy = new Node();
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
	}

	/**
	 * Finding the node with a particular index in a DLList is easy; we can either start at the head
	 * of the list (dummy:next) and work forward, or start at the tail of the list (dummy:prev) and
	 * work backward. This allows us to reach the ith node in O(1 + min{i, n-i}) time.
	 * 
	 * Source: ODS by PM http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-
	 * b14/java/util/LinkedList.java#LinkedList.entry%28int%29
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
	 * Return the value of the ith. The running time of this operation is dominated by the time it
	 * takes to find the ith node, and is therefore O(1 + min{i, n-i}).
	 * 
	 * Source: ODS by PM
	 * 
	 * @param i
	 * @return
	 */
	T get(int i) {
		return getNode(i).elementData;
	}

	/**
	 * First find the ith node, then set its value, and finally return the old value. The running
	 * time of this operation is dominated by the time it takes to find the ith node, and is
	 * therefore O(1 + min{i, n-i}).
	 * 
	 * Source: ODS by PM
	 * 
	 * @param i
	 * @param x
	 * @return
	 */
	T set(int i, T x) {
		Node u = getNode(i);
		T oldElement = u.elementData;
		u.elementData = x;
		return oldElement;
	}

	/*
	 * ADDING AND REMOVING
	 * 
	 * With a reference to node w in a DLList, insert node u before w by setting u.next = w, u.prev
	 * = w.prev, and then adjusting u.prev.next and u.next.prev. (See Figure 3.3 in ODS) The dummy
	 * node eliminates the need to worry about w.prev or w.next not existing.
	 * 
	 * Source: 3.2.1 ODS by PM and
	 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/
	 * LinkedList.java#LinkedList.addBefore%28java.lang.Object%2Cjava.util.LinkedList.Entry%29
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
	 * Insert element in front of index. Add(i, x) runs in O(1 + min{i, n-i}) time.
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
	 * The only expensive part of this operation is finding the ith node using getNode(i), so
	 * remove(i) runs in O(1 + min{i, n-i}) time.
	 */

	/**
	 * Adjust pointers at w.next and w.prev so they skip w. The dummy node eliminates the need to
	 * consider any special cases.
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

	// STACK OPERATIONS

	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("START DLL TESTING");



		System.out.println("END   DLL TESTING");
	}
}
