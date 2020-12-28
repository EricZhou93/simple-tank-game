/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-18 Last modified.
*
* AnimationControlPanel.java: 
* AnimationControlPanel class is designed to control the animation in this game.
* An animation control panel has a play button and a pause button to start and 
* stop the timer in model (shared database) to control animation of enemy 
* vehicles.
*******************************************************************************/

import javax.swing.*;
import java.awt.*;

class AnimationControlPanel extends GridBagPanel {
    // Shared database of this game.
    private Model model;

    // Buttons.
    private PlayButton playButton; // Start or resume animation of enemy 
        // vehicles, disable itself and enable the pause button.
    private PauseButton pauseButton; // Pause the animation of enemy vehicles, 
        // disable itself and enable the play button.

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects the game control panel to the shared database. 
    //       It connects the game control panel to the vehicle control panel. 
    //       It adds buttons to add more tanks into the battlefield and adds 
    //       buttons to switch which tank to control.
    //       It adds a combo box to select the canvas background color.
    public AnimationControlPanel(Model model) {
        // Connect to the shared database of this game.
        this.model = model;

        // Setup the panel.
        // Use GridBagLayout.
        setLayout(new GridBagLayout());
        GridBagConstraints currGridBagConstraints = new GridBagConstraints();
        setDefaultInsets(5, 5, 5, 5);
        setGridBagConstraintsToDefault(currGridBagConstraints);

        // Add a label for animation control panel.
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        currGridBagConstraints.gridwidth = 2;
        add(new JLabel("Animation Control"), currGridBagConstraints);

        // Add play (resume) animation button.
        playButton = new PlayButton("Play", this.model);
        playButton.setEnabled(false);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 1;
        add(playButton, currGridBagConstraints);

        // Add pause animation button.
        pauseButton = new PauseButton("Pause", this.model);
        pauseButton.setEnabled(true);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 1;
        add(pauseButton, currGridBagConstraints);

        // Connect play and pause buttons.
        playButton.setPauseButton(this.pauseButton);
        pauseButton.setPlayButton(this.playButton);


        // Set the panel visible.
        setVisible(true);
    }
}