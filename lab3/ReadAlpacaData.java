
/**
 * An app that contains the Alpaca Class, which reads and stores information about Alpacas
 * @author Dylan Nguyen
 **/
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

// Creates Alpaca Classes
public class ReadAlpacaData {
    // Initalizes Blank Array for Alpacas
    private static String[] alpacas = new String[0];

    public static void main(String[] args) {
        // Try Catch for Import Errors
        try {
            // Imports File
            Scanner infile = new Scanner(new File("alpaca-data.txt"));
            
            // Uses % as Delimiter
            infile.useDelimiter("%");

            // Reads Until File End
            while (infile.hasNextLine()) {
                // Creates New Alpaca Class with All Alpaca Data
                Alpaca alpaca = new Alpaca(infile.next(), infile.nextInt(), infile.nextDouble(), infile.nextBoolean());
                
                String alpacaString = alpaca.toString();
    
                // Adds New Alpaca to Array
                alpacas = add(alpacas, alpacaString);

                // Moves Scanner to Next Line
                infile.nextLine();
              }

            for (int i = 0; i < alpacas.length; i++) {
                System.out.println(alpacas[i]);
              }
        } catch (FileNotFoundException e) {
            // Log for File Import Errors
            System.out.println("Error Importing File");
            e.printStackTrace();
          }
    }

    // Class to Add to Array
    public static String[] add(String[] array, String newString) 
    { 
        int arraySize = array.length;
  
        // Creates a Bigger Array
        String[] newArray = new String[arraySize + 1]; 
  
        // Moves Elements from Old, Smaller Array into New, Larger Array
        for (int i = 0; i < arraySize; i++) 
            newArray[i] = array[i]; 
  
        // Adds New Value
        newArray[newArray.length - 1] = newString; 
  
        return newArray; 
    } 
}