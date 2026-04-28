package setModule;

@SuppressWarnings("unchecked")
public class SimpleLinkedSet<E> implements SimpleSet<E> {

    private class Node {
        E value;
        Node next;

        Node(E value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;

    @Override
    public boolean add(E element) {
        if (element == null)
            throw new IllegalArgumentException("El elemento a añadir puede ser null.");

        if (contains(element))
            return false;

        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        if (element == null)
            throw new IllegalArgumentException("El elemento a remover puede ser null.");

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (element.equals(current.value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        if (element == null)
            throw new IllegalArgumentException("El elemento no puede ser null.");

        Node current = head;

        while (current != null) {
            if (element.equals(current.value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        Node current = head;
        int i = 0;

        while (current != null) {
            result[i] = current.value;
            current = current.next;
            i++;
        }

        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        if (other == null)
            throw new IllegalArgumentException("El otro set no puede ser null.");

        SimpleLinkedSet<E> result = new SimpleLinkedSet<>();

        Node current = head;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }

        for (E e : other.toArray()) {
            result.add(e);
        }

        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        if (other == null)
            throw new IllegalArgumentException("El otro set no puede ser null.");

        SimpleLinkedSet<E> result = new SimpleLinkedSet<>();

        Node current = head;
        while (current != null) {
            if (other.contains(current.value)) {
                result.add(current.value);
            }
            current = current.next;
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        if (other == null)
            throw new IllegalArgumentException("El otro set no puede ser null.");

        SimpleLinkedSet<E> result = new SimpleLinkedSet<>();

        Node current = head;
        while (current != null) {
            if (!other.contains(current.value)) {
                result.add(current.value);
            }
            current = current.next;
        }

        return result;
    }
}