import static org.junit.Assert.assertEquals;

import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

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

	// Class variables
	static TestSuite theTester;

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
		CommonSuite.printIndentedArray(details);

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
		System.out.println(
				CommonSuite.indentString("Expected: 0.09 < p < 0.11; Observed: " + observed + " O==E? " + isExpected));

		String expectedS, observedS;
		if (isExpected)
			observedS = expectedS = "0.09 < p < 0.11, p = " + observed;

		else {
			expectedS = "0.09 < p < 0.11";
			observedS = "p = " + observed;
		}

		// Collect test results
		theTester.isValidTestOutput("Does remove() randomly pick elements?", "10 add(x), followed by 1 remove()",
				expectedS, observedS);
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

	private static class StopWatch {
		private static long startTime;

		public static void start() {
			startTime = System.currentTimeMillis();
		}

		public static long stop() {
			return (System.currentTimeMillis() - startTime);
		}
	}

	/**
	 * Generates statistics to determine whether remove() and add(x) run in
	 * constant time.
	 * 
	 * @param list2
	 *            an ArrayQueue of type Integer
	 * @param incrementR
	 *            increment for each round
	 * @param reps
	 *            number of repetitions
	 */
	private static void runsInConstantTime(ArrayQueue<Integer> list2, int incrementR, int reps) {
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
				StopWatch.start();
				createFilledArrayQueue(list2, r);
				int aStop = (int) StopWatch.stop();

				// record time for add(x) operations
				addTimeBins[(int) (r / incrementR - 1)][i] = aStop;

				StopWatch.start();
				removeTest(list2, 1, true);
				int bStop = (int) StopWatch.stop();

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
	}

	/**
	 * Tests size(), add(x) and remove() for n = 0, n = 1 and n = 2 for
	 * RandomQueue.
	 */

	private static void testSmallSizes() {
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);

		// request size of an empty queue
		theTester.isValidTestOutput("size() of an empty queue", "new RandomQueue<Integer>; .size();", "" + 0,
				"" + list.size());

		// perform remove operation on an empty queue
		String resultS;
		try {
			resultS = "" + list.remove();
		} catch (NoSuchElementException nE) {
			resultS = "NoSuchElementException";
		}
		theTester.isValidTestOutput("remove() on an empty queue", "remove()", "NoSuchElementException", resultS, true,
				TestSuite.TestStage.Stage4);

		// add 3 items to an empty queue and check size after each add operation
		for (int i = 0; i < 3; i++) {
			theTester.isValidTestOutput("add(" + (55 + i) + ") to queue of size = " + (0 + i), "add(" + (55 + i) + ");",
					"true, " + (55 + i), "" + list.add(55 + i) + ", " + list.get(i));
			theTester.isValidTestOutput("size() when queue size = " + (1 + i), "size();", "" + (1 + i),
					"" + list.size());
		}

		// remove 3 items from a queue with 3 items and check size afer each
		// remove operation
		for (int i = 0; i < 3; i++) {
			list.remove();
		}
		theTester.isValidTestOutput("size() after 3 add() & 3 remove() ops", "remove(); remove(); remove();", "" + 0,
				"" + list.size());
	}

	private static void headerPrint(String[] tasksList, int currentTask) {
		System.out.println();
		System.out.println("============================================================");
		System.out.println(tasksList[currentTask]);
		System.out.println("============================================================");
		System.out.println();

	}

	public static void main(String[] args) {
		// Are we testing?
		boolean testing = true;

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart(1, 4, "RandomQueue", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. do add(x) and remove() work for n = {0, 1, 2}", "2. is RandomQueue.remove() random",
				"3. is tail element moved into empty index position",
				"4. do add(x) and remove() run in constant time" };

		int currentTask = 0;

		System.out.println("TASKS:");
		CommonSuite.printIndentedArray(tasksList);

		// perform the tasks required by the question

		// TASK 1.

		headerPrint(tasksList, currentTask++);
		testSmallSizes();

		// TASK 2.

		headerPrint(tasksList, currentTask++);
		confirmRandomRemove(theTester);

		// TASK 3.

		headerPrint(tasksList, currentTask++);
		// TODO task 3

		// TASK 4.
		// ArrayQueue<Integer> lister = new ArrayQueue<>(Integer.class);
		// runsInConstantTime(lister, 350_000);

		headerPrint(tasksList, currentTask++);
		RandomQueue<Integer> lister2 = new RandomQueue<>(Integer.class);
		runsInConstantTime(lister2, (int) Math.pow(2, 16), 10);

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
