/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-25 Created.
* 2019-10-25 Last modified.
*
* FireButton.java: 
*******************************************************************************/

import java.awt.event.*;

// public class DirectionButton extends Button implements ActionListener{
public class FireButton extends Button {
    // Model to access, a shared database and a central controller.
    private Model model;

    // Parameterized constructor
    public FireButton(String label, Model model) {
        // Invoke base-class(Button) constructor 
        super(label); 

        // Set the model.
        this.model = model;
    }

    // Method actionPerformed
    // Parameters: ActionEvent e: An action event object.
    // Returns: Nothing.
    // Does: It prints a message that the button is pressed and moves the
    // player tank.
    public void actionPerformed(ActionEvent e) {
        // Print the message for being pushed.
        printPushed();
        
        // Move or turn the to the given direction.
        this.model.fireControlledVehicle();
    }
}
