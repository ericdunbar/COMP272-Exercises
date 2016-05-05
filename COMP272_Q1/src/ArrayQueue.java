
/**
 * Class: ArrayQueue Purpose: To implement a simple, unoptimized FIFO queue
 * using an array. Source: Open Data Structures, Chapter 2
 * 
 * @author erdun
 *
 * @param <T>
 */
public class ArrayQueue<T> {
	T[] array; // the backing array
	int n; // the number of elements in the array

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
		if (n == 0) {
			throw new IndexOutOfBoundsException();
		}
		return remove(n - 1);
	}

	public void resize() {
		T[] b = newArray(max(n * 2, 1));
		for (int i = 0; i < n; i++) {
			b[i] = array[i];
		}
		array = b;
	}

}
