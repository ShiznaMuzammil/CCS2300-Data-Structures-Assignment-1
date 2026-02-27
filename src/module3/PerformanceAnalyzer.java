package module3;

import java.util.Arrays;
import java.util.Random;

public class PerformanceAnalyzer {

    // Three different input sizes to test
    private static final int[] INPUT_SIZES = {100, 500, 1000};

    public static void runAnalysis() {

        System.out.println("\n========================================");
        System.out.println("  MODULE 3: ALGORITHM PERFORMANCE ANALYZER");
        System.out.println("========================================");

        // Arrays to store times for summary table
        long[] mergeTimes = new long[INPUT_SIZES.length];
        long[] searchTimes = new long[INPUT_SIZES.length];

        // ---- MERGE SORT SECTION ----
        System.out.println("\n--- MERGE SORT PERFORMANCE ---");
        System.out.printf("%-15s %-25s%n", "Input Size", "Execution Time (ns)");
        System.out.println("----------------------------------------");

        for (int i = 0; i < INPUT_SIZES.length; i++) {
            int size = INPUT_SIZES[i];
            int[] data = generateRandom(size);
            int[] copy = Arrays.copyOf(data, data.length);

            long start = System.nanoTime();
            MergeSort.sort(copy, 0, copy.length - 1);
            long end = System.nanoTime();

            mergeTimes[i] = end - start;
            System.out.printf("%-15d %-25d%n", size, mergeTimes[i]);
        }

        // ---- BINARY SEARCH SECTION ----
        System.out.println("\n--- BINARY SEARCH PERFORMANCE ---");
        System.out.println("(Array sorted with Merge Sort first, then Binary Search applied)");
        System.out.printf("%-15s %-25s %-20s%n", "Input Size", "Execution Time (ns)", "Target Found?");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < INPUT_SIZES.length; i++) {
            int size = INPUT_SIZES[i];
            int[] data = generateRandom(size);
            int[] sorted = Arrays.copyOf(data, data.length);

            // Sort the array first (binary search needs sorted array)
            MergeSort.sort(sorted, 0, sorted.length - 1);

            // Pick a target that definitely exists (middle element)
            int target = sorted[size / 2];

            long start = System.nanoTime();
            int result = BinarySearch.search(sorted, target);
            long end = System.nanoTime();

            searchTimes[i] = end - start;
            String found = (result != -1) ? "Yes (index " + result + ")" : "No";
            System.out.printf("%-15d %-25d %-20s%n", size, searchTimes[i], found);
        }

        // ---- SUMMARY COMPARISON TABLE ----
        System.out.println("\n========================================");
        System.out.println("         SUMMARY COMPARISON TABLE");
        System.out.println("========================================");
        System.out.printf("%-15s %-25s %-25s%n", "Input Size", "Merge Sort (ns)", "Binary Search (ns)");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < INPUT_SIZES.length; i++) {
            System.out.printf("%-15d %-25d %-25d%n", INPUT_SIZES[i], mergeTimes[i], searchTimes[i]);
        }

        System.out.println("\n--- TIME COMPLEXITY NOTES ---");
        System.out.println("Merge Sort    : O(n log n) - consistent for all cases");
        System.out.println("Binary Search : O(log n)  - requires sorted array");
        System.out.println("========================================\n");
    }

    // Generates an array of random integers
    private static int[] generateRandom(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}