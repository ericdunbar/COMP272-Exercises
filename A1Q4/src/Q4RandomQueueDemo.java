import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Eric Dunbar
 * @date 7/7/2016
 * @assignment 1
 * @question 4
 * @title Random Queue Demonstration
 * @description Demonstrates operation of RandomQueue Queue Interface for
 *              assignment 1, question 4 in COMP272. RandomQueue expectations:
 *              1. remove() removes and returns an element, uniformly at random;
 *              2. add(x) and remove() run in constant time per operation.
 */
public class Q4RandomQueueDemo {

	// Class variables
	static TestSuite theTester; // track and display test and program info

	/**
	 * Test the randomness of RandomQueue.remove(). This test removes one item
	 * from a queue of ten items and compares it with the last item added. When
	 * repeated enough times the probability of picking the last item should
	 * approach 0.10 if random() is truly random.
	 * 
	 * @param theTester
	 */
	private static void taskIsRemoveRandom() {
		int hits = 0;
		int tries = 1_000_000;
		int listSize = 10;
		String[] details = { "Confirm remove() is random", "",
				"Test whether remove() randomly picks, removes and returns ",
				"an element from the queue. The test removes one item",
				"from a queue of ten items and compares it with the last item added. When",
				"repeated enough times the probability of picking the last item should", "approach 0.10.", "",
				"Repeat " + listSize + " additions and 1 remove " + tries + " times." };

		// Display general information
		System.out.println();
		CommonSuite.printlnIndentArray(details);

		// Perform testing of random() to see randomness of pick
		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
			for (int i = 0; i < listSize; i++) {
				list.add(i);
			}
			if (list.remove() == listSize - 1)
				hits++;
		}
		double observed = (double) hits / tries; // p remove() last element
		boolean isExpected = (0.09 < observed) && (observed < 0.11);

		String descriptionS = "Does remove() randomly pick elements?";
		String inputS = "10 add(x), followed by 1 remove()";

		String expectedS = "0.09 < p < 0.11";
		String observedS = "p = " + observed;

