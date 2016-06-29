/**
 * Tests DLList. Not for submission as part of assignment.
 * @author 094360
 *
 */
public class DLListDemo {

	private static void displayList(DLList theDLList) {
		for (int i = 0; i < theDLList.size(); i++) {
			System.out.println("i: " + i + " e: " + theDLList.get(i));
		}
	}

	/**
	 * Demonstrate node swapping functionality.
	 */
	private static void DLListNodeSwapDemo() {
		TestingSupport.setTesting(true);
		DLList<Double> theDLList = new DLList<>();

		for (int i = 0; i < 10; i++) {
			theDLList.add(i + Math.random());
		}

		displayList(theDLList);
		System.out.println();
		theDLList.swapWithNextNode(theDLList.getNode(8));
		// error: if node within 2 of end it cannot swap correctly.
		displayList(theDLList);
		System.out.println();

		int theDLListsize = theDLList.size();
		for (int i = 0; i < theDLListsize; i++) {
			TestingSupport.methodInfo(Double.toString(theDLList.remove(0)));
		}
	}

	/**
	 * Demonstrate basic DLList functionality.
	 */
	private static void DLListDemo() {
		TestingSupport.setTesting(false);
		DLList<Double> theDLList = new DLList<>();

		for (int i = 0; i < 10; i++) {
			theDLList.add(i + Math.random());
			theDLList.add(i * 2, 100 + i + 100 * i + Math.random());
		}

		int theDLListsize = theDLList.size();
		for (int i = 0; i < theDLListsize; i++) {
			TestingSupport.methodInfo(Double.toString(theDLList.remove(0)));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Comparison code");
		DLList<Integer> dT = new DLList<>();
		dT.add(55);
		dT.add(88);

		System.out.println(dT.get(0) + " > " + dT.get(1));
		System.out.println(dT.get(0) > dT.get(1));

		System.out.println("Comparison code");

		System.out.println("START DLL TESTING");

		DLListDemo();
		DLListNodeSwapDemo();

		System.out.println("END   DLL TESTING");
	}
}

