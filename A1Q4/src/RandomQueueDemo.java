import java.util.Arrays;

/**
 * Demonstrates operation of RandomQueue for COMP272.
 * 
 * Expectations: 1. remove() removes and returns an element, chosen at random;
 * 2. add(x) and remove() run in constant time per operation.
 * 
 * A1Q4. Exercise 2.3. Design and implement a RandomQueue. This is an
 * implementation of the Queue interface in which the remove() operation removes
 * an element that is chosen uniformly at random among all the elements
 * currently in the queue. (Think of a RandomQueue as a bag in which we can add
 * elements or reach in and blindly remove some random element.) The add(x) and
 * remove() operations in a RandomQueue should run in constant time per
 * operation.
 * 
 * @author Eric Dunbar
 *
 */
public class RandomQueueDemo {

	//TODO confirm add(x) works, as expected
	//TODO confirm RandomQueue.remove() is random
	//TODO confirm that n = 0, n = 1 return appropriate error messages, if applicable
	//TODO confirm add(x) and remove() run in constant time per operation
	
	/**
	 * Test the remove() function from the RandomQueue. Remove() randomly picks,
	 * removes and returns an element from the queue. The test removes one item
	 * from a queue of ten items and compares it with the last item added. When
	 * repeated enough times the probability of picking the last item should
	 * approach 0.10.
	 * 
	 * Repeat 10 additions and 1 remove 1_000_000 times.
	 */
	private static void removeTenPercentTest() {

		String title = ("Remove 10% Test");
		String[] details = {
				"Test the remove() function from the RandomQueue. Remove() randomly picks,",
				"removes and returns an element from the queue. The test removes one item",
				"from a queue of ten items and compares it with the last item added. When",
				"repeated enough times the probability of picking the last item should",
				"approach 0.10.", "", "Repeat 10 additions and 1 remove 1_000_000 times." };
		
		Support.printDescription(title, details);
		System.out.println();

		int hits = 0;
		int tries = 1_000_000;
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
	 * @param tries number of repetitions to subject remove() to
	 */
	private static void remove10to1Test(long tries, ArrayQueue list, int removeDivisor) {
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
	 * @param arraySize number of repetitions to subject remove() to
	 * @return
	 */
	private static void createFilledArrayQueue(ArrayQueue<Integer> returnList, int arraySize) {
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
	private static void removeTest(ArrayQueue<?> list, int testEvery, boolean isSilent) {
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

	private static void generateStats(ArrayQueue<Integer> list2, int incrementRepetitions) {

		int reps = 10;

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

		System.out.println("RandomQueue, remove(), add(x)");
		for (int i = 0; i < 10; i++) {
			Arrays.sort(removeArrayQBins[i]);
			Arrays.sort(removeArrayAddQBins[i]);
			System.out.println(i + ", " + removeArrayQBins[i][reps / 2] + ", "
					+ removeArrayAddQBins[i][reps / 2]);
		}
	}

	public static void main(String[] args) {

		removeTenPercentTest();
		// ArrayQueueED<Integer> lister = new ArrayQueueED<>(Integer.class);
		// generateStats(lister, 350_000);

		RandomQueue<Integer> lister2 = new RandomQueue<>(Integer.class);
		generateStats(lister2, (int) Math.pow(2, 18));

	}

}
