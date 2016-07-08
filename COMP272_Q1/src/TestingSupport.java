
public class TestingSupport {
	private static boolean isTesting = false;
	public static int MICounter = 0;
	
	public static void resetCounter(){
		MICounter = 0;
	}

	public static boolean isTesting() {
		return isTesting;
	}

	public static void setTesting(boolean isTesting) {
		TestingSupport.isTesting = isTesting;
	}

	public static boolean getTesting() {
		return TestingSupport.isTesting;
	}

	/**
	 * Method info designed for TwoQueueStack
	 * 
	 * @param methodDetail
	 * @param counter
	 */
	public static void TQSMethodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (isTesting) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("   # " + ++MICounter + " #   "
					+ stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}

	public static void methodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (isTesting) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("   " + stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
