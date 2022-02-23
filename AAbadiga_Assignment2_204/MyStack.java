import java.util.ArrayList;

/**
 * @author Adam Abadiga
 * @param
 **/

public class MyStack <T> implements StackInterface<T> {
	
	private Node frontNode;
	private int size;
	private T[] stack;
    private class Node {
	private T data;
	private Node next;
    }
    public MyStack() {
        frontNode = null;
        size = 0;
    }
    
	@Override
	public boolean isEmpty() {
		return frontNode == null;
	}

	@Override
	public boolean isFull() {
		return size == stack.length;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		stack[size++] = e;
		return true;
	}

	@Override
	public T pop() throws StackUnderflowException{
		if (frontNode == null) {
			throw new StackUnderflowException ();
		} else {
			T p = frontNode.data;
			frontNode = frontNode.next;
			size--;
			return p;
		}
	}

	public T[] toArray() {
	    int count = 0;
		for (Node n = frontNode; n != null; n = n.next) {
			count++;
		}
		
		T[] x= (T[]) new Object[count];
		int num_one = 0;
		for (Node n = frontNode; n!= null; n = n.next) {
			 x[num_one] = n.data;
			 num_one++;
		}
		
		T[] t= (T[]) new Object[x.length];
		int num_two = x.length;
		for (int cc = 0; cc < (x.length); cc++) {
			t[cc] = x[num_two-1];
			num_two = num_two-1; 
		}
		return t;
	}

	@Override
	public T top() throws StackUnderflowException {
		return null;
	}

	@Override
	public String toString(String delimiter) {
		return null;
	}

	@Override
	public void fill(ArrayList<T> list) {		
	}
}