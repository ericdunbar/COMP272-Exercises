import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
 * A queue in which the remove() operation removes an element that is chosen
 * uniformly and at random among all the elements currently in the queue.
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
	 * Randomly removes and returns an element from the queue. This is not
	 * standard behaviour for a remove() method in a queue.
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
		
		// move tail to newly vacated index
		set(rndIndex, tailElement);

		n--;
		if (backArray.length >= 3 * n)
			resize();

		return randomElement;
	}

	public T get(int i) {
		if (i < 0 || i > n - 1)
			throw new IndexOutOfBoundsException();
		return backArray[(head + i) % backArray.length];
	}

	public T set(int i, T x) {
		if (i < 0 || i > n - 1)
			throw new IndexOutOfBoundsException();
		int index = (head + i) % backArray.length;
		T y = backArray[index];
		backArray[index] = x;
		return y;
	}

	/**
	 * Test the remove() function from the RandomQueue. Remove() randomly picks,
	 * removes and returns an element from the queue. The test removes one item
	 * from a queue of ten items and compares it with the last item added. When
	 * repeated enough times the probability of picking the last item should
	 * approach 0.10.
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
		System.out.println("Expected: 0.09 < p < 0.11; Observed: " + observed + " ... " + isExpected);
	}

	/**
	 * Test the remove() function from the RandomQueue. Construct a RandomQueue
	 * with tries number of elements. Then remove() 10% of those elements. In a
	 * normal FIFO queue remove() 10% of elements would return the smallest
	 * elements since they were first in (thus, first out). If the remove()
	 * function is indeed random there should be an even distribution of
	 * elements removed from each of the 10 bins rather than one bin being
	 * filled. Running this test on ArrayQueueED should result in a single bin
	 * being filled
	 * 
	 * Repeat 10 additions and 1 remove tries times.
	 *
	 * @param tries
	 *            number of repetitions to subject remove() to
	 */
	private static void remove10to1Test(long tries, ArrayQueueED list, int removeDivisor) {
		int[] counts = new int[10];
		final long divisor = tries / removeDivisor;
		// RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);

		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			list.add(outerIdx);
		}

		for (int i = 0; i < tries / removeDivisor; i++) {
			counts[(int) ((int) list.remove() / divisor)]++;
		}

		for (int i = 0; i < 10; i++) {
			// TODO Uncomment
			// System.out.println(" " + i + ", " + counts[i]);
		}
	}

	/**
	 * Create an ArrayQueue of size arraySize.
	 *
	 * @param arraySize
	 *            number of repetitions to subject remove() to
	 * @return
	 */
	private static void createFilledArrayQueue(ArrayQueueED<Integer> returnList, int arraySize) {
		for (int outerIdx = 0; outerIdx < arraySize; outerIdx++)
			returnList.add(outerIdx);
	}

	/**
	 * @formatter:off
	 * 
	 * 				Test the remove() function of a RandomQueue or ArrayQueue.
	 *                Given a RandomQueue with a certain number of elements, if
	 *                you then remove() 10% of those elements. In a normal FIFO
	 *                queue remove() 10% of elements would return the smallest
	 *                elements since they were first in (thus, first out). If
	 *                the remove() function is indeed random there should be an
	 *                even distribution of elements removed from each of the 10
	 *                bins rather than one bin being filled. Running this test
	 *                on ArrayQueue should result in a single bin being filled.
	 *
	 * @param list
	 *            an ArrayList with elements
	 * @param testEvery
	 *            how often to remove an element
	 * @param isSilent
	 *            false if output is printed
	 * 
	 * @formatter:on
	 */
	private static void removeTest(ArrayQueueED list, int testEvery, boolean isSilent) {
		int[] counts = new int[10];
		final int numRepetitions = list.size() / testEvery;

		final int divisor = numRepetitions / 10;

		if (isSilent)
			for (int i = 0; i < numRepetitions; i++)
				list.remove();
		else
			for (int i = 0; i < numRepetitions; i++) {
				counts[(int) list.remove() / divisor]++;
				for (int j = 0; j < 10; j++) {
					System.out.println(" " + j + ": " + counts[j]);
				}
			}
	}

	private static class timerED {
		private static long startTime;

		public static void start() {
			startTime = System.currentTimeMillis();
		}

		public static int stop() {
			return (int) (System.currentTimeMillis() - startTime);
		}
	}

	private static void generateStats(ArrayQueueED list2, int incrementRepetitions) {

		int reps = 50;

		int[][] removeArrayQBins = new int[10][reps];
		int[][] removeArrayAddQBins = new int[10][reps];

		for (int repetitions = incrementRepetitions; repetitions < incrementRepetitions * 10
				+ 1; repetitions += incrementRepetitions) {
			System.out.println();
			System.out.println("***************************");
			System.out.println("Repetitions: " + repetitions);
			System.out.println("***************************");

			for (int i = 0; i < reps; i++) {
				timerED.start();
				;
				createFilledArrayQueue(list2, repetitions);
				int aStop = timerED.stop();

				removeArrayAddQBins[(int) (repetitions / incrementRepetitions - 1)][i] = aStop;

				timerED.start();
				removeTest(list2, 1, true);
				int bStop = timerED.stop();

				removeArrayQBins[(int) (repetitions / incrementRepetitions - 1)][i] = bStop;

				System.out.println("  add(x) = " + aStop + "  remove() = " + bStop);
			}
		}

		System.out.println("RandomQueue remove() summary");
		for (int i = 0; i < 10; i++) {
			Arrays.sort(removeArrayQBins[i]);
			System.out.println(i + ", " + removeArrayQBins[i][reps / 2]);
		}

		System.out.println("RandomQueue add() summary");
		for (int i = 0; i < 10; i++) {
			Arrays.sort(removeArrayAddQBins[i]);
			System.out.println(i + ", " + removeArrayAddQBins[i][reps / 2]);
		}
	}

	public static void main(String[] args) {
		// removeTenPercentTest();
		// ArrayQueueED<Integer> lister = new ArrayQueueED<>(Integer.class);
		// generateStats(lister, 350_000);

		RandomQueue<Integer> lister2 = new RandomQueue<>(Integer.class);
		generateStats(lister2, 350_000);

	}

}
