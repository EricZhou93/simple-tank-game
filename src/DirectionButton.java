/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
* 2019-10-04 Created.
* 2019-10-17 Last modified.
*
* DirectionButton.java: 
* DirectionButton class is based on Button classed. It is designed for buttons 
* to control the movement of a player vehicle.
*******************************************************************************/

import java.awt.event.*;

// public class DirectionButton extends Button implements ActionListener{
public class DirectionButton extends Button {
    // Direction denoted by this direction button.
    private int direction;

    // Model to access, a shared database and a central controller.
    private Model model;

    // Parameterized constructor
    public DirectionButton(String label, int direction, Model model) {
        // Invoke base-class(Button) constructor 
        super(label); 

        // Set the direction.
        this.direction = direction;

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
        this.model.moveControlledVehicle(this.direction);
    }
}
