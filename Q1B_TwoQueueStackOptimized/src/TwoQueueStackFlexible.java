import java.util.AbstractQueue;

/**
 * Class: TwoQueueStack. Purpose: A stack of objects.
 * 
 * Details: Its implementation a stack using two queues. To call it use the syntax, new
 * TwoQueueStack <T>(T.class). This stack implements push(x) and pop().
 * 
 * @author Eric Dunbar
 * @date 2016.07.18
 * @param <T>
 */
public class TwoQueueStackFlexible<T> {
	private AbstractQueue<T> qUnused;
	private AbstractQueue<T> qUsed;
	private Class<T> theType; // added from Factory.java

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
	 * @param optimized true for optimized backing array queues, false for simple backing array queues
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
	 * @param dataElement
	 * @return the given element
	 */
	public T push(T dataElement) {
		// Last element in needs to be at the tail of the queue (i.e. first out)
		CollectMethodInfo.methodInfo("(" + dataElement + ")");
		qUnused.add(dataElement);

		// Move tail element from one queue to the head of another queue
		int elementsToRemove = qUsed.size();
		// Fix: was using qUsed.size() directly but qUsed.size() changes each
		// iteration!
		for (int i = 0; i < elementsToRemove; i++) {
			// note: since remove will resize the backing array many times this
			// is a highly inefficient way of using the backing array
			qUnused.add(qUsed.remove());
		}

		AbstractQueue<T> qTemp = qUsed;

		/*
		 * figure it's faster and uses less memory to copy a pointer to an existing ArrayQueue
		 * object than to create and allocate the memory for a new one
		 */
		qUsed = qUnused;
		qUnused = qTemp;

		return dataElement;
	}

	@Override
	public String toString() {
		return "TwoQueueStack [qUnused=" + qUnused + ", qUsed=" + qUsed + ", theType=" + theType + "]";
	}

	/**
	 * Returns the last element added.
	 * 
	 * @return last element in
	 */
	public T pop() {
		// Check this. Why having to type-cast?
		return (T) qUsed.remove();
	}
}
