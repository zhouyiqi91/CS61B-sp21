package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        public T item;
        public Node prev, next;

        public Node(T i, Node prevNode, Node nextNode) {
            item = i;
            prev = prevNode;
            next = nextNode;
        }
    }

    private final Node dummy;
    private int size;

    public LinkedListDeque() {
        dummy = new Node(null, null, null);
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node nxt = dummy.next;
        Node cur = new Node(item, dummy, nxt);
        dummy.next = cur;
        nxt.prev = cur;
        size += 1;
    }

    /**
     * Adds an item to the end of the list.
     */
    @Override
    public void addLast(T item) {
        Node prev = dummy.prev;
        Node cur = new Node(item, prev, dummy);
        prev.next = cur;
        dummy.prev = cur;
        size += 1;
    }

    @Override
    public void printDeque() {
        Node p = dummy.next;
        while (p != dummy) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        Node nxt = dummy.next;
        Node newNext = dummy.next.next;
        dummy.next = newNext;
        newNext.prev = dummy;
        size -= 1;
        return nxt.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        Node prev = dummy.prev;
        Node newPrev = dummy.prev.prev;
        dummy.prev = newPrev;
        newPrev.next = dummy;
        size -= 1;
        return prev.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node p = dummy.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public int size() {
        return size;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(dummy.next, index);
    }

    /** Helper method that performs the recursive traversal. */
    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node nxt = dummy.next;

        @Override
        public boolean hasNext() {
            return nxt != dummy;
        }

        @Override
        public T next() {
            T res = nxt.item;
            nxt = nxt.next;
            return res;
        }
    }

    @Override
    public boolean equals(Object o) {
        // Step 1: Check for self-reference
        if (this == o) return true;

        // Step 2: Check if 'o' is null or if they are not the same class
        if (!(o instanceof LinkedListDeque)) return false;

        // Step 3: Cast 'o' to LinkedListDeque
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;

        // Step 4: Check if sizes are different
        if (this.size != other.size) return false;

        // Step 5: Check if contents are the same
        Node thisNode = this.dummy.next;
        Node otherNode = other.dummy.next;

        while (thisNode != this.dummy && otherNode != other.dummy) {
            if (!thisNode.item.equals(otherNode.item)) {
                return false;
            }
            thisNode = thisNode.next;
            otherNode = otherNode.next;
        }

        return true;
    }
}
