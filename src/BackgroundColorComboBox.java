/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-17 Created.
* 2019-10-17 Last modified.
*
* BackgroundColorComboBox.java: 
* BackgroundColorComboBox is designed for a combo box to select canvas 
* background color.
*******************************************************************************/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class BackgroundColorComboBox extends JComboBox<String> implements ItemListener {
    // Shared database of this game.
    private Model model;

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects to the model (shared database), sets color options and 
    //       the default selection of the combo box.
    public BackgroundColorComboBox(Model model) {
        // Connect to the shared database of this game.
        this.model = model;

        // Add color options.
        addItem("black");
        addItem("light gray");
        addItem("white");

        // Set default selection.
        setSelectedItem("black");

        addItemListener(this);
    }

    // Method itemStateChanged.
    // Parameters: ItemEvent e
    // Does: It sets the canvas background color according to the current combo 
    //       box selection.
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedColor = (String) e.getItem();

            // Print the newly selected color.
            System.out.println("BackgroundColorComboBox: " + selectedColor);

            // Set the canvas background to the newly selected color.
            if (selectedColor == "black") {
                model.getCanvas().setBackground(Color.black);
            } else if (selectedColor == "light gray") {
                model.getCanvas().setBackground(Color.lightGray);
            } else if (selectedColor == "white") {
                model.getCanvas().setBackground(Color.white);
            } 
        }
    }
}