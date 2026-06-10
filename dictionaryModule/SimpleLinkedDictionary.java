package dictionaryModule;

import listModule.SimpleList;
import listModule.SimpleLinkedList;

/**
 * Implementación con lista simplemente enlazada.
 * El orden de las keys no está garantizado.
 * Todas las operaciones de búsqueda son O(n).
 */
public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {

    private static class DictionaryNode<K, V> {
        K key;
        V value;
        DictionaryNode<K, V> next;

        DictionaryNode(K key, V value) {
            this.key   = key;
            this.value = value;
        }
    }

    private DictionaryNode<K, V> first = null;
    private int size = 0;

    @Override
    public V put(K key, V value) {
        if (key == null)   throw new NullPointerException("La key no puede ser null.");
        if (value == null) throw new NullPointerException("El value no puede ser null.");

        // Si ya existe la key, reemplazar y devolver el valor anterior.
        DictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) {
                V old = current.value;
                current.value = value;
                return old;
            }
            current = current.next;
        }

        // Key nueva: insertar al frente (O(1)).
        DictionaryNode<K, V> newNode = new DictionaryNode<>(key, value);
        newNode.next = first;
        first = newNode;
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException("La key no puede ser null.");

        // Caso especial: el primer nodo tiene la key.
        if (first != null && first.key.equals(key)) {
            first = first.next;
            size--;
            return true;
        }

        DictionaryNode<K, V> current = first;
        while (current != null && current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new NullPointerException("La key no puede ser null.");

        DictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new NullPointerException("La key no puede ser null.");

        DictionaryNode<K, V> current = first;
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public SimpleList<K> keys() {
        SimpleList<K> result = new SimpleLinkedList<>();
        DictionaryNode<K, V> current = first;
        while (current != null) {
            result.add(current.key);
            current = current.next;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        Object[] result = new Object[size];
        DictionaryNode<K, V> current = first;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current   = current.next;
        }
        return (V[]) result;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        first = null;
        size  = 0;
    }
}
