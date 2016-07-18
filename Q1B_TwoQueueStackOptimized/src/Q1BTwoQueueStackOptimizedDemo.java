/**
 * @author Eric Dunbar
 * @date 18/7/2016
 * @assignment 1
 * @question 1b
 * @title Optimized Two Queue Stack Demonstration
 * @description Demonstrate the implementation of a two queue-backed stack for assignment 1,
 *              question 1 b.
 */
public class Q1BTwoQueueStackOptimizedDemo {
	static TestSuite theTester;

	/**
	 * Confirm that a single queue works properly.
	 */
	private static void OneQueueTest() {
		System.out.println("START One queue only");
		System.out.println();
		System.out.println("Confirm operation of an optimized single queue and");
		System.out.println("check error handling of remove() from empty queue.");

		System.out.println();

		ArrayQueueOptimized<Integer> mine = new ArrayQueueOptimized<Integer>(Integer.class);

		Integer[] integers = { 5, 10, 15, 20, 25, 30, 45 };

		for (Integer integer : integers) {
			mine.add(integer);
			System.out.printf("add(%d), size = %d%n", integer, mine.size());
		}

		System.out.println();

		try {
			int size = mine.size();
			for (int i = 0; i < size / 2 + 1; i++) {
				System.out.printf("removeLast() = %d, removeFirst() = %d%n", mine.removeLast(), mine.removeFirst());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		System.out.println();
		System.out.println("END   One queue only");
	}

	/**
	 * Demonstrate that push and pop work for a two queue stack.
	 */
	private static void PushPopTest() {
		System.out.println("START PushPop Testing");
		System.out.println();
		System.out.println("BEGIN: Demonstration of push(x).");

		System.out.println();
		System.out.println("Use an optimized backing array queue? y/n?");

		boolean optimizedArray = CommonSuite.getBooleanInput();
		System.out.println();
		System.out.println("Perform timing tests? y/n?");

		boolean showTimer = CommonSuite.getBooleanInput();

		System.out.println();
		System.out.println("  Run for how many rounds (100+ to 50000 suggested)?");
		int arraySize = CommonSuite.getIntegerInput();

		System.out.println();
		System.out.println("qUnused and qUsed are the two queues used inside the stack.");
		System.out.println();
		System.out.println("Task: Create stack");

		TwoQueueStackFlexible<Integer> demoStack = new TwoQueueStackFlexible<>(Integer.class, optimizedArray);
		System.out.println();

		if (showTimer) {
			CommonSuite.StopWatch.start();
			for (int i = 0; i < arraySize; i++) {
				if (i % (arraySize / 50) == 0)
					System.out.println(i + ", " + CommonSuite.StopWatch.lap());
				demoStack.push(88 * i);
			}
		} else {
			for (int i = 0; i < arraySize; i++) {
				System.out.printf("%4d: push(%s) ", i, "" + demoStack.push(88 * i));

				System.out.println(demoStack.toString());
			}
		}

		System.out.println();
		System.out.println("BEGIN: Demonstration of pop().");
		System.out.println();
		System.out.println("This is the full stack as returned by a sequence of pop() operations:");

		String prepend = "";
		System.out.print("{");
		for (int i = 0; i < arraySize; i++) {
			try {
				System.out.print(prepend + demoStack.pop());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			prepend = ", ";
		}
		System.out.println("}");
		System.out.println("END   PushPop Testing");
	}

	/**
	 * Perform testing of two queue-backed stacks.
	 * @param args
	 */
	public static void main(String[] args) {
		// Are we testing?
		boolean testing = false;

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart("1", "1b", "Two Queue-backed Stack Demo", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. One queue test", "2. Push-pop demo" };

		int currentTask = 0;

		System.out.println("TASKS:");
		CommonSuite.printlnIndentArray(tasksList);

		// perform the tasks required by the question
		// TASK 1.

		CommonSuite.headerPrint(tasksList, currentTask++);
		OneQueueTest();

		// perform the tasks required by the question
		// TASK 2.

		CommonSuite.headerPrint(tasksList, currentTask++);
		PushPopTest();

		CommonSuite.commonProgramEnd(theTester);
	}
}