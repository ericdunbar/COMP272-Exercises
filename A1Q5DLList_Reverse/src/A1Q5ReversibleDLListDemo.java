
public class A1Q5ReversibleDLListDemo {
	// Are we testing?
	final static boolean testing = false;

	static TestSuite theTester;

	/**
	 * Demonstrate basic ReversibleDLList functionality.
	 */
	private static void reversibleDLListDemo() {
		System.out.println("START REVERSIBLE DLL TESTING");

		String[] ordinals = { "Ten", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };

		for (int j = 0; j < 10; j += 1) {
			ReversibleDLList<Double> reversibleDoubleDLL = new ReversibleDLList<>();
			ReversibleDLList<String> reversibleStringDLL = new ReversibleDLList<>();

			System.out.println();
			System.out.println("Number of elements: " + j);

			// Demonstrate double
			for (double i = 0; i < j; i++) {
				reversibleDoubleDLL.add(i);// + Math.random());
			}

			System.out.println("Forward double: " + reversibleDoubleDLL);
			reversibleDoubleDLL.reverse();
			int theDLListsize = reversibleDoubleDLL.size();
			for (int i = 0; i < theDLListsize; i++) {
				if (testing)
					CommonSuite.methodInfo(String.format("%7.2f", reversibleDoubleDLL.remove(0)));
			}
			System.out.println("Reverse double: " + reversibleDoubleDLL.toString());

			// Demonstrate String
			System.out.println();
			for (int i = 0; i < j; i++) {
				reversibleStringDLL.add(ordinals[i]);// + Math.random());
			}

			System.out.println("Forward String: " + reversibleStringDLL);
			reversibleStringDLL.reverse();
			theDLListsize = reversibleStringDLL.size();
			for (int i = 0; i < theDLListsize; i++) {
				if (testing)
					CommonSuite.methodInfo(String.format("%7.2f", reversibleStringDLL.remove(0)));
			}
			System.out.println("Reverse String: " + reversibleStringDLL.toString());

		
		}

		System.out.println("END   REVERSIBLE DLL TESTING");
	}

	public static void main(String[] args) {

		// Display programmer info and create testing object
		theTester = CommonSuite.commonProgramStart("1", "5", "Reversible Doubly-linked List Demo", testing);
		theTester.setSilentRecording(false); // report results immediately

		// Display tasks
		String[] tasksList = { "1. REVERSIBLE DLL TESTING", "      Reverse a DLList of double elements", "      Reverse a DLList of String elements" };

		int currentTask = 0;

		System.out.println("TASKS:");
		CommonSuite.printlnIndentArray(tasksList);

		// perform the tasks required by the question
		// TASK 1.

		CommonSuite.headerPrint(tasksList, currentTask++);
		reversibleDLListDemo();

		CommonSuite.commonProgramEnd(theTester);

	}

}
