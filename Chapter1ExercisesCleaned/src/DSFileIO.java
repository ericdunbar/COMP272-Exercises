import java.io.BufferedReader;
import java.io.FileReader;

public class DSFileIO {
	public static BufferedReader openAFile(String fileName) {
		try {
			FileReader theFReader;
			theFReader = new FileReader(fileName);
			BufferedReader theBReader = new BufferedReader(theFReader);
			return theBReader;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
