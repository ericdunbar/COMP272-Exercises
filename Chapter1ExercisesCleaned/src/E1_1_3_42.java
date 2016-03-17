import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class: Exercise 1.1.3 42 lines Author: Eric D. Date: 16/03/2016
 * 
 * 3. Read the input one line at a time. At any point after reading the first 42
 * lines, if some line is blank (i.e., a string of length 0), then output the
 * line that occurred 42 lines prior to that one. For example, if Line 242 is
 * blank, then your program should output line 200.
 * 
 * This program should be implemented so that it never stores more than 43 lines
 * of the input at any given time.
 */

public class E1_1_3_42 {

	public static void main(String[] args) {
		FileReader theFReader;
		try {
			theFReader = new FileReader(DSCommonUtils.theFileNameX3);
			BufferedReader theBReader = new BufferedReader(theFReader);

			String[] theArray = new String[42];
			int currentPosition = 0;

			String theInput;

			while ((theInput = (String) theBReader.readLine()) != null) {
//				System.out.print("x" + theInput + "X  ");
//				System.out.println((currentPosition - 42) % 42 + " " + currentPosition);
				theArray[(currentPosition++)] = theInput;
				if (currentPosition > 41) {
					break;
				}
			}

			
			while ((theInput = (String) theBReader.readLine()) != null) {
				if (theInput.equals("")){
					System.out.println((currentPosition - 42) % 42 + " " + currentPosition);
					System.out.println("            \"" + theArray[(currentPosition - 42) % 42] + "\"");
				}
				theArray[(currentPosition++) % 42] = theInput;
			}

			theBReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
