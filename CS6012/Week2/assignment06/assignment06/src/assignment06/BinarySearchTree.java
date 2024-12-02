package assignment06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
/**
 * A binary search tree (BST) implementation of the {@code SortedSet} interface.
 * This implementation allows for efficient add, remove, and contains operations,
 * as well as retrieval of the first and last elements in sorted order.
 *
 * @param <T> the type of elements in the set, which must implement {@code Comparable}.
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
    private BSTNode root;
    private int size;

    /**
     * A node in the binary search tree.
     */
    private class BSTNode{
        T data;
        BSTNode left;
        BSTNode right;
        /**
         * Creates a new node with the specified data.
         *
         * @param data the data to be stored in the node
         */
        BSTNode(T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Constructs an empty {@code BinarySearchTree}.
     */
    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean add(T item) {
        if(item == null){
            throw new NullPointerException("Item cannot be null");
        }
        if(contains(item)){
            return false;
        }
//        //Insert the item to the BST
//        root = addRecursive(root, item);
//        size++;
//        return true;
        // Insert the item to the BST iteratively
        if (root == null) {
            root = new BSTNode(item);
            size++;
            return true;
        }

        BSTNode current = root;
        while (true) {
            int comparison = item.compareTo(current.data);
            if (comparison < 0) {
                // Go left
                if (current.left == null) {
                    current.left = new BSTNode(item);
                    size++;
                    return true;
                }
                current = current.left;
            } else if (comparison > 0) {
                // Go right
                if (current.right == null) {
                    current.right = new BSTNode(item);
                    size++;
                    return true;
                }
                current = current.right;
            } else {
                // Item already exists
                return false;
            }
        }
    }
    /**
     * Helper method to recursively insert an item into the binary search tree.
     *
     * @param node the current node in the tree
     * @param item the item to be inserted
     * @return the new root node after insertion
     */
    private BSTNode addRecursive(BSTNode node, T item){
        if(node == null){
            return new BSTNode(item);// Insert a new node if the current node is null
        }
        int comparison = item.compareTo(node.data);
        if(comparison < 0){
            node.left = addRecursive(node.left, item);
        } else if(comparison > 0){
            node.right = addRecursive(node.right, item);
        }
        // If comparison == 0, the item already exists in the tree and is not added
        return node;
    }
    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean modified = false;
        for(T item: items){
            if(item == null){
                throw new NullPointerException("item cannot be null");
            }
            if(add(item)){
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean contains(T item) {
        if(item == null){
            throw new NullPointerException("item cannot be null");
        }
//        return containsRecursive(root, item);
        BSTNode current = root;
        while (current != null) {
            int comparison = item.compareTo(current.data);
            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return true;  // Found the item
            }
        }
        return false;  // Item not found
    }

    /**
     * Helper method to recursively check if an item is in the binary search tree.
     *
     * @param node the current node in the tree
     * @param item the item to be searched for
     * @return {@code true} if the item is found, {@code false} otherwise
     */
    private boolean containsRecursive(BSTNode node, T item){
        if(node == null){
            return false;
        }
        int comparison = item.compareTo(node.data);
        if(comparison < 0){
            return containsRecursive(node.left, item);
        } else if(comparison > 0){
            return containsRecursive(node.right, item);
        }
        return true;
    }


    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) {

        for(T item: items){
            if(item == null){
                throw new NullPointerException("item cannot be null");
            }
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        if(root == null){
            throw new NoSuchElementException("Set is empty");
        }
        return firstRecursive(root);
    }
    /**
     * Helper method to recursively find the smallest item in the binary search tree.
     *
     * @param node the current node in the tree
     * @return the smallest item in the tree
     */
    private T firstRecursive(BSTNode node){
        if(node.left == null){
            return node.data;
        }
        return firstRecursive(node.left);
    }
    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        if(root == null){
            throw new NoSuchElementException("set is empty");
        }
        return lastRecursive(root);
    }
    /**
     * Helper method to recursively find the largest item in the binary search tree.
     *
     * @param node the current node in the tree
     * @return the largest item in the tree
     */
    private T lastRecursive(BSTNode node){
        if(node.right ==  null){
            return node.data;
        }
        return lastRecursive(node.right);
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean remove(T item) {
        if(item == null){
            throw new NullPointerException("Item cannot be null");
        }
        if(contains(item)){
            root = removeRecursive(root, item);
            size--;
            return true;
        }
        return false;
    }
    /**
     * Helper method to recursively remove an item from the binary search tree.
     *
     * @param node the current node in the tree
     * @param item the item to be removed
     * @return the new root node after removal
     */
    private BSTNode removeRecursive(BSTNode node, T item){
        if(node == null){
            return null;
        }
        int comparison = item.compareTo(node.data);
        if (comparison < 0) {
            // Item is smaller, go to the left subtree
            node.left = removeRecursive(node.left, item);
        } else if (comparison > 0) {
            // Item is larger, go to the right subtree
            node.right = removeRecursive(node.right, item);
        } else {
            // Node to be removed found

            // Case 1: Node has no children (leaf node)
            if(node.left == null && node.right == null){
                return null;
            }
            // Case 2: Node has one child
            if(node.left == null){
                return node.right; // Replace node with its right child
            } else if(node.right == null){
                return node.left; //Replace node with its left child
            }
            // Case 3: Node has 2 children
            // Find the in-order successor (the smallest node in the right subtree)
            BSTNode successor = findMin(node.right);
            node.data = successor.data;// Replace the node's data with successor's data
            node.right = removeRecursive(node.right, successor.data); // Remove the successor node
        }
        return node;

    }

    private BSTNode findMin(BSTNode node) {
        // In a BST, the minimum value is always the leftmost node
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        boolean modified = false;
        for (T item : items) {
            if(item == null){
                throw new NullPointerException("Item cannot be null");
            }
            if (remove(item)) {
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
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }
    /**
     * Traverses nodes in order and adds value to array list
     */
    private void inOrderTraversal(BSTNode node, ArrayList<T> list){
        if(node != null){
            inOrderTraversal(node.left,list);// Visit left subtree
            list.add(node.data);// Add node data
            inOrderTraversal(node.right, list);// Visit right subtree
        }
    }

}
