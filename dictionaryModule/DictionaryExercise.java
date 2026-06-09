package dictionaryModule;

import java.util.Scanner;
import application.Exercise;

public class DictionaryExercise extends Exercise {

    private int currentPhase = 0;

    // El diccionario mapea username (String) -> UserAccount.
    // Cambiar por SimpleArrayDictionary para probar la implementación alternativa.
    private SimpleDictionary<String, UserAccount> users;

    public DictionaryExercise(Scanner scanner) {
        super(scanner);
        users = new SimpleLinkedDictionary<>();
    }

    @Override
    protected void exerciseLogic() {
        if (currentPhase == 0) {
            System.out.println("\n=== Sistema de login ===");
            System.out.println("Registrate o ingresa con tu cuenta.");
            currentPhase++;
        } else {
            menuLogic();
        }
    }

    // -------------------------------------------------------------------------
    // Menú principal
    // -------------------------------------------------------------------------

    private void menuLogic() {
        System.out.println("\n--- Usuarios registrados: " + users.size() + " ---");
        System.out.println("1. Registrarse");
        System.out.println("2. Ingresar");
        System.out.println("0. Volver al menu principal");
        System.out.print("Seleccione una opc: ");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1": register(); break;
            case "2": login();    break;
            case "0": running = false; break;
            default:  System.out.println("Opcion invalida, intente nuevamente.");
        }
    }

    // -------------------------------------------------------------------------
    // Registro
    // -------------------------------------------------------------------------

    private void register() {
        System.out.println("\n--- Registro de nuevo usuario ---");

        String username = readUsername();
        if (username == null) return;

        if (users.containsKey(username)) {
            System.out.println("El nombre de usuario \"" + username + "\" ya existe. Probá con otro.");
            return;
        }

        String password = readPassword();
        if (password == null) return;

        users.put(username, new UserAccount(username, password));
        System.out.println("Cuenta creada exitosamente. Ya podés ingresar.");
    }

    // -------------------------------------------------------------------------
    // Login
    // -------------------------------------------------------------------------

    /*
     * Decisión de diseño: el bloqueo persiste en el objeto UserAccount dentro del
     * diccionario, por lo que sobrevive a volver al menú principal.
     * Esto refleja el comportamiento realista de un sistema de seguridad donde
     * bloquear una cuenta impide el acceso sin importar cuántas veces se intente.
     */
    private void login() {
        System.out.println("\n--- Ingreso ---");

        String username = readUsername();
        if (username == null) return;

        if (!users.containsKey(username)) {
            System.out.println("El usuario \"" + username + "\" no existe.");
            return;
        }

        UserAccount account = users.get(username);

        if (account.isBlocked()) {
            System.out.println("La cuenta \"" + username + "\" esta bloqueada por demasiados intentos fallidos.");
            return;
        }

        String password = readPassword();
        if (password == null) return;

        if (account.login(password)) {
            System.out.println("Bienvenido, " + username + "!");
        } else if (account.isBlocked()) {
            System.out.println("Contrasena incorrecta. La cuenta ha sido bloqueada por demasiados intentos.");
        } else {
            System.out.println("Contrasena incorrecta. Intentos restantes: " + account.getRemainingAttempts() + ".");
        }
    }

    // -------------------------------------------------------------------------
    // Helpers de lectura
    // -------------------------------------------------------------------------

    /**
     * Solicita un nombre de usuario no vacío.
     * Devuelve null si el usuario escribe "0" para cancelar.
     */
    private String readUsername() {
        System.out.print("Nombre de usuario (0 para cancelar): ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                System.out.println("Operacion cancelada.");
                return null;
            }
            if (!input.isEmpty()) return input;
            System.out.print("El nombre no puede estar vacio. Ingrese nuevamente (0 para cancelar): ");
        }
    }

    /**
     * Solicita una contraseña de al menos 1 carácter.
     * Devuelve null si el usuario escribe "0" para cancelar.
     *
     * Decisión de diseño: no se impone una longitud mínima compleja para mantener
     * el foco en la lógica del TDA. En un sistema real se aplicarían reglas de
     * complejidad de contraseña.
     */
    private String readPassword() {
        System.out.print("Contrasena (0 para cancelar): ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                System.out.println("Operacion cancelada.");
                return null;
            }
            if (!input.isEmpty()) return input;
            System.out.print("La contrasena no puede estar vacia. Ingrese nuevamente (0 para cancelar): ");
        }
    }
}
