
/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-12 Created.
* 2019-10-18 Last modified.
*
* AddEnemyLightTankButton.java: 
* The AddEnemyLightTankButton class is based on AddVehicleButton and is 
* designed for buttons to add an enemy light tank to a given position in the 
* battlefield. 
*******************************************************************************/

import java.awt.event.*;
import javax.swing.*;


public class AddEnemyLightTankButton extends AddVehicleButton {
    // Parameterized constructor
    public AddEnemyLightTankButton(
        String label, Model model, JTextField posXInput, JTextField posYInput) {
        // Invoke base-class(Button) constructor 
        super(label, model, posXInput, posYInput); 
    }

    // Method actionPerformed
    // Parameters: ActionEvent e: An action event object.
    // Returns: Nothing.
    // Does: It prints a message that the button is pressed and adds an enemy 
    //       light tank into the battle field.
    public void actionPerformed(ActionEvent e) {
        // Print the message for being pushed.
        printPushed();
        
        // Add an enemy light tank to the position given by starting position 
        // input text fields.
        this.model.addEnemyLightTank(
            Double.valueOf(posXInput.getText()), 
            Double.valueOf(posYInput.getText()),
            Vehicle.SOUTH);
        this.model.getCanvas().repaint();
    }
}
