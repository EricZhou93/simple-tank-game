/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-17 Last modified.
*
* Button.java: 
* The Button class is designed as a base class for all buttons in this game.
* When a Button class object is pressed, the system prints a message about it.
*******************************************************************************/

import javax.swing.*;
import java.awt.event.*;

public class Button extends JButton implements ActionListener {
    // Parameterized constructor.
    // Parameters: String label: the text showed on the button.
    // Returns: nothing
    // Does: It construct a Button class object with a given text showed on it 
    //       and an action listener that listens to itself.
    public Button(String label) {
        setText(label);
        addActionListener(this);
    }

    // Method actionPerformed
    // Parameters: ActionEvent e: An action event object.
    // Returns: nothing
    // Does: It prints a message when the button is pushed.
    public void actionPerformed(ActionEvent e) {
        printPushed();
    }

    // Method printPushed
    // Parameters: None.
    // Returns: Nothing.
    // Does: It prints a message that this button is pushed.
    protected void printPushed() {
        System.out.println(getText() + " button was pushed");
    }
}
