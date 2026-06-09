package priorityQueueModule;

public interface PriorityQueue<E> {
    void enqueue(E element, int priority);
    E dequeue();
    E peek();
    boolean isEmpty();
    void showAll();
}
