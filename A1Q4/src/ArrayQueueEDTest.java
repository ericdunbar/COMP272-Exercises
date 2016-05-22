import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing using JUnit.
 * 
 * Source: https://courses.cs.washington.edu/
 * courses/cse143/11wi/eclipse-tutorial/junit.shtml
 */

/**
 * @author erdun
 *
 */
public class ArrayQueueEDTest {
	ArrayQueueED<Integer> int1Queue;
	ArrayQueueED<Integer> int2Queue;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ArrayQueueED#size()}.
	 */
	@Test
	public final void testSize() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#ArrayQueueED(java.lang.Class)}.
	 */
	@Test
	public final void testArrayQueueED() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddT() {
	    ArrayQueueED<Integer> list = new ArrayQueueED<Integer>(Integer.class);
	    list.add(42);
	    list.add(-3);
	    list.add(17);
	    list.add(99);
	    assertEquals(42, list.peek(0));
	    assertEquals(-3, list.peek(1));
	    assertEquals(17, list.peek(2));
	    assertEquals(99, list.peek(3));

	    assertEquals("second attempt", 42, list.get(0));   // make sure I can get them a second time
	    assertEquals("second attempt", 99, list.get(3));	}

	/**
	 * Test method for {@link ArrayQueueED#remove()}.
	 */
	@Test
	public final void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#resize()}.
	 */
	@Test
	public final void testResize() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#offer(java.lang.Object)}.
	 */
	@Test
	public final void testOffer() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#peek()}.
	 */
	@Test
	public final void testPeek() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#poll()}.
	 */
	@Test
	public final void testPoll() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ArrayQueueED#iterator()}.
	 */
	@Test
	public final void testIterator() {
		fail("Not yet implemented"); // TODO
	}

}
