
/**
 * An app that allows for the creation, modification, and deletion of Alpacas
 * @author Dylan Nguyen
 **/

// Imports Required Packages
import javax.swing.*; // Java GUI with Swing
import javax.swing.SpringLayout; // Spring Layout
import java.awt.*; 
import java.awt.event.*; // Action Listener
import java.io.File;  // File class
import java.io.FileNotFoundException;  // FileNotFound Error Handler for File Import Errors
import java.util.Scanner; // Scanner Class for Reading Text Files
import java.io.FileOutputStream; // Class for Writing Files
import java.io.IOException; // IOException Error Handler for File Writing Errors


// Extends JPanel to Create Panel
public class AlpacaGUI extends JPanel {
    // Initalizes Blank Array for Alpacas
    private static String[] alpacas = new String[0];

    // Declares Necessary Panel Components
    private JTextField nameTextField, ageTextField, weightTextField, likesPetsTextField;
    private JButton searchButton, saveButton, addButton;
    private JLabel nameLabel, ageLabel, weightLabel, likesPetsLabel, messageLabel, responseLabel;

    // Creates Scanner for File Reading
    public static Scanner infile;

    public AlpacaGUI() {
        // Creates JFrame with Name
        JFrame frame = new JFrame("Alpaca Frame");

        // Sets Properies
        frame.setSize(500, 300);  // Size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Main Panel
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        // Adds Labels
        panel.add(nameLabel = new JLabel("Alpaca Name"));
        panel.add(ageLabel = new JLabel("Alpaca Age"));
        panel.add(weightLabel = new JLabel("Alpaca Weight"));
        panel.add(likesPetsLabel = new JLabel("Likes Pets"));
        panel.add(messageLabel = new JLabel("Message"));

        // Adds Fields
        panel.add(nameTextField = new JTextField(25));
        panel.add(ageTextField = new JTextField(25));
        panel.add(weightTextField = new JTextField(25));
        panel.add(likesPetsTextField = new JTextField(25));
        panel.add(responseLabel = new JLabel(""));

        // Create Button Panel
        JPanel buttonPanel = new JPanel();
        GridLayout buttonLayout = new GridLayout();
        buttonPanel.setLayout(buttonLayout);

        // Adds Buttons
        buttonPanel.add(searchButton = new JButton("Search"));
        buttonPanel.add(saveButton = new JButton("Save"));
        buttonPanel.add(addButton = new JButton("Add"));
        panel.add(buttonPanel);

        // Adds Listeners
        searchButton.addActionListener(new SearchListener());
        saveButton.addActionListener(new SaveListener());
        addButton.addActionListener(new AddListener());

        // Constraints
        layout.putConstraint(SpringLayout.WEST, nameLabel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 25, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameTextField, 15, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, nameTextField, 0, SpringLayout.NORTH, nameLabel);

        layout.putConstraint(SpringLayout.WEST, ageLabel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 25, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST, ageTextField, 15, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, ageTextField, 0, SpringLayout.NORTH, ageLabel);

        layout.putConstraint(SpringLayout.WEST, weightLabel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, weightLabel, 25, SpringLayout.NORTH, ageLabel);
        layout.putConstraint(SpringLayout.WEST, weightTextField, 15, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, weightTextField, 0, SpringLayout.NORTH, weightLabel);

        layout.putConstraint(SpringLayout.WEST, likesPetsLabel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, likesPetsLabel, 25, SpringLayout.NORTH, weightLabel);
        layout.putConstraint(SpringLayout.WEST, likesPetsTextField, 15, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, likesPetsTextField, 0, SpringLayout.NORTH, likesPetsLabel);

        layout.putConstraint(SpringLayout.WEST, messageLabel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, messageLabel, 25, SpringLayout.NORTH, likesPetsLabel);
        layout.putConstraint(SpringLayout.WEST, responseLabel, 15, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, responseLabel, 0, SpringLayout.NORTH, messageLabel);

        layout.putConstraint(SpringLayout.WEST, messageLabel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, messageLabel, 25, SpringLayout.NORTH, likesPetsLabel);
        layout.putConstraint(SpringLayout.WEST, responseLabel, 15, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, responseLabel, 0, SpringLayout.NORTH, messageLabel);

        layout.putConstraint(SpringLayout.WEST, buttonPanel, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, buttonPanel, 25, SpringLayout.NORTH, messageLabel);
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 15, SpringLayout.EAST, nameLabel);

