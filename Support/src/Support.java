
public class Support {
	private static boolean isTesting = false;

	public static void printDescription(String title, String[] details){
		String indent = "  ";
		System.out.println("TITLE");
		System.out.println(indent + title);
		System.out.println();
		System.out.println("DETAILS:");
		for (int i = 0; i < details.length; i++) {
			System.out.println(indent + details[i]);
		}
	}
	
	public static boolean isTesting() {
		return isTesting;
	}

	public static void setTesting(boolean isTesting) {
		Support.isTesting = isTesting;
	}

	public static void methodInfo(String methodDetail) {
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		if (isTesting) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("  " + stackTraceElements[2].getMethodName() + " " + methodDetail);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
