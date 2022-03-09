import java.util.Comparator;
import java.util.ListIterator;

/**
 * 
 * @author aabad
 *
 * @param <T>
 */

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> c;

	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		c = comparator2;
	}

	public SortedDoubleLinkedList<T> add(T data) {
		if (size == 0) {
			super.addToFront(data);
			return this;
		}

		if (c.compare(head.getData(), data) > 0 || c.compare(tail.getData(), data) == 0) {
			super.addToFront(data);
			return this;
		}

		if (c.compare(tail.getData(), data) < 0 || c.compare(head.getData(), data) == 0) {
			super.addToEnd(data);
			return this;
		}

		int previousC = -1;
		int currentC;

		Node current = head;
		Node newNode;
		while (true) {
			currentC = c.compare(current.getData(), data);
			System.out.println(currentC);
			if (previousC < 0 && currentC > 0 || currentC == 0) {
				newNode = new Node(data, current.getPrev(), current);
				current.getPrev().setNext(newNode);
				current.setPrev(newNode);
				size++;
				break;
			}
			previousC = currentC;
			current = current.getNext();
		}
		return this;
	}

	@Override
	public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	@Override
	public void addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}

}