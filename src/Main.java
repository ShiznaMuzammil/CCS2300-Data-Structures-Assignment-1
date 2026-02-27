import module3.PerformanceAnalyzer;
import tree.BST;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("         CCS2300 ASSIGNMENT 1");
            System.out.println("========================================");
            System.out.println("1. Smart City Route Planner (Module 1)");
            System.out.println("2. Data Sorter (Module 2)");
            System.out.println("3. Algorithm Performance Analyzer (Module 3)");
            System.out.println("4. BST Location Tree Demo");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n[Module 1 - To be implemented by Member 1]");
                    break;
                case 2:
                    System.out.println("\n[Module 2 - To be implemented by Member 2]");
                    break;
                case 3:
                    PerformanceAnalyzer.runAnalysis();
                    break;
                case 4:
                    bstDemo();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void bstDemo() {
        BST locationTree = new BST();
        System.out.println("\n--- BST: Adding locations ---");

        String[] locations = {"Colombo", "Kandy", "Galle", "Jaffna", "Negombo", "Matara"};
        for (String loc : locations) {
            locationTree.insert(loc);
            System.out.println("Inserted: " + loc);
        }

        locationTree.displayInOrder();

        System.out.println("Searching for 'Kandy': " + locationTree.search("Kandy"));
        System.out.println("Searching for 'Badulla': " + locationTree.search("Badulla"));
    }
}