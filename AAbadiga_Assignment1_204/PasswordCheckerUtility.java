import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author aabad
 *
 */
public class PasswordCheckerUtility {
	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}
	}
	/**
	 * 
	 * @param passwords
	 * @return
	 */
	static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalid = new ArrayList<String>();
		for (int i = 0; i < passwords.size(); i++) {
			try {
				isValidPassword(passwords.get(i));
			}
			catch (Exception e) {
				invalid.add(passwords.get(i) + " -> " + e.getMessage());
			}
		}
		return invalid;
	}
	/**
	 * 
	 * @param password
	 * @param validPassword
	 * @return
	 */
	static boolean comparePasswordsWithReturn(String password, String validPassword) {
		return password.equals(validPassword);
	}
	/**
	 * 
	 * @param password
	 * @return
	 */
	static boolean hasBetweenSixAndNineChars(String password) {
		return (6 <= password.length() && password.length() <= 9);
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoDigitException
	 */
	static boolean hasDigit(String password) throws NoDigitException {
		if (!password.matches(".*\\d.*")) {
			throw new NoDigitException();
		}
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoSpecialCharacterException
	 */
	static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		if (!password.matches(".*[^A-Za-z0-9].*")) {
			throw new NoSpecialCharacterException();
		}
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoUpperAlphaException
	 */
	static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		if (!password.matches(".*[A-Z].*")) {
			throw new NoUpperAlphaException();
		}
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoLowerAlphaException
	 */
	static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		if (!password.matches(".*[a-z].*")) {
			throw new NoLowerAlphaException();
		}
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws InvalidSequenceException
	 */
	static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		for (int i = 0; i < password.length(); i++) {
			try {
				if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2))
					throw new InvalidSequenceException();
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws LengthException
	 */
	static boolean isValidLength(String password) throws LengthException {
		if (password.length() < 6) {
			throw new LengthException();
		} return true;
	}
	/**
	 * 
	 * @param newPass
	 * @return
	 * @throws WeakPasswordException
	 */
	static boolean isWeakPassword(String newPass) throws WeakPasswordException {
		try {
			if (!(hasBetweenSixAndNineChars(newPass)) && isValidPassword(newPass)) {
				return false;
			}
		} catch (Exception e) {
			throw new WeakPasswordException();
		} throw new WeakPasswordException();
	}
	/**
	 * 
	 * @param newPass
	 * @return
	 * @throws Exception
	 */
	static boolean isValidPassword(String newPass) throws Exception {
		try {
			if (
				isValidLength(newPass) &&
				hasDigit(newPass) &&
				hasUpperAlpha(newPass) &&
				hasLowerAlpha(newPass) &&
				hasSpecialChar(newPass) &&
				NoSameCharInSequence(newPass))
				return true;
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
}