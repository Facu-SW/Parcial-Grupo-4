package graphModule2;
public class Arista {
    private String destino;
    private int costo;

    public Arista(String destino, int costo) {
        this.destino = destino;
        this.costo = costo;
    }

    public String getDestino() {
        return destino;
    }

    public int getCosto() {
        return costo;
    }
}