/**
 * @author Eric Dunbar
 * @date 7/7/2016
 * @assignment 1
 * @question 5
 * @title Reversible DLL
 * @description Exercise 3.12. A method, reverse(), that reverses the order of
 *              elements in a DLList.
 *
 * @param <T>
 *            the data type of the DLList
 */
public class ReversibleDLList<T> extends DLList<T> {

	/*
	 * Problem: How to extend DLList? The previous construction <T extends
	 * Comparable<T>> wasn't working. 'extends' works for DLList<T>.
	 * 
	 * Inferred type is not a valid substitute for a Comparable generic type.
	 * (2013, July 19). Retrieved May 28, 2016, from
	 * http://stackoverflow.com/questions/17739720/inferred-type-is-not-a-valid-
	 * substitute -for-a-comparable-generic-type
	 */

	/**
	 * Reverses the order of elements by adjusting links.
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
