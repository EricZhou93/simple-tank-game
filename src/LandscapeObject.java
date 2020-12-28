/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-17 Created.
* 2019-10-25 Last modified.
*
* LandscapeObject.java: 
* LandscapeObject class is the abstract base for all the Landscape object in 
* this game.
* It contains basic settings for landscape objects, including position and size.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public abstract class LandscapeObject{
    // Center coordinate.
    protected double centerPosX;
    protected double centerPosY;

    // Collision size.
    protected double collisionWidth;
    protected double collisionLength;
    protected Rectangle2D collisionBoundary;

    // Unit size for the landscape objects.
    protected final static double unitWidth = 10;
    protected final static double unitLength = 10;

    // Draw the landscape object itself.
    protected abstract void draw(Graphics g);

    // Create and return the a collision boundary shape.
    public Rectangle2D getCollisionBoundary() {
        // Set the collision boundary size.
        this.collisionWidth = Vehicle.unitWidth * 4;
        this.collisionLength = Vehicle.unitLength * 4;

        // Set the collision boundary center is the vehicle center.

        // Create a collision boundary shape.
        Rectangle2D collisionBoundaryShape = new Rectangle2D.Double(
            this.centerPosX - this.collisionWidth / 2,
            this.centerPosY - this.collisionLength / 2, 
            this.collisionWidth, 
            this.collisionLength);

        return collisionBoundaryShape;
    }

    // Draw a collision boundary.
    protected void drawCollisionBoundary(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        this.collisionBoundary = getCollisionBoundary();

        g2.setColor(new Color(0, 0, 0, 0));
        g2.draw(this.collisionBoundary);
    }

    // Check if the given shape intersects with this collision boundary.
    public boolean isCollided(Rectangle2D r) {
        return this.collisionBoundary.intersects(r);
    }
}