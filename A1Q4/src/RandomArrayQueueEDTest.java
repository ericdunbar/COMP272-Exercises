import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author erdun
 *
 */
public class RandomArrayQueueEDTest {
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
	 * Test method for {@link RandomArrayQueueED#remove()}.
	 */
	@Test
	public final void testRemove() {
		incrementInstances();
		int hits = 0;
		int tries = 1000;
		int listSize = 10;
		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			RandomArrayQueueED<Integer> list = new RandomArrayQueueED<Integer>(Integer.class);
			for (int i = 0; i < listSize; i++) {
				list.add(i);
			}
			if (list.remove() == listSize - 1)
				hits++;
		}
		double observed = (double) hits / tries;
		boolean isExpected = (0.09 < observed) && (observed < 0.11);
		assertTrue("Expected: 0.09 < p < 0.11; Observed: " + observed, isExpected);
	}

	/**
	 * Test method for {@link RandomArrayQueueED#RandomArrayQueueED(java.lang.Class)}.
	 */
	@Test
	public final void testRandomArrayQueueED() {
		incrementInstances();
		RandomArrayQueueED<Integer> list = new RandomArrayQueueED<Integer>(Integer.class);
		for (int i = 1; i < 100001; i++) {
			list.add(i);
			assertEquals("Size() = " + i, i, list.size());
		}
		assertEquals("Size() = 100000", 100_000, list.size());
		list.remove();
		list.remove();
		assertEquals("Size() = 99998", 99_998, list.size());
	}

	/**
	 * Test method for {@link ArrayQueueED#size()}.
	 */
	@Test
	public final void testSize() {
		incrementInstances();
		RandomArrayQueueED<Integer> list = new RandomArrayQueueED<Integer>(Integer.class);
		for (int i = 1; i < 100001; i++) {
			list.add(i);
			assertEquals("Size() = " + i, i, list.size());
		}
		assertEquals("Size() = 100000", 100_000, list.size());
		list.remove();
		list.remove();
		assertEquals("Size() = 99998", 99_998, list.size());
	}

	/**
	 * Test method for {@link ArrayQueueED#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddT() {
		incrementInstances();
		RandomArrayQueueED<Integer> list = new RandomArrayQueueED<Integer>(Integer.class);
		for (int i = 1; i < 1000001; i++) {
			assertTrue("add(" + i + ")", list.add(i));
		}
	}

}
