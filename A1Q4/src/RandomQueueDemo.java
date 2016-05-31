import java.rmi.UnexpectedException;
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

	// TODO confirm add(x) works, as expected
	// TODO confirm RandomQueue.remove() is random
	// TODO confirm that n = 0, n = 1 return appropriate error messages, if
	// applicable
	// TODO confirm add(x) and remove() run in constant time per operation

	/**
	 * Test the remove() function from the RandomQueue. Remove() randomly picks,
	 * removes and returns an element from the queue. The test removes one item
	 * from a queue of ten items and compares it with the last item added. When
	 * repeated enough times the probability of picking the last item should
	 * approach 0.10.
	 * 
	 * Repeat 10 additions and 1 remove 1_000_000 times.
	 * 
	 * @param theTester
	 */
	private static void confirmRandomRemove(TestSuite theTester) {

		// Create test fact tracker
		String[] details = { "Remove 10% Test", "",
				"Test the remove() function from the RandomQueue. Remove() randomly picks,",
				"removes and returns an element from the queue. The test removes one item",
				"from a queue of ten items and compares it with the last item added. When",
				"repeated enough times the probability of picking the last item should", "approach 0.10.", "",
				"Repeat 10 additions and 1 remove 1_000_000 times." };

		// Display general information
		System.out.println();
		CommonSuite.printArray(details);

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
		System.out.println("Expected: 0.09 < p < 0.11; Observed: " + observed + " ... " + isExpected);

		// Collect test results
		if (theTester.isTesting()) {
			theTester.isValidTestOutput(TestSuite.ObtainTestInput.obtainComment(), "This is program input",
					TestSuite.ObtainTestInput.obtainExpectedOutput(), "", false,
					TestSuite.ObtainTestInput.obtainStage());
		}

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
	 * @throws UnexpectedException
	 */
	private static void remove10to1Test(long tries, ArrayQueue list, int removeDivisor) throws UnexpectedException {
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
		throw new UnexpectedException("asdf");
	}

	/**
	 * Create an ArrayQueue of size arraySize.
	 *
	 * @param arraySize
	 *            number of repetitions to subject remove() to
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

	private static void confirmConstantTime(ArrayQueue<Integer> list2, int incrementRepetitions) {

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
			System.out.println(i + ", " + removeArrayQBins[i][reps / 2] + ", " + removeArrayAddQBins[i][reps / 2]);
		}
	}

	public static void main(String[] args) {
		// Are we testing?
		boolean testing = true;

		// Display programmer info and create testing object
		TestSuite theTester = CommonSuite.commonProgramStart("Eric Dunbar", "3243614", 1, 4, "RandomQueue", testing);

		// Display tasks
		String[] tasksList = { "TASKS:", "1. confirm add(x) works, as expected",
				"2. confirm RandomQueue.remove() is random",
				"3. confirm that n = 0, n = 1 return appropriate error messages, if applicable",
				"4. confirm add(x) and remove() run in constant time per operation",
				"5. confirm the tail element is moved into the newly emptied index position"};

		CommonSuite.printArray(tasksList);

		// perform the tasks required by the question

		// TASK 1. add(x) works
		
		// TASK 2. remove() is truly random
		
		confirmRandomRemove(theTester);

		// TASK 3. n = 0 and n = 1 perform as expected
		
		// TASK 4. add(x) and remove() run in constant time
		ArrayQueue<Integer> lister = new ArrayQueue<>(Integer.class);
		confirmConstantTime(lister, 350_000);

		RandomQueue<Integer> lister2 = new RandomQueue<>(Integer.class);
		confirmConstantTime(lister2, (int) Math.pow(2, 18));


		// Thank the user
		System.out.println();
		System.out.println("The demonstration is complete.");
		System.out.println();
		System.out.println("Press enter to continue");
		CommonSuite.getTextInput(); // discard input
		System.out.println();

		// Display testing results, if applicable
		CommonSuite.commonProgramEnd(theTester);

	}

}
