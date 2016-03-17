import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 
 * 4. Read the input one line at a time and write each line to the output if it
 * is not a duplicate of some previous input line. Take special care so that a
 * file with a lot of duplicate lines does not use more memory than what is
 * required for the number of unique lines.
 */

public class E1_1_4_EliminateDuplicates {

	public static void main(String[] args) {

	}
}

class SortedArray {
	private String[] theArray = new String[10];
	public int length = 0;

	/**
	 * Returns true if theItem is unique and was added to the array.
	 * 
	 * @param theItem
	 * @return
	 */
	public boolean push(String theItem) {
		return true;
	}

/**
 * Binary search using String array
 * 
 * @param theLocalArray An array of Strings
 * @param key The search key
 * @return Position of key or -(position) where it would insert
 */
	public static int binarySearch(String[] theLocalArray, String key) {
		// @formatter:off
		/*
		 * https://www.khanacademy.org/computing/computer-science/algorithms/
		 * binary-search/a/implementing-binary-search-of-an-array
		 * 
		 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/
		 * openjdk/6-b27/java/util/Arrays.java#Arrays.binarySearch%28java.lang.
		 * Object%5B%5D%2Cjava.lang.Object%29
		 * 
		 * 1. Let min = 0 and max = n-1.
		 * 2. Compute guess as the average of max and min, rounded down so that it is an integer.
		 * 3. If array[guess] equals target, then stop. You found it! Return guess.
		 * 4. If the guess was too low, that is, array[guess] < target, 
		 * then set min = guess + 1.
		 * 5. Otherwise, the guess was too high. Set max = guess - 1.
		 * 6. Go back to step two.
		 * 

http://googleresearch.blogspot.ca/2006/06/extra-extra-read-all-about-it-nearly.html
https://reprog.wordpress.com/2010/04/19/are-you-one-of-the-10-percent/
http://stackoverflow.com/questions/3170412/why-is-132-1


		 */
		// @formatter:on
		int min = 0;
		int max = theLocalArray.length - 1;
		int guess;
		int compare;

		while (min < max) {

			guess = (max - min) / 2;

			compare = theLocalArray[guess].compareTo(key);

			if (compare < 0) {
				min = guess + 1;
			} else if (compare > 0) {
				max = guess - 1;
			} else {
				return guess; // found it
			}
		}

		return -(min + 1); // it's got to be greater than the low point
	}

	/**
	 * Double size of array.
	 */
	private void growArray() {
		theArray = Arrays.copyOf(theArray, theArray.length * 2);
	}
}