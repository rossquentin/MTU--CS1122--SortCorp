import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Creates three different sorting methods, two of which are a variation of the selection sort,
 * and the last being a quick sort.
 */
public class Sorts {

    /**
     * Uses the selection sorting algorithm to sort an integer array.
     *
     * @param arr   The array to be sorted
     */
    public static void intSelectionSort(int[] arr) {
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Uses the selection sorting algorithm to sort a generic array.
     *
     * @param arr   The array to be sorted.
     * @param <E>   The generic type
     */
    public static <E extends Comparable<E>> void genericSelectionSort(E[] arr) {
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[i]) < 0) {
                    E temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Uses the quick sort algorithm to sort a generic array.
     *
     * @param arr   The array to be sorted
     * @param <E>   The generic type
     * @return  A sorted array
     */
    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> E[] quickSort(E[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        E pivot = arr[0];
        int n = 0;
        int index1 = 0;
        int index2 = 0;

        for (int i = 1; i < arr.length ; i++) {
            if(arr[i].compareTo(pivot) <= 0) {
                n++;
            }
        }

        E[] arr1 = (E[]) new Comparable[n];
        E[] arr2 = (E[]) new Comparable[arr.length - n - 1];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(pivot) <= 0) {
                arr1[index1] = arr[i];
                index1++;
            }
            else {
                arr2[index2] = arr[i];
                index2++;
            }
        }
        quickSort(arr1);
        quickSort(arr2);

        System.arraycopy(arr1, 0, arr, 0, arr1.length);
        arr[n] = pivot;
        System.arraycopy(arr2, 0, arr, n+1, arr2.length);

        return arr;
    }

    /**
     * Used for testing the three sorting methods.
     */
    public static void main(String[] args) {
        int[] selArr1 = new int[10];
        int[] selArr2 = new int[50];
        Integer[] genQuickArr1 = new Integer[10];
        String[] genQuickArr2 = {"Bop", "meh", "ImPleMeNT thIS", "HAH", "coding is fun", "Debugging is not fun", "123", "OneTwoThree", "1Two3"};

        String[] genSelArr1 = {"Alpha", "Poggers", "Greg", "lowercase", "soRt this", "sort That", "12345", "bad", "good"};
        SortCorp[] genSelArr2 =
                {new SortCorp("Sort"), new SortCorp("SortC"), new SortCorp("So"),
                        new SortCorp("SortCor"), new SortCorp("Sor"), new SortCorp("SportCorp"),
                        new SortCorp("StCorp"), new SortCorp("SortCo"), new SortCorp("SortCorp"),
                        new SortCorp("S"), new SortCorp("ScottPomerville")};

        for (int i = 0; i < selArr1.length; i++) {
            selArr1[i] = (int) (Math.random() * 100 - Math.random() * 100);
            genQuickArr1[i] = (int) (Math.random() * 100 - Math.random() * 100);
        }
        for (int i = 0; i < selArr2.length; i++) {
            selArr2[i] = (int) (Math.random() * 100 - Math.random() * 100);
        }

        System.out.println("Selection Sort Array 1");
        intSelectionSort(selArr1);
        System.out.println("Selection Array 2");
        intSelectionSort(selArr2);

        System.out.println();

        System.out.println("Generic Selection Sort Array 1");
        genericSelectionSort(genSelArr1);
        System.out.println("Generic Selection Sort Array 2");
        genericSelectionSort(genSelArr2);

        System.out.println();

        System.out.println("Generic Quick Sort Array 1");
        System.out.println(Arrays.toString(genQuickArr1));
        System.out.println(Arrays.toString(quickSort(genQuickArr1)));
        System.out.println();
        System.out.println("Generic Quick Sort Array 2");
        System.out.println(Arrays.toString(genQuickArr2));
        System.out.println(Arrays.toString(quickSort(genQuickArr2)));
    }
}