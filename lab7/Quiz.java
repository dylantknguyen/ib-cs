/** 
 * A quiz on Unit 6: Computational Thinking
 * @author Dylan Nguyen
 */

import javax.swing.*; // Java GUI with Swing
import java.awt.*; // Import Container, GridBadLayout, GridBagConstraints

public class Quiz extends JFrame {
    // creates labels
    JLabel firstNameLabel, lastNameLabel, trueFalseLabel, multipleChoiceLabel, selectAllThatApplyLabel, fillInTheBlankFieldLabel, longAnswerLabel;

    // creates textfields, buttons, and boxes
    JTextField firstNameTextField, lastNameTextField;
    JRadioButton trueButton, falseButton, optionAButton, optionBButton, optionCButton,optionDButton;
    ButtonGroup trueFalseGroup, multipleChoiceGroup;
    Checkbox option1Checkbox, option2Checkbox, option3Checkbox, option4Checkbox, option5Checkbox;
    JComboBox<String> fillInTheBlankComboBox;
    JTextArea longAnswerTextArea;
    JButton submitButton;

  public Quiz() {
    // set title
    this.setTitle("Unit 6 Quiz");

    // sets properties
    this.setSize(450, 600);  // frame size
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // creates panel
    JPanel panel = new JPanel();

    // creates and sets layout
    panel.setLayout(new GridBagLayout());

    // creates constrains for layout
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    // adds variables to panel
    // first name
    c.gridx = 0;
    c.gridy = 0;
    panel.add(firstNameLabel = new JLabel("First Name:"), c);
    c.gridy = 1;
    panel.add(firstNameTextField = new JTextField(20), c);

    // last name
    c.gridy = 2;
    panel.add(lastNameLabel = new JLabel("Last Name:"), c);
    c.gridy = 3;
    panel.add(lastNameTextField = new JTextField(20), c);

    // true false
    trueFalseGroup = new ButtonGroup();
    c.gridy = 4;
    panel.add(trueFalseLabel = new JLabel("True False"), c);
    c.gridy = 5;
    panel.add(trueButton = new JRadioButton("True"), c);
    trueFalseGroup.add(trueButton);
    c.gridy = 6;
    panel.add(falseButton = new JRadioButton("False"), c);
    trueFalseGroup.add(falseButton);

    // mutiple choice
    multipleChoiceGroup = new ButtonGroup();
    c.gridy = 7;
    panel.add(multipleChoiceLabel = new JLabel("Multiple Choice"), c);
    c.gridy = 8;
    panel.add(optionAButton = new JRadioButton("Option A"), c);
    multipleChoiceGroup.add(optionAButton);
    c.gridy = 9;
    panel.add(optionBButton = new JRadioButton("Option B"), c);
    multipleChoiceGroup.add(optionBButton);
    c.gridy = 10;
    panel.add(optionCButton = new JRadioButton("Option C"), c);
    multipleChoiceGroup.add(optionCButton);
    c.gridy = 11;
    panel.add(optionDButton = new JRadioButton("Option D"), c);
    multipleChoiceGroup.add(optionDButton);

    // select all that apply
    c.gridy = 12;
    panel.add(selectAllThatApplyLabel = new JLabel("Select All That Apply"), c);
    c.gridy = 13;
    panel.add(option1Checkbox = new Checkbox("Option 1"), c);
    c.gridy = 14;
    panel.add(option2Checkbox = new Checkbox("Option 2"), c);
    c.gridy = 15;
    panel.add(option3Checkbox = new Checkbox("Option 3"), c);
    c.gridy = 16;
    panel.add(option4Checkbox = new Checkbox("Option 4"), c);
    c.gridy = 17;
    panel.add(option5Checkbox = new Checkbox("Option 5"), c);

    // fill in the blank
    // creates dropdown
    String[] fillInTheBlankOptions = {"Option A", "Option B", "Option C", "Option D"};
    fillInTheBlankComboBox = new JComboBox<>(fillInTheBlankOptions);

    c.gridy = 18;
    panel.add(fillInTheBlankFieldLabel = new JLabel("Fill In The Blank"), c);
    c.gridy = 19;
    panel.add(fillInTheBlankComboBox, c);

    // long paragraph answer
    c.gridy = 20;
    panel.add(longAnswerLabel = new JLabel("Answer the Question"), c);

    // paragraph answer
    longAnswerTextArea = new JTextArea(10, 10);
    longAnswerTextArea.setLineWrap(true);
    longAnswerTextArea.setRows(5);
    c.gridy = 21;
    panel.add(longAnswerTextArea, c);

    // submit button
    c.gridy = 22;
    c.fill = GridBagConstraints.NONE;
    panel.add(submitButton = new JButton("Submit"), c);

    // adds panel to frame
    this.add(panel);

    // sets frame as visible
    this.setVisible(true);
  }

  // start quiz
  public static void main(String[] args) {
    new Quiz();
  }
}
