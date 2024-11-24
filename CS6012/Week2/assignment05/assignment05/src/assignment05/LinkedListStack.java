package assignment05;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
    private SinglyLinkedList<E> stackList;

    public LinkedListStack(){
        stackList = new SinglyLinkedList<>();
    }
    /**
     * Removes all of the elements from the stack.
     */
    @Override
    public void clear() {
        stackList.clear();
    }

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        return stackList.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() throws NoSuchElementException {
        return stackList.deleteFirst();
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    @Override
    public void push(E element) {
        stackList.insertFirst(element);
    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return stackList.size();
    }
}
