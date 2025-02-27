//-----------------------------------------------------
// Title: Stack.java class

// Description: This class represents custom stack class
//-----------------------------------------------------


public class Stack<T> {
    private DoublyLinkedList<T> stackList = new DoublyLinkedList<>();


    public void push(T item) {
        stackList.addLast(item); 
    }

    
    public T pop() {
        return stackList.removeLast(); 
    }

    // peek: get the item from the top of the stack (last item) without removing it
    public T peek() {
        return stackList.getLast(); 
    }


    public boolean isEmpty() {
        return stackList.isEmpty();
    }
}
