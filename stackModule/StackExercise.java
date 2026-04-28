package stackModule;

import application.Exercise;
import java.util.Scanner;

public class StackExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleStack<String> stack;

    public StackExercise(Scanner scanner) {
        super(scanner);
        stack = new SimpleArrayStack<>(); // cambias a SimpleLinkedStack<>() para ver el otro
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic(); break;
            case 1: pushElement(); break;
            case 2: popElement(); break;
            case 3: peekElement(); break;
            case 4: clearStack(); break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("\n=== STACK EXERCISE ===");
            System.out.println("Bienvenido! Aqui puedes probar las operaciones de Stack.");
            firstTime = false;
        } else {
            System.out.println("\n--- Stack ---");
        }
        System.out.println("Tamaño: " + stack.size() + " | Vacio: " + stack.isEmpty());
        System.out.println("\nOpciones:");
        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. Peek");
        System.out.println("4. Clear");
        System.out.println("0. Volver al menu principal");
        System.out.print("Choice: ");
        String input = scanner.nextLine().trim();
        switch (input) {
            case "1": currentPhase = 1; break;
            case "2": currentPhase = 2; break;
            case "3": currentPhase = 3; break;
            case "4": currentPhase = 4; break;
            case "0": running = false; break;
            default: System.out.println("Input Invalido."); break;
        }
    }

    private void pushElement() {
        System.out.print("Ingresar elemento para hacer push: ");
        String element = scanner.nextLine().trim();
        stack.push(element);
        System.out.println("Pushed: " + element);
        currentPhase = askRepeat() ? 1 : 0;
    }

    private void popElement() {
        if (stack.isEmpty()) {
            System.out.println("Stack vacio, no se puede ejecutar pop.");
            currentPhase = 0;
            return;
        }
        String element = stack.pop();
        System.out.println("Popped: " + element);
        currentPhase = askRepeat() ? 2 : 0;
    }

    private void peekElement() {
        if (stack.isEmpty()) {
            System.out.println("Stack vacio, no se puede ejecutar peek.");
        } else {
            System.out.println("Primer elemento: " + stack.peek());
        }
        currentPhase = 0;
    }

    private void clearStack() {
        if (stack.isEmpty()) {
            System.out.println("El stack ya está vacio.");
        } else {
            stack.clear();
            System.out.println("Stack vaciado.");
        }
        currentPhase = 0;
    }

    private boolean askRepeat() {
        while (true) {
            System.out.print("Repetir operación? (si/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("si") || input.equals("s")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.println("Input Invalido.");
        }
    }
}
