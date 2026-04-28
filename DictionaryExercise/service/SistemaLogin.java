package service;

import tda.SimpleDictionary;
import tda.SimpleArrayDictionary;
import java.util.Scanner;

public class SistemaLogin {

    private SimpleDictionary<String, String> usuarios;

    private SimpleDictionary<String, Boolean> bloqueados;

    public SistemaLogin() {
        usuarios = new SimpleArrayDictionary<>();
        bloqueados = new SimpleArrayDictionary<>();
    }

    public void registrar(Scanner scanner) {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();

        if (user.isEmpty()) {
            System.out.println("Usuario inválido");
            return;
        }

        if (usuarios.containsKey(user)) {
            System.out.println("Ya existe");
            return;
        }

        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        if (pass.isEmpty()) {
            System.out.println("Contraseña inválida");
            return;
        }

        usuarios.put(user, pass);
        bloqueados.put(user, false);
    }

    public boolean login(Scanner scanner) {
        System.out.print("Usuario: ");
        String user = scanner.nextLine();

        if (!usuarios.containsKey(user)) {
            System.out.println("No existe");
            return true;
        }

        if (bloqueados.get(user)) {
            System.out.println("Usuario bloqueado.");
            return true;
        }

        for (int i = 1; i <= 3; i++) {
            System.out.print("Contraseña: ");
            String pass = scanner.nextLine();

            if (usuarios.get(user).equals(pass)) {
                System.out.println("Login correcto");
                return true;
            } else {
                System.out.println("Error intento " + i + "/3");
            }
        }

        bloqueados.put(user, true);
        System.out.println("Usuario bloqueado por 3 intentos fallidos.");

        return true; 
    }
}