package BTSExercise;

import java.util.Scanner;

public class Main {

    private static String readValidName(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("El nombre no puede estar vacio");
                continue;
            }

            boolean hasNumber = false;

            for (int i = 0; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i))) {
                    hasNumber = true;
                    break;
                }
            }

            if (hasNumber) {
                System.out.println("El nombre no puede contener numeros");
                continue;
            }

            return name;
        }
    }

    private static String readValidPhone(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);
            String phone = scanner.nextLine().trim();

            if (phone.isEmpty()) {
                System.out.println("El telefono no puede estar vacio");
                continue;
            }

            boolean hasDigit = false;
            boolean validFormat = true;

            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);

                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                else if (c != ' ' && c != '+' && c != '-' && c != '(' && c != ')') {
                    validFormat = false;
                    break;
                }
            }

            if (!validFormat || !hasDigit) {
                System.out.println("El telefono solo puede contener numeros y simbolos basicos (+ - ( ))");
                continue;
            }

            return phone;
        }
    }

    private static String readValidEmail(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);
            String email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("El mail no puede estar vacio");
                continue;
            }

            int atSymbolCount = 0;

            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    atSymbolCount++;
                }
            }

            boolean atSymbolAtStart = email.charAt(0) == '@';
            boolean atSymbolAtEnd = email.charAt(email.length() - 1) == '@';

            if (atSymbolCount != 1 || atSymbolAtStart || atSymbolAtEnd) {
                System.out.println("El mail debe contener un @ valido");
                continue;
            }

            return email;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ContactBook contactBook = new ContactBook();

        int option;

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

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:

                    String name = readValidName(scanner, "Nombre: ");
                    String phone = readValidPhone(scanner, "Telefono: ");
                    String email = readValidEmail(scanner, "Mail: ");

                    try {
                        contactBook.addContact(name, phone, email);
                        System.out.println("Contacto agregado");
                    }
                    catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:

                    System.out.print("Nombre a buscar: ");
                    String search = scanner.nextLine();

                    Contact found = contactBook.searchContact(search);

                    if (found != null) {
                        System.out.println(found);
                    }
                    else {
                        System.out.println("Contacto no encontrado");
                    }

                    break;

                case 3:

                    String edit = readValidName(scanner, "Nombre del contacto: ");
                    String newPhone = readValidPhone(scanner, "Nuevo telefono: ");
                    String newEmail = readValidEmail(scanner, "Nuevo mail: ");

                    contactBook.editContact(edit, newPhone, newEmail);

                    System.out.println("Contacto editado");

                    break;

                case 4:

                    System.out.print("Nombre a eliminar: ");
                    String delete = scanner.nextLine();

                    contactBook.deleteContact(delete);

                    System.out.println("Contacto eliminado");

                    break;

                case 5:

                    System.out.println();
                    System.out.println("===== CONTACTOS =====");
                    contactBook.displayContacts();

                    break;

                case 6:

                    contactBook.loadTestData();

                    System.out.println("Datos cargados");

                    break;

                case 0:

                    System.out.println("Programa finalizado");

                    break;

                default:

                    System.out.println("Opcion invalida");
            }

        } while (option != 0);

        scanner.close();
    }
}
