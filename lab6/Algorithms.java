/**
 * A file that contains implementations of selection sort and bubble sort
 * @author Dylan Nguyen
 * 
 * Documentation Used:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
 * https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 */

// Imports Required Packages
import java.util.Random; // For Random Class and Random Ints

// Algorithms Class
public class Algorithms {
    /* Selection Sort Methods */
    public static int[] selectionSortInt(int[] intArray) {
        // Variables
        int i = 0;
        int j = 0;
        int min = 0;
        int minIndex = 0;
        int[] sortedArray = new int[intArray.length];
        
        // Loop Until Integers are Copied
        while (j < intArray.length) {
            // Set Min to 100 (Maximum Possible Value)
            min = 100;
            // Loops through Array
            for (i = 0; i < intArray.length; i++) {
                // Sets New Minimum if Below Current Minimum and Not 0 (Not Copied Yet)
                if ((intArray[i] < min) && (intArray[i] != 0)) {
                    min = intArray[i];
                    // Records Index of New Minimum
                    minIndex = i;
                }
            }
            // Copies Minimum to New Array
            sortedArray[j] = intArray[minIndex];
            // Sets Old Value to 0 (Outside of Possible Values)
            intArray[minIndex] = 0;
            // Increments J, Number of Integers in Array
            j += 1;
        }

        // Return Sorted Array when Copied
        return sortedArray;
    }

    public static String[] selectionSortString(String[] stringArray) {
        // Variables
        int i = 0;
        int j = 0;
        String min = "";
        int minIndex = 0;
        String[] sortedArray = new String[stringArray.length];
        
        // Loop Until Variables are Copied
        while (j < stringArray.length) {
            // Set Minimum of ZZ (Highest Value)
            min = "zz";
            // Loops through Array
            for (i = 0; i < stringArray.length; i++) {
                // Compares Strings and Sets Minimum if Applicable
                if (((min.compareToIgnoreCase(stringArray[i])) > 0)  && (!stringArray[i].isEmpty())) {
                    min = stringArray[i];
                    // Records Index
                    minIndex = i;
                }
            }
            // Copy Minimum to Sorted Array
            sortedArray[j] = stringArray[minIndex];
            // Set Value in Original Array to Empty
            stringArray[minIndex] = "";
            j += 1;
        }

        // Return Final Sorted Array
        return sortedArray;
    }

    /* Bubble Sort Methods */
    public static int[] bubbleSortInt(int[] intArray) {
        // Variables
        boolean sorted = false;
        int i = 0;
        int temp = 0;

        // While Not Sorted
        while (sorted == false) {
            // Sets Sorted to True (Will Remain True if Sorted)
            sorted = true;
            // Loop Through Array Length - 1 (Cannot Compare Last Value + Next Value)
            for (i = 0; i < intArray.length-1; i++) {
                // Swaps Values if Next Value is Smaller
                if (intArray[i] > intArray[i+1]) {
                    // Swaps Values
                    temp = intArray[i];
                    intArray[i] = intArray[i+1];
                    intArray[i+1] = temp;
                    // Sets Sorted to False
                    sorted = false;
                }
            }
        }

        // Returns Array when Sorted
        return intArray;
    }

    public static String[] bubbleSortString(String[] stringArray) {
        // Variables
        boolean sorted = false;
        int i = 0;
        String temp = "";

        // While Not Sorted
        while (sorted == false) {
            // Sets Sorted to True (Will Remain True if Sorted)
            sorted = true;
            // Loop Through Array Length - 1 (Cannot Compare Last Value + Next Value)
            for (i = 0; i < stringArray.length-1; i++) {
                // Swaps Values if Next Value is Smaller
                if (stringArray[i].compareToIgnoreCase(stringArray[i+1]) > 0) {
                    // Swaps Values
                    temp = stringArray[i];
                    stringArray[i] = stringArray[i+1];
                    stringArray[i+1] = temp;
                    // Sets Sorted to False
                    sorted = false;
                }
            }
        }

        // Returns Array when Sorted
        return stringArray;
    }

    /* Helper Methods */
    // Generate Array of Length 10
    public static int[] generateIntArray() {
        // Variables
        int[] array = new int[10];
        Random random = new Random();

        // Loops through Array
        for (int i = 0; i < array.length; i++) {
            // Adds Random Int from 1 to 100
            array[i] = random.nextInt(100) + 1;
        }

        // Return Random Array
        return array;
    }

    // Print Integer Arrays
    public static void printIntArray(int[] intArray, String startingString) {
        // Initial String is Inputted String
        String arrayString = startingString;

        // Loops through Array
        for (int i = 0; i < intArray.length; i++) {
            // Adds Values to String
            arrayString += " " + String.valueOf(intArray[i]);
        }

        // Print String
        System.out.println(arrayString);
    }

    // Print String Arrays
    public static void printStringArray(String[] stringArray, String startingString) {
        // Initial String is Inputted String
        String arrayString = startingString;

        // Loops through Array
        for (int i = 0; i < stringArray.length; i++) {
            // Adds Values to String
            arrayString += " " + stringArray[i];
        }
        
        // Prints String
        System.out.println(arrayString);
    }

    public static void main(String[] args) {
        // Selection Sort Int
        System.out.println("Selection Sort (Integer)");
        int[] intArraySelection = generateIntArray(); // New Integer Array
        printIntArray(intArraySelection, "Unsorted:"); // Print Unsorted Integer Array
        int[] sortedIntArraySelection = selectionSortInt(intArraySelection); // Sorts Integer Array using Selection Sort
        printIntArray(sortedIntArraySelection, "Sorted:"); // Prints Sorted Integer Array
        System.out.println("");

        // Bubble Sort Int
        System.out.println("Bubble Sort (Integer)");
        int[] intArrayBubble = generateIntArray(); // New Integer Array
        printIntArray(intArrayBubble, "Unsorted:"); // Prints Unsorted Integer Array
        int[] sortedIntArrayBubble = bubbleSortInt(intArrayBubble); // Sorts Integer Array using Bubble Sort
        printIntArray(sortedIntArrayBubble, "Sorted:"); // Prints Sorted Integer Array
        System.out.println("");

        // Selection Sort String
        System.out.println("Selection Sort (String)");
        String[] stringArraySelection = {"mango", "apple", "Nectarine", "banana", "pear", "cherries", "Peach", "Grapes", "orange", "strawberries"};
        printStringArray(stringArraySelection, "Unsorted:"); // Prints Unsorted String Array
        String[] sortedStringArraySelection = selectionSortString(stringArraySelection); // Sorts String Array with Selection Sort
        printStringArray(sortedStringArraySelection, "Sorted:"); // Prints Sorted String Array
        System.out.println("");

        // Bubble Sort String
        System.out.println("Bubble Sort (String)");
        String[] stringArrayBubble = {"Lettuce", "mushrooms", "avocado", "zucchini", "tomatoes", "broccoli", "Squash", "kale", "radish", "Cabbage"};
        printStringArray(stringArrayBubble, "Unsorted:"); // Prints Unsorted String Array
        String[] sortedStringArrayBubble = bubbleSortString(stringArrayBubble); // Sorts String Array with Bubble Sort
        printStringArray(sortedStringArrayBubble, "Sorted:"); // Prints Sorted String Array
        System.out.println("");
    }
}
