
/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-12 Created.
* 2019-10-17 Last modified.
*
* AddVehicleButton.java: 
* AddVehicleButton class is a base class for buttons to add a vehicle of a 
* given category to a given position in the battlefield. 
* An AddVehicleButton object connects to 2 text fields to get the starting 
* position of the new vehicle.
*******************************************************************************/

import java.awt.event.*;
import javax.swing.*;


public abstract class AddVehicleButton extends Button {
    // Model to access, a shared database and a central controller.
    protected Model model;

    // Input text fields for starting position of the new vehicle.
    protected JTextField posXInput;
    protected JTextField posYInput;

    // Parameterized constructor
    public AddVehicleButton(
        String label, Model model, JTextField posXInput, JTextField posYInput) {
        // Invoke base-class(Button) constructor 
        super(label); 

        // Set the model.
        this.model = model;

        // Set the input text fields.
        this.posXInput = posXInput;
        this.posYInput = posYInput;
    }

    // Method actionPerformed
    // Parameters: ActionEvent e: An action event object.
    // Returns: Nothing.
    // Does: It prints a message that the button is pressed and add a player 
    //       tank into the battlefield.
    public void actionPerformed(ActionEvent e) {
        // Print the message for being pushed.
        printPushed();
        
        // Add a player light tank to the given position.
        this.model.addPlayerLightTank(
            Double.valueOf(posXInput.getText()), 
            Double.valueOf(posYInput.getText()),
            Vehicle.SOUTH);
        this.model.getCanvas().repaint();
    }
}
