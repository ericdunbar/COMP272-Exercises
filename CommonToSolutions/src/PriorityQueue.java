import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * @author Eric Dunbar
 * @date 15/7/2016
 * @assignment 1
 * @question 1A
 * @title PriorityQueue
 * @description Implementation of a priority queue for
 *              assignment 1, question 1 a.
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class PriorityQueue<T> extends SLList {
	/**
	 * Search for, removes and returns the smallest element in the singly-linked list. Ties are
	 * arbitrarily broken by removing the "oldest" or "first in" element.
	 * 
	 * @param <T>
	 * @return T representing the smallest element
	 */
	@SuppressWarnings("unchecked")
	public T deleteMin() {
		if (n == 0)
			return null; // nothing to delete
		// this should throw an error, don't you think?
		else if (n == 1)
			return (T) pop(); // one item to delete

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
			return (T) remove(); // the HEAD element is the minimum
		else if (minimumIsNext.next == tail) {
			// TAIL is minimum
			T element = (T) minimumIsNext.next.elementData;

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
			return element;
		} else if (minimumIsNext.next.next == tail) {
			// TAIL comes after minimum
			T element = (T) minimumIsNext.next.elementData;

			// remove node n-2 from the list
			tail.next = minimumIsNext;
			minimumIsNext.next = tail;

			n--;
			return element;
		} else
			return (T) removeNextNode(minimumIsNext);
	}

	/**
	 * Do the actual removing of the node next node
	 * 
	 * @param removeNextNode node before node to remove
	 * @return element of removed node
	 */
	@SuppressWarnings("unchecked")
	protected T removeNextNode(Node removeNextNode) {
		if (removeNextNode == tail || removeNextNode.next == tail) {
			throw new NoSuchElementException("Cannot remove nodes at size()-1 or size()-2 using this method.");
		} else if (n == 0) {
			throw new EmptyStackException(); // should be EmptyLinkedListException but close enough
		}

		T element = (T) removeNextNode.next.elementData; // get the element to be returned

		removeNextNode.next = removeNextNode.next.next; // remove the next node

		n--; // decrement size of the SLList

		return element; // return the Object
	}

}
