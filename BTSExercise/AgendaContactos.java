package BTSExercise;

public class AgendaContactos {

    private BinarySearchTree<Contacto> contactos;

    public AgendaContactos() {
        contactos = new BinarySearchTree<>();
    }

    public void agregarContacto(String nombre, String telefono, String mail) {

        Contacto contacto = new Contacto(nombre, telefono, mail);

        contactos.insert(contacto);
    }

    public Contacto buscarContacto(String nombre) {

        Contacto buscado = new Contacto(nombre, "", "");

        return contactos.search(buscado);
    }

    public void eliminarContacto(String nombre) {

        Contacto contacto = new Contacto(nombre, "", "");

        contactos.remove(contacto);
    }

    public void mostrarContactos() {
        contactos.inOrder();
    }

    public void editarContacto(String nombre, String nuevoTelefono, String nuevoMail) {

        Contacto contacto = buscarContacto(nombre);

        if (contacto != null) {
            contacto.setTelefono(nuevoTelefono);
            contacto.setMail(nuevoMail);
        }
    }

    public void cargarDatosPrueba() {

        agregarContacto("Juan", "1111-1111", "juan@gmail.com");
        agregarContacto("Ana", "2222-2222", "ana@gmail.com");
        agregarContacto("Pedro", "3333-3333", "pedro@gmail.com");
        agregarContacto("Lucia", "4444-4444", "lucia@gmail.com");
    }
}