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
 * @author Eric D
 *
 */
public class ArrayQueueEDTest {
	ArrayQueueED<Integer> int1Queue;
	ArrayQueueED<Integer> int2Queue;
	public static int instances = 0;

	public static void incrementInstances() {
		instances++;
		System.out.println("Instance number " + instances);
	}

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
		incrementInstances();
		ArrayQueueED<Integer> list = new ArrayQueueED<Integer>(Integer.class);
		assertEquals("Size() = 0", 0, list.size());
		list.add(55);
		list.add(56);
		assertEquals("Size() = 2", 2, list.size());
		list.remove();
		list.remove();
		assertEquals("Size() = 0", 0, list.size());
	}

	/**
	 * Test method for {@link ArrayQueueED#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddT() {
		incrementInstances();
		ArrayQueueED<Integer> list = new ArrayQueueED<Integer>(Integer.class);
		list.add(42);
		list.add(-3);
		list.add(17);
		list.add(99);
		assertEquals((Integer) 42, (Integer) list.remove());
		assertEquals((Integer) (-3), (Integer) list.remove());
		assertEquals((Integer) 17, (Integer) list.remove());
		assertEquals((Integer) 99, (Integer) list.remove());
	}

	/**
	 * Test method for {@link ArrayQueueED#remove()}.
	 */
	@Test
	public final void testRemove() {
		incrementInstances();
		ArrayQueueED<Integer> list = new ArrayQueueED<Integer>(Integer.class);
		list.add(42);
		list.add(-3);
		list.add(17);
		list.add(99);
		assertEquals((Integer) 42, (Integer) list.remove());
		assertEquals((Integer) (-3), (Integer) list.remove());
		list.add(17);
		assertEquals((Integer) 17, (Integer) list.remove());
		assertEquals((Integer) 99, (Integer) list.remove());
		assertEquals((Integer) 17, (Integer) list.remove());
		try {
			assertEquals((Integer) 17, (Integer) list.remove());
		} catch (Exception e) {
			System.out.println("Exception was generated, as expected");
		}
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
