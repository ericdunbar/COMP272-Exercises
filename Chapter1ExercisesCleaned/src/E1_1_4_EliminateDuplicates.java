import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 4. Read the input one line at a time and write each line to the output if it
 * is not a duplicate of some previous input line. Take special care so that a
 * file with a lot of duplicate lines does not use more memory than what is
 * required for the number of unique lines.
 */

public class E1_1_4_EliminateDuplicates {

	public static void main(String[] args) {

		// What are we doing?
		System.out.println();
		System.out.println("What does this program do?");
		System.out.println();
		DSCommonUtils.generateProblemDesc();
		DSCommonUtils.printArrayList(DSCommonUtils.theFour);
		System.out.println();

		// Do the thing we're doing
		BufferedReader theBReader = DSFileIO.openAFile(DSCommonUtils.theFileNameX3);

		ArrayList<String> theSortedArray = new ArrayList<String>();

		String theInput;

		try {
			// Read the contents of the file into a sorted list; no duplicates
			while (((theInput = (String) theBReader.readLine()) != null)) {
				int comparisonResult = MyArray.binarySearch(theSortedArray.toArray(new String[0]), theInput);

				// add item if it's not been seen before
				if (comparisonResult < 0) {
					theSortedArray.add(-comparisonResult - 1, theInput);
					System.out.println(theInput);
				}
			}

			theBReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Now to show you what we did...");
		System.out.println();
		System.out.println("A sorted result...");
		System.out.println();
		for (int i = 0; i < theSortedArray.size(); i++) {
			System.out.println(theSortedArray.get(i));
		}
	}
}

class MyArray {
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