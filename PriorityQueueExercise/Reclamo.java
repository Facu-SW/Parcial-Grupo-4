public class Reclamo {
    private String titulo;
    private String descripcion;
    private int prioridad; 

    public Reclamo(String titulo, String descripcion, int prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        String prioridadTexto = "";

        switch (prioridad) {
            case 1:
                prioridadTexto = "Crítico";
                break;
            case 2:
                prioridadTexto = "Alto";
                break;
            case 3:
                prioridadTexto = "Medio";
                break;
            case 4:
                prioridadTexto = "Bajo";
                break;
        }

        return "Título: " + titulo +
            " | Descripción: " + descripcion +
            " | Prioridad: " + prioridadTexto;
    }
}