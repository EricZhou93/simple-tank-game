/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* PauseButton.java: 
* The PauseButton class is designed for the pause button that stops the timer 
* in the model (shared database) to pause the animation.
* When the timer is not working, the pause button is disabled.
*******************************************************************************/

import java.awt.event.*;
import javax.swing.*;

// public class DirectionButton extends Button implements ActionListener{
public class PauseButton extends Button {
    // Model to access, a shared database and a central controller.
    private Model model;
    
    // Connected pause button.
    private JButton playButton;

    // Parameterized constructor
    public PauseButton(String label, Model model) {
        // Invoke base-class(Button) constructor 
        super(label); 

        // Connect to the shared database.
        this.model = model;
    }

    // Connect to a play button.
    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }
    
    // Method actionPerformed
    // Parameters: ActionEvent e: An action event object.
    // Returns: Nothing.
    // Does: It prints a message that the button is pressed, stops the timer to 
    //       pause the animation, disables itself and enables the connected 
    //       play button.
    public void actionPerformed(ActionEvent e) {
        // Print the message for being pushed.
        printPushed();
        
        this.model.deactivateEnemy();

        this.setEnabled(false);

        playButton.setEnabled(true);
    }
}
