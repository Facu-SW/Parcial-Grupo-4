package listModule;

public class SimpleLinkedList<E> implements SimpleList<E> {

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data) { this.data = data; }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private Node<E> nodeAt(int index) {
        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    @Override
    public boolean add(E element) {
        Node<E> node = new Node<>(element);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index);
        if (index == size) { add(element); return; }
        Node<E> node = new Node<>(element);
        if (index == 0) {
            node.next = head;
            if (head != null) head.prev = node;
            head = node;
            if (tail == null) tail = node;
        } else {
            Node<E> after = nodeAt(index);
            Node<E> before = after.prev;
            node.prev = before;
            node.next = after;
            before.next = node;
            after.prev = node;
        }
        size++;
    }

    private void unlink(Node<E> node) {
        if (node.prev != null) node.prev.next = node.next; else head = node.next;
        if (node.next != null) node.next.prev = node.prev; else tail = node.prev;
        node.prev = node.next = null;
        size--;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        Node<E> node = nodeAt(index);
        unlink(node);
        return node.data;
    }

    @Override
    public boolean remove(Object object) {
        Node<E> current = head;
        while (current != null) {
            if (object == null ? current.data == null : object.equals(current.data)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        Node<E> current = head;
        while (current != null) {
            if (object == null ? current.data == null : object.equals(current.data)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        return nodeAt(index).data;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
        Node<E> node = nodeAt(index);
        E old = node.data;
        node.data = element;
        return old;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
