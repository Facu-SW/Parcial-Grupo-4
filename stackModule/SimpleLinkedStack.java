package stackModule;

public class SimpleLinkedStack<E> implements SimpleStack<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }

    private Node<E> top;
    private int size;

    @Override
    public void push(E element) {
        Node<E> node = new Node<>(element);
        node.next = top;
        top = node;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new RuntimeException("Stack vacio");
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new RuntimeException("Stack vacio");
        return top.data;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
