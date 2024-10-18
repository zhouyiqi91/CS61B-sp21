package deque;

import java.util.Iterator;

public interface Deque<T> {
    /** Adds an item to the front of the list. */
    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void printDeque();

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null */
    T removeFirst();

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null */
    T removeLast();

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    T get(int index);

    public Iterator<T> iterator();

}
