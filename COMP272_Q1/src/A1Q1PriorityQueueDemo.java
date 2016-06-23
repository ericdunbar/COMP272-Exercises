
/**
 * Demonstrate the implementation of a priority queue for assignment 1, question
 * 1 b.
 * 
 * @author Eric Dunbar
 *
 */
public class A1Q1PriorityQueueDemo {

	static SLList<String> myPriorityQueue = new SLList<>();
	static TestSuite theTester;

	/**
	 * Demo the add() and size() methods for a PriorityQueue based on a
	 * singly-linked list for assignment 1, question 1 b in COMP272.
	 */
	private static void addElementsDemo() {
		String[] ordinals = { "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth",
				"Tenth", "first" };

		String[] description = { "The elements are not ordered alphabetically when",
				"initially added to PriorityQueue.", "" };
		System.out.println();
		for (String string : description) {
			System.out.println(string);
		}

		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < ordinals.length; i++) {
				String s = ordinals[i] + (1 - j);
				System.out.printf("size = %4d;  add(\"%s\") = %s; size = %4d;%n", myPriorityQueue.size(), s,
						myPriorityQueue.add(s), myPriorityQueue.size());
			}
		}

	}

	/**
	 * Demo the deleteMin() method for a PriorityQueue based on a singly-linked
	 * list for assignment 1, question 1 b in COMP272.
	 */
	private static void deleteMinDemo() {

		String[] description = { "The elements should be returned in ascending order by deleteMin()." };
		System.out.println();
		for (String string : description) {
			System.out.println(string);
		}

		System.out.println();
		while (myPriorityQueue.size() > 0) {
			System.out.printf("size() = %5d, deleteMin() = %14s, size() = %5d;%n", myPriorityQueue.size(),
					myPriorityQueue.deleteMin(), myPriorityQueue.size());
		}

	}

	/**
	 * Demo what happens when deleteMin() method is performed on a PriorityQueue
	 * that's been emptied and one that's been newly declared.
	 * 
	 */
	private static void deleteOnEmpty() {
		String[] description = { "", "deleteMin() on empty PriorityQueue:", "" };
		System.out.println();
		for (String string : description) {
			System.out.println(string);
		}

		System.out.printf("size = %5d;  deleteMin() = %s; size = %5d;%n", myPriorityQueue.size(),
				myPriorityQueue.deleteMin(), myPriorityQueue.size());

		description = new String[] { "", "deleteMin() on newly created PriorityQueue:", "" };
		System.out.println();
		for (String string : description) {
			System.out.println(string);
		}
		SLList<String> novel = new SLList<>();
		System.out.printf("size = %5d;  deleteMin() = %s; size = %5d;%n", novel.size(), novel.deleteMin(),
				novel.size());
	}

	public static void main(String[] args) {
		// Are we testing?
		boolean testing = false;

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart(1, 1, "PriorityQueue Demo", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. Do add(x) and size() perform as expected?",
				"2. Does deleteMin() return the minimum element?",
				"3. Does the PriorityQueue perform as expected when empty?" };

		int currentTask = 0;

		System.out.println("TASKS:");
		CommonSuite.printlnIndentArray(tasksList);

		// perform the tasks required by the question
		// TASK 1.

		CommonSuite.headerPrint(tasksList, currentTask++);
		addElementsDemo();

		// perform the tasks required by the question
		// TASK 2.

		CommonSuite.headerPrint(tasksList, currentTask++);
		deleteMinDemo();

		// perform the tasks required by the question
		// TASK 3.

		CommonSuite.headerPrint(tasksList, currentTask++);
		deleteOnEmpty();
		
		CommonSuite.commonProgramEnd(theTester);

	}

}
