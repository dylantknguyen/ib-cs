/** 
 * A quiz on Unit 6: Computational Thinking
 * @author Dylan Nguyen
 */

import javax.swing.*; // Java GUI with Swing
import java.awt.*; // Import Container, GridBadLayout, GridBagConstraints
import java.awt.event.*; // Action Listener

public class Quiz extends JFrame {
  // creates labels
  JLabel firstNameLabel, lastNameLabel, trueFalseLabel, multipleChoiceLabel, selectAllThatApplyLabel, fillInTheBlankFieldLabel, longAnswerLabel, errorLabel;

  // creates textfields, buttons, and boxes
  JTextField firstNameTextField, lastNameTextField;
  JRadioButton trueButton, falseButton, optionAButton, optionBButton, optionCButton,optionDButton;
  ButtonGroup trueFalseGroup, multipleChoiceGroup;
  Checkbox option1Checkbox, option2Checkbox, option3Checkbox, option4Checkbox, option5Checkbox;
  JComboBox<String> fillInTheBlankComboBox;
  JTextArea longAnswerTextArea;
  JButton submitButton;

  // textfields for graded section
  int grade;
  JLabel nameLabel, gradeLabel;


  public Quiz() {
    // set title
    this.setTitle("Unit 6 Quiz");

    // sets properties
    this.setSize(500, 600);  // frame size
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // creates panel
    JPanel panel = new JPanel();

    // creates and sets layout
    panel.setLayout(new GridBagLayout());

    // creates constrains for layout
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    // adds variables to panel
    // error label
    c.gridy = 0;
    panel.add(errorLabel = new JLabel(""), c);

    // first name
    c.gridx = 0;
    c.gridy = 1;
    panel.add(firstNameLabel = new JLabel("First Name:"), c);
    c.gridy = 2;
    panel.add(firstNameTextField = new JTextField(20), c);

    // last name
    c.gridy = 3;
    panel.add(lastNameLabel = new JLabel("Last Name:"), c);
    c.gridy = 4;
    panel.add(lastNameTextField = new JTextField(20), c);

    // true false
    trueFalseGroup = new ButtonGroup();
    c.gridy = 5;
    panel.add(trueFalseLabel = new JLabel("Parallel arrays should be used to store data when there is only one property."), c);
    c.gridy = 6;
    panel.add(trueButton = new JRadioButton("True"), c);
    trueFalseGroup.add(trueButton);
    c.gridy = 7;
    panel.add(falseButton = new JRadioButton("False"), c);
    trueFalseGroup.add(falseButton);

    // mutiple choice
    multipleChoiceGroup = new ButtonGroup();
    c.gridy = 8;
    panel.add(multipleChoiceLabel = new JLabel("For which type of arrays is binary search most efficient?"), c);
    c.gridy = 9;
    panel.add(optionAButton = new JRadioButton("Small arrays"), c);
    multipleChoiceGroup.add(optionAButton);
    c.gridy = 10;
    panel.add(optionBButton = new JRadioButton("Large arrays"), c);
    multipleChoiceGroup.add(optionBButton);
    c.gridy = 11;
    panel.add(optionCButton = new JRadioButton("Empty arrays"), c);
    multipleChoiceGroup.add(optionCButton);
    c.gridy = 12;
    panel.add(optionDButton = new JRadioButton("Not sorted arrays"), c);
    multipleChoiceGroup.add(optionDButton);

    // select all that apply
    c.gridy = 13;
    panel.add(selectAllThatApplyLabel = new JLabel("Which of the following are steps in problem solving?"), c);
    c.gridy = 14;
    panel.add(option1Checkbox = new Checkbox("Identification"), c);
    c.gridy = 15;
    panel.add(option2Checkbox = new Checkbox("Debugging"), c);
    c.gridy = 16;
    panel.add(option3Checkbox = new Checkbox("Discussion"), c);
    c.gridy = 17;
    panel.add(option4Checkbox = new Checkbox("Implementation"), c);
    c.gridy = 18;
    panel.add(option5Checkbox = new Checkbox("Selection"), c);

    // fill in the blank
    // creates dropdown
    String[] fillInTheBlankOptions = {"the end", "an if statement", "a certain condition", "the answer"};
    fillInTheBlankComboBox = new JComboBox<>(fillInTheBlankOptions);

    c.gridy = 19;
    panel.add(fillInTheBlankFieldLabel = new JLabel("Iteration is repeating until ________ is met"), c);
    c.gridy = 20;
    panel.add(fillInTheBlankComboBox, c);

    // long paragraph answer
    c.gridy = 21;
    panel.add(longAnswerLabel = new JLabel("What is pre-fetching?"), c);

    // paragraph answer
    longAnswerTextArea = new JTextArea(10, 10);
    longAnswerTextArea.setLineWrap(true);
    longAnswerTextArea.setRows(5);
    c.gridy = 22;
    panel.add(longAnswerTextArea, c);

    // submit button
    c.gridy = 23;
    c.fill = GridBagConstraints.NONE;
    panel.add(submitButton = new JButton("Submit"), c);
    submitButton.addActionListener(new SubmitListener());

    // adds panel to frame
    this.add(panel);

    // sets frame as visible
    this.setVisible(true);
  }

  private class SubmitListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      // checks if the user has entered a name
      if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()) {
        errorLabel.setText("Please enter your name");
      }
      else {
        // create new window
        JFrame frame = new JFrame();

        // set title
        frame.setTitle("Quiz Results");

        // sets properties
        frame.setSize(300, 100);  // frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creates panel
        JPanel panel = new JPanel();

        // creates and sets layout
        panel.setLayout(new GridBagLayout());

        // creates constrains for layout
        GridBagConstraints c = new GridBagConstraints();

        // sets grade to 0
        grade = 0;

        // display name
        c.gridx = 0;
        c.gridy = 0;
        panel.add(nameLabel = new JLabel(), c);
        nameLabel.setText(firstNameTextField.getText() + " " + lastNameTextField.getText());

        // calculates grade
        // checks certain buttons, checkboxes, or items are selected
        // adds 1 to grade if they are
        if (falseButton.isSelected()) {
          grade += 1;
        }

        if (optionBButton.isSelected()) {
          grade += 1;
        }

        if ((option1Checkbox.getState() == true) && (option4Checkbox.getState() == true) && (option5Checkbox.getState() == true)) {
          grade += 1;
        }

        if (fillInTheBlankComboBox.getSelectedItem() == "a certain condition") {
          grade += 1;
        }

        // grade
        c.gridy = 2;
        panel.add(gradeLabel = new JLabel(), c);
        gradeLabel.setText(String.valueOf(grade));

        // adds panel to frame
        frame.add(panel);

        // sets frame as visible
        frame.setVisible(true);
      }
    }
  }

  // start quiz
  public static void main(String[] args) {
    new Quiz();
  }
}
