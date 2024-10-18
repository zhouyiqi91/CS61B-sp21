package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    /** position of first element */
    private int front;
    /** position after the last element */
    private int back;
    private static final int INITIAL_CAPACITY = 8;
    private static final int RESIZE_FACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        back = 0;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        resize();
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        resize();
        items[back] = item;
        back = (back + 1) % items.length;
        size++;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        resize();
        return item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        back = (back - 1 + items.length) % items.length;
        T item = items[back];
        items[back] = null;
        size--;
        resize();
        return item;
    }

    /** Gets the item at the given index. If no such item exists, returns null. */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front + index) % items.length];
    }

    /** Resizes the array to the target capacity */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(front + i) % items.length];
        }
        items = newItems;
        front = 0;
        back = size;
    }

    /** resize when needed */
    private void resize() {
        if (size == items.length) {
            resize(items.length * RESIZE_FACTOR);
        } else if (size > INITIAL_CAPACITY && size <= items.length / 4) {
            resize(items.length / RESIZE_FACTOR);
        }
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(front + i) % items.length] + " ");
        }
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T res = get(index);
            index++;
            return res;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayDeque)) return false;

        ArrayDeque<?> that = (ArrayDeque<?>) o;

        if (this.size != that.size) return false;

        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(that.get(i))) {
                return false;
            }
        }
        return true;
    }
}

