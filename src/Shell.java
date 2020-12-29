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

public class Shell extends Object {
    // Whether this shell is fired from player vehicle.
    private boolean isPlayerShell;

    // Diameter of a shell.
    private double shellSize;

    // Linear speed of this vehicle.
    protected double speed;

        // Default constructor.
       public Shell(double centerPosX, double centerPosY, int direction, 
            boolean isPlayerShell, double originPosX, double originPosY, 
            double zoomScale) {
            // Set the initial position.
            this.centerPosX = centerPosX;
            this.centerPosY = centerPosY;
    
            // Set the initial direction.
            this.direction = direction;

            // Set whether this shell is from player.
            this.isPlayerShell = isPlayerShell;
    
            // Set linear speed.
            // this.speed = 4 * Object.unitSize;
            this.speed = 0.5 * Object.unitSize;
    
            // Set color.
            this.strokeColor = Color.black;
            this.fillColor = Color.red;
    
            this.collisionBoundary = this.getCollisionBoundary(originPosX, 
                originPosY, zoomScale);
        }


    protected void draw(Graphics g, double originPosX, double originPosY, 
            double zoomScale) {
        this.drawShell(g, originPosX, originPosY, zoomScale);
        this.drawCollisionBoundary(g, originPosX, originPosY, zoomScale);
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
                this.centerPosY -= this.speed;
            } else if (direction == Shell.SOUTH) {
                this.centerPosY += this.speed;
            } else if (direction == Shell.WEST) {
                this.centerPosX -= this.speed;
            } else if (direction == Shell.EAST) {
                this.centerPosX += this.speed;
            }
        } else {
            this.direction = direction;
        }
    }

    // Animation procedure method.
    protected void tick() {
        if (this.direction == Shell.NORTH) {
            centerPosY -= speed;
        } else if (this.direction == Shell.SOUTH) {
            centerPosY += speed;
        } else if (this.direction == Shell.EAST) {
            centerPosX += speed;
        } else if (this.direction == Shell.WEST) {
            centerPosX -= speed;
        }
    }

    // Create and return the a shell shape.
    protected Shape getShellShape(double originPosX, double originPosY, 
            double zoomScale) {
        this.shellSize = Object.unitSize * 0.5;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Create a hull with the center position and the size.
        Shape shellShape = new Ellipse2D.Double(
            drawingCenterPosX - this.shellSize / 2, 
            drawingCenterPosY - this.shellSize / 2, 
            this.shellSize, 
            this.shellSize
        );

        return shellShape;
    }

    // Draw a shell.
    protected void drawShell(Graphics g, double originPosX, double originPosY, 
            double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape shellShape 
                = this.getShellShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(shellShape);

        g2.setColor(this.strokeColor);
        g2.draw(shellShape);
    }

    // Create and return the a collision boundary shape.
    protected Rectangle2D getCollisionBoundary(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the collision boundary size.
        this.collisionWidth = this.shellSize;
        this.collisionLength = this.shellSize;

        // Set the collision boundary center is the landscape object center.

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Create a collision boundary shape.
        Rectangle2D collisionBoundaryShape 
                = new Rectangle2D.Double(
                        drawingCenterPosX - this.collisionWidth / 2,
                        drawingCenterPosY - this.collisionLength / 2, 
                        this.collisionWidth, this.collisionLength);

        return collisionBoundaryShape;
    }

    // Getter for isPlayerShell.
    public boolean is_playerShell() {
        return this.isPlayerShell;
    }
}