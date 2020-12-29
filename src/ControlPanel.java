/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-18 Last modified.
*
* ControlPanel.java: 
* The control panel works as a container for all the other control panels for 
* specific features.
*   Separating the control panel into multiple control panels for a simple 
* function improves the modularity of codes and facilitates future developments.
*******************************************************************************/

import javax.swing.border.*;
import java.awt.*;

class ControlPanel extends GridBagPanel{
    // Shared database of this game.
    private Model model;

    // Control panels for specific functions.
    private BackgroundColorPanel backgroundColorPanel;
    private AnimationControlPanel animationControlPanel;
    private VehicleAddingPanel vehicleAddingPanel;
    private VehicleControlPanel vehicleControlPanel;
    private ZoomPanel zoomPanel;

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects the control panel to the shared database. 
    //       It adds an overall control panel and a vehicle control panel that 
    //       controls one given vehicle.
    public ControlPanel(Model model) {
        // Connect to the shared database of this game.
        this.model = model;

        // Setup the panel.
        // Use GridBagLayout.
        setLayout(new GridBagLayout());
        GridBagConstraints currGridBagConstraints = new GridBagConstraints();
        setGridBagConstraintsToDefault(currGridBagConstraints);

        // Add the background color panel that select canvas background color.
        this.backgroundColorPanel = new BackgroundColorPanel(this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        backgroundColorPanel.setBorder(new LineBorder(Color.black, 1));
        add(backgroundColorPanel, currGridBagConstraints);

        // Add the animation control panel that control the animation of enemy vehicles.
        this.animationControlPanel = new AnimationControlPanel(this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 1;
        animationControlPanel.setBorder(new LineBorder(Color.black, 1));
        add(animationControlPanel, currGridBagConstraints);

        // Add the zoom control panel that control the animation of enemy vehicles.
        this.zoomPanel = new ZoomPanel(this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 2;
        zoomPanel.setBorder(new LineBorder(Color.black, 1));
        add(zoomPanel, currGridBagConstraints);

        // Add the vehicle adding panel.
        this.vehicleAddingPanel = new VehicleAddingPanel(this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 3;
        vehicleAddingPanel.setBorder(new LineBorder(Color.black, 1));
        add(vehicleAddingPanel, currGridBagConstraints);

        // Add the vehicle control panel that controls one given vehicle.
        this.vehicleControlPanel = new VehicleControlPanel(this.model);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 5;
        vehicleControlPanel.setBorder(new LineBorder(Color.black, 1));
        add(vehicleControlPanel, currGridBagConstraints);

        // Set the panel visible.
        setVisible(true);
    }

    // Hide the background color panel to save some space for the canvas.
    public void hideBackgroundColorPanel(){
        backgroundColorPanel.setVisible(false);
    }

    // Show the background color panel when space is abundant.
    public void showBackgroundColorPanel(){
        backgroundColorPanel.setVisible(true);
    }
}