/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-17 Last modified.
*
* BackgroundColorPanel.java: 
* The background color panel has a combo box to select canvas background color.
*******************************************************************************/

import javax.swing.*;
import java.awt.*;

class BackgroundColorPanel extends GridBagPanel {
    // Shared database of this game.
    private Model model;

    // Combo box to set the canvas background color.
    BackgroundColorComboBox backgroundColorComboBox;

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects the game control panel to the shared database. 
    //       It connects the game control panel to the vehicle control panel. 
    //       It adds buttons to add more tanks into the battlefield and adds 
    //       buttons to switch which tank to control.
    //       It adds a combo box to select the canvas background color.
    public BackgroundColorPanel(Model model) {
        // Connect to the shared database of this game.
        this.model = model;

        // Setup the panel.
        // Use GridBagLayout.
        setLayout(new GridBagLayout());
        GridBagConstraints currGridBagConstraints = new GridBagConstraints();
        setDefaultInsets(5, 5, 5, 5);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        
        // Add a title label for this panel.
        JLabel comboBoxLabel = new JLabel("Background Color");
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        add(comboBoxLabel, currGridBagConstraints);
        
        // Add a background color combo box.
        backgroundColorComboBox = new BackgroundColorComboBox(this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 0;
        add(backgroundColorComboBox, currGridBagConstraints);

        // Set the panel visible.
        setVisible(true);
    }
}