public class QueueUnderflowException extends RuntimeException {
	
	public QueueUnderflowException () {
		super ("The queue is empty.");
	}
}
