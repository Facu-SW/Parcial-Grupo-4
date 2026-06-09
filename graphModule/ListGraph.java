package graphModule;

import listModule.SimpleList;
import listModule.SimpleLinkedList;
import dictionaryModule.SimpleDictionary;
import dictionaryModule.SimpleArrayDictionary;

public class ListGraph<T> implements Graph<T> {
    private SimpleDictionary<T, SimpleList<Edge<T>>> adjacencyList;
    
    public ListGraph(){
        adjacencyList = new SimpleArrayDictionary<T, SimpleList<Edge<T>>>();
    }

    public T[] vertex() { return adjacencyList.keys(); }

    public boolean addVertex(T vertex) {
        if (containsVertex(vertex)) return false;
        adjacencyList.put(vertex, new SimpleLinkedList<Edge<T>>());
        return true;
    }

    public boolean removeVertex(T vertex) {
        // Si no está el vertice no se puede remover
        if(!containsVertex(vertex)) return false;
        adjacencyList.remove(vertex);
        // Guardamos todos los vertices
        T[] vertices = vertex();
        // Para cada uno, intentamos remover el edge de ese vertice al vertice a eliminar. 
        // Si el edge no existe, removeEdge devuelve false pero no nos importa porque lo que queremos es eliminar el edge si existe
        for(int i = 0; i < vertices.length; i++)
            removeEdge(vertices[i], vertex);
        return true;
    }

    public boolean addEdge(T from, T to, int weight) {
        // Se crean los vertices si no existen
        addVertex(from);
        addVertex(to);
        // Buscamos el edge de from a to
        Edge<T> edge = getEdge(from, to);
        // Si no existe, lo creamos y lo agregamos a la lista de edges del vertice de origen        
        if(edge == null){
            adjacencyList.get(from).add(new Edge<T>(to, weight));
            return true;
        }        
        // Si no es igual, actualizamos
        if(edge.weight != weight) {
            edge.weight = weight;
            return true;
        }
        // Si llegamos hasta aca todo es igual
        return false;
    }

    public boolean removeEdge(T from, T to) {
        // Buscamos el edge de from a to
        Edge<T> edge = getEdge(from, to);
        // Si existe, lo eliminamos   
        if(edge != null){
            adjacencyList.get(from).remove(edge);
            return true;
        }
        return false;
    }

    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean containsEdge(T from, T to) {
        return getEdge(from, to) != null;
    }

    public int getWeight(T from, T to) {
        Edge<T> targetEdge = getEdge(from, to);
        if (targetEdge != null) {
            return targetEdge.weight;
        }
        return -1;
    }

    private Edge<T> getEdge(T from, T to) {
        // Si no está el nodo de origen, no va a estar el edge
        if(!containsVertex(from)) return null;
        // Si llegamos hasta acá podemos buscar la lista de edges del nodo de origen
        SimpleList<Edge<T>> edges = adjacencyList.get(from);
        // Iteramos la lista buscando el edge a devolver
        for(int i = 0; i < edges.size(); i++)
            if(edges.get(i).destination.equals(to)) return edges.get(i);
        //Si llegamos hasta aca no está el edge
        return null;
    }
}
