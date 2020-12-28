/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-04 Created.
* 2019-10-17 Last modified.
*
* GridBagPanel.java: 
* GridBagPanel class is a base class designed for JPanel subclass using 
* GridBagLayout.
* Its method setGridBagConstraintsToDefault can set the given ridBagConstraints 
* object to a specified default state. It facilitates the reuse of 
* GridBagConstraints objects.
*******************************************************************************/

import javax.swing.*;
import java.awt.*;

public abstract class GridBagPanel extends JPanel {
    // Default GridBagConstraints settings.
    private int defaultGridx = 0;
    private int defaultGridy = 0;
    private int defaultGridwidth = 1;
    private int defaultGridheight = 1;
    private int defaultFill = GridBagConstraints.NONE;
    private int defaultIpadx = 0;
    private int defaultIpady = 0;
    private Insets defaultInsets = new Insets(0, 0, 0, 0);
    private int defaultAnchor = GridBagConstraints.CENTER;
    private double defaultWeightx = 1;
    private double defaultWeighty = 1;

    // Method setGridBagConstraintsToDefault
    // Parameters: GridBagConstraints gridBagConstraints: 
    //             a GridBagConstraints object whose instance variables will be 
    //             set to specified default values.
    // Returns: nothing
    // Does: It sets the instance variables of the given GridBagConstraints 
    //       object to specified default values.
    protected GridBagConstraints setGridBagConstraintsToDefault(
        GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = defaultGridx;
        gridBagConstraints.gridy = defaultGridy;
        gridBagConstraints.gridwidth = defaultGridwidth;
        gridBagConstraints.gridheight = defaultGridheight;
        gridBagConstraints.fill = defaultFill;
        gridBagConstraints.ipadx = defaultIpadx;
        gridBagConstraints.ipady = defaultIpady;
        gridBagConstraints.insets = defaultInsets;
        gridBagConstraints.anchor = defaultAnchor;
        gridBagConstraints.weightx = defaultWeightx;
        gridBagConstraints.weighty = defaultWeighty;
        return gridBagConstraints;
    }

    // Method setDefaultInsets
    // Parameters: Insets insets: The new insets value the default insets will 
    //                            be set to.
    // Returns: nothing
    // Does: It sets the defaultInsets according to the given new Insets object.
    protected void setDefaultInsets(Insets insets){
        defaultInsets = insets;
    }

    // Method setDefaultInsets
    // Parameters: int top: The new inset from the top.
    //             int left: The new inset from the left.
    //             int bottom: The new inset from the bottom.
    //             int right The new inset from the right.
    // Returns: nothing
    // Does: It sets the defaultInsets according to the given new insets from 
    //       the top, left, bottom and right.
    protected void setDefaultInsets(int top, int left, int bottom, int right){
        defaultInsets = new Insets(top, left, bottom, right);
    }
}