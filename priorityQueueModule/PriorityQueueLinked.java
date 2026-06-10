package priorityQueueModule;

public class PriorityQueueLinked<E> implements PriorityQueue<E> {

    private class Node {
        E element;
        int priority;
        Node next;

        Node(E element, int priority) {
            this.element = element;
            this.priority = priority;
        }
    }

    private Node head;

    @Override
    public void enqueue(E element, int priority) {
        if (element == null)
            throw new IllegalArgumentException("Elemento nulo");

        Node newNode = new Node(element, priority);

        if (head == null || priority < head.priority) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null && current.next.priority <= priority) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Cola vacía");

        E element = head.element;
        head = head.next;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new IllegalStateException("Cola vacía");

        return head.element;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
public void showAll() {
    if (isEmpty()) {
        System.out.println("No hay reclamos.");
        return;
    }

    Node current = head;

    while (current != null) {
        System.out.println(current.element);
        current = current.next;
    }
}
}