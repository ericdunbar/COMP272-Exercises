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
 * @param <T>
 *            type
 */
public class RandomQueue<T> extends ArrayQueueED<T> {

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
		T randomElement = backArray[(head + rndIndex) % this.backArray.length];
		// move tail to current position
		backArray[(head + rndIndex) % this.backArray.length] = backArray[head + n - 1];
		n--;
		if (backArray.length >= 3 * n)
			resize();
		return randomElement;
	}

	/**
	 * Test the remove() function from the RandomQueue. Remove() randomly picks, removes
	 * and returns an element from the queue. The test removes one item from a queue of
	 * ten items and compares it with the last item added. When repeated enough times the
	 * probability of picking the last item should approach 0.10.
	 * 
	 * Repeat 10 additions and 1 remove 100_000_000 times.
	 */
	private static void removeTenPercentTest() {
		int hits = 0;
		int tries = 100_000_000;
		int listSize = 10;
		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
			for (int i = 0; i < listSize; i++) {
				list.add(i);
			}
			if (list.remove() == listSize - 1)
				hits++;
		}
		double observed = (double) hits / tries;
		boolean isExpected = (0.09 < observed) && (observed < 0.11);
		System.out
				.println("Expected: 0.09 < p < 0.11; Observed: " + observed + " ... " + isExpected);
	}

	/**
	 * Test the remove() function from the RandomQueue. Construct a RandomQueue with tries
	 * number of elements. Then remove() 10% of those elements. In a normal FIFO queue
	 * remove() 10% of elements would return the smallest elements since they were first
	 * in (thus, first out). If the remove() function is indeed random there should be an
	 * even distribution of elements removed from each of the 10 bins rather than one bin
	 * being filled. Running this test on ArrayQueueED should result in a single bin being
	 * filled
	 * 
	 * Repeat 10 additions and 1 remove tries times.
	 *
	 * @param tries
	 *            number of repetitions to subject remove() to
	 */
	private static void remove10to1Test(long tries, ArrayQueueED list) {
		int[] counts = new int[10];
		final long divisor = tries / 10;
		// RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);

		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			list.add(outerIdx);
		}

		for (int i = 0; i < tries / 10; i++) {
			counts[(int) ((int) list.remove() / divisor)]++;
		}

		for (int i = 0; i < 10; i++) {
			//TODO Uncomment
			//System.out.println("  " + i + ": " + counts[i]);
		}
	}

	public static void main(String[] args) {
		// removeTenPercentTest();

		long repetitions;

		for (repetitions = 100_000; repetitions < 1_000_001; repetitions += 100_000) {

			System.out.println("***************************");
			System.out.println("Repetitions: " + repetitions);
			System.out.println("***************************");

			System.out.println("RandomQueue distribution...");
			long startTime = System.currentTimeMillis();
			RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
			remove10to1Test(repetitions, list);
			long endTime = System.currentTimeMillis();
			System.out.println("Took: " + (endTime - startTime));
			System.out.println();
			System.out.println("ArrayQueueED distribution...");
			startTime = System.currentTimeMillis();
			ArrayQueueED<Integer> list2 = new ArrayQueueED<Integer>(Integer.class);
			remove10to1Test(repetitions, list2);
			endTime = System.currentTimeMillis();
			System.out.println("Took: " + (endTime - startTime));
		}
	}

}
