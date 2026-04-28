package tda;

@SuppressWarnings("unchecked")
public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    private Object[] keys;
    private Object[] values;
    private int size;
    private static final int CAPACITY = 100;

    public SimpleArrayDictionary() {
        keys = new Object[CAPACITY];
        values = new Object[CAPACITY];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Llave o valor nulo");
        }

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V old = (V) values[i];
                values[i] = value;
                return old;
            }
        }

        keys[size] = key;
        values[size] = value; 
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) {
            throw new NullPointerException("Llave nula");
        }

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                keys[i] = keys[size - 1];
                values[i] = values[size - 1];
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("Llave nula");
        }

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Llave nula");
        }

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }
}
