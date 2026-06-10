package graphModule2;
import java.util.ArrayList;

public class Grafo {
    private ArrayList<String> nodos;
    private ArrayList<ArrayList<Arista>> adyacencias;

    public Grafo() {
        nodos = new ArrayList<>();
        adyacencias = new ArrayList<>();
    }

    public void agregarNodo(String nodo) {
        if (!existeNodo(nodo)) {
            nodos.add(nodo);
            adyacencias.add(new ArrayList<>());
        }
    }

    public void agregarArista(String origen, String destino, int costo) {
        agregarNodo(origen);
        agregarNodo(destino);

        int posicionOrigen = buscarPosicion(origen);
        adyacencias.get(posicionOrigen).add(new Arista(destino, costo));
    }

    public boolean existeNodo(String nodo) {
        return buscarPosicion(nodo) != -1;
    }

    public int buscarPosicion(String nodo) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).equalsIgnoreCase(nodo)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getNodos() {
        return nodos;
    }

    public ArrayList<Arista> getVecinos(String nodo) {
        int posicion = buscarPosicion(nodo);
        if (posicion == -1) {
            return new ArrayList<>();
        }
        return adyacencias.get(posicion);
    }

    public void imprimirGrafo() {
        System.out.println("GRAFO CARGADO:");

        for (int i = 0; i < nodos.size(); i++) {
            String origen = nodos.get(i);
            ArrayList<Arista> vecinos = adyacencias.get(i);

            for (Arista arista : vecinos) {
                System.out.println(origen + " -> " + arista.getDestino() + ": " + arista.getCosto());
            }
        }
    }
}