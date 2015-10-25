package collection;

public class LinkedList implements Collection {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void add(Object o) {
        Node n = new Node(o, null);
        if (head == null) {
            head = n;
        } else {
            tail.setNext(n);
        }
        tail = n;
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
