12
import java.util.Scanner;
import java.util.Random;

public class DataSorter {

    static Scanner sc = new Scanner(System.in);

    public static void start() {

        System.out.println("===== DATA SORTER MODULE =====");
        System.out.println("1. Enter Numbers Manually");
        System.out.println("2. Generate Random Numbers");

        int choice = sc.nextInt();
        int[] arr;

        if (choice == 1) {
            System.out.print("How many numbers? ");
            int size = sc.nextInt();
            arr = new int[size];

            for (int i = 0; i < size; i++) {
                System.out.print("Enter number: ");
                arr[i] = sc.nextInt();
            }

        } else {
            System.out.print("Enter size of dataset: ");
            int size = sc.nextInt();
            arr = generateRandomArray(size);
        }

        compareAlgorithms(arr);
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }

        return arr;
    }

    public static void compareAlgorithms(int[] original) {

        int[] arr1 = original.clone();
        int[] arr2 = original.clone();
        int[] arr3 = original.clone();

        long start, end;

        // Bubble Sort
        start = System.nanoTime();
        bubbleSort(arr1);
        end = System.nanoTime();
        long bubbleTime = end - start;

        // Merge Sort
        start = System.nanoTime();
        mergeSort(arr2, 0, arr2.length - 1);
        end = System.nanoTime();
        long mergeTime = end - start;

        // Quick Sort
        start = System.nanoTime();
        quickSort(arr3, 0, arr3.length - 1);
        end = System.nanoTime();
        long quickTime = end - start;

        System.out.println("\n===== PERFORMANCE COMPARISON =====");
        System.out.println("Bubble Sort Time: " + bubbleTime + " ns");
        System.out.println("Merge Sort Time: " + mergeTime + " ns");
        System.out.println("Quick Sort Time: " + quickTime + " ns");
    }

    // BUBBLE SORT
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // MERGE SORT
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // QUICK SORT
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}