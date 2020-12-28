/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-17 Last modified.
*
* VehicleAddingPanel.java:
* VehicleAddingPanel class is designed for adding a vehicle of a given category 
* to a given position in the battlefield.
* A vehicle adding panel has text fields to input starting x and y coordinates 
* of the new tank and buttons to add different kinds of vehicles to the 
* battlefield.
*******************************************************************************/

import javax.swing.*;
import java.awt.*;

class VehicleAddingPanel extends GridBagPanel {
    // Shared database of this game.
    private Model model;

    // Widgets.
    private JButton addEnemyLightTankButton; // Button to add an enemy light 
        // tank to the canvas.
    private JButton addEnemyHeavyTankButton; // Button to Add an enemy heavy 
        // tank to the canvas.
    private JButton addPlayerLightTankButton; // Button to Add a player light 
        // tank to the canvas.
    private JButton addPlayerHeavyTankButton; // Button to Add a player heavy 
        // tank to the canvas.
    private JTextField posXTextField; // Text field to input the starting x 
        // coordinate of the new tank.
    private JTextField posYTextField; // Text field to input the starting y 
        // coordinate of the new tank.

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects the game control panel to the shared database. 
    //       It connects the game control panel to the vehicle control panel. 
    //       It adds buttons to add more tanks into the battlefield and adds 
    //       buttons to switch which tank to control.
    //       It adds a combo box to select the canvas background color.
    public VehicleAddingPanel(Model model) {
        // Connect to the shared database of this game.
        this.model = model;

        // Setup the panel.
        // Use GridBagLayout.
        setLayout(new GridBagLayout());
        GridBagConstraints currGridBagConstraints = new GridBagConstraints();
        setDefaultInsets(5, 5, 5, 5);
        setGridBagConstraintsToDefault(currGridBagConstraints);

        // Add a title label for this panel.
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        currGridBagConstraints.gridwidth = 4;
        add(new JLabel("Vehicle Adding"), currGridBagConstraints);

        // Add a text field to input starting x coordinate of the new tank.
        // Add a label for the text field.
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 1;
        add(new JLabel("x = "), currGridBagConstraints);
        posXTextField = new TextField("Pos X Input Text Field", "300");
        // Add the text field.
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 1;
        add(posXTextField, currGridBagConstraints);

        // Add a text field to input starting y coordinate of the new tank.
        // Add a label for the text field.
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 2;
        currGridBagConstraints.gridy = 1;
        add(new JLabel("y = "), currGridBagConstraints);
        // Add the text field.
        posYTextField = new TextField("Pos Y Input Text Field", "50");
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 3;
        currGridBagConstraints.gridy = 1;
        add(posYTextField, currGridBagConstraints);

        // Add a button to add an enemy light tank into the battlefield.
        addEnemyLightTankButton = new AddEnemyLightTankButton(
            "Enemy Light Tank", model, posXTextField, posYTextField);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 2;
        currGridBagConstraints.gridwidth = 4;
        add(addEnemyLightTankButton, currGridBagConstraints);

        // Add a button to add an enemy heavy tank into the battlefield.
        addEnemyHeavyTankButton = new AddEnemyHeavyTankButton(
            "Enemy Heavy Tank", model, posXTextField, posYTextField);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 3;
        currGridBagConstraints.gridwidth = 4;
        add(addEnemyHeavyTankButton, currGridBagConstraints);

        // Add a button to add an enemy light tank into the battlefield.
        addPlayerLightTankButton = new AddPlayerLightTankButton("Player Light Tank", model, posXTextField, posYTextField);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 4;
        currGridBagConstraints.gridwidth = 4;
        add(addPlayerLightTankButton, currGridBagConstraints);

        // Add a button to add an enemy heavy tank into the battlefield.
        addPlayerHeavyTankButton = new AddPlayerHeavyTankButton("Player Heavy Tank", model, posXTextField, posYTextField);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 5;
        currGridBagConstraints.gridwidth = 4;
        add(addPlayerHeavyTankButton, currGridBagConstraints);

        // Set the panel visible.
        setVisible(true);
    }
}