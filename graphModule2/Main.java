package graphModule2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Grafo grafo = new Grafo();

        cargarGrafoGPS(grafo);

        grafo.imprimirGrafo();

        System.out.println();
        System.out.println("Ingrese punto de origen:");
        String origen = scanner.nextLine();

        while (!grafo.existeNodo(origen)) {
            System.out.println("El origen ingresado no existe. Intente nuevamente:");
            origen = scanner.nextLine();
        }

        System.out.println("Ingrese punto de destino:");
        String destino = scanner.nextLine();

        while (!grafo.existeNodo(destino)) {
            System.out.println("El destino ingresado no existe. Intente nuevamente:");
            destino = scanner.nextLine();
        }

        ArrayList<DatoDijkstra> tabla = Dijkstra.calcular(grafo, origen);

        System.out.println();
        Dijkstra.mostrarCamino(grafo, tabla, origen, destino);

        scanner.close();
    }

    private static void cargarGrafoGPS(Grafo grafo) {
        grafo.agregarArista("Casa", "Supermercado", 4);
        grafo.agregarArista("Casa", "Escuela", 2);
        grafo.agregarArista("Escuela", "Supermercado", 1);
        grafo.agregarArista("Escuela", "Hospital", 7);
        grafo.agregarArista("Supermercado", "Hospital", 3);
        grafo.agregarArista("Supermercado", "Trabajo", 6);
        grafo.agregarArista("Hospital", "Trabajo", 2);
    }
}
