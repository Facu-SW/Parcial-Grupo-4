package dictionaryModule;

import listModule.SimpleList;

public interface SimpleDictionary<K, V> {
    // Agrega el par (key, value). Si key ya existe, reemplaza value y devuelve el anterior.
    // Si no existe, agrega el par y devuelve null.
    // Lanza NullPointerException si key o value son null.
    V put(K key, V value);

    // Remueve key y su value si existen. Devuelve true si se removió.
    // Lanza NullPointerException si key es null.
    boolean remove(K key);

    // Devuelve el value asociado a key, o null si key no existe.
    // Lanza NullPointerException si key es null.
    V get(K key);

    // Devuelve true si key existe en el diccionario.
    // Lanza NullPointerException si key es null.
    boolean containsKey(K key);

    // Devuelve una lista con todas las keys del diccionario.
    SimpleList<K> keys();

    // Devuelve un array con todos los values del diccionario.
    V[] values();

    int size();

    boolean isEmpty();

    void clear();
}
