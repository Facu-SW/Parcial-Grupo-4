package application;

import java.util.Scanner;

public class TestExercise extends Exercise {

    public TestExercise(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("\n=== TEST EXERCISE ===");
        System.out.println("Bienvenido al test exercise!");
        System.out.println("0. Volver al menú");
        System.out.print("Elección: ");
        String input = scanner.nextLine().trim();
        if (input.equals("0")) {
            running = false;
        } else {
            System.out.println("input Invalido.");
        }
    }
}
