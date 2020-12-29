/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-31 Created.
* 2019-10-31 Last modified.
*
* ZoomPanel.java:
*******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

class ZoomPanel extends GridBagPanel implements ActionListener {
    // Shared database of this game.
    private Model model;

    // Buttons.
    private Button zoomInButton; // Let the map zoom in.
    private Button zoomOutButton; // Let the map zoom out.
    private JLabel zoomScaleLabel; // Label of current zoom scale.

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects the control panel to the shared database. 
    //       It adds 4 direction buttons and a fire button to control a tank.
    public ZoomPanel(Model model) {
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
        add(new JLabel("Zoom Control"), currGridBagConstraints);

        // Add the zoom out button to the zoom panel.
        this.zoomOutButton = new Button("-");
        this.zoomOutButton.addActionListener(this);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 1;
        this.add(zoomOutButton, currGridBagConstraints);
        
        // Add a label for vehicle control panel.
        this.zoomScaleLabel = new JLabel("100.0%");
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 1;
        add(zoomScaleLabel, currGridBagConstraints);

        // Add the zoom out button to the zoom panel.
        this.zoomInButton = new Button("+");
        this.zoomInButton.addActionListener(this);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 2;
        currGridBagConstraints.gridy = 1;
        this.add(zoomInButton, currGridBagConstraints);

                // Set the panel visible.
        this.setVisible(true);
    }

    // Callback function for zooming buttons.
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == this.zoomInButton) {
            model.zoomIn();
        }
        else if (e.getSource() == this.zoomOutButton) {
            model.zoomOut();
        }
        double newZoomScale = model.getZoomScale() * 100;
        this.zoomScaleLabel.setText(
                Double.toString(Math.floor(newZoomScale)) + "%");
    }
}