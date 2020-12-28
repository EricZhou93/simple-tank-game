/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-25 Created.
* 2019-10-25 Last modified.
*
* Shell.java: 
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public class Shell {
    // Whether this shell is fired from player vehicle.
    protected boolean isPlayerShell;

    // Center coordinate.
    protected double centerPosX;
    protected double centerPosY;
    
    // Unit size for the vehicle.
    protected final static double unitWidth = 10;
    protected final static double unitLength = 10;

    // Collision size.
    protected double shellWidth;
    protected double shellLength;

    // Collision size.
    protected double collisionWidth;
    protected double collisionLength;
    protected Rectangle2D collisionBoundary;

    // Color settings.
    protected Color strokeColor;
    protected Color fillColor;

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


    // Linear speed of this vehicle.
    protected double speedX;
    protected double speedY;

        // Default constructor.
        public Shell(
            double centerPosX, 
            double centerPosY, 
            int direction,
            boolean isPlayerShell
            ) {
            // Set the initial position.
            this.centerPosX = centerPosX;
            this.centerPosY = centerPosY;
    
            // Set the initial direction.
            this.direction = direction;

            // Set whether this shell is from player.
            this.isPlayerShell = isPlayerShell;
    
            // Set linear speed.
            this.speedX = 4 * Vehicle.unitWidth;
            this.speedY = 4 * Vehicle.unitLength;
    
            // Set color.
            this.strokeColor = Color.black;
            this.fillColor = Color.red;
    
            this.collisionBoundary = this.getCollisionBoundary();
        }


    // Abstract method draw.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws the vehicle itself.
    protected void draw(Graphics g){
        this.drawShell(g);
        this.drawCollisionBoundary(g);
    }

    // Method move.
    // Parameters: int direction: The moving direction.
    // Returns: Nothing.
    // Does: It moves the vehicle to the given direction.
    // If the current vehicle direction is the given direction, the
    // vehicle will move forward on that direction.
    // If the current vehicle direction is not the given direction, the
    // vehicle will turn to that direction.
    public void move(int direction) {
        if (this.direction == direction) {
            if (direction == Shell.NORTH) {
                this.centerPosY -= this.speedY;
            } else if (direction == Shell.SOUTH) {
                this.centerPosY += this.speedY;
            } else if (direction == Shell.WEST) {
                this.centerPosX -= this.speedX;
            } else if (direction == Shell.EAST) {
                this.centerPosX += this.speedX;
            }
        } else {
            this.direction = direction;
        }
    }

    // Animation procedure method.
    protected void tick() {
        if (this.direction == Shell.NORTH) {
            centerPosY -= speedY;
        } else if (this.direction == Shell.SOUTH) {
            centerPosY += speedY;
        } else if (this.direction == Shell.EAST) {
            centerPosX += speedX;
        } else if (this.direction == Shell.WEST) {
            centerPosX -= speedX;
        }
    }

    // Create and return the a shell shape.
    protected Shape getShellShape() {
        // Set the collision boundary size.
        if (this.direction == Shell.NORTH || this.direction == Shell.SOUTH) {
            this.shellWidth = Shell.unitWidth * 0.5;
            this.shellLength = Shell.unitLength * 0.5;
        } else if (this.direction == Shell.EAST || this.direction == Shell.WEST) {
            this.shellWidth = Shell.unitLength * 0.5;
            this.shellLength = Shell.unitWidth * 0.5;
        }

        // Create a hull with the center position and the size.
        Shape shellShape = new Ellipse2D.Double(
            this.centerPosX - this.shellWidth / 2, 
            this.centerPosY - this.shellLength / 2, 
            this.shellWidth, 
            this.shellLength
        );

        return shellShape;
    }

    // Draw a hull.
    protected void drawShell(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape shellShape = this.getShellShape();

        g2.setColor(this.fillColor);
        g2.fill(shellShape);

        g2.setColor(this.strokeColor);
        g2.draw(shellShape);
    }

    // Create and return the a collision boundary shape.
    public Rectangle2D getCollisionBoundary() {
        // Set the collision boundary size.
        if (this.direction == Shell.NORTH || this.direction == Shell.SOUTH) {
            this.collisionWidth = Shell.unitWidth * 0.5;
            this.collisionLength = Shell.unitLength * 0.5;
        } else if (this.direction == Shell.EAST || this.direction == Shell.WEST) {
            this.collisionWidth = Shell.unitLength * 0.5;
            this.collisionLength = Shell.unitWidth * 0.5;
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

        g2.setColor(new Color(0, 0, 0, 0));
        g2.draw(this.collisionBoundary);
    }

    // Check if the given shape intersects with this collision boundary.
    public boolean isCollided(Rectangle2D r){
        return this.collisionBoundary.intersects(r);
    }

    // Getter for isPlayerShell.
    public boolean is_playerShell() {
        return this.isPlayerShell;
    }
}