/**
 * Demonstrate the implementation of a two queue-backed stack for assignment 1,
 * question 1 b.
 * 
 * @author Eric Dunbar
 *
 */

public class A1Q1TwoQueueStackDemo {
	static TestSuite theTester;

	private static void OneQueueTest() {
		System.out.println("START One queue only");
		System.out.println();
		System.out.println("Confirm FIFO operation of a single queue.");
		System.out.println("Check error handling of remove on empty queue.");
		
		TestingSupport.setTesting(false);
		System.out.println();

		ArrayQueue<Integer> mine = new ArrayQueue<Integer>(Integer.class);

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

	private static void PushPopTest() {
		System.out.println("START PushPop Testing");
		System.out.println();
		System.out.println("Demonstration of push(x).");
		TestingSupport.setTesting(true);

		TwoQueueStack<Integer> demoStack = new TwoQueueStack<>(Integer.class);

		int arraySize = 15;
		for (int i = 0; i < arraySize; i++) {
			System.out.printf("Round %3d " , i);
			System.out.println(demoStack.toString());
			TestingSupport.methodInfo("push(" + demoStack.push(88 * i) + ");");
		}

		System.out.println();
		System.out.println("Demonstration of pop().");
		System.out.println();
		String prepend ="";
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

	public static void main(String[] args) {
		// Are we testing?
		boolean testing = false;

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart("1", "1b", "Two Queue-backed Stack Demo", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. OneQueueTest?", "2. PushPopTest?", "3. ...?" };

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

		// perform the tasks required by the question
		// TASK 3.

		CommonSuite.headerPrint(tasksList, currentTask++);

		CommonSuite.commonProgramEnd(theTester);

	}

}
