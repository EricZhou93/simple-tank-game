/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-17 Created.
* 2019-10-25 Last modified.
*
* Rock.java: 
* Rock class is designed for rock, a kind of landscape objects in this game.
* Rock class has its own drawing method and procedure.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

class Rock extends LandscapeObject{
    // Drawing settings.
    private Color strokeColor = Color.black;
    private Color fillColor = Color.lightGray;
    // Rock.
    private double rockWidth;
    private double rockLength;

    public Rock(double centerPosX, double centerPosY){
        // Set the initial position.
        this.centerPosX = centerPosX;
        this.centerPosY = centerPosY;

        this.collisionBoundary = this.getCollisionBoundary();
    }

    private Shape getRockShape(){
        this.rockWidth = 4 * LandscapeObject.unitWidth;
        this.rockLength = 4 * LandscapeObject.unitLength;
        Shape rockShape = new Rectangle2D.Double(
            this.centerPosX - this.rockWidth / 2, 
            this.centerPosY - this.rockLength / 2, 
            this.rockWidth, 
            this.rockLength);
        return rockShape;
    }

    // Draw this rock.
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape rockShape = getRockShape();

        g2.setColor(this.fillColor);
        g2.fill(rockShape);

        g2.setColor(this.strokeColor);
        g2.draw(rockShape);

        this.drawCollisionBoundary(g);
    }
}