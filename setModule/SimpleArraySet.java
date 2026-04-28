package setModule;

@SuppressWarnings("unchecked")
public class SimpleArraySet<E> implements SimpleSet<E> {

    private static final int INITIAL_CAPACITY = 8;
    private E[] elements;
    private int size;

    public SimpleArraySet() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    public SimpleArraySet(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            E[] newelements = (E[]) new Object[elements.length * 2];
            for(int i = 0; i < elements.length; i++) 
                newelements[i] = elements[i];
            elements = newelements;
        }
    }

    @Override
    public boolean add(E element) {
        if(element == null)
            throw new IllegalArgumentException("El elemento a añadir no puede ser null.");
        if (contains(element)) return false;
        ensureCapacity();
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        if (element == null)
            throw new IllegalArgumentException("El elemento a remover no puede ser null.");
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                if(i == size - 1) elements[i] = null;
                else elements[i] = elements[size - 1];
                size--;
                return true;
                
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        if (element == null)
            throw new IllegalArgumentException("El elemento no puede ser null.");

        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public int size() { return size; }

    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];

        for(int i = 0; i < size; i++) 
            result[i] = elements[i];
        
        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        if (other == null)
            throw new IllegalArgumentException("El otro set no puede ser null.");

        SimpleArraySet<E> result = new SimpleArraySet<>();

        for (int i = 0; i < size; i++) {
            result.add(elements[i]);
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

        SimpleArraySet<E> result = new SimpleArraySet<>();

        for (int i = 0; i < size; i++) {
            E e = elements[i];
            if (other.contains(e)) {
                result.add(e);
            }
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        if (other == null)
            throw new IllegalArgumentException("El otro set no puede ser null.");

        SimpleArraySet<E> result = new SimpleArraySet<>();

        for (int i = 0; i < size; i++) {
            E e = elements[i];
            if (!other.contains(e)) {
                result.add(e);
            }
        }

        return result;
    }
}
