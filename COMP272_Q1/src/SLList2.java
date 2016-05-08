
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

/**
 * An implementation of a FIFO Queue as a singly-linked list. This also includes
 * the stack operations push and pop, which operate on the head of the queue
 * 
 * @author morin
 *
 * @param <T>
 *            the class of objects stored in the queue
 */
public class SLList2<T extends Comparable<T>> {
	class Node {
		T x;
		Node next;
	}

	/**
	 * Front of the queue
	 */
	Node head;

	/**
	 * Tail of the queue
	 */
	Node tail;

	/**
	 * The number of elements in the queue
	 */
	int n;

	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	public boolean add(T x) {
		Node u = new Node();
		u.x = x;
		if (n == 0) {
			head = u;
		} else {
			tail.next = u;
		}
		tail = u;
		n++;
		return true;
	}

	public boolean offer(T x) {
		return add(x);
	}

	public T peek() {
		return head.x;
	}

	public T poll() {
		if (n == 0)
			return null;
		T x = head.x;
		head = head.next;
		if (--n == 0)
			tail = null;
		return x;
	}

	/**
	 * Stack push operation - push x onto the head of the list
	 * 
	 * @param x
	 *            the element to push onto the stack
	 * @return x
	 */
	public T push(T x) {
		Node u = new Node();
		u.x = x;
		u.next = head;
		head = u;
		if (n == 0)
			tail = u;
		n++;
		return x;
	}

	public T deleteMin() {
		if (n == 0)
			return null; // nothing to delete
		else if (n == 1)
			return pop(); // one item to delete

		Node trackerNode = head;
		// find the minimum Object
		for (int i = 1; i < n; i++) {
			System.out.println(trackerNode.x.compareTo(trackerNode.next.x));
		}

		// delete min
		return null;
	}

	protected void deleteNext(Node u) {
		if (u.next == tail)
			tail = u;
		u.next = u.next.next;
	}

	protected void addAfter(Node u, Node v) {
		v = u.next.next;
		u.next = v;
		if (u == tail)
			tail = v;
	}

	protected Node getNode(int i) {
		Node u = head;
		for (int j = 0; j < i; j++)
			u = u.next;
		return u;
	}

	/**
	 * Stack pop operation - pop off the head of the list
	 * 
	 * @return the element popped off
	 */
	public T remove() {
		if (n == 0)
			return null;
		T x = head.x;
		head = head.next;
		if (--n == 0)
			tail = null;
		return x;
	}

	public T pop() {
		if (n == 0)
			return null;
		T x = head.x;
		head = head.next;
		if (--n == 0)
			tail = null;
		return x;
	}

	public static void main(String[] args) {
		Queue<Integer> q = new PriorityQueueSLList<Integer>(Integer.class);
		for (int i = 0; i < 100; i++) {
			q.add(i);
		}
		System.out.println(q);
		for (int i = 0; i < 50; i++) {
			q.remove();
		}
		System.out.println(q);
		for (int i = 100; i < 200; i++) {
			q.add(i);
		}
		System.out.println(q);
		for (int i = 0; i < 50; i++) {
			q.remove();
		}
		System.out.println(q);
		while (!q.isEmpty()) {
			q.remove();
		}
		System.out.println(q);

	}
}
