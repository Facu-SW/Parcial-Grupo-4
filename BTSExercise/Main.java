package BTSExercise;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AgendaContactos agenda = new AgendaContactos();

        int opcion;

        do {

            System.out.println();
            System.out.println("===== AGENDA DE CONTACTOS =====");
            System.out.println("1 - Agregar contacto");
            System.out.println("2 - Buscar contacto");
            System.out.println("3 - Editar contacto");
            System.out.println("4 - Eliminar contacto");
            System.out.println("5 - Mostrar contactos");
            System.out.println("6 - Cargar datos de prueba");
            System.out.println("0 - Salir");
            System.out.print("Opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese un numero valido");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine();

                    System.out.print("Mail: ");
                    String mail = scanner.nextLine();

                    try {
                        agenda.agregarContacto(nombre, telefono, mail);
                        System.out.println("Contacto agregado");
                    }
                    catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:

                    System.out.print("Nombre a buscar: ");
                    String buscar = scanner.nextLine();

                    Contacto encontrado = agenda.buscarContacto(buscar);

                    if (encontrado != null) {
                        System.out.println(encontrado);
                    }
                    else {
                        System.out.println("Contacto no encontrado");
                    }

                    break;

                case 3:

                    System.out.print("Nombre del contacto: ");
                    String editar = scanner.nextLine();

                    System.out.print("Nuevo telefono: ");
                    String nuevoTelefono = scanner.nextLine();

                    System.out.print("Nuevo mail: ");
                    String nuevoMail = scanner.nextLine();

                    agenda.editarContacto(editar, nuevoTelefono, nuevoMail);

                    System.out.println("Contacto editado");

                    break;

                case 4:

                    System.out.print("Nombre a eliminar: ");
                    String eliminar = scanner.nextLine();

                    agenda.eliminarContacto(eliminar);

                    System.out.println("Contacto eliminado");

                    break;

                case 5:

                    System.out.println();
                    System.out.println("===== CONTACTOS =====");
                    agenda.mostrarContactos();

                    break;

                case 6:

                    agenda.cargarDatosPrueba();

                    System.out.println("Datos cargados");

                    break;

                case 0:

                    System.out.println("Programa finalizado");

                    break;

                default:

                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
