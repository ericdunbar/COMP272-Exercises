import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Eric D.
 *
 */
public class RandomQueueJUnitTest {
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
	 * Test method for {@link RandomQueue#remove()}.
	 */
	@Test
	public final void testRemove() {
		incrementInstances();
		int hits = 0;
		int tries = 1000;
		int listSize = 10;
		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
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
	 * Test method for {@link RandomQueue#remove()}.
	 * 
	 * Tests to see if the last element is moved from the tail into the sequence.
	 */
	@Test
	public final void testMoveLastElement() {
		incrementInstances();
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
		int hits = 0;
		int tries = 100;
		int listSize = 10;
		for (int outerIdx = 0; outerIdx < tries; outerIdx++) {
			for (int i = 0; i < listSize; i++) {
				list.add(i);
			}

			int removed = (list.remove());

			System.out.print(outerIdx + ": " + removed + "; ");

			StringBuilder sB = new StringBuilder();
			for (int i = 0; i < listSize - 1; i++) {
				sB.append(list.get(i));
			}
			String remainder = sB.toString();

			System.out.print(remainder);
			// could simplify the following with || instead of &&
			if (removed != 9 && remainder.charAt(removed) == '9') {
				System.out.print("***");
				hits++;
			}
			System.out.println();
			for (int i = 0; i < listSize - 1; i++) {
				list.remove();
			}

		}
		double observed = (double) hits / tries;
		double lower = 0.85;
		double upper = 0.95;
		boolean isExpected = (lower < observed) && (observed < upper);
		assertTrue("Expected: " + lower + " < p < " + upper + "; Observed: " + observed,
				isExpected);
	}

	/**
	 * Test method for {@link RandomQueue#RandomArrayQueueED(java.lang.Class)}.
	 */
	@Test
	public final void testRandomQueue() {
		incrementInstances();
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
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
	 * Test method for {@link ArrayQueue#size()}.
	 */
	@Test
	public final void testSize() {
		incrementInstances();
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
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
	 * Test method for {@link ArrayQueue#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddT() {
		incrementInstances();
		RandomQueue<Integer> list = new RandomQueue<Integer>(Integer.class);
		for (int i = 1; i < 1000001; i++) {
			assertTrue("add(" + i + ")", list.add(i));
		}
	}
}
