package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
    A {@code BinarySearchSet} is a SortedSet implementation that uses binary search to store elements in sorted order.
    This allows no duplicates, faster lookup, addition and removal of elements.
    @param "E" is the type of element in this set.
 */

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

    private E[] elements;
    private int size;
    private Comparator<? super E> comparator;

    /* constructs a new empty set, with natural ordering for sorting elements
     */
    public BinarySearchSet(){
        this.comparator = null;
        elements = (E[]) new Object[10];
        size = 0;
    }
    /** constructs a new empty set, with the provided comparator for ordering elements
        @param comparator object used for ordering elements
     **/
    public BinarySearchSet(Comparator<? super E> comparator){
        this.comparator = comparator;
        elements = (E[]) new Object[10];
        size = 0;
    }
    /** compares two elements based on natural ordering or comparator if provided.
        @param o1 - first element to compare
        @param o2 - second element to compare
        @return negative integer, zero or positive integer if first element is less than, equal to or greater than second respectively.
    **/
    private int compare(E o1, E o2){
        if(this.comparator != null){
            return comparator.compare(o1,o2);
        } else {
            return ((Comparable<? super E>)o1).compareTo(o2);
        }
    }

    /**
        returns the comparator to order elemnts in this set if it exists else returns null
    **/
    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    /**
       Returns first element in sorted set.
        @return the first element in set.
        @throws NoSuchElementException if set is empty
    **/
    @Override
    public E first() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("Set is empty");
        } else {
            return elements[0];
        }
    }

    /**
     * returns last element in set
     * @return last element in set
     * @throws NoSuchElementException if set is empty
     */
    @Override
    public E last() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("Set is empty");
        } else {
            return elements[size -1 ];
        }
    }

    /**
     * performs binary search on set to find index of specific element or insertion point in sorted order if element is not in set
     * @param element to be found in set
     * @return the index of element if found, negative value of sorted insertion index if not found
     */
    private int binarySearch(E element){
        int low = 0;
        int high = size - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            int comparison = compare(element, elements[mid]);
            if(comparison == 0){
                return mid;
            } else if(comparison < 0){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -(low + 1); //insertion point (negative value)
    }

    /**
     * resizes the internal array, increasing its length by 2x current length if it is full
     */
    private void resize(){
        E[] newArray = (E[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    /**
     * adds a new element if it is not already present in the set
     * @param element element to be added to this set
     * @return {@code true} if element was added, {@code false} if it was a duplicate element and cannot be added
     */
    @Override
    public boolean add(E element) {
        //check for null parameters
        if(element == null){
            throw new NullPointerException("cannot add null element");
        }
        //check for duplicates or insertion index
        int index = binarySearch(element);
        if(index >= 0){
            return false; // no duplicates allowed
        }
        //index where element should be inserted
        index = -(index + 1);
        //resize array if necessary
        if(size == elements.length){
            resize();
        }
        //shift elements and insert new element
        System.arraycopy(elements, index, elements, index+1, size - index);
        elements[index] = element;
        size ++;

        return true;
    }

    /**
     * adds all elements in a provided collection to the set
     * @param es collection containing elements to be added to this set
     * @return {@code true} if any elements were added, {@code false} if none were added
     */
    @Override
    public boolean addAll(Collection<? extends E> es) {
        boolean added = false;
        for(E element : es){
            if(add(element)){
                added = true;
            }
        }
        return added;
    }

    /**
     * Removes all elements from the set, making it empty, setting size to original size
     */
    @Override
    public void clear() {
        size = 0;
        elements = (E[]) new Object[10];
    }

    /**
     * checks if an element is contained within the set
     * @param element element whose presence in this set is to be tested
     * @return {@code true} if element is present, {@code false} if otherwise
     */
    @Override
    public boolean contains(E element) {
        return binarySearch(element) >= 0;
    }

    /**
     * checks if all elements in a collection are contained within the set
     * @param es collection to be checked for containment in this set
     * @return {@code true} if all elements are present, {@code false} if otherwise
     */
    @Override
    public boolean containsAll(Collection<? extends E> es) {

        for(E element: es){
            if(!contains(element)){
                return false;
            }
        }
        return true;
    }

    /**
     * checks if set is empty
     * @return {@code true} if set is empty, {@code false} if otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * creates and returns an iterator for the set
     * @return an iterator for the set
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * An Iterator for the BinarySearchSet class
     */
    private class MyIterator implements Iterator<E>{
        private int index;

        MyIterator(){
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return elements[index++];
        }

        @Override
        public void remove() {
            //remove last returned element from underlying set
            int lastReturned = index - 1;
            if(lastReturned < 0){
                throw new IllegalStateException("No element to remove");
            } else {
                BinarySearchSet.this.remove(elements[lastReturned]);
            }
        }
    }

    /**
     * removes the given element if it's present in set
     * @param element element to be removed from this set, if present
     * @return {@code true} if element is removed, {@code false} if otherwise
     */
    @Override
    public boolean remove(E element) {
        int index = binarySearch(element);
        if(index < 0){
            return false;
        }
        //shift elements to the left and remove E
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return true;
    }

    /**
     * removes all the elements of collection present in set
     * @param es of elements to be removed from this set, if present
     * @return {@code true} if element is any element is removed, {@code false} if otherwise
     */
    @Override
    public boolean removeAll(Collection<? extends E> es) {
        boolean modified = false;
        for(E element : es){
            if(remove(element)){
                modified = true;
            }
        }
        return modified;
    }

    /**
     * returns current size of set
     * @return size of set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns an array form of the sorted set
     * @return array of sorted set
     */
    @Override
    public E[] toArray() {
        E[] newArray = (E[]) new Object[size];
        System.arraycopy(elements, 0, newArray, 0, size);
        return newArray;
    }
}
