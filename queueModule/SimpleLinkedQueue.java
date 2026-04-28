package queueModule;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }

    private Node<E> head; 
    private Node<E> tail; 
    private int size;

    @Override
    public void enqueue(E element) {
        Node<E> node = new Node<>(element);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue vacia");
        E data = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new RuntimeException("Queue vacia");
        return head.data;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
