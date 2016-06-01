import java.util.NoSuchElementException;

/*
 * A1Q4. Exercise 2.3. Design and implement a RandomQueue. This is an implementation of
 * the Queue interface in which the remove() operation removes an element that is chosen
 * uniformly at random among all the elements currently in the queue. (Think of a
 * RandomQueue as a bag in which we can add elements or reach in and blindly remove some
 * random element.) The add(x) and remove() operations in a RandomQueue should run in
 * constant time per operation.
 */

/**
 * A queue in which the remove() operation removes an element that is chosen uniformly and
 * at random among all the elements currently in the queue.
 * 
 * @author Eric D.
 *
 * @param <T> type
 */
public class RandomQueue<T> extends ArrayQueue<T> {

	public RandomQueue(Class<T> t) {
		super(t);
	}

	/**
	 * Randomly removes and returns an element from the queue. This is not standard
	 * behaviour for a remove() method in a queue.
	 * 
	 * @return element
	 */
	@Override
	public T remove() {
		if (n == 0)
			throw new NoSuchElementException();
		int rndIndex = (int) (Math.random() * n);
		T randomElement = get(rndIndex);
		T tailElement = get(head + n - 1);
		set(rndIndex, tailElement); // move tail to newly vacated index
		n--;
		if (backArray.length >= 3 * n)
			resize();
		return randomElement;
	}
}
