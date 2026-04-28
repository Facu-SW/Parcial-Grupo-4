package application;

import java.util.Scanner;
import listModule.ListExercise;
import stackModule.StackExercise;
import queueModule.QueueExercise;
import setModule.SetExercise;

public class MainProgram {

    private boolean running = true;
    private Exercise exercise;

    public static void main(String[] args) {
        MainProgram program = new MainProgram();
        program.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in); 
        while (running) {
            selectExercise(scanner);
            if (exercise != null) {
                exercise.run();
                exercise = null;
            }
        }
        scanner.close();
        System.out.println("Programa Terminado");
    }

    private void selectExercise(Scanner scanner) {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Test Exercise");
            System.out.println("2. List Exercise");
            System.out.println("3. Stack Exercise");
            System.out.println("4. Queue Exercise");
            System.out.println("5. Set Exercise");
            System.out.println("0. Salir");
            System.out.print("Elección: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": exercise = new TestExercise(scanner); return;
                case "2": exercise = new ListExercise(scanner); return;
                case "3": exercise = new StackExercise(scanner); return;
                case "4": exercise = new QueueExercise(scanner); return;
                case "5": exercise = new SetExercise(scanner); return;
                case "0": running = false; return;
                default: System.out.println("Input invalido, intente de nuevo");
            }
        }
    }
}
