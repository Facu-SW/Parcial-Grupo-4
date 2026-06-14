package GPSExcercise;

import graphModule.DijkstraSolver;
import graphModule.Edge;
import graphModule.ListGraph;
import dictionaryModule.SimpleDictionary;
import listModule.SimpleLinkedList;
import listModule.SimpleList;

import java.util.Scanner;

public class Main {

    static ListGraph<String> graph = new ListGraph<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // cargamos el grafo inicial con rutas predefinidas entre paises
        graph.addEdge("Argentina", "Brasil", 4);
        graph.addEdge("Argentina", "Chile", 2);
        graph.addEdge("Brasil", "Peru", 5);
        graph.addEdge("Chile", "Brasil", 1);
        graph.addEdge("Chile", "Peru", 8);
        graph.addEdge("Peru", "Colombia", 2);
        graph.addEdge("Brasil", "Colombia", 6);

        System.out.println("=== GPS ===");

        boolean running = true;
        while (running) {
            // imprimimos el grafo completo antes de cada interaccion con el menu
            printGraph();
            // menu
            System.out.println("\n1. Calcular camino mas corto");
            System.out.println("2. Agregar ruta");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            String op = scanner.nextLine().trim();

            switch (op) {
                case "1": findShortestPath(); break;
                case "2": addRoute();         break;
                case "0": running = false;    break;
                default:  System.out.println("Opcion invalida.");
            }
        }
        System.out.println("Hasta luego.");
    }

    // imprimimos cada arista del grafo con el formato: origen -> destino: peso
    static void printGraph() {
        System.out.println("\n--- Mapa de rutas ---");
        SimpleList<String> vertices = graph.vertex();
        for (int i = 0; i < vertices.size(); i++) {
            String from = vertices.get(i);
            SimpleList<Edge<String>> neighbors = graph.getNeighbors(from);
            for (int j = 0; j < neighbors.size(); j++) {
                Edge<String> e = neighbors.get(j);
                System.out.println(from + " -> " + e.destination + ": " + e.weight);
            }
        }
    }

    // pedimos origen y destino, luego ejecuta Dijkstra para encontrar el camino mas corto
    static void findShortestPath() {
        System.out.print("Origen: ");
        String origin = scanner.nextLine().trim();
        System.out.print("Destino: ");
        String destination = scanner.nextLine().trim();

        // validamos que ambos nodos existan en el grafo y que origen y destino no sean iguales
        if (!graph.containsVertex(origin) || !graph.containsVertex(destination)) {
            System.out.println("Nodo no encontrado en el mapa.");
            return;
        }
        if (origin.equals(destination)) {
            System.out.println("Origen y destino son iguales. Distancia: 0");
            return;
        }

        // ejecutamos Dijkstra pasandole el origen; el resultado mapea cada nodo a su entrada de camino mas corto
        SimpleDictionary<String, Edge<String>> result = DijkstraSolver.dijkstraAllNodes(origin, graph);
        Edge<String> destEntry = result.get(destination);

        // validamos si el destino es alcanzable
        if (destEntry == null || destEntry.weight == Integer.MAX_VALUE) {
            System.out.println("No existe camino entre " + origin + " y " + destination + ".");
            return;
        }

        // reconstruimos el camino siguiendo los nodos predecesores desde el destino hasta el origen
        SimpleList<String> path = new SimpleLinkedList<>();
        String current = destination;
        while (!current.equals(origin)) {
            path.add(0, current);
            current = result.get(current).destination; // .destination tiene el nodo predecesor
        }
        path.add(0, origin);

        // imprimimos el camino resultante y la distancia total
        System.out.print("Camino: ");
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) System.out.print(" -> ");
            System.out.print(path.get(i));
        }
        System.out.println("\nDistancia total: " + destEntry.weight);
    }

    // pedimos origen, destino y costo, luego agregamos la arista al grafo
    static void addRoute() {
        System.out.print("Origen: ");
        String from = scanner.nextLine().trim();
        System.out.print("Destino: ");
        String to = scanner.nextLine().trim();
        System.out.print("Costo: ");
        String costStr = scanner.nextLine().trim();

        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Los nombres no pueden estar vacios.");
            return;
        }

        // validamos que el costo sea un entero positivo
        int cost;
        try {
            cost = Integer.parseInt(costStr);
        } catch (NumberFormatException e) {
            System.out.println("Costo invalido. Debe ser un numero entero.");
            return;
        }
        if (cost <= 0) {
            System.out.println("El costo debe ser mayor a 0.");
            return;
        }

        graph.addEdge(from, to, cost);
        System.out.println("Ruta agregada: " + from + " -> " + to + ": " + cost);
    }
}
