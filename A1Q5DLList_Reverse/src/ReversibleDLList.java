/**
 * 5. Exercise 3.12. Write a method, reverse(), that reverses the order of elements in a
 * DLList.
 * 
 * @author Eric Dunbar
 *
 */
public class ReversibleDLList<T> extends DLList<T> {

	/*
	 * How to extend DLList. The previous construction <T extends Comparable<T>> wasn't
	 * working. implements works better for DLList.
	 * 
	 * Inferred type is not a valid substitute for a Comparable generic type. (2013, July
	 * 19). Retrieved May 28, 2016, from
	 * http://stackoverflow.com/questions/17739720/inferred-type-is-not-a-valid-substitute
	 * -for-a-comparable-generic-type
	 */

	/**
	 * Reverses the order of elements in a DLList.
	 */
	public void reverse() {
		Node curNode = dummy.next;

		for (int i = 0; i < this.size() + 1; i++) {
			Node swapNode = curNode.next;
			curNode.next = curNode.prev;
			curNode.prev = swapNode;
			curNode = curNode.prev; // the new prev was the former next
		}
	}

	/**
	 * Demonstrate basic ReversibleDLList functionality.
	 */
	private static void reversibleDLListDemo() {
		CommonSuite.setTesting(true);
		ReversibleDLList<Double> theReversibleDLList = new ReversibleDLList<>();

		for (int j = 0; j < 10; j += 2) {
			System.out.println();
			System.out.println("Number of elements: " + j);
			for (int i = 0; i < j; i++) {
				theReversibleDLList.add(i + Math.random());
				// theReversibleDLList.add(i * 2, 100 + i + 100 * i + Math.random());
			}
			theReversibleDLList.reverse();
			int theDLListsize = theReversibleDLList.size();
			for (int i = 0; i < theDLListsize; i++) {
				CommonSuite.methodInfo(String.format("%7.2f", theReversibleDLList.remove(0)));
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("START REVERSIBLE DLL TESTING");

		reversibleDLListDemo();

		System.out.println("END   REVERSIBLE DLL TESTING");
	}

}
