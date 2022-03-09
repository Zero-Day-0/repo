import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author aabad
 *
 * @param <T> generic double-linked list class with an iterator
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head;
	protected Node tail;
	protected int size;

	/**
	 * 
	 */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * @param data
	 * @return reference to the current object
	 */
	public void addToFront(T data) {
		if (head == null) {
			head = new Node(data, null, null);
			tail = head;
		} else {
			Node nextFirst = new Node(data, null, head);
			head.setPrev(nextFirst);
			head = nextFirst;
		}
		size++;
		return;
	}
	
	/**
	 * @param data
	 * @return reference to the current object
	 */
	public void addToEnd(T data) {
		if (tail == null) {
			tail = new Node(data, null, null);
			head = tail;
		} else {
			Node nextLast = new Node(data, tail, null);
			tail.setNext(nextLast);
			tail = nextLast;
		}
		size++;
		return;
	}

	/**
	 * Head of linked list
	 * @return the data element or null
	 */
	public T getFirst() {
		if (head == null) {
			return null;
		}
		return head.getData();
	}

	/**
	 * Tail of linked list
	 * @return last element
	 */
	public T getLast() {
		if (tail == null) {
			return null;
		}
		return tail.getData();
	}

	/**
	 * Size of linked list
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Iterator
	 * @return an iterator positioned at the head of the list
	 * @throws UnsupportedOperationException
	 * @throws NoSuchElementException
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
		return new DoubleIterator();
	}

	/**
	 * Removes first instance of targetData from list
	 * @param targetData
	 * @param comparator
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node current = head;
		
		if (size == 0) {
			return this;
		} if (size == 1) {
			if (comparator.compare(targetData, head.getData()) == 0) {
				head = null;
				tail = null;
				size--;
			}
			return this;
		}
		
		while (current != null) {
			if (comparator.compare (targetData, current.getData()) == 0) {
				if (current.equals(head)) {
					head = current.getNext();
					current.getNext().setPrev(null);
				} else if (current.equals(tail)) {
					tail = current.getPrev();
					current.getPrev().setNext(null); 
				} else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());				
				}
				
				size--;
				break;
			}
			current = current.getNext();
		}
		return this;
	}

	/**
	 * Removes and returns first element from linked list
	 * @return the data element or null
	 */
	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		T t = head.getData();

		if (size == 1) {
			head = null;
			tail = null;
			return t;
		}

		head = head.getNext();
		head.setPrev(null);
		size--;
		return t;
	}

	/**
	 * Removes and returns last element from linked list
	 * @return data of last element if exists, otherwise null
	 */
	public T retrieveLastElement() {
		if (tail == null) {
			return null;
		}
		T t = tail.getData();

		if (size == 1) {
			head = null;
			tail = null;
			return t;
		}

		tail = tail.getPrev();
		tail.setNext(null);
		size--;
		return t;
	}

	
	public ArrayList<T> toArrayList() {
		ArrayList<T> arr = new ArrayList<T>();
		DoubleIterator iter = (BasicDoubleLinkedList<T>.DoubleIterator) iterator();
		
		while (iter.hasNext()) {
			arr.add(iter.next());	
		}	
		return arr;
	}
	
	protected class Node {
		private T data;

		private Node prev, next;

		Node(T data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public Node getPrev() {
			return prev;
		}
		
		public void setPrev(Node n) {
			prev = n;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node n) {
			next = n;
		}
		
		public boolean equals(Node n) {
			if (next == n.getNext() && prev == n.getPrev() && data == n.getData()) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Double linked iterator
	 */
	protected class DoubleIterator implements ListIterator<T> {
		Node i;
		Node last;
		boolean isHead = false;
		boolean isTail = false;

		DoubleIterator() {
			i = head;
			isHead = true;
			last = null;
		}

		/**
		 * Checks if there is an element after in list
		 * @return true if not last element
		 */
		@Override
		public boolean hasNext() {
			return (i != null);
		}
		
		/**
		 * Checks if there is an element before in list
		 * @return true if not first element
		 */
		@Override
		public boolean hasPrevious() {
			return last != null;
		}

		/**
		 * Sets current pointer to next one
		 * @throw NoSuchElementException
		 * @return data of next element
		 */
		@Override
		public T next() throws NoSuchElementException {
			if (hasNext()) {
				last = i;
				i = i.getNext();
				return last.getData();
			}
			throw new NoSuchElementException();
		}

		/**
		 * Sets current pointer to previous one
		 * @throw NoSuchElementException
		 * @return data of previous element
		 */
		@Override
		public T previous() throws NoSuchElementException {
			if (hasPrevious()) {
				i = last;
				last = last.getPrev();
				return i.getData();
			}
			throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}

	}
}