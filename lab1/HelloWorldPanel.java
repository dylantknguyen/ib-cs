
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
public class HelloWorldPanel extends JPanel {
    // declare what is needed for the panel

    // use meaningful variable names
    JLabel nameLabel;
    JTextField nameTextField;
    JButton enterButton;

    // this is the contructor for the class
    public HelloWorldPanel() {
        // review your layout options in Java. This is a cludgy feature of swing
        setLayout(new FlowLayout());

        // adds label, text field, and enter button
        add(nameLabel = new JLabel("Type your name, then press enter"));
        add(nameTextField = new JTextField(100));
        add(enterButton = new JButton("Enter"));

        // adds listener to enter button
        enterButton.addActionListener(new NameListener());

    }

    // listener for submit button
    private class NameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // gets name text field
            String name = nameTextField.getText();
            // checks for only alphanumeric characters using regex
            // checks for empty string using isEmpty()
            // also includes the "-" and space character just in case
            if (name.matches("^[a-zA-Z- ]*$") && !name.isEmpty()) {
                    nameLabel.setText("Hello " + name);
            } 
            else {
                nameLabel.setText("Please enter a valid name");
            }
        }
    
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World Frame");
        frame.setSize(250, 100);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HelloWorldPanel()); // set the frame to contain an instance of the panel
        frame.setVisible(true);
    }

}