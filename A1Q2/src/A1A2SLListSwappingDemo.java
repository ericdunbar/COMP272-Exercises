
public class A1A2SLListSwappingDemo {
	static TestSuite theTester;

	private static void swapNodeDemo() {
		SLList<String> mySwapQueue = new SLList<>();

		String[] ordinals = { "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth" };

		String[] description = { "The elements are not ordered alphabetically when", "initially added to SLList.", "" };
		System.out.println();
		for (String string : description) {
			System.out.println(string);
		}

		for (int j = 0; j < 1; j++) {
			for (int i = 0; i < ordinals.length; i++) {
				String s = ordinals[i] + (1 - j);
				// System.out.print(mySwapQueue.size() + ", ");
				boolean bool = mySwapQueue.add(s);
				// System.out.println(bool);

			}
		}

		for (int idx = 0; idx < mySwapQueue.size(); idx++) {
			System.out.printf("%8s ", mySwapQueue.getNode(idx).elementData);
		}

		System.out.println();
		
		int j = 9;

		mySwapQueue.swapWithNextNode(mySwapQueue.getNode(j));

		System.out.println(
				"j = " + mySwapQueue.getNode(j).elementData + ", j + 1 = " + mySwapQueue.getNode(j + 1).elementData);

		for (int idx = 0; idx < mySwapQueue.size(); idx++) {
			System.out.printf("%8s ", mySwapQueue.getNode(idx).elementData);
		}

		System.out.println();

	}

	public static void main(String[] args) {
		// Are we testing?
		boolean testing = false;

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart("1", "2a", "Singly-linked List Swap Demo", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. Do", "2. ?", "3. ?" };

		int currentTask = 0;

		System.out.println("TASKS:");
		CommonSuite.printlnIndentArray(tasksList);

		// perform the tasks required by the question
		// TASK 1.

		CommonSuite.headerPrint(tasksList, currentTask++);
		System.out.println("START SLL TESTING");

		swapNodeDemo();

		System.out.println("END   SLL TESTING");
		System.out.println(-2 >>> 3);

		// perform the tasks required by the question
		// TASK 2.

		CommonSuite.headerPrint(tasksList, currentTask++);
		;

		// perform the tasks required by the question
		// TASK 3.

		CommonSuite.headerPrint(tasksList, currentTask++);
		;

		CommonSuite.commonProgramEnd(theTester);

	}

}
