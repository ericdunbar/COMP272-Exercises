/**
 * A reversible doubly-linked list. Implements the method reverse() in exercise
 * 3.12 from Open Data Structures by Pat Morin.
 * 
 * @author Eric Dunbar
 *
 * @param <T> the data type stored by the DLList
 */
public class ReversibleDLList<T> extends DLList<T> {
	/*
	 * 5. Exercise 3.12. Write a method, reverse(), that reverses the order of
	 * elements in a DLList.
	 * 
	 * @author Eric Dunbar
	 *
	 */

	/*
	 * How to extend DLList. The previous construction <T extends Comparable<T>>
	 * wasn't working. extends works for DLList.
	 * 
	 * Inferred type is not a valid substitute for a Comparable generic type.
	 * (2013, July 19). Retrieved May 28, 2016, from
	 * http://stackoverflow.com/questions/17739720/inferred-type-is-not-a-valid-
	 * substitute -for-a-comparable-generic-type
	 */

	/**
	 * Reverses the order of elements in a DLList.
	 */
	public void reverse() {
		Node curNode = dummy.next;

		for (int i = 0; i <= this.size(); i++) {
			Node swapNode = curNode.next;
			curNode.next = curNode.prev;
			curNode.prev = swapNode;
			curNode = curNode.prev; // new previous node was former next node
		}
	}
}
