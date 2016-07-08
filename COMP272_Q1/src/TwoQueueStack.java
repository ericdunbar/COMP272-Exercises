
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
 * @author Eric Dunbar
 * @version 2016.05.04.1
 * @param <T>
 * @formatter:on
 */
public class TwoQueueStack<T> {
	private ArrayQueue<T> qUnused;
	private ArrayQueue<T> qUsed;
	private Class<T> theType; // added from Factory.java

	public TwoQueueStack(Class<T> theQT) {
		theType = theQT;
		qUnused = new ArrayQueue<T>(theType);
		qUsed = new ArrayQueue<T>(theType);
	}


	public T push(T dataElement) {
		
		// Last element in needs to be at the tail of the queue (i.e. first out)
		//TestingSupport.methodInfo("(" + dataElement + ")");
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

}
