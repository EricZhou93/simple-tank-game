/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 

* Author: Keren Zhou    Student ID: 1328495

* 2019-10-03 Created.
* 2019-10-25 Last modified.
*
* InfoPanel.java: 
* The InfoPanel class is designed for the text display of the battlefield in 
* this game. 
* The information board has not been fully implemented but it will display game 
* information, such as the HP player tank and player current score.
*******************************************************************************/

import javax.swing.*;
import java.awt.*;

class InfoPanel extends GridBagPanel {
    // The settings of the information board.
    private JTextArea infoBoard;
    private final String INFO_BOARD_DEFAULT_TEXT 
        = "*** Simple Tank Simulator ***\n";
    private final int INFO_BOARD_DEFAULT_HEIGHT = 10;
    private final int INFO_BOARD_DEFAULT_WIDTH = 60;

    // The settings of the scrollbar for the information board.
    private JScrollPane scrollbar;

    // The settings of the text labels.
    private JLabel playerKillsLabel;
    private JLabel enemyLeftLabel;

    // Default constructor.
    public InfoPanel() {

        // Setup the panel.
        // Use GridBagLayout.
        setLayout(new GridBagLayout());
        GridBagConstraints currGridBagConstraints = new GridBagConstraints();
        setDefaultInsets(5, 5, 5, 5);
        setGridBagConstraintsToDefault(currGridBagConstraints);

        // Add the game information board.
        infoBoard = new JTextArea(INFO_BOARD_DEFAULT_TEXT, INFO_BOARD_DEFAULT_HEIGHT, INFO_BOARD_DEFAULT_WIDTH);
        // Set the game
        infoBoard.setEditable(false);
        this.infoBoard.setLineWrap(true);
        this.infoBoard.setWrapStyleWord(true);
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        currGridBagConstraints.gridheight = 2;
        add(this.infoBoard, currGridBagConstraints);

        // Add scrollbars to the information board.
        this.scrollbar = new JScrollPane(infoBoard, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 0;
        currGridBagConstraints.gridy = 0;
        currGridBagConstraints.gridheight = 2;
        add(this.scrollbar, currGridBagConstraints);

        // Add label for player kills.
        this.playerKillsLabel = new JLabel("Player Kills: 0");
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 0;
        add(this.playerKillsLabel, currGridBagConstraints);

        // Add label for enemy left.
        this.enemyLeftLabel = new JLabel("Enemy Left: 0");
        setGridBagConstraintsToDefault(currGridBagConstraints);
        currGridBagConstraints.gridx = 1;
        currGridBagConstraints.gridy = 1;
        add(this.enemyLeftLabel, currGridBagConstraints);

        // Set the panel visible.
        setVisible(true);
    }

    // Set the player kills label.
    public void setPlayerKills(int n){
        this.playerKillsLabel.setText("Player Kills: " + Integer.toString(n));
    }

    // Set the enemy left label.
    public void setEnemyLeft(int n){
        this.enemyLeftLabel.setText("Enemy Left: " + Integer.toString(n));
    }

    // Hide the infoBoard and scrollbar.
    public void hideInfoBoard(){
        this.infoBoard.setVisible(false);
        this.scrollbar.setVisible(false);
    }

    // Show the infoBoard and scrollbar.
    public void showInfoBoard() {
        this.infoBoard.setVisible(true);
        this.scrollbar.setVisible(true);
    }
}