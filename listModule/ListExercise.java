package listModule;

import application.Exercise;
import java.util.Scanner;

public class ListExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleList<String> list;

    public ListExercise(Scanner scanner) {
        super(scanner);
        list = new SimpleArrayList<>(); // cambia a SimpleLinkedList<>() para probar la otra implementacion
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic(); break;
            case 1: addElement(); break;
            case 2: removeByIndex(); break;
            case 3: removeByReference(); break;
            case 4: clearList(); break;
        }
    }

    private String listToString() {
        if (list.isEmpty()) return "(vacío)";
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("\n=== LIST EXERCISE ===");
            System.out.println("Bienvenido! Aqui puedes probar las operaciones de list.");
            firstTime = false;
        } else {
            System.out.println("\n--- List: " + listToString());
        }
        System.out.println("Tamaño: " + list.size() + " | Vacío: " + list.isEmpty());
        System.out.println("\nOpciones:");
        System.out.println("1. Añadir elemento");
        System.out.println("2. Remover por index");
        System.out.println("3. Remover por referencia");
        System.out.println("4. Vaciar lista");
        System.out.println("0. Volver al menú principal");
        System.out.print("Elección: ");
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

    private void addElement() {
        System.out.print("Elija elemento a añadir: ");
        String element = scanner.nextLine().trim();
        list.add(element);
        System.out.println("Lista después de añadir: " + listToString());
        currentPhase = askRepeat() ? 1 : 0;
    }

    private void removeByIndex() {
        if (list.isEmpty()) {
            System.out.println("Listra vacía, no se puede remover por index");
            currentPhase = 0;
            return;
        }
        System.out.print("Inserte index a remover (0-" + (list.size() - 1) + "): ");
        String input = scanner.nextLine().trim();
        try {
            int index = Integer.parseInt(input);
            if (index < 0 || index >= list.size()) {
                System.out.println("Index fuera de rango.");
                currentPhase = 0;
                return;
            }
            list.remove(index);
            System.out.println("List despues de remover: " + listToString());
            currentPhase = askRepeat() ? 2 : 0; 
        } catch (NumberFormatException e) {
            System.out.println("Input invalido, debe ser un numero");
            currentPhase = 0;
        }
    }

    private void removeByReference() {
        if (list.isEmpty()) {
            System.out.println("Lista vacia, no se puede remover por index");
            currentPhase = 0;
            return;
        }
        System.out.print("Inserte elemento a remover: ");
        String element = scanner.nextLine().trim();
        boolean removed = list.remove((Object) element);
        System.out.println(removed ? "Elemento removido." : "Elemento no encontrado.");
        System.out.println("Lista: " + listToString());
        currentPhase = askRepeat() ? 3 : 0; 
    }

    private void clearList() {
        list.clear();
        System.out.println("Lista vaciada.");
        currentPhase = 0;
    }

    private boolean askRepeat() {
        while (true) {
            System.out.print("Repetir operación? (si/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("si") || input.equals("s")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.println("Invalid input.");
        }
    }
}
