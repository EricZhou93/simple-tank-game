/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-11 Created.
* 2019-10-25 Last modified.
*
* Vehicle.java: 
* Vehicle class is the abstract base class for all the vehicles in this game.
* A Vehicle class object contains basic settings for a vehicle, including id, 
* position, direction, speed and unit size.
* Vehicle class has basic rotate and move methods for all the vehicles in this 
* game.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public abstract class Vehicle extends Object{
    // Vehicle id.
    protected int id;

    // Color settings.
    protected Color highlightColor = Color.green;

    // Whether this vehicle is selected and highlighted.
    protected boolean isHighlighted;

    // Linear speed of this vehicle.
    protected double speed;

    // Abstract draw method.
    protected abstract void draw(Graphics g, double originPosX, 
            double originPosY, double zoomScale);

    // Method rotateToCurrDirection.
    // Parameters: Shape s: The shape before rotation.
    // Returns: (Shape) The shape after rotation.
    // Does: It rotates a shape around the vehicle center to get to the given 
    //       direction.
    protected Shape rotateToCurrDirection(Shape s, double originPosX, 
            double originPosY, double zoomScale) {
        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        AffineTransform rotation = new AffineTransform();
        // Rotation of n * 90 degrees clockwise around this tank center.
        // Turn north: n = direction = 0.
        // Turn east: n = direction = 1.
        // Turn south: n = direction = 2.
        // Turn west: n = direction = 3.
        rotation.setToRotation(Math.PI / 2 * direction, drawingCenterPosX, 
                drawingCenterPosY);
        s = rotation.createTransformedShape(s);
        return s;
    }

    // Method move.
    // Parameters: int direction: The moving direction.
    // Returns: Nothing.
    // Does: It moves the vehicle to the given direction. 
    //       If the current vehicle direction is the given direction, the 
    //       vehicle will move forward on that direction.
    //       If the current vehicle direction is not the given direction, the 
    //       vehicle will turn to that direction.
    public void move(int direction) {
        if (this.direction == direction) {
            if (direction == Object.NORTH) {
                this.centerPosY -= this.speed;
            } else if (direction == Object.SOUTH) {
                this.centerPosY += this.speed;
            } else if (direction == Object.WEST) {
                this.centerPosX -= this.speed;
            } else if (direction == Object.EAST) {
                this.centerPosX += this.speed;
            }
        } else {
            this.direction = direction;
        }
    }

    // Animation procedure method.
    protected abstract void tick();

    // Create and return the a collision boundary shape.
    public Rectangle2D getCollisionBoundary(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the collision boundary size based on vehicle direction.
        if (this.direction == Object.NORTH 
        || this.direction == Object.SOUTH) {
            this.collisionWidth = Object.unitSize * 4;
            this.collisionLength = Object.unitSize * 4;
        }
        else if (this.direction == Object.EAST 
        || this.direction == Object.WEST) {
            this.collisionWidth = Object.unitSize * 4;
            this.collisionLength = Object.unitSize * 4;
        }

        // Set the collision boundary center is the vehicle center.

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Create a collision boundary shape.
        Rectangle2D collisionBoundaryShape = new Rectangle2D.Double(
            drawingCenterPosX - this.collisionWidth / 2,
            drawingCenterPosY - this.collisionLength / 2, 
            this.collisionWidth, 
            this.collisionLength);

        return collisionBoundaryShape;
    }

    // Draw a collision boundary.
    protected void drawCollisionBoundary(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        // Update collision boundary.
        this.collisionBoundary 
                = getCollisionBoundary(originPosX, originPosY, zoomScale);

        if (isHighlighted) {
            g2.setColor(highlightColor);
        }
        else{
            g2.setColor(new Color(0, 0, 0, 0));
        }
        g2.draw(this.collisionBoundary);
    }

    // Set the vehicle highlight state.
    // If the argument is true, highlight this vehicle .
    // If the argument is false, unhighlight this vehicle.
    public void setHighlighted(boolean isSelect){
        if (isSelect) {
            this.isHighlighted = true;
        }
        else {
            this.isHighlighted = false;
        }
    }

    // Getter for vehicle direction.
    public int getDirection(){
        return this.direction;
    }

    // Draw the vehicle id.
    protected void drawId(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;
        
        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.drawString(
                "ID:" + Integer.toString(this.id), 
                (float) (drawingCenterPosX - Object.unitSize * 1),
                (float) (drawingCenterPosY - Object.unitSize * 2.5));
    }

    public int getID() {
        return this.id;
    }
}