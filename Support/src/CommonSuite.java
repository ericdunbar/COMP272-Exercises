

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//@formatter:off
/**
*             Assignment: None, COMP268
* Class:      CommonSuite.java
* Purpose:    Provides a number of supporting methods reusable in many classes.
*
* @author:    Eric Dunbar
* Student ID: 3243514
* Date:       October 30, 2015
* Version     1.2
*
* Based on:   Eck, David J. (2015). Introduction to Programming Using Java, 
*             Seventh Edition.  Web access http://math.hws.edu/javanotes/
*/
//@formatter:on
public class CommonSuite {
	// Class variables to store author information for the currently running
	// program
	private static String authorData;
	private static String studentNumberData;
	private static int assignmentData;
	private static int questionData;
	private static String questionTitleData;
	private static Scanner myScanner = new Scanner(System.in);

	/**
	 * Gets and returns a String from System.in. A message is displayed.
	 * 
	 * @param message
	 *            an optional message to be displayed
	 * @return String containing current line of text
	 */
	public static String getTextInput(String message) {
		System.out.println("Please enter " + message);
		return getTextInput();
	}

	/**
	 * Gets and returns a String from System.in
	 * 
	 * @return String containing current line of text
	 */
	public static String getTextInput() {
		System.out.print(">");
		return myScanner.nextLine();
	}

	/**
	 * Gets and returns a double from System.in. A message is displayed.
	 * 
	 * @param message
	 *            an optional message to be displayed
	 * @return double containing value
	 */
	public static double getDoubleInput(String message) {
		System.out.println("Please enter " + message);
		return getDoubleInput();
	}

	/**
	 * Gets and returns a double from System.in
	 * 
	 * @return double containing a numeric value
	 */
	public static double getDoubleInput() {
		while (true) {
			try {
				return Double.valueOf(getTextInput());

			} catch (Exception e) {
				System.out.println("Not a numeric value. Please try again.");
			}
		}
	}

	/**
	 * Gets and returns a boolean from System.in. Displays a message.
	 * 
	 * @param message
	 *            String of message to be displayed
	 * @return boolean value
	 */

	public static boolean getBooleanInput(String message) {
		System.out.println("Please enter " + message);
		return getBooleanInput();
	}

	/**
	 * Gets and returns a boolean from System.in
	 * 
	 * @return boolean value
	 */
	public static boolean getBooleanInput() {
		String input;
		while (true) {
			input = getTextInput();
			if (input.equalsIgnoreCase("t") || input.equalsIgnoreCase("y"))
				return true;
			else if (input.equalsIgnoreCase("f") || input.equalsIgnoreCase("n"))
				return false;
			System.out.println("Please enter t/y for true or f/n for false.");
		}
	}

	/**
	 * Gets and returns an int from System.in. A message is displayed.
	 * 
	 * @param message
	 *            an optional message to be displayed
	 * @return integer containing value
	 */
	public static int getIntegerInput(String message) {
		System.out.println("Please enter " + message);
		return getIntegerInput();
	}

	/**
	 * Gets and returns an int from System.in
	 * 
	 * @return int containing current integer
	 */
	public static int getIntegerInput() {
		while (true) {
			try {
				return Integer.valueOf(getTextInput());

			} catch (Exception e) {
				System.out.println("Not a numeric value. Please try again.");
			}
		}
	}

	/**
	 * Returns a string with numRepetitions repeats of stringToRepeat. A value
	 * lower than 1 numRepetitions results in an empty String "".
	 * 
	 * @param stringToRepeat
	 *            The string to repeat
	 * @param numRepetitions
	 *            The number of times to repeat the string
	 * @return String with repeating characters
	 */
	public static String stringRepeat(String stringToRepeat, int numRepetitions) {
		if (stringToRepeat == null || numRepetitions < 1) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder(numRepetitions * stringToRepeat.length());
		for (int index = 0; index < numRepetitions; index++) {
			stringBuilder.append(stringToRepeat);
		}
		return stringBuilder.toString();
	} // end of stringRepeat()

	/**
	 * Prints an ArrayList of type String to System.out.
	 * 
	 * @param theArray
	 *            <i>ArrayList</i> of type <i>String</i>
	 */
	public static void printArrayList(ArrayList<String> theArray) {
		for (String string : theArray) {
			System.out.println(string);
		}
	}

