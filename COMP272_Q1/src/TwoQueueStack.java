
/*
 * 1. b. Implement the stack methods push(x) and pop() using two queues (5 marks
 * for each method). Analyze the running time of the push(x) and pop()
 * operations based on this implementation (5 marks).
 */

/*@formatter:off
 * Citations:
 * Hirondelle Systems. 2016. Use javadoc liberally. http://www.javapractices.com/topic/TopicAction.do?Id=60
 * 
 * @formatter:on
 */

/**
 * @formatter:off
 * Class: TwoQueueStack.
 * Purpose: A stack of objects.
 * 
 * Details: Its implementation calls for two queues. To call it use the syntax, new
 * TwoQueueStack <T>(T.class)
 * 
 * @author 094360
 * @version 2016.05.04.1
 * @param <T>
 * @formatter:on
 */
public class TwoQueueStack<T> {
	private ArrayQueue qA;
	private ArrayQueue qB;
	private Class<T> theType; // added from Factory.java

	public TwoQueueStack(Class<T> theQT) {
		theType = theQT;
	}

	public TwoQueueStack() {
		qA = new ArrayQueue(theType);
		qB = new ArrayQueue(theType);
	}

	private static void OneQueueTest() {
		System.out.println("START One queue only");
		TestingSupport.setTesting(true);
		System.out.println();

		ArrayQueue<Integer> mine = new ArrayQueue<Integer>(Integer.class);

		mine.add(new Integer(5));
		mine.add(new Integer(10));
		mine.add(new Integer(15));
		mine.add(new Integer(20));
		mine.add(new Integer(25));
		mine.add(new Integer(30));

		System.out.println();

		mine.removeLast();
		mine.removeLast();
		mine.removeFirst();
		mine.removeFirst();
		mine.removeLast();
		mine.removeLast();

		try {
			mine.removeLast();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		;

		System.out.println();
		System.out.println("END   One queue only");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("START TwoQueueStack Testing");
		OneQueueTest();
		System.out.println("END   TwoQueueStack Testing");
	}
}
