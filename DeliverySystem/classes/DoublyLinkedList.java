//-----------------------------------------------------
// Title: DoublyLinkedList.java class
// Author: Basak Nehir Artan
// ID: 26051100858
// Section: 2
// Assignment: 1
// Description: This class represents the custom implementation of doubly linked list used by both the stack and queue classes
//-----------------------------------------------------



public class DoublyLinkedList<T> { //T is for representing generic types in the class 
    // Node, add, remove, and other list methods like traversing the list

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        Node( T data){  //constructor for node
            this.data=data;
        }}

        public DoublyLinkedList(){
            this.head=null;
            this.tail=null;
            this.size=0;
        }

        public void addFirst(T data){
            Node<T> newNode= new Node<>(data);
            if(head==null){
                //this is the first element: set both head and tail to it , head=tail=newNode
                head= newNode;
                tail=newNode;
            }
            else{
                newNode.setNext(head); //pointing new node's next to current head
                head.setPrev(newNode); //setting current head's prev to the new node
                head= newNode; //updating head to the new node
            }

            size++;
            }

        public void addLast(T data){
            Node<T> newNode= new Node<>(data);
            if(tail==null){
                head=tail=newNode;
                newNode.setPrev(null);
                newNode.setNext(null);
            }
            else{
                tail.setNext(newNode);//pointing the current tail's next to the new node
                newNode.setPrev(tail);//setting the new node's prev to current tail
                newNode.setNext(null);
                tail=newNode;//updating tail to the new node
            }
            size++;
        }    

        public T removeFirst(){
            if(head==null){
                return null; //empty list
            }
            T data= head.data;
            if(head==tail){
                head=tail=null; //one element only
            }
            else{
                head=head.next;
                head.prev=null;
            }
            size--;
            return data;
        }

        public T removeLast(){
            if(tail==null){
                return null; //empty list
            }
            T data=tail.data;
            if(head==tail){
                head=tail=null;
            }
            else{
                tail=tail.prev;
                tail.next=null;
            }

            size--;
            return data;
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size==0;
        }


//writing traversing methods for debugging 
        public void TraversingFromHeadtoTail(){
            Node<T> current=head;
            while(current!=null){
                System.out.println(current.data + " ");
                current= current.next;   
            }
            System.out.println();
        }

        public void TraversingFromTailtoHead(){
            Node<T> current = tail;
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.prev;
            }
            System.out.println();
        }



        }

    

