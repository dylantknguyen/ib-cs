
/**
 * Write a simple application that outputs "Hello World" to a panel
 * @author Ms. Davis 
 * @version 1 Sept 8, 2011
 **/
// import required packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 'extends JPanel' makes our class a panel  
public class HelloNamePanel extends JPanel {
    // declare what is needed for the panel
    // use meaningful variable names
    JLabel nameLabel;
    JTextField nameTextField;
    JButton enterButton;
    String[] namesArray = new String[10];
    static int amtNames = 0;


    // this is the contructor for the class
    public HelloNamePanel() {
        // review your layout options in Java. This is a cludgy feature of swing
        setLayout(new FlowLayout());

        // adds label, text field, and enter button
        add(nameLabel = new JLabel("Type your name, then press Enter. Enter -1 if finished."));
        add(nameTextField = new JTextField(50));
        add(enterButton = new JButton("Enter"));

        // adds listener to enter button
        enterButton.addActionListener(new NameListener());

    }

    // listener for submit button
    private class NameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // gets name text field
            String name = nameTextField.getText();

            // checks if there are less than 10 names in the array
            if (amtNames < 10) {
                // checks for only alphanumeric characters using regex
                // also checks for empty string using isEmpty() and includes the "-" and space character just in case
                if (name.matches("^[a-zA-Z- ]*$") && !name.isEmpty()) {
                    // sets text, adds name to list, increments variable
                    nameLabel.setText("Hello " + name);
                    namesArray[amtNames] = name;
                    amtNames++;
                } 
                // checks if the input is equal to -1 and prints all names if it does
                else if (name.equals("-1")) {
                    printNames();
                // exits once all names are printed
                System.exit(0);
                }
                // else for invalid name
                else {
                    nameLabel.setText("Please enter a valid name");
                }
            }
            // checks if there are more than 10 names in the array and prints them if there is more than 10 names
            else if (amtNames >= 10) {
                System.out.println("There are already more than 10 names");
                printNames();
            }
            // reprompts for another name just in case
            else {
                nameLabel.setText("Please enter a valid name");
            }
        }
    }

    // method to print all names in the array
    void printNames() {
        for (int i = 0; i < amtNames; i++){
            System.out.println(namesArray[i]);}
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World Frame");
        frame.setSize(1000, 200);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HelloNamePanel()); // set the frame to contain an instance of the panel
        frame.setVisible(true);
    }

}
