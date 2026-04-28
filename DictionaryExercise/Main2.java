import service.SistemaLogin;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaLogin sistema = new SistemaLogin();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");

            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    sistema.registrar(scanner);
                    break;
                case "2":
                    if (!sistema.login(scanner)) {
                        salir = true;
                    }
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

        scanner.close();
    }
}