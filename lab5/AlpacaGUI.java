
/**
 * An app that allows for the creation, modification, and deletion of Alpacas
 * @author Dylan Nguyen
 * 
 * Documentation Used:
 * https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

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
import java.util.ArrayList; // Imports Array Lists


// Extends JPanel to Create Panel
public class AlpacaGUI extends JPanel {
    // Initalizes Blank Array for Alpacas
    private static ArrayList<Alpaca> alpacas = new ArrayList<Alpaca>(5); // Array List of Size 5

    // Declares Necessary Panel Components
    private JTextField nameTextField, ageTextField, weightTextField, likesPetsTextField;
    private JButton searchButton, saveButton, addButton, removeButton, printButton;
    private JLabel nameLabel, ageLabel, weightLabel, likesPetsLabel, messageLabel, responseLabel;

    // Creates Scanner for File Reading
    public static Scanner infile;

    // Initilization
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
        buttonPanel.add(removeButton = new JButton("Remove"));
        buttonPanel.add(printButton = new JButton("Print"));
        panel.add(buttonPanel);

        // Adds Listeners
        searchButton.addActionListener(new SearchListener());
        saveButton.addActionListener(new SaveListener());
        addButton.addActionListener(new AddListener());
        removeButton.addActionListener(new RemoveListener());
        printButton.addActionListener(new PrintListener());


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

        // Adds Panel and Sets Visible
        frame.add(panel);
        frame.setVisible(true);

        // Try to Import File
        try {
            // Imports File
            Scanner infile = new Scanner(new File("alpaca-data.txt"));
            
            // Uses % as Delimiter
            infile.useDelimiter("%");

            // Reads Until File End
            while (infile.hasNextLine()) {
                // Trims Alpaca Array List
                alpacas.trimToSize();

                // Creates New Alpaca Class with All Alpaca Data
                Alpaca alpaca = new Alpaca(infile.next(), infile.nextInt(), infile.nextDouble(), infile.nextBoolean());

                // Increase Array List Size
                alpacas.ensureCapacity(alpacas.size()+1);
                
                // Adds New Alpaca to Array List
                alpacas.add(alpaca);

                // Moves Scanner to Next Line
                infile.nextLine();
            }

            // Prints Alpaca Array List
            for (int i = 0; i < alpacas.size(); i++) {
                System.out.println(alpacas.get(i));
            }
        } catch (FileNotFoundException e) {
            // Log for File Import Errors
            System.out.println("Error Importing File");
            e.printStackTrace();
        }
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

                // Iterates Through Alpacas ArrayList
                for (int i = 0; i < alpacas.size(); i++) {
                    // Gets Alpaca Name
                    String alpacaName = alpacas.get(i).getName();

                    // Checks for Same Name
                    if (alpacaName.equalsIgnoreCase(searchName)) {
                        // Sets Response Label if Alpaca is Found
                        responseLabel.setText("Alpaca Found!");

                        // Get Alpaca
                        Alpaca alpaca = alpacas.get(i);
                        
                        // Sets Text Fields
                        nameTextField.setText(alpaca.getName());
                        ageTextField.setText(String.valueOf(alpaca.getAge()));
                        weightTextField.setText(String.valueOf(alpaca.getWeight()));
                        likesPetsTextField.setText(String.valueOf(alpaca.getLikesPets()));
                    } else if ((i == alpacas.size()-1) && (responseLabel.getText().isEmpty())) {
                        // Sets Name
                        nameTextField.setText(searchName);

                        // Sets Response Label if Alpaca Not Found in File
                        responseLabel.setText("Alpaca Not Found :(");
                    }
                }
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
                // Iterates through Array List
                for (int i = 0; i < alpacas.size(); i++) {
                    // Checks for Same Name
                    if (alpacas.get(i).getName().equalsIgnoreCase(nameTextField.getText())) {
                        // Gets New Variables
                        String name = nameTextField.getText();
                        int age = Integer.valueOf(ageTextField.getText());
                        double weight = Double.valueOf(weightTextField.getText());
                        boolean likesPets = Boolean.valueOf(likesPetsTextField.getText());

                        // Makes New Alpaca
                        Alpaca alpaca = new Alpaca(name, age, weight, likesPets);

                        // Adds Alpaca to Array List
                        alpacas.set(i, alpaca);

                        // Set Text Fields Blank
                        setTextFieldsBlank();

                        // Sets Response Label if Alpaca is Found
                        responseLabel.setText("Changes Saved");

                        // Prints Changes
                        printAlpacas();
                        
                    } else if (i == alpacas.size()-1 && !alpacas.get(i).getName().equalsIgnoreCase(nameTextField.getText())){
                            // Sets Response Label if Alpaca Not Found
                            responseLabel.setText("Alpaca Not Found :(");
                    } 
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
                for (int i = 0; i < alpacas.size(); i++) {
                    if (alpacas.get(i).getName().equalsIgnoreCase(nameTextField.getText())) {
                        // Sets Label if Alpaca Exists
                        responseLabel.setText(alpacas.get(i).getName() + " the Alpaca already exists!");
                    } else if ((i==alpacas.size()-1) && (responseLabel.getText().isEmpty())) {
                        // Gets New Variables
                        String name = nameTextField.getText();
                        int age = Integer.valueOf(ageTextField.getText());
                        double weight = Double.valueOf(weightTextField.getText());
                        boolean likesPets = Boolean.valueOf(likesPetsTextField.getText());

                        // Makes New Alpaca
                        Alpaca alpaca = new Alpaca(name, age, weight, likesPets);

                        // Increases Array List Size if Neccessary
                        if (i == alpacas.size() - 1) {
                            alpacas.ensureCapacity(alpacas.size() + 1);
                        }

                        // Adds Alpaca to Array List
                        alpacas.add(i, alpaca);

                        // Set Text Fields Blank
                        setTextFieldsBlank();

                        // Sets Response Label if Alpaca is Found
                        responseLabel.setText("Alpaca Added");
                        
                        // Prints Changes
                        printAlpacas();
                    }
                }
            }
        }
    }

    // Listens for Remove Button
    private class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Sets Response Label to Blank
            responseLabel.setText("");

            // Checks for Valid Name Input
            if (nameTextField.getText().matches("^[a-zA-Z- ]*$") && !nameTextField.getText().isEmpty()) {
                // Iterates Through Alpacas ArrayList
                for (int i = 0; i < alpacas.size(); i++) {
                    // Checks for Same Name
                    if (alpacas.get(i).getName().equalsIgnoreCase(nameTextField.getText())) {
                        // Removes Alpaca
                        alpacas.remove(i);

                        // Clears Text Fields
                        setTextFieldsBlank();

                        // Sets Response Label if Alpaca is Found
                        responseLabel.setText("Alpaca Removed :(");

                        // Print Results
                        printAlpacas();

                    } else if ((i == alpacas.size()-1) && (!alpacas.get(i).getName().equalsIgnoreCase(nameTextField.getText()))){
                        // Sets Response Label if Alpaca Not Found in File
                        responseLabel.setText("Alpaca Not Found");
                    }
                }
            } else {
                // Sets Label for Invalid Character
                responseLabel.setText("Please input a valid character");
            }
        }
    }

    // Listens for Print Button
    private class PrintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Prints Alpacas
            printAlpacas();
        }
    }

    /*
     * Helper Methods
     */

    // Method to Print File
    public static void printAlpacas() {
        System.out.println("<--- Alpaca Data --->");
        for (int i = 0; i < alpacas.size(); i++) {
            // Gets Alpaca Name
            String alpacaName = alpacas.get(i).getName();

            // Get Alpaca
            Alpaca alpaca = alpacas.get(i);
            
            // Prints Alpaca Data
            System.out.println(alpaca.toString());
        }
    }

    // Sets All TextFields Blank
    public void setTextFieldsBlank() {
        nameTextField.setText("");
        ageTextField.setText("");
        weightTextField.setText("");
        likesPetsTextField.setText("");
    }

    public static void main(String[] args) {
        new AlpacaGUI();
    }
}
