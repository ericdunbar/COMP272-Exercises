import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
6. Read the entire input one line at a time. Then output all lines sorted
by length, with the shortest lines first. In the case where two lines
have the same length, resolve their order using the usual "sorted
order." Duplicate lines should be printed only once.
 */

public class E1_1_6ReadAllSortByStringLength {

	static int numTimesToRead = 2000;
	static int theModulo = 100;
	static int numLinesToPrint = 2;

	/**
	 * Read a file, print lines by ascending length; using BufferedReader and
	 * Arrays.
	 * 
	 * Origin: COMP272 Forums
	 * 
	 * @author Eric D
	 */
	public static void readAndSortByStringLength() {
		System.out.println("... using a BufferedReader with an Array...");

		String[] lines = new String[10];
		String line = null;

		int i = 0;

		// read each line from the file
		try {
			BufferedReader br = new BufferedReader(new FileReader(DSCommonUtils.theFileNameX3));

			/* http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/
			   util/ArrayList.java#ArrayList.add%28java.lang.Object%29
			*/

			while ((line = br.readLine()) != null) {
				if (i + 1 > lines.length)
					lines = Arrays.copyOf(lines, lines.length * 2);
				lines[i++] = line;
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// print in the reverse order
		for (int j = 0; j < numLinesToPrint; j++)
			System.out.println(lines[j]);

	} // end of main

	public static void main(String[] args) {

		// Generate arrays with problem descriptions
		DSCommonUtils.generateProblemDesc();

		// Display problem description
		DSCommonUtils.printArrayList(DSCommonUtils.theSix);

		long theTime;

		// Run problem one, forum
		theTime = System.currentTimeMillis();
		System.out.println("\n\n");
		readAndSortByStringLength();
		System.out.println("Time: " + (System.currentTimeMillis() - theTime));

	}
}
