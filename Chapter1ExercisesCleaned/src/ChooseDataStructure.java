import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.Buffer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.Timer;

public class ChooseDataStructure {

	// x2.txt has a few million lines; x.txt has 200 lines
	static String theFileName = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\x.txt";
	static int numTimesToRead = 2000;
	static int theModulo = 100;
	static int numLinesToPrint = 2;

	/**
	 * Reads data file and prints the items in reverse order using Scanner and
	 * an ArrayList.
	 * 
	 * Author: Eric Dunbar, 4/3/16
	 */
	private static void problemOneScanner() {
		System.out.println("... using Scanner...");

		ArrayList<String> myInput = new ArrayList<String>();
		File theFile = new File(theFileName);
		Scanner theScanner = null;

		for (int i = 0; i < numTimesToRead; i++) {
			try {
				theScanner = new Scanner(new FileReader(theFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			while (theScanner.hasNext()) {
				myInput.add((String) theScanner.nextLine());
			}
		}
		for (int i = myInput.size() - 1; i >= myInput.size() - numLinesToPrint; i--) {
			System.out.println(i + " " + myInput.get(i));
		}
	}

	/**
	 * Reads data file and prints the items in reverse order using a
	 * BufferedReader and an ArrayList.
	 * 
	 * Author: Eric Dunbar, 4/3/16
	 */
	private static void problemOneBufferedReader() {
		System.out.println("... using BufferedReader with an ArrayList<String>...");

		ArrayList<String> myInput = new ArrayList<String>();

		for (int i = 0; i < numTimesToRead; i++) {
			try {
				BufferedReader theBuffer = new BufferedReader(new FileReader(theFileName));
				String textIn = "";
				while ((textIn = (String) theBuffer.readLine()) != null) {
					myInput.add(textIn);
				}

				theBuffer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = myInput.size() - 1; i >= myInput.size() - numLinesToPrint; i--) {
			System.out.println(i + " " + myInput.get(i));
		}
	}

	/**
	 * Read a file, print in reverse order using BufferedReader and Arrays
	 * rather than ArrayLists.
	 * 
	 * Author: COMP272 Forums
	 */
	public static void e1_1_1() {
		System.out.println("... using a BufferedReader with an Array...");
		String[] lines = new String[10]; // initialized with 10
		String line = null;

		int i = 0;

		for (int l = 0; l < numTimesToRead; l++) {

			// read each line from the file
			try {
				BufferedReader br = new BufferedReader(new FileReader(theFileName));

				while ((line = br.readLine()) != null) {
					if (i + 1 > lines.length)
						lines = Arrays.copyOf(lines, lines.length * 2);

					lines[i] = line;
					i++;
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// print in the reverse order
		for (int j = i - 1; j >= i - numLinesToPrint; j--)
			System.out.println(lines[j]);

	} // end of main

	public static void main(String[] args) {
		// Print overview
		DSCommonUtils.printArrayList(DSCommonUtils.GenerateOverview());
		System.out.println();
		System.out.println();

		// Generate arrays with problem descriptions
		DSCommonUtils.generateProblemDesc();

		// Display problem description
		DSCommonUtils.printArrayList(DSCommonUtils.theOne);

		// Run problem one, Scanner
		long theTime = System.currentTimeMillis();
		System.out.println("\n\n");
		problemOneScanner();
		System.out.println("Time: " + (System.currentTimeMillis() - theTime));

		// Run problem one, forum
		theTime = System.currentTimeMillis();
		System.out.println("\n\n");
		e1_1_1();
		System.out.println("Time: " + (System.currentTimeMillis() - theTime));

		// Run problem one, BufferedReader
		theTime = System.currentTimeMillis();
		System.out.println("\n\n");
		problemOneBufferedReader();
		System.out.println("Time: " + (System.currentTimeMillis() - theTime));

		// System.out.println();
		// DSCommonUtils.printArrayList(theTwo);

	}
}
