import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Reclamo> cola = new PriorityQueueLinked<>();

        int opcion;

        do {
            System.out.println("\n1. Crear reclamo");
            System.out.println("2. Ver todos los reclamos");
            System.out.println("3. Resolver mayor reclamo");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Descripción: ");
                    String desc = scanner.nextLine();

                    System.out.print("Prioridad (1=Crítico, 2=Alto, 3=Medio, 4=Bajo): ");
                    int prioridad = scanner.nextInt();
                    scanner.nextLine();

                    if (prioridad < 1 || prioridad > 4) {
                        System.out.println("Prioridad inválida.");
                        break;
                    }

                    cola.enqueue(new Reclamo(titulo, desc, prioridad), prioridad);
                    System.out.println("Reclamo agregado.");
                    break;

                case 2:
                    System.out.println("Listado de reclamos:");
                    cola.showAll();
                    break;

                case 3:
                    if (cola.isEmpty()) {
                        System.out.println("No hay reclamos.");
                    } else {
                        System.out.println("Reclamo resuelto:");
                        System.out.println(cola.dequeue());
                    }
                    break;
            }

        } while (opcion != 4);

        scanner.close();
    }
}