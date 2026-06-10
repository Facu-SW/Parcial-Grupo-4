package graphModule2;
import java.util.ArrayList;

public class Dijkstra {

    public static ArrayList<DatoDijkstra> calcular(Grafo grafo, String origen) {
        ArrayList<String> nodos = grafo.getNodos();
        ArrayList<DatoDijkstra> tabla = new ArrayList<>();
        ArrayList<String> visitados = new ArrayList<>();

        for (String nodo : nodos) {
            if (nodo.equalsIgnoreCase(origen)) {
                tabla.add(new DatoDijkstra(0, null));
            } else {
                tabla.add(new DatoDijkstra(Integer.MAX_VALUE, null));
            }
        }

        while (visitados.size() < nodos.size()) {
            String actual = buscarNoVisitadoMasCercano(nodos, tabla, visitados);

            if (actual == null) {
                break;
            }

            visitados.add(actual);

            int posicionActual = grafo.buscarPosicion(actual);
            int distanciaActual = tabla.get(posicionActual).getDistancia();

            ArrayList<Arista> vecinos = grafo.getVecinos(actual);

            for (Arista arista : vecinos) {
                String vecino = arista.getDestino();

                if (!estaVisitado(visitados, vecino)) {
                    int posicionVecino = grafo.buscarPosicion(vecino);
                    int nuevaDistancia = distanciaActual + arista.getCosto();

                    if (nuevaDistancia < tabla.get(posicionVecino).getDistancia()) {
                        tabla.get(posicionVecino).setDistancia(nuevaDistancia);
                        tabla.get(posicionVecino).setPrevio(actual);
                    }
                }
            }
        }

        return tabla;
    }

    private static String buscarNoVisitadoMasCercano(
            ArrayList<String> nodos,
            ArrayList<DatoDijkstra> tabla,
            ArrayList<String> visitados
    ) {
        int menorDistancia = Integer.MAX_VALUE;
        String nodoMasCercano = null;

        for (int i = 0; i < nodos.size(); i++) {
            String nodo = nodos.get(i);

            if (!estaVisitado(visitados, nodo)) {
                int distancia = tabla.get(i).getDistancia();

                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    nodoMasCercano = nodo;
                }
            }
        }

        return nodoMasCercano;
    }

    private static boolean estaVisitado(ArrayList<String> visitados, String nodo) {
        for (String visitado : visitados) {
            if (visitado.equalsIgnoreCase(nodo)) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarCamino(Grafo grafo, ArrayList<DatoDijkstra> tabla, String origen, String destino) {
        int posicionDestino = grafo.buscarPosicion(destino);

        if (tabla.get(posicionDestino).getDistancia() == Integer.MAX_VALUE) {
            System.out.println("No existe camino entre " + origen + " y " + destino);
            return;
        }

        ArrayList<String> camino = new ArrayList<>();
        String actual = destino;

        while (actual != null) {
            camino.add(0, actual);

            int posicionActual = grafo.buscarPosicion(actual);
            actual = tabla.get(posicionActual).getPrevio();
        }

        System.out.println("Camino mas corto:");
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));

            if (i < camino.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.println("Costo total: " + tabla.get(posicionDestino).getDistancia());
    }
}
