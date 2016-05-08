
/**@formatter:off
 * Class: DoubleQueueStack
 * Purpose: Implements a stack using a pair of queues.
 * 
 * @author Eric Dunbar
 * Date:	4/5/2016
 *
 *@formatter:on
 */

/*
 * 1. b. (15 marks total) Implement the stack methods push(x) and pop() using
 * two queues (5 marks for each method). Analyze the running time of the push(x)
 * and pop() operations based on this implementation (5 marks).
 */

/**
 * A stack that stores objects. To call it use the syntax, new TwoQueueStack
 * <T>(T.class)
 * 
 * @author 094360
 *
 * @param <T>
 */
public class TwoQueueStack<T> {
	private ArrayQueue queueA;
	private ArrayQueue queueB;
	private Class<T> theType; // added from Factory.java

	public TwoQueueStack(Class<T> theQT) {
		theType = theQT;
	}

	public TwoQueueStack() {
		queueA = new ArrayQueue(theType);
		queueB = new ArrayQueue(theType);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("START TwoQueueStack Testing");
		System.out.println();

		ArrayQueue<Integer> mine = new ArrayQueue<Integer>(Integer.class);

		mine.add(new Integer(5));
		mine.add(new Integer(10));
		mine.add(new Integer(15));
		mine.add(new Integer(20));
		mine.add(new Integer(25));
		mine.add(new Integer(30));

		System.out.println();

		System.out.printf("       removeLast() %5d%n", mine.removeLast());
		System.out.printf("pop()/removeFirst() %5d%n", mine.removeFirst());

		System.out.println();
		System.out.println("END   TwoQueueStack Testing");
	}
}
