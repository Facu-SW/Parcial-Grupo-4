package tda;

public interface SimpleDictionary<K, V> {
    V put(K key, V value);
    boolean remove(K key);
    boolean containsKey(K key);
    V get(K key);
}