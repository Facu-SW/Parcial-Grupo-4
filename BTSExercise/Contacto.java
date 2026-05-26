package BTSExercise;

public class Contacto implements Comparable<Contacto> {

    private String nombre;
    private String telefono;
    private String mail;

    public Contacto(String nombre, String telefono, String mail) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int compareTo(Contacto otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                " | Telefono: " + telefono +
                " | Mail: " + mail;
    }
}