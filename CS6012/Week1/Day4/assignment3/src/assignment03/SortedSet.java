package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A set that provides a total ordering on its elements. The elements are
 * ordered using their natural ordering (i.e., using compareTo() from the
 * Comparable interface), or by a Comparator provided at sorted set creation
 * time. Thus, all elements inserted into a sorted set must implement the
 * Comparable interface or be accepted by the specified Comparator. The set's
 * iterator will traverse the set in ascending element order.
 * 
 * @param <E>
 *          the type of elements maintained by this set
 */
public interface SortedSet<E> {

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	public Comparator<? super E> comparator();

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException if the set is empty
	 */
	public E first() throws NoSuchElementException;

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException if the set is empty
	 */
	public E last() throws NoSuchElementException;

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param  element element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	public boolean add(E element);

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param elements collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	public boolean addAll(Collection<? extends E> elements);

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	public void clear();

	/**
	 * @param element element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	public boolean contains(E element);

	/**
	 * @param elements collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	public boolean containsAll(Collection<? extends E> elements);

	/**
	 * @return true if this set contains no elements
	 */
	public boolean isEmpty();

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	public Iterator<E> iterator();

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param element element to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	public boolean remove(E element);

	/**
	 * Removes from this set all of its elements that are contained in the
	 * specified collection.
	 * 
	 * @param elements collection containing elements to be removed from this set
	 * @return true if this set changed as a result of the call
	 */
	public boolean removeAll(Collection<? extends E> elements);

	/**
	 * @return the number of elements in this set
	 */
	public int size();

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	public E[] toArray();


}

