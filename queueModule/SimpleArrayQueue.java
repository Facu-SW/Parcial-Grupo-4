package queueModule;

@SuppressWarnings("unchecked")
public class SimpleArrayQueue<E> implements SimpleQueue<E> {

    private static final int INITIAL_CAPACITY = 8;
    private Object[] data;
    private int size;

    public SimpleArrayQueue() {
        data = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    @Override
    public void enqueue(E element) {
        ensureCapacity();
        data[size++] = element; 
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");

        E first = (E) data[0];

        // correr todos los elementos a la izquierda
        for (int i = 0; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null; 
        return first;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return (E) data[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() { 
        return size; 
    }

    @Override
    public boolean isEmpty() { 
        return size == 0; 
    }
}
