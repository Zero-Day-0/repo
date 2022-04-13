import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that is used to convert morse code to English
 * @author Adam Abadiga
 */

public class MorseCodeConverter {
	private static final MorseCodeTree tree = new MorseCodeTree();

	/**
	 * Translate morse code into English
	 * @param code
	 * @return English translation
	 */
	protected static String convertToEnglish (String code) {
		String s = "";
		String[] letter;
		String[] word = code.split("/");
		for (String i: word) {
			letter = i.split(" ");
			for (int j = 0; j < letter.length; j++) {
				s += tree.fetch(letter[j]);
			}
			s += (" ");
		}
		return s.trim();
	}

	/**
	 * Convert file of morse code into English
	 * @param convertFile
	 * @return English translation
	 * @throws FileNotFoundException
	 */
	protected static String convertToEnglish(File convertFile) throws FileNotFoundException {
		BufferedReader fileReader = new BufferedReader(new FileReader(convertFile));
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		try {
			while ((line = fileReader.readLine()) != null) {
				sb.append(convertToEnglish(line));
				sb.append("\n");
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String str = sb.toString().trim();
		return str;
	}

	/**
	 * Print binary tree
	 * @return the data in the tree in LNR order separated by a space
	 */
	protected static String printTree() {
		ArrayList<String> morseTree = tree.toArrayList();
		String str = "";

		for (String letter : morseTree) {
			str += letter + " ";
		}
		return str.trim();
	}
}