        // Adds Panel and Sets Visible
        frame.add(panel);
        frame.setVisible(true);
    }

    /*
     * Listeners 
     */

    // Listens for Search Button 
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Gets Name
            String searchName = nameTextField.getText();

            // Sets Response Label to Blank
            responseLabel.setText("");

            // Checks for Valid Name Input
            if (searchName.matches("^[a-zA-Z- ]*$") && !searchName.isEmpty()) {
                // Sets Text Fields Blank
                setTextFieldsBlank(); 

                // Creates New Scanner
                newScanner();

                // Reads Until File End
                while (infile.hasNextLine()) {
                    // Gets Alpaca Name
                    String alpacaName = infile.next();

                    // Checks for Same Name
                    if (alpacaName.equalsIgnoreCase(searchName)) {
                        // Sets Response Label if Alpaca is Found
                        responseLabel.setText("Alpaca Found!");
                        
                        // Sets Text Fields
                        nameTextField.setText(alpacaName);
                        ageTextField.setText(String.valueOf(infile.nextInt()));
                        weightTextField.setText(String.valueOf(infile.nextDouble()));
                        likesPetsTextField.setText(String.valueOf(infile.nextBoolean()));
                    }
                    // Moves Scanner to Next Line
                    infile.nextLine();
                }

                // If End of File is Reached
                if (!infile.hasNextLine() && (!responseLabel.getText().equals("Alpaca Found!"))) {
                    // Sets Name
                    nameTextField.setText(searchName);

                    // Sets Response Label if Alpaca Not Found in File
                    responseLabel.setText("Alpaca Not Found :(");
                }
                
                // Closer Scanner
                infile.close();
            } else {
                // Sets Label for Invalid Character
                responseLabel.setText("Please input a valid character");
            }
        }
    }

    // Listens for Save Button
    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Sets Response Label to Blank
            responseLabel.setText("");

            // Checks for Correct Inputs
            if (!nameTextField.getText().matches("^[a-zA-Z- ]*$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else if (!ageTextField.getText().matches("^[0-9]*$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else if (!weightTextField.getText().matches("^[0-9.]*$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else if (!likesPetsTextField.getText().matches("^true$|^True%|^false$|^False$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else {
                // Gets New Scanner
                newScanner();

                // Create String Buffer
                StringBuffer inputBuffer = new StringBuffer();

                // Reads Until File End
                while (infile.hasNextLine()) {
                    // Gets Alpaca Name
                    String alpacaName = infile.next();

                    // Checks Line for Correct Alpaca Name
                    if (alpacaName.equalsIgnoreCase(nameTextField.getText())) {
                        // Sets Response Label if Alpaca is Found
                        responseLabel.setText("Changes Saved");
                        
                        // Creates New String
                        String modifiedAlpacaData = nameTextField.getText() + "%" + ageTextField.getText() + "%" + weightTextField.getText() + "%" + likesPetsTextField.getText().toLowerCase() + "%";

                        // Appends to Buffer
                        inputBuffer.append(modifiedAlpacaData + "\n");
                    } else {
                        // Writes Line to Buffer if Unchanged
                        inputBuffer.append(alpacaName + "%" + infile.nextInt() + "%" + infile.nextDouble() + "%" + infile.nextBoolean() + "%" + "\n");
                    }

                    // Moves Scanner to Next Line
                    infile.nextLine();
                } 

                // If End of File is Reached without Changes
                if (!infile.hasNextLine() && (!responseLabel.getText().equals("Changes Saved"))) {
                    // Sets Response Label 
                    responseLabel.setText("No Changes to Save");
                } else if (!infile.hasNextLine() && responseLabel.getText().equals("Changes Saved")) {
                    // End of File Reached with Changes
                    // Try Catch for Import Errors
                    try {
                        // Write New File over Old File
                        FileOutputStream file = new FileOutputStream("alpaca-data.txt");
                        file.write(inputBuffer.toString().getBytes());

                        // Close File
                        file.close();
                    } catch (IOException g) {
                        // Log for Write Errors
                        System.out.println("Error Writing File");
                        g.printStackTrace();
                    }

                    // Close Scanner
                    infile.close();

                    // Set Text Fields Blank
                    setTextFieldsBlank();

                    // Prints Changes
                    printAlpacas();
                }
            }
        }
    }

    // Listens for Add Button
    private class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Sets Response Label to Blank
            responseLabel.setText("");

            // Checks for Valid Input
            if (!nameTextField.getText().matches("^[a-zA-Z- ]*$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else if (!ageTextField.getText().matches("^[0-9]*$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else if (!weightTextField.getText().matches("^[0-9.]*$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else if (!likesPetsTextField.getText().matches("^true$|^True%|^false$|^False$") || nameTextField.getText().isEmpty()) {
                responseLabel.setText("Please input a valid character");
            } else {
                // Retrieves Alpaca Name from Text Field
                String searchName = nameTextField.getText();

                // Creates New Scanner
                newScanner();
                
                // Goes Through File
                while (infile.hasNextLine()) {
                    // Gets Alpaca Name
                    String alpacaName = infile.next();

                    // Checks for Same Name
                    if (alpacaName.equalsIgnoreCase(searchName)) {
                        // Sets Label if Alpaca Exists
                        responseLabel.setText(searchName + " the Alpaca already exists!");
                    }
                    // Moves Scanner to Next Line
                    infile.nextLine();
                }

                // If Alpaca does not Exist
                if (!infile.hasNextLine() && (!responseLabel.getText().contains("already exists"))) {
                    // Create String to Add to Data
                    String newCuteAlpaca = nameTextField.getText() +"%" + ageTextField.getText() + "%" + weightTextField.getText() + "%" +  likesPetsTextField.getText() + "%" + "\n";

                    // Try Catch for Import Errors
                    try {
                        // Write New File over Old File
                        FileOutputStream file = new FileOutputStream("alpaca-data.txt", true);

                        // Appends Alpaca
                        file.write(newCuteAlpaca.toString().getBytes());

                        // Close File
                        file.close();
                    } catch (IOException g) {
                        // Log for Write Errors
                        System.out.println("Error Writing File");
                        g.printStackTrace();
                    }
                    
                    // Set Response Label on Creation
                    responseLabel.setText("Your New Cute Alpaca, " + nameTextField.getText() + ", has been addded");
                    
                    // Set Labels to Blank
                    setTextFieldsBlank();

                    // Print Data
                    printAlpacas();
                }
            }
        }
    }

    /*
     * Helper Methods
     */

    // Method to Print File
    public static void printAlpacas() {
        System.out.println("<--- Alpaca Data --->");
        // Creates New Scanner
        newScanner();

        while (infile.hasNextLine()) {
            // Creates New Alpaca Class with All Alpaca Data
            Alpaca alpaca = new Alpaca(infile.next(), infile.nextInt(), infile.nextDouble(), infile.nextBoolean());
            
            // Gets String of Alpaca
            String alpacaString = alpaca.toString();

            // Adds New Alpaca to Array
            alpacas = addToArray(alpacas, alpacaString);

            // Moves Scanner to Next Line
            infile.nextLine();
          }

        // Print Alpaca Array
        for (int i = 0; i < alpacas.length; i++) {
            System.out.println(alpacas[i]);
          }

        // Close Scanner
        infile.close();
    }

    // Creates New Scanner
    public static void newScanner() {
        // Try Catch for Import Errors
        try {
            // Imports File
            infile = new Scanner(new File("alpaca-data.txt"));
            
            // Uses % as Delimiter
            infile.useDelimiter("%");
        } catch (FileNotFoundException f) {
            // Log for File Import Errors
            System.out.println("Error Importing File");
            f.printStackTrace();
          }

    }

    // Sets All TextFields Blank
    public void setTextFieldsBlank() {
        nameTextField.setText("");
        ageTextField.setText("");
        weightTextField.setText("");
        likesPetsTextField.setText("");
    }

    // Method to Add to Array
    public static String[] addToArray(String[] array, String newString) { 
        // Array Size
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

    public static void main(String[] args) {
        new AlpacaGUI();
    }
}
