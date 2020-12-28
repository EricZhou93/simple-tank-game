/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* PlayerHeavyTank.java: 
* PlayerHeavyTank class is designed for heavy tanks that can be controlled by 
* play in this game.
* PlayerHeavyTank class is based on HeavyTank class but the fill color is set 
* to yellow.
*******************************************************************************/

import java.awt.*;

public class PlayerHeavyTank extends HeavyTank {
    // Default constructor.
    public PlayerHeavyTank(
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
