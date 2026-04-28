package queueModule;

import application.Exercise;
import java.util.Scanner;

public class QueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleQueue<String> queue;

    public QueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleArrayQueue<>(); // cambia a SimpleLinkedQueue<>() para probar el otro
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic(); break;
            case 1: enqueueElement(); break;
            case 2: dequeueElement(); break;
            case 3: peekElement(); break;
            case 4: clearQueue(); break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("\n=== QUEUE EXERCISE ===");
            System.out.println("Bienvenido! Aquí puedes probar las operaciones de Queue.");
            firstTime = false;
        } else {
            System.out.println("\n--- Queue ---");
        }
        System.out.println("Size: " + queue.size() + " | Empty: " + queue.isEmpty());
        System.out.println("\nOpciones:");
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Peek");
        System.out.println("4. Clear");
        System.out.println("0. Volver al main menu");
        System.out.print("Elección: ");
        String input = scanner.nextLine().trim();
        switch (input) {
            case "1": currentPhase = 1; break;
            case "2": currentPhase = 2; break;
            case "3": currentPhase = 3; break;
            case "4": currentPhase = 4; break;
            case "0": running = false; break;
            default: System.out.println("input Invalido."); break;
        }
    }

    private void enqueueElement() {
        System.out.print("Ingrese elemento a encolar: ");
        String element = scanner.nextLine().trim();
        queue.enqueue(element);
        System.out.println("Encolado: " + element);
        currentPhase = askRepeat() ? 1 : 0;
    }

    private void dequeueElement() {
        if (queue.isEmpty()) {
            System.out.println("Cola vacia, no se pudo desencolar.");
            currentPhase = 0;
            return;
        }
        String element = queue.dequeue();
        System.out.println("Desencolado: " + element);
        currentPhase = askRepeat() ? 2 : 0;
    }

    private void peekElement() {
        if (queue.isEmpty()) {
            System.out.println("Cola vacía no se puede ejecutar operación peek");
        } else {
            System.out.println("Elemento al frente: " + queue.peek());
        }
        currentPhase = 0;
    }

    private void clearQueue() {
        if (queue.isEmpty()) {
            System.out.println("Cola ya estaba vacia");
        } else {
            queue.clear();
            System.out.println("Cola vaciada.");
        }
        currentPhase = 0;
    }

    private boolean askRepeat() {
        while (true) {
            System.out.print("Repetir operación? (si/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("si") || input.equals("s")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.println("Input invalido.");
        }
    }
}
