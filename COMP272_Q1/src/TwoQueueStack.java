
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
 * Details: Its implementation a stack using two queues. To call it use the syntax, new
 * TwoQueueStack <T>(T.class). This stack implements push(x) and pop().
 * 
 * @author 094360
 * @version 2016.05.04.1
 * @param <T>
 * @formatter:on
 */
public class TwoQueueStack<T> {
	private ArrayQueue qUnused;
	private ArrayQueue qUsed;
	private Class<T> theType; // added from Factory.java

	public TwoQueueStack(Class<T> theQT) {
		theType = theQT;
		qUnused = new ArrayQueue(theType);
		qUsed = new ArrayQueue(theType);
	}

/*	public TwoQueueStack() {
		qUnused = new ArrayQueue(theType);
		qUsed = new ArrayQueue(theType);
	}
*/
	
	public T push(T dataElement) {
		// Last element in needs to be at the tail of the queue (i.e. first out)
		TestingSupport.methodInfo("(" + dataElement + ")");
		qUnused.add(dataElement);

		// Move tail element from one queue to the head of another queue
		int elementsToRemove = qUsed.size();
		//Fix: was using qUsed.size() directly but qUsed.size() changes each iteration!
		for (int i = 0; i < elementsToRemove; i++) {
			// note: since remove will resize the backing array many times this
			// is a highly inefficient way of using the backing array
			qUnused.add(qUsed.remove());
		}

		ArrayQueue<T> qTemp = qUsed;

		/*
		 * figure it's faster and uses less memory to copy a pointer to an
		 * existing ArrayQueue object than to create and allocate the memory for
		 * a new one
		 */
		qUsed = qUnused;
		qUnused = qTemp;
		
		return dataElement;
	}

	@Override
	public String toString() {
		return "TwoQueueStack [qUnused=" + qUnused + ", qUsed=" + qUsed + ", theType=" + theType
				+ "]";
	}

	public T pop() {
		// Check this. Why having to type-cast?
		return (T) qUsed.remove();
	}

	private static void OneQueueTest() {
		System.out.println("START One queue only");
		System.out.println();
		System.out.println("Demonstration only. No use to final TwoQueueStack class.");
		TestingSupport.setTesting(false);
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

	private static void PushPopTest() {
		System.out.println("START PushPop Testing");
		System.out.println();
		System.out.println("Demonstration of push(x) and pop().");
		TestingSupport.setTesting(false);

		TwoQueueStack<Integer> demoStack = new TwoQueueStack<>(Integer.class);
		
		for (int i = 0; i < 10000; i++) {
			System.out.println("Round " + i);
			System.out.println("    " + demoStack.toString());
			TestingSupport.methodInfo("push(" + demoStack.push(88*i) + ");");
		}
		for (int i = 0; i < 11; i++) {
			try {
				System.out.println(demoStack.pop());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		System.out.println("END   PushPop Testing");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("START TwoQueueStack Testing");
		OneQueueTest();
		PushPopTest();
		System.out.println("END   TwoQueueStack Testing");
	}
}
