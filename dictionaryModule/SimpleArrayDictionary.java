package dictionaryModule;

import listModule.SimpleList;
import listModule.SimpleLinkedList;

/**
 * Implementación con array desordenado.
 * El orden de las keys no está garantizado.
 * Para remove, el último elemento ocupa el lugar vacío (sin desplazar).
 * Todas las operaciones de búsqueda son O(n).
 */
public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    private Object[] keys;
    private Object[] values;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 8;

    public SimpleArrayDictionary() {
        keys   = new Object[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        if (key == null)   throw new NullPointerException("La key no puede ser null.");
        if (value == null) throw new NullPointerException("El value no puede ser null.");

        // Si ya existe la key, reemplazar y devolver el valor anterior.
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                @SuppressWarnings("unchecked")
                V old = (V) values[i];
                values[i] = value;
                return old;
            }
        }

        // Key nueva: agregar al final.
        ensureCapacity();
        keys[size]   = key;
        values[size] = value;
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException("La key no puede ser null.");

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                // Mover el último al lugar vacío (orden no garantizado).
                keys[i]        = keys[size - 1];
                values[i]      = values[size - 1];
                keys[size - 1]   = null;
                values[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K key) {
        if (key == null) throw new NullPointerException("La key no puede ser null.");

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new NullPointerException("La key no puede ser null.");

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) return true;
        }
        return false;
    }

    @Override
    public SimpleList<K> keys() {
        SimpleList<K> result = new SimpleLinkedList<>();
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            K key = (K) keys[i];
            result.add(key);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) result[i] = values[i];
        return (V[]) result;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            keys[i]   = null;
            values[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity() {
        if (size == keys.length) {
            Object[] newKeys   = new Object[keys.length * 2];
            Object[] newValues = new Object[keys.length * 2];
            for (int i = 0; i < size; i++) {
                newKeys[i]   = keys[i];
                newValues[i] = values[i];
            }
            keys   = newKeys;
            values = newValues;
        }
    }
}
