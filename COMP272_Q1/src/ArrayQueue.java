import java.lang.reflect.Array;

/**
 * Class: ArrayQueue Purpose: To implement a simple, unoptimized FIFO queue
 * using an array. Source: Open Data Structures, Chapter 2
 * 
 * @author Eric Dunbar
 *
 * @param <T>
 */
public class ArrayQueue<T> {
	T[] array; // the backing array
	int n = 0; // the number of elements in the array
	Class<T> theType; // added from Factory.java

	public String toString(){
		String toReturn = "{";
		String comma = "";
		for (int i = 0; i < n; i++) {
			toReturn += comma + array[i].toString();
			comma = ",";
		}
		toReturn += "}";
		return toReturn;
	}
	public ArrayQueue(Class<T> theQT) {// added from Factory.java
		TestingSupport.methodInfo("Class<T> " + theQT);
		theType = theQT;
		array = this.newArray(1);
	}

	int size() {
		return n;
	}

	public T get(int i) {

		// TODO check range
		return array[i];
	}

	public T set(int i, T x) {
		// TODO check range
		T y = array[i];
		array[i] = x;
		return y;
	}

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
