/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-11 Created.
* 2019-10-25 Last modified.
*
* Tank.java: 
* Tank class is the abstract base class for all the tanks in this game.
* A Tank class object contains shape settings for a tank, including shape settings for hull, turret, artillery and tracks.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public abstract class Tank extends Vehicle {
    // Drawing settings.
    // Hull.
    protected double hullCenterPosX;
    protected double hullCenterPosY;
    protected double hullWidth;
    protected double hullLength;
    // Turret.
    protected double turretCenterPosX;
    protected double turretCenterPosY;
    protected double turretWidth;
    protected double turretLength;
    // Artillery.
    protected double artilleryRootPosX;
    protected double artilleryRootPosY;
    protected double artilleryWidth;
    protected double artilleryLength;
    // Tracks.
    protected double leftTrackCenterPosX;
    protected double leftTrackCenterPosY;
    protected double rightTrackCenterPosX;
    protected double rightTrackCenterPosY;
    protected double trackWidth;
    protected double trackLength;

    // Default constructor.
    public Tank(int id, double centerPosX, double centerPosY, int direction, 
            double originPosX, double originPosY, double zoomScale) {
        // Set the vehicle id.
        this.id = id;

        // Set the initial position.
        this.centerPosX = centerPosX;
        this.centerPosY = centerPosY;

        this.isHighlighted = false;

        // Set the initial direction.
        this.direction = direction;

        // Set linear speed.
        this.speed = 1 * Object.unitSize;

        // Set color.
        this.strokeColor = Color.black;
        this.fillColor = Color.yellow;

        this.collisionBoundary = getCollisionBoundary(originPosX, originPosY, 
                zoomScale);
    }

    // Method draw.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws the light tank itself.
    public void draw(Graphics g, double originPosX, double originPosY, 
            double zoomScale) {
        // Draw ID.
        this.drawId(g, originPosX, originPosY, zoomScale);
        
        // Draw the tank.
        this.drawHull(g, originPosX, originPosY, zoomScale);
        this.drawTurret(g, originPosX, originPosY, zoomScale);
        this.drawArtillery(g, originPosX, originPosY, zoomScale);
        this.drawLeftTrack(g, originPosX, originPosY, zoomScale);
        this.drawRightTrack(g, originPosX, originPosY, zoomScale);
        
        // Draw the center point as reference.
        // this.drawCenter(g, originPosX, originPosY, zoomScale);

        this.drawCollisionBoundary(g, originPosX, originPosY, zoomScale);
    }

    // Draw the center point for reference.
    protected void drawCenter(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        Shape center = new Ellipse2D.Double(
            drawingCenterPosX - Object.unitSize / 2, 
            drawingCenterPosY - Object.unitSize / 2,
            Object.unitSize, 
            Object.unitSize
        );

        g2.setColor(this.strokeColor);
        g2.fill(center);

        g2.setColor(this.strokeColor);
        g2.draw(center);
    }

    // Create and return the a hull shape.
    protected abstract Shape getHullShape(double originPosX, 
            double originPosY, double zoomScale);

    // Draw a hull.
    // protected abstract void drawHull(Graphics g, double originPosX, 
    //         double originPosY, double zoomScale);
    protected void drawHull(Graphics g, double originPosX, double originPosY, 
            double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape hullShape = getHullShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(hullShape);

        g2.setColor(this.strokeColor);
        g2.draw(hullShape);
    }

    // Create and return the a turret.
    protected abstract Shape getTurretShape(double originPosX, 
            double originPosY, double zoomScale);

    // Draw a turret.
    // protected abstract void drawTurret(Graphics g, double originPosX, 
    //         double originPosY, double zoomScale);
    protected void drawTurret(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape turretShape = getTurretShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(turretShape);

        g2.setColor(this.strokeColor);
        g2.draw(turretShape);
    }

    // Create and return the an artillery.
    protected abstract Shape getArtilleryShape(double originPosX, 
            double originPosY, double zoomScale);

    // Draw the artillery.
    // protected abstract void drawArtillery(Graphics g, double originPosX, 
    //         double originPosY, double zoomScale);
    protected void drawArtillery(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape artilleryShape = getArtilleryShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(artilleryShape);

        g2.setColor(this.strokeColor);
        g2.draw(artilleryShape);
    }

    // Create and return the a left track.
    protected abstract Shape getLeftTrackShape(double originPosX, 
            double originPosY, double zoomScale);

    // Draw the left track.
    // protected abstract void drawLeftTrack(Graphics g, double originPosX, 
    //         double originPosY, double zoomScale);
    protected void drawLeftTrack(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape leftTrackShape = getLeftTrackShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(leftTrackShape);

        g2.setColor(this.strokeColor);
        g2.draw(leftTrackShape);
    }

    // Create and return the a right track.
    protected abstract Shape getRightTrackShape(double originPosX, 
            double originPosY, double zoomScale);

    // Draw the left track.
    // protected abstract void drawRightTrack(Graphics g, double originPosX, 
            // double originPosY, double zoomScale);
    protected void drawRightTrack(Graphics g, double originPosX, 
            double originPosY, double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape rightTrackShape = getRightTrackShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(rightTrackShape);

        g2.setColor(this.strokeColor);
        g2.draw(rightTrackShape);
    }

    // Animation procedure method.
    @Override
    protected void tick() {

    }
}
