/**
 * A quick support class created for testing purposes. Needs to be rolled into Testing.
 * @author Eric Dunbar
 * @date 13/7/2016
 *
 */
public class CollectMethodInfo {
	private static boolean testing = false;
	public static int MICounter = 0;

	public static void resetCounter() {
		MICounter = 0;
	}

	public static boolean isTesting() {
		return testing;
	}

	public static void setTesting(boolean testingParam) {
		CollectMethodInfo.testing = testingParam;
	}

	public static boolean getTesting() {
		return CollectMethodInfo.testing;
	}

	/**
	 * Method info designed for TwoQueueStack
	 * 
	 * @param methodDetail
	 * @param counter
	 */
	public static void TQSMethodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (testing) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println(
					"   # " + ++MICounter + " #   " + stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}

	/**
	 * Displays info about calling method.
	 * 
	 * @param methodDetail
	 */
	public static void methodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (testing) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("   " + stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}
}