import java.lang.reflect.Array;

/**
 * @author Eric Dunbar
 * @date 28/4/2016
 * @assignment 1
 * @question 1
 * @title Array Queue
 * @description   To implement a simple, unoptimized FIFO queue using an
 * array. Source: Open Data Structures, Chapter 2

 * @param <T>
 */
public class ArrayQueue<T> {
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
	 * @param theQT
	 *            type of elements stored in the ArrayQueue
	 */
	public ArrayQueue(Class<T> theQT) {// added from Factory.java
		TestingSupport.methodInfo("Class<T> " + theQT);
		theType = theQT;
		array = this.newArray(1);
	}

	/**
	 * Reports the number of elements contained by the ArrayQueue.
	 * 
	 * @return Number of elements in the ArrayQueue
	 */
	int size() {
		return n;
	}

	/**
	 * Returns the element at the index requested. Ought to throw an exception if
	 * IndexOutOfBounds
	 * 
	 * @param i
	 *            index position
	 * @return element at index position
	 */
	public T get(int i) {

		// TODO check range
		// TODO throw exception IndexOutOfBounds?
		return array[i];
	}

	/**
	 * Sets the element at the index. Returns the old element stored at that index.
	 * 
	 * @param i
	 *            index in ArrayQueue
	 * @param x
	 *            element to store in ArrayQueue
	 * @return old element stored at the index position
	 */
	public T set(int i, T x) {
		// TODO check range
		T y = array[i];
		array[i] = x;
		return y;
	}

	/**
	 * Inserts the element at the requested index position.
	 * 
	 * @param i
	 * @param x
	 */
	public void add(int i, T x) {
		TestingSupport.methodInfo(x.toString());
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
	 */
	public void add(T x) {
		addFirst(x);
	}

	public void addFirst(T x) {
		add(0, x);
	}

	public T remove(int i) {
		T x = array[i];
		for (int j = i; j < n - 1; j++)
			array[j] = array[j + 1];
		n--;
		if (array.length >= 3 * n)
			resize();
		TestingSupport.methodInfo("[i = " + i + ", x = " + x.toString() + "]");
		return x;
	}

	/**
	 * In a queue removes and returns the last item from the tail of the queue.
	 * 
	 * @return
	 */
	public T remove() {
		return removeLast();
	}

	public T removeLast() {
		return remove(n - 1);
	}

	public T removeFirst() {
		return remove(0);
	}

	// added from Factory.java
	/**
	 * Allocate a new array of objects of type T.
	 * 
	 * @param n
	 *            the size of the array to allocate
	 * @return the array allocated
	 */
	@SuppressWarnings({ "unchecked" })
	private T[] newArray(int n) {
		// Modify protected as the access level
		// https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
		TestingSupport.methodInfo(String.format("n = %d", n));
		return (T[]) Array.newInstance(theType, n);
	}

	public void resize() {
		// modified by adding code from Factory.java
		T[] b = newArray(Math.max(n * 2, 1));
		for (int i = 0; i < n; i++) {
			b[i] = array[i];
		}
		array = b;
	}

}
