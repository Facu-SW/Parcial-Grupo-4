package graphModule;

import dictionaryModule.SimpleDictionary;
import dictionaryModule.SimpleArrayDictionary;
import listModule.SimpleList;
import setModule.SimpleSet;
import setModule.SimpleArraySet;
import priorityQueueModule.PriorityQueueLinked;

public class DijkstraSolver {

    /**
     * Ejecuta el algoritmo de Dijkstra desde un nodo inicial.
     * Devuelve un diccionario donde cada key es un nodo del grafo,
     * y el value es un Edge<T> que almacena el nodo previo (destination) y el costo total (weight).
     * Para el nodo inicial, el previo es null y el costo es 0.
     */
    public static <T> SimpleDictionary<T, Edge<T>> dijkstraAllNodes(T origin, Graph<T> graph) {
        // Inicializar el diccionario de resultados
        SimpleDictionary<T, Edge<T>> result = new SimpleArrayDictionary<>();

        // Obtener todos los vertices del grafo
        SimpleList<T> vertices = graph.vertex();

        // Paso 1: Asignar valores iniciales
        // Para el nodo inicial: distancia 0, previo null
        // Para los demás: distancia infinita (Integer.MAX_VALUE), previo null
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(origin)) {
                result.put(vertices.get(i), new Edge<T>(vertices.get(i), 0));
            } else {
                result.put(vertices.get(i), new Edge<T>(vertices.get(i), Integer.MAX_VALUE));
            }
        }

        // Cola de prioridad para nodos no visitados
        PriorityQueueLinked<T> unvisited = new PriorityQueueLinked<>();

        // Set de nodos visitados
        SimpleSet<T> visited = new SimpleArraySet<>();        

        // Encolar el nodo inicial con prioridad 0
        unvisited.enqueue(origin, 0);

        // Bucle principal
        while (!unvisited.isEmpty()) {
            // Paso 2: Seleccionar el nodo no visitado más cercano al origen
            T current = unvisited.dequeue();

            // Si ya fue visitado, lo saltamos
            if (visited.contains(current)) continue;

            // Obtener el costo total del nodo actual
            int currentCost = result.get(current).weight;

            // Paso 3: Actualizar valores de los vecinos usando getNeighbors
            SimpleList<Edge<T>> neighbors = graph.getNeighbors(current);
            int neighborsCount = neighbors.size();

            for (int i = 0; i < neighborsCount; i++) {
                Edge<T> edge = neighbors.get(i);
                T neighbor = edge.destination;

                // Si el vecino ya fue visitado, lo saltamos
                if (visited.contains(neighbor)) continue;

                // Calcular nuevo costo: costo actual + peso de la arista
                int newCost = currentCost + edge.weight;

                // Si el nuevo costo es menor al costo actual del vecino, actualizar
                int neighborCost = result.get(neighbor).weight;
                if (newCost < neighborCost) {
                    // Actualizar: el previo del vecino es el nodo actual, con el nuevo costo total
                    result.put(neighbor, new Edge<T>(current, newCost));
                    // Encolar el vecino con el nuevo costo como prioridad
                    unvisited.enqueue(neighbor, newCost);
                }
            }

            // Paso 4: Marcar como visitado
            visited.add(current);

        }

        return result;
    }

}
