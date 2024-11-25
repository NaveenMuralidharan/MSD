package assignment05;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>{
    private Node head;
    private int size;

    private class Node{
        E data;
        Node next;

        Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    /** default constructor */
    public SinglyLinkedList(){
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node newNode = new Node(element);
        newNode.next = this.head;
        this.head = newNode;
        size++;
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index   - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(index == 0){
            insertFirst(element);
            return;
        }
        Node newNode = new Node(element);
        Node current = head;
        for(int i=0; i<index-1; i++){
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        return current.data;
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("List is empty");
        }
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(index == 0){
            return deleteFirst();
        }
        Node current = head;
        for(int i=0; i<index-1; i++){
            current = current.next;
        }
        E data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;

    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(E element) {
        Node current = head;
        int index = 0;
        while(current != null){
            if(current.data.equals(element)){
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list.
     * O(1) for a singly-linked list.
     */
    @Override
    public void clear() {
        this.head = null;
        size = 0;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        Object[] array  = new Object[size];
        int i=0;
        Node current = head;

        while(current != null){
            array[i] = current.data;
            current = current.next;
            i++;
        }

        return array;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator iterator() {
        return new SinglyLinkedListIterator();
    }

    //Nested iterator class
    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node current = head;
        private Node lastReturned = null;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException("No more elements");
            }
            lastReturned = current;
            current = current.next;
            index++;
            return lastReturned.data;
        }

        @Override
        public void remove() {
            if(lastReturned == null){
                throw new IllegalStateException("Nothing to remove");
            }
            if(lastReturned == head){
                head = head.next;
            } else {

//                while(current != null && current.next != lastReturned){
//                    current = current.next;
//                }
//                if(current != null){
//                    current.next = lastReturned.next;
//                }
                Node removeNode = head;
                while(removeNode != null && removeNode.next != lastReturned){
                    removeNode = removeNode.next;
                }
                if(removeNode != null) {
                    removeNode.next = current;
                }
            }
            size--;
            lastReturned = null;
        }
    }
}
