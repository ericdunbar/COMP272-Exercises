
public class TestingSupport {

	public static void methodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		System.out.println("  " + stackTraceElements[2].getMethodName() + " " + methodDetail);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
