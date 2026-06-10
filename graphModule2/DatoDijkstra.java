package graphModule2;
public class DatoDijkstra {
    private int distancia;
    private String previo;

    public DatoDijkstra(int distancia, String previo) {
        this.distancia = distancia;
        this.previo = previo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getPrevio() {
        return previo;
    }

    public void setPrevio(String previo) {
        this.previo = previo;
    }
}