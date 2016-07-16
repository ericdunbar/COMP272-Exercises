import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Eric Dunbar
 * @date 7/7/2016
 * @assignment 1
 * @question 4
 * @title Array Queue
 * @description A minimial implementation of a FIFO queue that extends the Queue<T> interface, based
 *              largely on sample code from Open Data Structures by Pat Morin. This data structure
 *              implements a FIFO (first-in-first-out) queue with add(x) and remove() operations
 *              that perform in constant amortized time; elements are removed from the queue in the
 *              same order they are added. This queue functions strictly as a FIFO queue.
 * 
 *              THIS CLASS SUPPORTS RandomQueue.
 * 
 *              See also: https://docs.oracle.com/javase/8/docs/api/java/util/AbstractQueue.html
 * 
 * @author Eric Dunbar
 * @param <T>
 *
 */
public class ArrayQueueOptimized<T> extends AbstractQueue<T> {
	// INSTANCE VARIABLES

	// number of elements in queue
	protected int n;

	// next element to remove
	protected int head; // j in ODS by Pat Morin

	// array
	protected T[] backArray;

	// special class to create generic arrays
	protected FactoryODS<T> arrayFactory;

	// INSTANCE METHODS

	/**
	 * Gets the element at the given index.
	 * 
	 * @throws IndexOutOfBoundsException if given index is < 0 or > size of array queue
	 * @param idx index
	 * @return element
	 */
	public T get(int idx) {
		if (idx < 0 || idx > n - 1)
			throw new IndexOutOfBoundsException();
		return backArray[(head + idx) % backArray.length];
	}

	/**
	 * Sets the element of the Node at the given index to the data element
	 * 
	 * @throws IndexOutOfBoundsException if given index is < 0 or > size of array queue
	 * @param idx index
	 * @param x element
	 * @return
	 */
	public T set(int idx, T x) {
		if (idx < 0 || idx > n - 1)
			throw new IndexOutOfBoundsException();
		int index = (head + idx) % backArray.length;
		T y = backArray[index];
		backArray[index] = x;
		return y;
	}

	/**
	 * Create instance of an array queue capable of handling generics.
	 * 
	 * @param t object type
	 */
	public ArrayQueueOptimized(Class<T> t) {
		arrayFactory = new FactoryODS<T>(t); // used for generic arrays
		backArray = arrayFactory.newArray(1); // assume min size of 1
		head = 0;
		n = 0;
	}

	/**
	 * Adds an element to the tail of the queue (FIFO behaviour).
	 * 
	 * @param x element to be added to queue
	 * @return true if element successfully added
	 */
	@Override
	public boolean add(T x) {
		if (n + 1 > backArray.length)
			resize();
		backArray[(head + n) % backArray.length] = x;
		n++;
		return true;
	}

	/**
	 * Removes and returns the first (oldest) element added to the queue.
	 * 
	 * @throws NoSuchElementException if queue is empty
	 * @return oldest element
	 */
	@Override
	public T remove() {
		if (n == 0)
			throw new NoSuchElementException();
		T x = backArray[head];
		head = (head + 1) % backArray.length;
		n--;
		if (backArray.length >= 3 * n)
			resize();
		return x;
	}

	/**
	 * Resize the backing array when the number of elements exceeds the array's size or when the
	 * number of elements stored decreases below a certain point.
	 */
	protected void resize() {
		T[] tempArray = arrayFactory.newArray(Math.max(1, n * 2));
		for (int index = 0; index < n; index++)
			tempArray[index] = backArray[(head + index) % backArray.length];
		backArray = tempArray;
		head = 0;
	}

	/**
	 * Attempts to add an element to the queue. Included for completeness. Not a strict
	 * implementation of offer since the method is not permitted to fail. A full queue situation is
	 * not allowed by this implementation of the AbstractQueue.
	 * 
	 * See: http://stackoverflow.com/questions/9343081/java-queues-why-poll-and-offer
	 * http://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
	 * 
	 * @return the element that was added
	 */
	@Override
	public boolean offer(T arg0) {
		return add(arg0);
	}

	/**
	 * Returns the next available element without removing it from the queue.
	 * 
	 * @return next available element in FIFO queue
	 */
	@Override
	public T peek() {
		T element = null;
		if (n > 0)
			element = backArray[head];
		return element;
	}

	/**
	 * Attempts to remove an element from the queue. Included for completeness.
	 * 
	 * See: http://stackoverflow.com/questions/9343081/java-queues-why-poll-and-offer
	 * http://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
	 * 
	 * @return next available element or null if queue is empty
	 */
	@Override
	public T poll() {
		try {
			return remove();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Included for completeness. Method not implemented.
	 */
	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return number of elements in the queue
	 */
	@Override
	public int size() {
		return n;
	}
}
