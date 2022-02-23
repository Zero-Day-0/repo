public class QueueOverflowException extends RuntimeException {
	
	public QueueOverflowException () {
		super ("The queue is full.");
	}
}