	/**
	 * Generates an ArrayList of type String from an array of type String.
	 * 
	 * @param myString
	 *            array of type String
	 * @return
	 */
	public static ArrayList<String> generateArrayList(String[] myString) {
		ArrayList<String> myArrayList = new ArrayList<String>(Arrays.asList(myString));
		return myArrayList;
	}

	/**
	 * Converts an ArrayList of type String to String. A newline character is
	 * inserted after each item in the ArrayList.
	 * 
	 * @param theArray
	 *            <i>ArrayList</i> of type <i>String</i>
	 */
	public static String convertArrayList(ArrayList<String> theArray) {
		return convertArrayList(theArray, "\n");
	}

	/**
	 * Converts an ArrayList of type String to String. Separator is a String
	 * that separates each item in the ArrayList.
	 * 
	 * @param theArray
	 *            <i>ArrayList</i> of type <i>String</i>
	 */
	public static String convertArrayList(ArrayList<String> theArray, String separator) {
		String stringOut = "";
		String localSeparator = "";
		if (theArray == null) {
			return "Error: null pointer";
		}
		for (String string : theArray) {
			stringOut += localSeparator + string;
			localSeparator = separator;
		}
		return stringOut;
	}

	/**
	 * Prints programmer details
	 * 
	 */
	public static void printProgrammerInfo() {
		// Display programmer info
		int offset = 10; // Adjust the print block
		int fancyFormat = 30; // How many repeating characters to use
		if (questionTitleData.length() > 18) {
			fancyFormat = 12 + questionTitleData.length();
		}
		String offsetChar = " "; // Use this String to offset a block
		String offsetBlock = stringRepeat(offsetChar, offset); // the offset
																// block
		{
			System.out.println(offsetBlock + CommonSuite.stringRepeat("=", fancyFormat));
			System.out.printf("%sAuthor:     %s", offsetBlock, authorData);
			System.out.println();
			System.out.printf("%sStudent ID: %s", offsetBlock, studentNumberData);
			System.out.println();
			System.out.printf("%sAssignment: %d", offsetBlock, assignmentData);
			System.out.println();
			System.out.printf("%sQuestion:   %d", offsetBlock, questionData);
			System.out.println();
			System.out.printf("%sTitle:      %s", offsetBlock, questionTitleData);
			System.out.println();
			System.out.println(offsetBlock + CommonSuite.stringRepeat("=", fancyFormat));
		}

	}

	/**
	 * Prints program details using the parameters passed as author, student
	 * number, assignment number and question number
	 * 
	 * @param author
	 *            Name of the author
	 * @param studentNumber
	 *            Student number of the author, as String
	 * @param assignment
	 *            Assignment number, as int
	 * @param question
	 *            Question number, as int
	 * @param questionTitle
	 *            Title of the question, as String
	 */
	public static void printProgrammerInfo(String author, String studentNumber, int assignment,
			int question, String questionTitle) {

		authorData = author;
		studentNumberData = studentNumber;
		assignmentData = assignment;
		questionData = question;
		questionTitleData = questionTitle;
		printProgrammerInfo();
	}

	/**
	 * Prints program details using the parameters passed as author, student
	 * number, assignment number and question number. The boolean isTesting sets
	 * whether the TestSuite object ought to be testing.
	 * 
	 * @param author
	 *            Name of the author
	 * @param studentNumber
	 *            Student number of the author, as String
	 * @param assignment
	 *            Assignment number, as int
	 * @param question
	 *            Question number, as int
	 * @param questionTitle
	 *            Title of the question, as String
	 * @param isTesting
	 *            Whether the TestSuite should be testing, as boolean
	 */
	public static TestSuite commonProgramStart(String author, String studentNumber, int assignment,
			int question, String questionTitle, boolean isTesting) {
		// Display programmer info
		printProgrammerInfo(author, studentNumber, assignment, question, questionTitle);

		// Test tracker
		TestSuite testSuite = new TestSuite();
		testSuite.setTesting(isTesting);
		return testSuite;

	}

	/**
	 * Displays a wide variety of test data from the TestSuite objects.
	 * 
	 * @param testSuite
	 *            Instance containing variety of tests, as type TestSuite
	 */
	public static void commonProgramEnd(TestSuite testSuite) {
		// Let's recap... display testing results, if applicable
		if (testSuite.isTesting()) {
			testSuite.printFullTestData();
		}

	}
}