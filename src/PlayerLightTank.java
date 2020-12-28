/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* PlayerLightTank.java: 
* PlayerLightTank class is designed for light tanks that can be controlled by 
* player in this game.
* PlayerLightTank class is based on LightTank class but the fill color is set 
* to yellow.
*******************************************************************************/

import java.awt.*;

public class PlayerLightTank extends LightTank {
    // Default constructor.
    public PlayerLightTank(
        int id,
        double centerPosX, 
        double centerPosY, 
        int direction
        ) {
        super(id, centerPosX, centerPosY, direction);
        
        // Set fill color to yellow.
        this.fillColor = Color.yellow;
    }

    // Draw the vehicle id.
    @Override
    protected void drawId(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.fillColor);
        g2.drawString(
            "Player:" + Integer.toString(this.id), 
            (float) (this.centerPosX - Vehicle.unitWidth * 2),
            (float) (this.centerPosY - Vehicle.unitLength * 2.5)
        );
    }
}
