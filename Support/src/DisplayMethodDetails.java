/**
 * Displays information about the current method.
 * 
 * @author Eric Dunbar
 *
 */
public class DisplayMethodDetails {
	private static boolean testing = false;
	public static int MICounter = 0;

	public static void resetCounter() {
		MICounter = 0;
	}

	public static boolean isTesting() {
		return testing;
	}

	public static void setTesting(boolean isTesting) {
		testing = isTesting;
	}

	public static boolean getTesting() {
		return testing;
	}

	/**
	 * Method info modified for TwoQueueStack
	 * 
	 * @param methodDetail
	 * @param counter
	 */
	public static void TQSMethodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (testing) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("   # " + ++MICounter + " #   "
					+ stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}

	public static void methodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (testing) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("   " + stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}
}
