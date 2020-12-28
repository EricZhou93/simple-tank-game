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
* position, direction, speed and size.
* Vehicle class has basic rotate and move methods for all the vehicles in this 
* game.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public abstract class Vehicle{
    // Vehicle id.
    protected int id;

    // Center coordinate.
    protected double centerPosX;
    protected double centerPosY;

    // Collision size.
    protected double collisionWidth;
    protected double collisionLength;
    protected Rectangle2D collisionBoundary;

    // Color settings.
    protected Color strokeColor;
    protected Color fillColor;
    protected Color highlightColor = Color.green;

    // Whether this vehicle is selected and highlighted.
    protected boolean isHighlighted;

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

    // Unit size for the vehicle.
    protected final static double unitWidth = 10;
    protected final static double unitLength = 10;

    // Linear speed of this vehicle.
    protected double speedX;
    protected double speedY;

    // Abstract method draw.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws the vehicle itself.
    protected abstract void draw(Graphics g);

    // Method rotateToCurrDirection.
    // Parameters: Shape s: The shape before rotation.
    // Returns: (Shape) The shape after rotation.
    // Does: It rotates a shape around the vehicle center to get to the given 
    //       direction.
    protected Shape rotateToCurrDirection(Shape s) {
        AffineTransform rotation = new AffineTransform();
        // Rotation of n * 90 degrees clockwise around this tank center.
        // Turn north: n = direction = 0.
        // Turn east: n = direction = 1.
        // Turn south: n = direction = 2.
        // Turn west: n = direction = 3.
        rotation.setToRotation(Math.PI / 2 * direction, centerPosX, centerPosY);
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
            if (direction == Vehicle.NORTH) {
                this.centerPosY -= this.speedY;
            } else if (direction == Vehicle.SOUTH) {
                this.centerPosY += this.speedY;
            } else if (direction == Vehicle.WEST) {
                this.centerPosX -= this.speedX;
            } else if (direction == Vehicle.EAST) {
                this.centerPosX += this.speedX;
            }
        } else {
            this.direction = direction;
        }
    }

    // Animation procedure method.
    protected abstract void tick();

    // Create and return the a collision boundary shape.
    public Rectangle2D getCollisionBoundary() {
        // Set the collision boundary size.
        if (this.direction == Vehicle.NORTH 
        || this.direction == Vehicle.SOUTH) {
            this.collisionWidth = Vehicle.unitWidth * 4;
            this.collisionLength = Vehicle.unitLength * 4;
        }
        else if (this.direction == Vehicle.EAST 
        || this.direction == Vehicle.WEST) {
            this.collisionWidth = Vehicle.unitLength * 4;
            this.collisionLength = Vehicle.unitWidth * 4;
        }

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

        if (isHighlighted) {
            g2.setColor(highlightColor);
        }
        else{
            g2.setColor(new Color(0, 0, 0, 0));
        }
        g2.draw(this.collisionBoundary);
    }

    // Check if the given point is in collision boundary.
    protected boolean isMouseSelected(Point point){
        return this.collisionBoundary.contains(point);
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

    // Check if the given shape intersects with this collision boundary.
    public boolean isCollided(Rectangle2D r){
        return this.collisionBoundary.intersects(r);
    }

    // Getter for vehicle position.
    public double getPosX(){
        return this.centerPosX;
    }
    public double getPosY(){
        return this.centerPosY;
    }

    // Setter for vehicle position.
    public void setPos(double x, double y){
        this.centerPosX = x;
        this.centerPosY = y;
    }

    // Getter for vehicle direction.
    public int getDirection(){
        return this.direction;
    }
}