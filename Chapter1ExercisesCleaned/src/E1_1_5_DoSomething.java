import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/*
5. Read the input one line at a time and write each line to the output
only if you have already read this line before. (The end result is that
you remove the first occurrence of each line.) Take special care so
that a file with a lot of duplicate lines does not use more memory
than what is required for the number of unique lines.
 */
public class E1_1_5_DoSomething {

	public static void main(String[] args) {
		// What are we doing?
		System.out.println();
		System.out.println("What does this program do?");
		System.out.println();
		DSCommonUtils.generateProblemDesc();
		DSCommonUtils.printArrayList(DSCommonUtils.theFive);
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
				if (comparisonResult < 0) 
					theSortedArray.add(-comparisonResult - 1, theInput);
				else
					System.out.println(theInput);
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
