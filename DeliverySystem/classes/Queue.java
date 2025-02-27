//-----------------------------------------------------
// Title: Queue.java class
// 
// Description: This class tests represents custom queue class 
//-----------------------------------------------------


import java.util.NoSuchElementException;

public class Queue<T> {
    private DoublyLinkedList<T> queueList;//instance of DoublyLinkedList to store the queue elements

    public Queue() { //constructor
        queueList = new DoublyLinkedList<>();
    }

    
    public void enqueue(T item) {
        queueList.addLast(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queueList.removeFirst();
    }
    
    public T peek() {
        if (isEmpty()) {
            return null; 
        }
        return queueList.getFirst();
    }

    public boolean isEmpty() {
        return queueList.isEmpty();
    }
}
