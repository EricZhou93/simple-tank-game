/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* PlayButton.java: 
* The PlayButton class is designed for the play button that starts the timer in 
* the model (shared database) to start / resume the animation.
* When the timer is working, the play button is disabled.
*******************************************************************************/

import java.awt.event.*;
import javax.swing.*;


// public class DirectionButton extends Button implements ActionListener{
public class PlayButton extends Button {
    // Model to access, a shared database and a central controller.
    private Model model;

    // Connected pause button.
    private JButton pauseButton;

    // Parameterized constructor
    public PlayButton(String label, Model model) {
        // Invoke base-class(Button) constructor 
        super(label); 

        // Connect to the shared database.
        this.model = model;
    }

    // Connect to a pause button.
    public void setPauseButton(JButton pauseButton){
        this.pauseButton = pauseButton;
    }

    // Method actionPerformed
    // Parameters: ActionEvent e: An action event object.
    // Returns: Nothing.
    // Does: It prints a message that the button is pressed, starts the timer 
    //       to play the animation, disables itself and enables the connected 
    //       pause button.
    public void actionPerformed(ActionEvent e) {
        // Print the message for being pushed.
        printPushed();
        
        this.model.activateEnemy();

        this.setEnabled(false);

        pauseButton.setEnabled(true);
    }
}
