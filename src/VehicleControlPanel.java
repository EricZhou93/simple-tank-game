/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-25 Last modified.
*
* VehicleControlPanel.java:
* VehicleControlPanel classes is designed for controlling a single tank.
* A vehicle control panel has 4 direction buttons to move the controlled tank 
* up, down, left and right and a fire button to let the controlled tank fire.
*******************************************************************************/

import javax.swing.*;
import java.awt.*;

class VehicleControlPanel extends GridBagPanel {
    // Shared database of this game.
    private Model model;

    // Buttons.
    private DirectionButton upButton; // Let player tank move up.
    private DirectionButton downButton; // Let player tank move down.
    private DirectionButton leftButton; // Let player tank move left.
    private DirectionButton rightButton; // Let player tank move right.
    private JButton fireButton; // Let player tank fire.

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects the control panel to the shared database. 
    //       It adds 4 direction buttons and a fire button to control a tank.
    public VehicleControlPanel(Model model) {
        // Connect to the shared database of this game.
        this.model = model;

        // Setup the panel.
        // Use GridBagLayout.
        setLayout(new GridBagLayout());
        GridBagConstraints currGridBagConstraints = new GridBagConstraints();
        setDefaultInsets(5, 5, 5, 5);
        setGridBagConstraintsToDefault(currGridBagConstraints);

        // Add a label for vehicle control panel.
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        currGridBagConstraints.gridwidth = 3;
        add(new JLabel("Vehicle Control"), currGridBagConstraints);

        // Add tank control buttons.
        // Add the up button to the control panel.
        upButton = new DirectionButton("^", Vehicle.NORTH, model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 1;
        this.add(upButton, currGridBagConstraints);

        // Add the left button to the control panel.
        leftButton = new DirectionButton("<", Vehicle.WEST, model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 2;
        this.add(leftButton, currGridBagConstraints);
        
        // Add the fire button to the control panel.
        fireButton = new FireButton("FIRE", this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 2;
        this.add(fireButton, currGridBagConstraints);
        
        // Add the right button to the control panel.
        rightButton = new DirectionButton(">", Vehicle.EAST, model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 2;
        currGridBagConstraints.gridy = 2;
        this.add(rightButton, currGridBagConstraints);

        // Add the down button to the control panel.
        downButton = new DirectionButton("v", Vehicle.SOUTH, model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 3;
        this.add(downButton, currGridBagConstraints);

        // Set the panel visible.
        this.setVisible(true);
    }
}