import java.util.ArrayList;

/**
 * @author Adam Abadiga
 * @param
 **/

public class MyQueue <T> implements QueueInterface<T>{
	
	 private Node frontNode;
	 private Node lastNode; 
	 private int size; 
	 
	 private class Node { 
	   T data;
	   Node next;
	 }
	 
	 public MyQueue() {
	   frontNode = null;
	   lastNode = null;
	   size = 0;
	 }
	  
	 @Override
		public int size() {
			return size;
		}
	 
	 @Override
	 public boolean isEmpty() {
		return (frontNode == null);
	 }

	 @Override
	 public boolean isFull() {
	     int count = 0;
		 for (Node n = frontNode; n != null; n = n.next) {
			 count++;
		 }
		 if (count >= size) {
			 return true;
		 } else {
			 return false;
		 }
	 }

	 @Override
	 public boolean enqueue(T item) throws QueueOverflowException {
		   Node lastLastNode = lastNode;
		   lastNode = new Node();
		   lastNode.data = item;
		   lastNode.next = null;
		   if (isEmpty()) {
		     frontNode = lastNode;
		     size++;
		     return true;
		   }
		   else {
			 if(!isFull()) {
		     lastLastNode.next = lastNode;
		     size++;
		     return true;
			 }
			 else {
				 throw new QueueOverflowException();
			 }
		   }
	 }
	
	 @Override
	 public T dequeue() throws QueueUnderflowException {
	    if (isEmpty()) {
		      throw new QueueUnderflowException ();
		    }
		    else {
		    	T t = frontNode.data;
		    	frontNode = frontNode.next;
		    	size--;
		    	return t;
		    }
	  }

	 public T[] toArray() {
	     int count = 0;
		 for (Node n = frontNode; n != null; n = n.next) {
			 count++;
		 }
		
		T[] x= (T[]) new Object[count];
		int num_one = 0;
		for (Node n = frontNode; n != null; n = n.next) {
			 x[num_one] = n.data; 
			 num_one++;
		 }
		 return x;
	}

	@Override
	public String toString(String delimiter) {
		return null;
	}

	@Override
	public void fill(ArrayList<T> list) {
	}
}