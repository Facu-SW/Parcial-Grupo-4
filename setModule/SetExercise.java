package setModule;

import application.Exercise;
import java.util.Scanner;

public class SetExercise extends Exercise {

    private final SimpleSet<String> setA;
    private final SimpleSet<String> setB;
    private boolean firstTime = true;

    public SetExercise(Scanner scanner) {
        super(scanner);
        setA = new SimpleArraySet<>();
        setB = new SimpleLinkedSet<>();
    }

    @Override
    protected void exerciseLogic() {
        if (firstTime) {
            System.out.println("Bienvenido al ejercicio de Sets.");
            firstTime = false;
        }

        showMainMenu();
        String option = scanner.nextLine().trim();

        switch (option) {
            case "1":
                workWithSet("A", setA);
                break; 
            case "2":
                workWithSet("B", setB);
                break;
            case "3":
                showGeneratedSet("A union B", setA.unionWith(setB));
                break;
            case "4":
                showGeneratedSet("A intersect B", setA.intersectWith(setB));
                break;
            case "5":
                showGeneratedSet("A difference B", setA.differenceWith(setB));
                break;
            case "6":
                showGeneratedSet("B difference A", setB.differenceWith(setA));
                break;
            case "0":
                running = false;
                System.out.println("Saliendo del ejercicio de Sets.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("===== MENU PRINCIPAL =====");
        showSetInfo("A", setA);
        showSetInfo("B", setB);
        System.out.println();
        System.out.println("1 - Trabajar con Set A");
        System.out.println("2 - Trabajar con Set B");
        System.out.println("3 - Mostrar A union B");
        System.out.println("4 - Mostrar A intersect B");
        System.out.println("5 - Mostrar A difference B");
        System.out.println("6 - Mostrar B difference A");
        System.out.println("0 - Salir");
        System.out.print("Elegí una opción: ");
    }

    private void showSetInfo(String name, SimpleSet<String> set) {
        System.out.println("Set " + name + ": " + formatSet(set));
        System.out.println("  size = " + set.size());
        System.out.println("  isEmpty = " + set.isEmpty());
    }

    private void workWithSet(String name, SimpleSet<String> set) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            System.out.println();
            System.out.println("===== SET " + name + " =====");
            System.out.println("Elementos: " + formatSet(set));
            System.out.println("1 - Agregar elemento");
            System.out.println("2 - Remover elemento");
            System.out.println("3 - Volver al menú principal");
            System.out.print("Elegí una opción: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.print("Ingresá el elemento a agregar: ");
                    String elementToAdd = scanner.nextLine();

                    if (set.add(elementToAdd)) {
                        System.out.println("Elemento agregado correctamente.");
                    } else {
                        System.out.println("No se pudo agregar porque ya existe en el Set.");
                    }
                    break;

                case "2":
                    System.out.print("Ingresá el elemento a remover: ");
                    String elementToRemove = scanner.nextLine();

                    if (set.remove(elementToRemove)) {
                        System.out.println("Elemento removido correctamente.");
                    } else {
                        System.out.println("No se pudo remover porque el elemento no existe en el Set.");
                    }
                    break;

                case "3":
                    backToMainMenu = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void showGeneratedSet(String title, SimpleSet<String> set) {
        System.out.println();
        System.out.println("===== " + title + " =====");
        System.out.println("Elementos: " + formatSet(set));
        System.out.println("size = " + set.size());
        System.out.println("isEmpty = " + set.isEmpty());
    }

    private String formatSet(SimpleSet<String> set) {
        Object[] array = set.toArray();

        if (array.length == 0) {
            return "[]";
        }

        String result = "[";

        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if (i < array.length - 1) {
                result += ", ";
            }
        }

        result += "]";
        return result;
    }
}