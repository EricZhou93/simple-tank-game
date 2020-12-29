
/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-31 Created.
* 2019-10-31 Last modified.
*
* Object.java: 
* Object class is the abstract base for all the objects showed on the map in 
* this game.
* It contains basic settings including center coordinates, unit size and 
* collision boundary.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public abstract class Object {
    // Center coordinate.
    protected double centerPosX;
    protected double centerPosY;

    // Direction.
    // These direction constants facilitate the setting of vehicle directions.
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    // Vehicle direction.
    protected int direction; // Integer 0 denotes north;
                             // Integer 1 denotes east;
                             // Integer 2 denotes south;
                             // Integer 3 denotes west;


    // Unit size for objects on the map.
    protected final static double unitSize = 10;

    // Color settings.
    protected Color strokeColor;
    protected Color fillColor;

    // Collision size.
    protected double collisionWidth;
    protected double collisionLength;
    protected Rectangle2D collisionBoundary;

    // Abstract draw method.
    protected abstract void draw(Graphics g, double originPosX, 
            double originPosY, double zoomScale);

    // Convert "real" x coordinate to drawing x coordinate.
    protected double getDrawingPosX(double originPosX, double zoomScale){
        return (this.centerPosX - originPosX) * zoomScale + originPosX;
    }

    // Convert "real" y coordinate to drawing y coordinate.
    protected double getDrawingPosY(double originPosY, double zoomScale){
        return (this.centerPosY - originPosY) * zoomScale + originPosY;
    }

    // Create and return the a collision boundary shape.
    protected Rectangle2D getCollisionBoundary(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the collision boundary size.
        this.collisionWidth = Object.unitSize * 4;
        this.collisionLength = Object.unitSize * 4;

        // Set the collision boundary center is the landscape object center.

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Create a collision boundary shape.
        Rectangle2D collisionBoundaryShape = new Rectangle2D.Double(
            drawingCenterPosX - this.collisionWidth / 2,
            drawingCenterPosY - this.collisionLength / 2, 
            this.collisionWidth, this.collisionLength);

        return collisionBoundaryShape;
    }

    // Draw a collision boundary.
    protected void drawCollisionBoundary(
        Graphics g, double originPosX, double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        // Update the collision boundary.
        this.collisionBoundary 
            = this.getCollisionBoundary(originPosX, originPosY, zoomScale);

        g2.setColor(new Color(0, 0, 0, 0));
        // g2.setColor(Color.yellow);
        g2.draw(this.collisionBoundary);
    }

    // Check if the given point is in collision boundary.
    protected boolean isMouseSelected(Point point) {
        return this.collisionBoundary.contains(point);
    }

    // Check if the given shape intersects with this collision boundary.
    public boolean isCollided(Rectangle2D r) {
        return this.collisionBoundary.intersects(r);
    }

    // Animation procedure.
    protected abstract void tick();

    // Getter for object position.
    public double getPosX() {
        return this.centerPosX;
    }

    public double getPosY() {
        return this.centerPosY;
    }

    // Setter for object position.
    public void setPos(double x, double y) {
        this.centerPosX = x;
        this.centerPosY = y;
    }
}