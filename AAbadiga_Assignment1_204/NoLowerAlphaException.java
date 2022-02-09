/**
 * 
 * @author Adam Abadiga
 *
 */
/**
 * @author aabad
 *
 */
public class NoLowerAlphaException extends Exception {
	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase alphabetic character.");
	}

}