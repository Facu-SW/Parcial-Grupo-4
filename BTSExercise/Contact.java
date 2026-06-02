package BTSExercise;

public class Contact implements Comparable<Contact> {

    private String name;
    private String phone;
    private String email;

    // crea un contacto con nombre, telefono y email
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // devuelvo el nombre del contacto
    public String getName() {
        return name;
    }

    // actualizo el nombre del contacto
    public void setName(String name) {
        this.name = name;
    }

    // devuelvo el telefono del contacto
    public String getPhone() {
        return phone;
    }

    // actualizo el telefono del contacto
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // devuelvo el email del contacto
    public String getEmail() {
        return email;
    }

    // actualizo el email del contacto
    public void setEmail(String email) {
        this.email = email;
    }

    // compara contactos por nombre sin distinguir mayusculas
    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // devuelve una representacion legible del contacto
    @Override
    public String toString() {
        return "Nombre: " + name +
                " | Telefono: " + phone +
                " | Mail: " + email;
    }
}