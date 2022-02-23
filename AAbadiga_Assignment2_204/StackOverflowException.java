public class StackOverflowException extends RuntimeException {
	
	public StackOverflowException () {
		super ("The stack is full.");
	}
}