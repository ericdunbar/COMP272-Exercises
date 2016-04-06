import java.util.ArrayList;

/**
 * Provide utilities for unit 1 of Data Structures by Pat Morin
 * 
 * @author Eric Dunbar
 *
 */
public class DSCommonUtils {

	public static String theFileNameZ = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\z.txt";
	public static String theFileNameY = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\y.txt";
	public static String theFileNameX = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\x.txt";
	public static String theFileNameX2 = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\x2.txt";
	public static String theFileNameX3 = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\x3.txt";

	
	public static ArrayList<String> theOne = new ArrayList<String>();
	public static ArrayList<String> theTwo = new ArrayList<String>();
	public static ArrayList<String> theThree = new ArrayList<String>();
	public static ArrayList<String> theFour = new ArrayList<String>();
	public static ArrayList<String> theFive = new ArrayList<String>();
	public static ArrayList<String> theSix = new ArrayList<String>();
	public static ArrayList<String> theSeven = new ArrayList<String>();
	public static ArrayList<String> theEight = new ArrayList<String>();
	public static ArrayList<String> theNine = new ArrayList<String>();


	
	public static void generateProblemDesc() {
		theOne.add("1. Read the input one line at a time and then write the lines out in");
		theOne.add("reverse order, so that the last input line is printed first, then the");
		theOne.add("second last input line, and so on.");

		theTwo.add("2. Read the first 50 lines of input and then write them out in reverse");
		theTwo.add("order. Read the next 50 lines and then write them out in reverse order.");
		theTwo.add("");
		theTwo.add("Do this until there are no more lines left to read, at which");
		theTwo.add("point any remaining lines should be output in reverse order.");
		theTwo.add("");
		theTwo.add("In other words, your output will start with the 50th line, then the");
		theTwo.add("49th, then the 48th, and so on down to the first line. This will be");
		theTwo.add("followed by the 100th line, followed by the 99th, and so on down to");
		theTwo.add("the 51st line. And so on.");
		theTwo.add("");
		theTwo.add("Your code should never have to store more than 50 lines at any given");
		theTwo.add("time.");

		theThree.add("3. Read the input one line at a time. At any point after reading the");
		theThree.add("first 42 lines, if some line is blank (i.e., a string of length 0), then");
		theThree.add("output the line that occured 42 lines prior to that one. For example,");
		theThree.add("if Line 242 is blank, then your program should output line 200.");
		theThree.add("This program should be implemented so that it never stores more");
		theThree.add("than 43 lines of the input at any given time.");

		theFour.add("4. Read the input one line at a time and write each line to the output");
		theFour.add("if it is not a duplicate of some previous input line. Take special care");
		theFour.add("so that a file with a lot of duplicate lines does not use more memory");
		theFour.add("than what is required for the number of unique lines.");

		theFive.add("5. Read the input one line at a time and write each line to the output");
		theFive.add("only if you have already read this line before. (The end result is that");
		theFive.add("you remove the first occurrence of each line.) Take special care so");
		theFive.add("that a file with a lot of duplicate lines does not use more memory");
		theFive.add("than what is required for the number of unique lines.");

		theSix.add("6. Read the entire input one line at a time. Then output all lines sorted");
		theSix.add("by length, with the shortest lines first. In the case where two lines");
		theSix.add("have the same length, resolve their order using the usual sorted");
		theSix.add("order. Duplicate lines should be printed only once.");

		theSeven.add("7. Do the same as the previous question except that duplicate lines");
		theSeven.add("should be printed the same number of times that they appear in the");
		theSeven.add("input.");

		theEight.add("8. Read the entire input one line at a time and then output the even");
		theEight.add("numbered lines (starting with the first line, line 0) followed by the");
		theEight.add("odd-numbered lines.");

		theNine.add("9. Read the entire input one line at a time and randomly permute the");
		theNine.add("lines before outputting them. To be clear: You should not modify");
		theNine.add("the contents of any line. Instead, the same collection of lines should");
		theNine.add("be printed, but in a random order.");
	}
	
	
	/** Generate the descriptive text for exercise 1.1
	 * 
	 * @return Intro for 1.1
	 */
	public static ArrayList<String> GenerateOverview(){

		ArrayList<String> toReturn = new ArrayList<String>();

		toReturn.add("Exercise 1.1.");
		toReturn.add("");
		toReturn.add("This exercise is designed to help familiarize the reader with");
		toReturn.add("choosing the right data structure for the right problem. If implemented,");
		toReturn.add("the parts of this exercise should be done by making use of an implementation");
		toReturn.add("of the relevant interface (Stack, Queue, Deque, USet, or SSet) provided");
		toReturn.add("by the Java Collections Framework.");
		toReturn.add("");
		toReturn.add("Solve the following problems by reading a text file one line at a time");
		toReturn.add("and performing operations on each line in the appropriate data structure(s).");
		toReturn.add("Your implementations should be fast enough that even files containing");
		toReturn.add("a million lines can be processed in a few seconds.");
		
		return toReturn;
	}

	/**
	 * Print an ArrayList<String>
	 * @param theList an ArrayList<String>
	 */
	public static void printArrayList(ArrayList<String> theList){
		for (String theStr : theList) {
			System.out.println(theStr);
		}
	}
}
