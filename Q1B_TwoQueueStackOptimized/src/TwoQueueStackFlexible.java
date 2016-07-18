import java.util.AbstractQueue;

/**
 * Class: TwoQueueStackFlexible.
 * 
 * Purpose: A stack of objects.
 * 
 * Details: Implementation of a stack using two queues. To call it use the syntax, new TwoQueueStack
 * <T>(T.class). This stack implements push(x) and pop().
 * 
 * @author Eric Dunbar
 * @date 2016.07.18
 * @param <T>
 */
public class TwoQueueStackFlexible<T> {
	private AbstractQueue<T> qUnused;
	private AbstractQueue<T> qUsed;
	private Class<T> theType; // added from Factory.java from ODS by Pat Morin

	/*
	 * Citations: Hirondelle Systems. 2016. Use javadoc liberally.
	 * http://www.javapractices.com/topic/TopicAction.do?Id=60
	 */

	/**
	 * Create instance of a stack backed by two optimized array queues.
	 * 
	 * @param theQT
	 */
	public TwoQueueStackFlexible(Class<T> theQT) {
		theType = theQT;
		qUnused = new ArrayQueueOptimized<T>(theType);
		qUsed = new ArrayQueueOptimized<T>(theType);
	}

	/**
	 * Create instance of a stack backed by two queues.
	 * 
	 * @param theQT
	 * @param optimized true for optimized backing array queues, false for simple backing array
	 *            queues
	 */
	public TwoQueueStackFlexible(Class<T> theQT, boolean optimized) {
		theType = theQT;
		if (!optimized) {
			qUnused = new ArrayQueueSimple<T>(theType);
			qUsed = new ArrayQueueSimple<T>(theType);
		} else {
			qUnused = new ArrayQueueOptimized<T>(theType);
			qUsed = new ArrayQueueOptimized<T>(theType);
		}
	}

	/**
	 * Adds a given element of the specified data type to the stack.
	 * 
	 * @param x element
	 * @return the given element
	 */
	public T push(T x) {
		// Last element in needs to be at the head of the queue (i.e. first out) since queues
		// operate on an addLast(x) and removeFirst() basis (ODS pg. 6)
		qUnused.add(x);

		// Move head element from one queue to the tail of another queue
		int elementsToRemove = qUsed.size();
		for (int i = 0; i < elementsToRemove; i++) {
			qUnused.add(qUsed.remove());
		}

		AbstractQueue<T> qTemp = qUsed;
		qUsed = qUnused;
		qUnused = qTemp;

		return x;
	}

	@Override
	public String toString() {
		return "TwoQueueStack [qUnused=" + qUnused.toString() + ", qUsed=" + qUsed.toString() + ", theType=" + theType
				+ "]";
	}

	/**
	 * Returns the last element added.
	 * 
	 * @return last element in
	 */
	public T pop() {
		return (T) qUsed.remove();
	}
}
