import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Iterator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Eric Dunbar
 * @date 18/7/2016
 * @assignment 1
 * @question 1
 * @title Array Queue
 * @description To implement a simple, unoptimized FIFO queue using an array. Source: Open Data
 *              Structures, Chapter 2
 * 
 * @param <T>
 */
public class ArrayQueueSimple<T> extends AbstractQueue<T> {
	T[] array; // the backing array
	int n = 0; // the number of elements in the array
	Class<T> theType; // added from Factory.java

	/**
	 * Returns the contents of the ArrayQueue as a String
	 */
	public String toString() {
		String toReturn = "{";
		String comma = "";
		for (int i = 0; i < n; i++) {
			toReturn += comma + array[i].toString();
			comma = ",";
		}
		toReturn += "}";
		return toReturn;
	}

	/**
	 * Constructs a new ArrayQueue of the type passed to it.
	 * 
	 * @param theQT type of elements stored in the ArrayQueue
	 */
	public ArrayQueueSimple(Class<T> theQT) {// added from Factory.java
		CollectMethodInfo.methodInfo("Class<T> " + theQT);
		theType = theQT;
		array = this.newArray(1);
	}

	/**
	 * Reports the number of elements contained by the ArrayQueue.
	 * 
	 * @return Number of elements in the ArrayQueue
	 */
	public int size() {
		return n;
	}

	/**
	 * Returns the element at the index requested. Ought to throw an exception if IndexOutOfBounds
	 * 
	 * @param i index position
	 * @return element at index position
	 */
	public T get(int i) {
		if (i < 0 || i > n - 1) {
			throw new IndexOutOfBoundsException();
		}
		return array[i];
	}

	/**
	 * Sets the element at the index. Returns the old element stored at that index.
	 * 
	 * @param i index in ArrayQueue
	 * @param x element to store in ArrayQueue
	 * @return old element stored at the index position
	 */
	public T set(int i, T x) {
		if (i < 0 || i > n - 1) {
			throw new IndexOutOfBoundsException();
		}
		T y = array[i];
		array[i] = x;
		return y;
	}

	/**
	 * Inserts the element at the requested index position.
	 * 
	 * @param i index position
	 * @param x element
	 */
	public void add(int i, T x) {
		CollectMethodInfo.methodInfo(x.toString());
		if (n + 1 > array.length)
			resize();
		for (int j = n; j > i; j--)
			array[j] = array[j - 1];
		array[i] = x;
		n++;
	}

	/**
	 * In a queue adds an item to the head of the queue.
	 * 
	 * @param x
	 * @return
	 */
	@Override
	public boolean add(T x) {
		addLast(x);
		return true;
	}

	public void addFirst(T x) {
		add(0, x);
	}

	public void addLast(T x) {
		add(n, x);
	}

	public T remove(int i) {
		T x = array[i];
		for (int j = i; j < n - 1; j++)
			array[j] = array[j + 1];
		n--;
		if (array.length >= 3 * n)
			resize();
		CollectMethodInfo.methodInfo("[i = " + i + ", x = " + x.toString() + "]");
		return x;
	}

	/**
	 * In a queue removes and returns the last item from the tail of the queue.
	 * 
	 * @return
	 */
	@Override
	public T remove() {
		return removeFirst();
	}

	/**
	 * Removes the last (tail) element of a queue.
	 * 
	 * @return element
	 */
	public T removeLast() {
		return remove(n - 1);
	}

	/**
	 * Removes the first (head) element of a queue.
	 * 
	 * @return element
	 */
	public T removeFirst() {
		return remove(0);
	}

	// added from Factory.java
	/**
	 * Allocate a new array of objects of type T.
	 * 
	 * @param n the size of the array to allocate
	 * @return the array allocated
	 */
	@SuppressWarnings({ "unchecked" })
	private T[] newArray(int n) {
		// Modify protected as the access level
		// https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
		CollectMethodInfo.methodInfo(String.format("n = %d", n));
		return (T[]) Array.newInstance(theType, n);
	}

	/**
	 * Resizes the backing array
	 */
	public void resize() {
		// modified by adding code from Factory.java
		T[] b = newArray(Math.max(n * 2, 1));
		for (int i = 0; i < n; i++) {
			b[i] = array[i];
		}
		array = b;
	}

	@Override
	public boolean offer(T e) {
		throw new NotImplementedException();
	}

	@Override
	public T peek() {
		throw new NotImplementedException();
	}

	@Override
	public T poll() {
		throw new NotImplementedException();
	}

	@Override
	public Iterator<T> iterator() {
		throw new NotImplementedException();
	}
}
