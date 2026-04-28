package stackModule;

@SuppressWarnings("unchecked")
public class SimpleArrayStack<E> implements SimpleStack<E> {

    private static final int INITIAL_CAPACITY = 8;
    private Object[] data;
    private int size;

    public SimpleArrayStack() {
        data = new Object[INITIAL_CAPACITY];
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
    public void push(E element) {
        ensureCapacity();
        data[size++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new RuntimeException("Stack vacio");
        E top = (E) data[--size];
        data[size] = null;
        return top;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new RuntimeException("Stack vacio");
        return (E) data[size - 1];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