		// Record test results
		theTester.recordTestKnownResult(descriptionS, inputS, expectedS, observedS, isExpected);
	}

	private static void taskIsRemoveUniform() {
		String[] background = { "Confirm remove() is uniformly random", "",
				"Confirm whether the remove() function is uniformly random.",
				"From a queue filled with a series of numbers remove() 10% of those",
				"elements. If the remove() function is uniformly random there should",
				"be an even distribution of elements removed and each bin should have",
				"similar numbers of the total of removed elements. Running this test on:",
				"(i) an ArrayQueueED should result in one full bin and the rest empty;",
				"and, (ii) a RandomQueue should result each bin having an equal", "share of the removed elements." };

		CommonSuite.printlnIndentArray(background);

		isUniformRemove(1_000_000, new ArrayQueue<Integer>(Integer.class), 20);
		isUniformRemove(1_000_000, new RandomQueue<Integer>(Integer.class), 20);
	}

	/**
	 * Is the remove() function is uniformly random. Fill a queue with elements.
	 * Remove() 10% of those elements. In a normal FIFO queue the smallest
	 * elements would be returned since they were first in (thus, first out). If
	 * the remove() function is uniformly random there should be an even
	 * distribution of all sizes of elements removed from each of the bins
	 * rather than one bin being filled. Running this method (i) on a queue of
	 * type ArrayQueueED should result in a single bin being filled and (ii) (i)
	 * on a queue of type RandomQueue should result each bin having an even
	 * distribution of elements.
	 *
	 * @param tries
	 *            number of repetitions to subject remove() to
	 * @param numBins
	 *            number of bins to use to capture elements
	 * @param list
	 *            the queue to use for testing, either RandomQueue or ArrayQueue
	 */
	private static boolean isUniformRemove(int tries, ArrayQueue<Integer> list, int numBins) {
		boolean uniformRemove;
		int[] counts = new int[numBins];

		int divisor = tries / numBins;

		fillQueueSequentially(list, tries);

		for (int i = 0; i < tries / numBins; i++) {
			counts[(int) ((int) list.remove() / divisor)]++;
		}

		int min = tries;
		int max = 0;
		int expectedPerBin = (int) (tries / numBins / numBins);
		for (int i = 0; i < numBins; i++) {
			min = (counts[i] < min) ? counts[i] : min;
			max = (counts[i] > max) ? counts[i] : max;
		}

		double minRatio = (double) min / expectedPerBin;
		double maxRatio = (double) max / expectedPerBin;

		// Note: to do this properly use variance rather than min/max
		String description = "Is random() uniformly random for " + list.getClass() + "?";
		String inputS = tries + " add(x), " + (int) tries / numBins + " remove(x)";
		String expected;

		if (list.getClass().toString().equals("class ArrayQueue")) {
			expected = "Bin counts: 0 or " + (tries / numBins);
			// all in one bin is maxRatio == numBins
			uniformRemove = (minRatio == 0) && ((int) maxRatio == numBins);
		} else {
			expected = "Bin counts: within 10% of " + expectedPerBin;
			uniformRemove = (minRatio > 0.9) && (maxRatio < 1.1);
		}
		String observed = "Bin counts: min = " + min + "; max = " + max;

		theTester.recordTestKnownResult(description, inputS, expected, observed, uniformRemove);

		return uniformRemove;
	}

	/**
	 * Create an ArrayQueue of size arraySize and fill it with a sequence of
	 * numbers.
	 *
	 * @param arraySize
	 *            number of repetitions to subject remove() to
	 */
	private static void fillQueueSequentially(ArrayQueue<Integer> list, int arraySize) {
		for (int outerIdx = 0; outerIdx < arraySize; outerIdx++)
			list.add(outerIdx);
	}

	/**
	 * Test the remove() function of a RandomQueue or ArrayQueue. Given a
	 * RandomQueue with a certain number of elements, if you then remove() 10%
	 * of those elements. In a normal FIFO queue remove() 10% of elements would
	 * return the smallest elements since they were first in (thus, first out).
	 * If the remove() function is indeed random there should be an even
	 * distribution of elements removed from each of the 10 bins rather than one
	 * bin being filled. Running this test on ArrayQueue should result in a
	 * single bin being filled.
	 * 
	 * @param list
	 *            an ArrayList with elements
	 * @param testEvery
	 *            how often to remove an element
	 * @param silent
	 *            false if output is printed
	 */
	private static void removeTest(ArrayQueue<?> list, int testEvery, boolean silent) {
		int[] counts = new int[10];
		final int numRepetitions = list.size() / testEvery;

		final int divisor = numRepetitions / 10;

		if (silent)
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

	/**
	 * Tests size(), add(x) and remove() for n = 0, n = 1 and n = 2 for
	 * RandomQueue.
	 */

	/**
	 * Do add(x) and remove() work for random queues of size = {0, 1, 2}?
	 */
	private static void taskSmallSizes() {
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);

		// request size of an empty queue
		theTester.recordTest("size() of an empty queue", "new RandomQueue<Integer>; .size();", "" + 0,
				"" + list.size());

		// perform remove operation on an empty queue
		String resultS;
		try {
			resultS = "" + list.remove();
		} catch (NoSuchElementException nE) {
			resultS = "NoSuchElementException";
		}
		theTester.recordTest("remove() on an empty queue", "remove()", "NoSuchElementException", resultS, true,
				TestSuite.TestStage.Stage4);

		// add 3 items to an empty queue and check size after each add operation
		for (int i = 0; i < 3; i++) {
			theTester.recordTest("add(" + (55 + i) + ") to queue of size = " + (0 + i), "add(" + (55 + i) + ");",
					"true, " + (55 + i), "" + list.add(55 + i) + ", " + list.get(i));
			theTester.recordTest("size() when queue size = " + (1 + i), "size();", "" + (1 + i), "" + list.size());
		}

		// remove 3 items from a queue with 3 items and check size afer each
		// remove operation
		for (int i = 0; i < 3; i++) {
			list.remove();
		}
		theTester.recordTest("size() after 3 add() & 3 remove() ops", "add(x); remove(); size();", "" + 0,
				"" + list.size());
	}

	/**
	 * Tests to see if the last element is moved from the tail into the newly
	 * vacated index position in the sequence.
	 */
	private final static void taskMoveLastElement() {
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
		int hits = 0;
		int tries = 100;
		int listSize = 10;
		String remainder = "";

		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			for (int i = 0; i < listSize; i++) {
				list.add(i);
			}

			int removed = (list.remove());

			StringBuilder sB = new StringBuilder();
			for (int i = 0; i < listSize - 1; i++) {
				sB.append(list.get(i));
			}
			sB.append(", remove() = " + removed);
			remainder = sB.toString();

			// could simplify the following with || instead of &&
			if (removed != 9 && remainder.charAt(removed) == '9') {
				hits++;
			}
			// System.out.println();
			for (int i = 0; i < listSize - 1; i++) {
				list.remove();
			}
		}

		double observed = (double) hits / tries;
		double lower = 0.85;
		double upper = 0.95;
		boolean isExpected = (observed > 0.85) && (observed < 0.95);

		String description = "Is tail element moved into empty position?";
		String inputS = tries + " repetitions of 10 add(x), 1 remove(x)";
		String expectedS = "" + lower + " < p < " + upper;
		String observedS = "p = " + (observed) + "; sample: " + remainder;

		theTester.recordTestKnownResult(description, inputS, expectedS, observedS, isExpected);
	}

	/**
	 * Generates statistics to determine whether remove() and add(x) run in
	 * constant time.
	 */
	private static void taskConstantTime() {
		RandomQueue<Integer> lister2 = new RandomQueue<>(Integer.class);
		runsInConstantTime(lister2, (int) Math.pow(2, 16), 10);
	}

	/**
	 * Generates statistics to determine whether remove() and add(x) run in
	 * constant time.
	 * 
	 * @param list
	 *            an ArrayQueue of type Integer
	 * @param incrementR
	 *            increment for each round
	 * @param reps
	 *            number of repetitions
	 */
	private static void runsInConstantTime(ArrayQueue<Integer> list, int incrementR, int reps) {
		// track time taken to perform a sequence of operations
		int[][] removeTimeBins = new int[10][reps];
		int[][] addTimeBins = new int[10][reps];

		// format output header
		System.out.println(CommonSuite.indentString(CommonSuite.stringRepeat("*", 57)));
		System.out.println(CommonSuite.indentString("* Table. Median time per operation in milliseconds (ms) *"));
		System.out.println(CommonSuite.indentString("*                                                       *"));
		System.out.println(CommonSuite.indentString("*    No. operations       add    remove                 *"));

		// run trials
		for (int r = incrementR; r < incrementR * 10 + 1; r += incrementR) {
			System.out.print(CommonSuite.indentString(String.format("*           %7d", r)));

			for (int i = 0; i < reps; i++) {
				CommonSuite.StopWatch.start();
				fillQueueSequentially(list, r);
				int aStop = (int) CommonSuite.StopWatch.stop();

				// record time for add(x) operations
				addTimeBins[(int) (r / incrementR - 1)][i] = aStop;

				CommonSuite.StopWatch.start();
				removeTest(list, 1, true);
				int bStop = (int) CommonSuite.StopWatch.stop();

				// record time for remove() operations
				removeTimeBins[(int) (r / incrementR - 1)][i] = bStop;
			}

			// summarize and print time taken for operations with the specified
			// number of repetitions, r
			Arrays.sort(removeTimeBins[(int) (r / incrementR - 1)]);
			Arrays.sort(addTimeBins[(int) (r / incrementR - 1)]);

			double medianAddTime = (addTimeBins[(int) (r / incrementR - 1)][(int) (reps / 2) - 1]
					+ addTimeBins[(int) (r / incrementR - 1)][(int) (reps / 2)]) / 2;
			double medianRemoveTime = (removeTimeBins[(int) (r / incrementR - 1)][(int) (reps / 2) - 1]
					+ removeTimeBins[(int) (r / incrementR - 1)][(int) (reps / 2)]) / 2;

			System.out.println(String.format("%10.1f%10.1f                 *", medianAddTime, medianRemoveTime));
		}

		// format output
		System.out.println(CommonSuite.indentString(CommonSuite.stringRepeat("*", 57)));
		System.out.println();

		theTester.recordTest("Do add(x) and remove() run in constant time?",
				"add(x), then remove() repeated " + incrementR + " to " + incrementR * 10 + " times.",
				"Linear response, R^2 > 0.9 when graphed", TestSuite.ObtainTestInput.obtainObservedOutput(),
				!theTester.isTesting(), TestSuite.TestStage.Stage2);
	}

	/**
	 * Performs all the testing for the RandomQueue class.
	 */
	private static void doTesting() {
		// Are we testing?
		boolean testing = true;

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart(1, 4, "RandomQueue", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. Do add(x) and remove() work for n = {0, 1, 2}?",
				"2. Is RandomQueue.remove() random?",
				"3. Is tail element moved into empty index position upon remove()?", "4. Is remove() uniformly random?",
				"5. Do add(x) and remove() run in constant time?" };

		int currentTask = 0;

		System.out.println("TASKS:");
		CommonSuite.printlnIndentArray(tasksList);

		// perform the tasks required by the question
		// TASK 1.

		CommonSuite.headerPrint(tasksList, currentTask++);
		taskSmallSizes();

		// TASK 2.

		CommonSuite.headerPrint(tasksList, currentTask++);
		taskIsRemoveRandom();

		// TASK 3.

		CommonSuite.headerPrint(tasksList, currentTask++);
		taskMoveLastElement();

		// TASK 4.
		CommonSuite.headerPrint(tasksList, currentTask++);
		taskIsRemoveUniform();

		// TASK 5.

		CommonSuite.headerPrint(tasksList, currentTask++);
		taskConstantTime();

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

	public static void main(String[] args) {
		// perform the demonstration
		doTesting();
	}
}