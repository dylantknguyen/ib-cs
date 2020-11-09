/**
 * Write a simple application that outputs "Hello World" to a panel
 * @author Ms. Martin 
 * @version 1 August 28, 2019
 **/
import javax.swing.*;  // import any of the packages needed for panel elements
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.lang.*;

// 'extends JPanel' makes our class a panel

public class HelloNamePanel extends JPanel  {
   // declare what is needed for the panel
   JLabel helloMsg; 			   // use meaningful variable names
   JTextField box;
   JButton button;
   String[] name = new String[10];
   
   static  int i = 0;
   static  int j = 0;
       
   
   public HelloNamePanel()  		// this is the contructor for the class
   {
      setLayout(new FlowLayout());  // review your layout options in Java. This is a cludgy feature of swing
         
      helloMsg = new JLabel("Type your name, then press Enter. Enter -1 if finished.");
      add(helloMsg);  
      
      box = new JTextField(20);
      add(box); 
      
      button = new JButton("Enter");
      add(button);
      button.addActionListener(new Listener());
            
   }
    
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String input = box.getText(); 
         
           if (i>=10){
             do{
                System.out.println(name[j]);
                  j++;
               }while(j<i);
               System.exit(0);
            }            
            else if (input.isEmpty())
               helloMsg.setText("Please enter your name.");
                  
            else if (input.equals("-1"))
            {
               for (int k = 0; k<i; k++){
               System.out.println(name[k]);}
               System.exit(0);
               
            }     
            else
             {
               helloMsg.setText("Hello "+input);
               name[i] =input;
               i++;
             }
               
               
         }
         
      }
                     
                       
     	
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Hello World Frame");
      frame.setSize(350, 100);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new HelloNamePanel());  // set the frame to contain an instance of the panel
      frame.setVisible(true);
        
   }
  

}