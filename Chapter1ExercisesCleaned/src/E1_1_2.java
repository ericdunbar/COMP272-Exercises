import java.io.BufferedReader;
import java.io.FileReader;

/*
2. Read the first 50 lines of input and then write them out in reverse
order. Read the next 50 lines and then write them out in reverse order.

Do this until there are no more lines left to read, at which
point any remaining lines should be output in reverse order.

In other words, your output will start with the 50th line, then the
49th, then the 48th, and so on down to the first line. This will be
followed by the 100th line, followed by the 99th, and so on down to
the 51st line. And so on.

Your code should never have to store more than 50 lines at any given
time.
 */

/**
 * Class: E1_1_2. Purpose: Reads a data file 50 lines at a time and prints them
 * in reverse order.
 * 
 * @author Eric
 *
 */
public class E1_1_2 {

	private static void printReverseStringArray(String[] theStrings, int length) {
		if (length > 0) {
			for (int i = length - 1; i >= 0; i--) {
				System.out.println(theStrings[i]);
			}
		}
	}

	public static void main(String[] args) {
		String[] theFifty = new String[50];
		int currentLength = 0;
		long someLong = 0;
		String theFileName = "C:\\Users\\erdun\\git\\Chapter1ExercisesCleaned\\src\\y.txt";

		try {
			BufferedReader theBuffer = new BufferedReader(new FileReader(theFileName));
			String lineIn;
			boolean isFinished = false;

			while (!isFinished) {
				while (currentLength < 50) {
					if ((lineIn = (String) theBuffer.readLine()) == null) {
						isFinished = true;
						break; // we're done
					}
					theFifty[currentLength++] = lineIn;
					someLong++;
				}
				printReverseStringArray(theFifty, currentLength);
				currentLength = 0;
			}

			theBuffer.close();
			System.out.println("...Done! " + someLong);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
