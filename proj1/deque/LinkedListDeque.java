package deque;

public class LinkedListDeque<T> implements Deque<T> {
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

    /** Adds an item to the front of the list. */
    @Override
    public void addFirst(T item) {
        Node nxt = dummy.next;
        Node cur = new Node(item, dummy, nxt);
        dummy.next = cur;
        nxt.prev = cur;
        size += 1;
    }

    /** Adds an item to the end of the list. */
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
    };

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null */
    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        Node nxt = dummy.next;
        Node newNext = dummy.next.next;
        dummy.next = newNext;
        newNext.prev = dummy;
        size -= 1;
        return nxt.item;
    };

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null */
    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        Node prev = dummy.prev;
        Node newPrev = dummy.prev.prev;
        dummy.prev = newPrev;
        newPrev.next = dummy;
        size -= 1;
        return prev.item;
    };

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index<0 || index >= size) return null;
        Node p = dummy.next;
        for (int i=0;i<index;i++) {
            p = p.next;
        }
        return p.item;
    };

    @Override
    public int size() {
        return size;
    }}
