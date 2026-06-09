package DijkstraExercise;

import graphModule.Graph;
import graphModule.Edge;
import graphModule.ListGraph;
import dictionaryModule.SimpleDictionary;

public class Main {

    public static void main(String[] args) {
        // Crear un grafo de ejemplo con Character como tipo de nodo
        Graph<Character> graph = new ListGraph<>();

        // Agregar aristas (grafo ponderado dirigido)
        graph.addEdge('A', 'B', 4);
        graph.addEdge('A', 'C', 2);
        graph.addEdge('B', 'C', 1);
        graph.addEdge('B', 'D', 5);
        graph.addEdge('C', 'B', 1);
        graph.addEdge('C', 'D', 8);
        graph.addEdge('C', 'E', 10);
        graph.addEdge('D', 'E', 2);
        graph.addEdge('E', 'D', 2);

        System.out.println("=== Algoritmo de Dijkstra ===");
        System.out.println("Grafo:");
        System.out.println("A --(4)--> B");
        System.out.println("A --(2)--> C");
        System.out.println("B --(1)--> C");
        System.out.println("B --(5)--> D");
        System.out.println("C --(1)--> B");
        System.out.println("C --(8)--> D");
        System.out.println("C --(10)--> E");
        System.out.println("D --(2)--> E");
        System.out.println("E --(2)--> D");
        System.out.println();

        // Ejecutar Dijkstra desde el nodo 'A'
        Character startNode = 'A';
        System.out.println("Nodo inicio: " + startNode);
        System.out.println();

        SimpleDictionary<Character, Edge<Character>> result = DijkstraSolver.dijkstra(startNode, graph);

        // Imprimir tabla de resultados
        System.out.println("=== Tabla de Distancias ===");
        DijkstraSolver.printTable(result);
        System.out.println();

        // Imprimir caminos más cortos desde A a cada nodo
        System.out.println("=== Caminos más cortos desde " + startNode + " ===");
        Character[] destinations = {'B', 'C', 'D', 'E'};
        for (Character destination : destinations) {
            DijkstraSolver.printPath(destination, startNode, result);
        }
    }
}
