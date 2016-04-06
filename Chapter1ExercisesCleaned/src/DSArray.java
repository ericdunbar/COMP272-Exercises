import java.util.Arrays;

public class DSArray {
	public String[] theArray;
	
	public static void add(String[] a, String item){
		
	}
	
	public DSArray (int arraySize){
		theArray = new String[arraySize];
	}
	
	private void ensureCapacity (int desiredCapacity){
		if (theArray.length < desiredCapacity) {
			theArray = Arrays.copyOf(theArray, desiredCapacity);
		}
	}
	
	// @formatter:off
	/**
	 * Binary search using a String array
	 * 
	 * 1. Let min = 0 and max = n-1.
	 * 2. Compute guess as the average of max and min, rounded down so that it is an integer.
	 * 3. If array[guess] equals target, then stop. You found it! Return guess.
	 * 4. If the guess was too low, that is, array[guess] < target, 
	 * then set min = guess + 1.
	 * 5. Otherwise, the guess was too high. Set max = guess - 1.
	 * 6. Go back to step two.

	 * http://googleresearch.blogspot.ca/2006/06/extra-extra-read-all-about-it-nearly.html
	 * https://reprog.wordpress.com/2010/04/19/are-you-one-of-the-10-percent/
	 * http://stackoverflow.com/questions/3170412/why-is-132-1

	 * https://www.khanacademy.org/computing/computer-science/algorithms/
	 * binary-search/a/implementing-binary-search-of-an-array
	 * 
	 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/
	 * openjdk/6-b27/java/util/Arrays.java#Arrays.binarySearch%28java.lang.
	 * Object%5B%5D%2Cjava.lang.Object%29
	 * 
	 * @param localArray
	 *            An array of Strings
	 * @param key
	 *            The search key
	 * @return Position of key or -(position) where it would insert
	 */
	// @formatter:on
	public static int binarySearch(String[] localArray, String key) {
		int min = 0;
		int max = localArray.length - 1;

		// Note: the key to a binary search is that the low value <= high, not
		// low < high
		while (min <= max) {
			int mid = min + (max - min) / 2;
			String midValue = localArray[mid];
			int compare = midValue.compareTo(key);

			if (compare < 0)
				min = mid + 1;
			else if (compare > 0)
				max = mid - 1;
			else
				return mid; // found it
		}

		// how does it handle a value that's lower than the smallest value?
		return -(min + 1);
	}
}
