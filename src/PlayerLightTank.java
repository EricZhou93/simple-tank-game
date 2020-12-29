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
    public PlayerLightTank(int id, double centerPosX, double centerPosY, 
            int direction, double originPosX, double originPosY, 
            double zoomScale) {
        super(id, centerPosX, centerPosY, direction, originPosX, originPosY, 
                zoomScale);
        
        // Set fill color to yellow.
        this.fillColor = Color.yellow;
    }

    // Draw the vehicle id.
    @Override
    protected void drawId(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.drawString(
            "Player:" + Integer.toString(this.id), 
            (float) (drawingCenterPosX - Object.unitSize * 2.5),
            (float) (drawingCenterPosY - Object.unitSize * 2.5)
        );
    }
}
