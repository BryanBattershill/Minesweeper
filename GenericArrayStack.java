// Author: Bryan Battershill
// Student number: 300014415
// Course: ITI 1121-B
// Assignment: 2


public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
	private E[] elems;
	private int top;
   // Constructor
   
	@SuppressWarnings("unchecked")
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE
		elems = (E[])new Object[capacity];
		top = 0;

    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
		return elems[0] == null;

    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
		elems[top] = elem;
		top++;

    }
    public E pop() {
        
    // ADD YOU CODE HERE
		E temp = elems[top-1];
		elems[top-1] = null;
		top--;
		return temp;

    }

    public E peek() {
        
    // ADD YOU CODE HERE
		return elems[top-1];

    }
}
