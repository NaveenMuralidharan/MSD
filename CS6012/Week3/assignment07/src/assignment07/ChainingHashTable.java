package assignment07;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{
    private LinkedList<String>[] storage;
    private int size;
    private HashFunctor functor;
    private static final double loadFactorThreshold = 0.5;
    private int collisions = 0;

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor){
        this.functor = functor;
        this.storage = (LinkedList<String>[]) new LinkedList[capacity];
        this.size = 0;

    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        //rehash if needed
        if(size >= storage.length * loadFactorThreshold){
            rehash();
        }
        int index = functor.hash(item) % storage.length;
        if(index < 0){
            index += storage.length;
        }
        if(storage[index]==null){
            storage[index] = new LinkedList<>();
        }
        if(!storage[index].contains(item)){
            storage[index].add(item);
            size++;
            if (!storage[index].isEmpty()) {
                collisions++;  // Increment collision count if there's already something in the bucket
            }
            return true;
        }
        return false;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean modified = false;
        for(String i : items){
            if(add(i)){
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        storage = (LinkedList<String>[]) new LinkedList[storage.length];
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(String item) {
        int index = functor.hash(item) % storage.length;
        if(index < 0){
            index += storage.length;
        }
        return storage[index] != null && storage[index].contains(item);
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {

        for(String i : items){
            if(!contains(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(String item) {
        int index = functor.hash(item) % storage.length;
        if(index < 0){
            index += storage.length;
        }
        if(storage[index] != null && storage[index].remove(item)){
            size--;
            return true;
        }
        return false;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        boolean modified = false;
        for(String i : items){
            if(remove(i)){
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Rehash the table by doubling the size and redistributing items
     */
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<String>[] oldStorage = storage;
        storage = (LinkedList<String>[]) new LinkedList[oldStorage.length * 2];
        size = 0;
        collisions = 0;
        for(LinkedList<String> bucket : oldStorage){
            if(bucket != null){
                for(String i : bucket){
                    add(i);
                }
            }
        }
    }

    public int getCollisions(){
        return collisions;
    }

}
