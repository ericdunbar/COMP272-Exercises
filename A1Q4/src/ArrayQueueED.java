import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements part of the Queue<T> interface using a few of the modification provided by
 * Open Data Structures by Pat Morin.
 * 
 * See also: https://docs.oracle.com/javase/8/docs/api/java/util/AbstractQueue.html
 * 
 * @author Eric D
 * @param <T>
 *
 */
public class ArrayQueueED<T> extends AbstractQueue<T> {
	/*
	 * the ArrayQueue data structure implements a FIFO (first-in-first-out) queue;
	 * elements are removed (using the remove() operation) from the queue in the same
	 * order they are added (using the add(x) operation).
	 */

	// INSTANCE VARIABLES

	// number of elements in queue
	protected int n;

	// next element to remove
	protected int head; // j in ODS by Pat Morin

	// array
	protected T[] backingArray;

	// special class to create generic arrays
	protected FactoryODS<T> arrayFactory;

	/**
	 * Create instance of an array capable of handling generics.
	 * 
	 * @param t
	 *            object type
	 */
	public ArrayQueueED(Class<T> t) {
		arrayFactory = new FactoryODS<T>(t); // used for generic arrays
		backingArray = arrayFactory.newArray(1); // assume min size of 1
		head = 0;
		n = 0;
	}

	/**
	 * Adds element to the tail of the queue.
	 * 
	 * @param x
	 *            element to be added to queue
	 * @return true if element successfully added
	 */
	@Override
	public boolean add(T x) {
		if (n + 1 > backingArray.length)
			resize();
		backingArray[(head + n) % backingArray.length] = x;
		n++;
		return true;
	}
s
	public T remove() {
		if (n == 0)
			throw new NoSuchElementException();
		T x = backingArray[head];
		head = (head + 1) % backingArray.length;
		n--;
		if (backingArray.length >= 3 * n)
			resize();
		return x;
	}

	/**
	 * Resize the backing array when the number of elements exceeds the array's size or
	 * when the number of elements stored decreases below a certain point.
	 */
	protected void resize() {
		T[] tempArray = arrayFactory.newArray(Math.max(1, n * 2));
		for (int index = 0; index < n; index++)
			tempArray[index] = backingArray[(head + index) % backingArray.length];
		backingArray = tempArray;
		head = 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean offer(T arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

		// return false;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

		// return null;
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

		// return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

		// return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

		// return 0;
	}

}
