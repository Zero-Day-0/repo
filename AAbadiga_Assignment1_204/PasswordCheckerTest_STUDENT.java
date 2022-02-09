import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Adam Abadiga
 *
 */
/**
 * @author aabad
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	String password, newPass;
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		String[] passwordArray = {"OFEh790FU9FH", "wklefunjqewf", "0278051284351", "felnudVW", "03rjrfuiweHF23", "KNOJCnjsdkvCNA", "wqu9efyawyer23", "bcfhiwebifhwEBOF", "awifh23htq", "OFH9234QTRT", "sjsvzs", "827532nkr"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(passwordArray)); 
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort() {
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("faN92gbf942!@"));
		}
		catch (Exception e) {
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password that is too short
		assertThrows(LengthException.class, () -> PasswordCheckerUtility.isValidPassword("f!@"));
	}
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("fG1@sdgf"));
		}
		catch (Exception e) {
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with no digits
		assertThrows(NoDigitException.class, () -> PasswordCheckerUtility.isValidPassword("fGk@sdgf"));
	}
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() {
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("ieagvfASF4398!@"));
		}
		catch (Exception e) {
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with no Uppercase Alpha characters
		assertThrows(NoUpperAlphaException.class, () -> PasswordCheckerUtility.isValidPassword("ieagvfrnb4398!"));
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() {
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("DFASFsd4398!@"));
		}
		catch (Exception e) {
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with no Lowercase Alpha characters
		assertThrows(NoLowerAlphaException.class, () -> PasswordCheckerUtility.isValidPassword("DFASFSD4398!@"));
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("fG1@sdgf"));
		}
		catch (Exception e) {
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with 2 of the same character in a row
		assertThrows(InvalidSequenceException.class, () -> PasswordCheckerUtility.isValidPassword("ffG1@sdgf"));
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		// Try strong valid password
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword("!2Dfghjasdfsd"));
		}
		catch (Exception e) {
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try weak password
		assertThrows(WeakPasswordException.class, () -> PasswordCheckerUtility.isWeakPassword("!2Dfghj"));
	}
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()	{
		// Try valid passwords
		try	{
			assertTrue(PasswordCheckerUtility.isValidPassword("fG1!ohjb"));
			assertTrue(PasswordCheckerUtility.isValidPassword("lK9#gjsg"));
			assertTrue(PasswordCheckerUtility.isValidPassword("SGHDBgjh149!"));
			assertTrue(PasswordCheckerUtility.isValidPassword("wiIFEGB328%"));
		}
		catch (Exception e)	{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(passwords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "JOFejfnjwe");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "we8f6wefh");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "7oefnOWEF");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "857348957");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "wgeWW34T4JT");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "9ijiqj345q");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );
	}
	
}