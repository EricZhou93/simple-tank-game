/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-11 Created.
* 2019-10-25 Last modified.
*
* LightTank.java: 
* LightTank class is designed for light tanks in this game.
* LightTank class has its own drawing method and procedure.
*******************************************************************************/

import java.awt.*;
import java.awt.geom.*;

public class LightTank extends Vehicle{
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
    public LightTank(
        int id,
        double centerPosX, 
        double centerPosY, 
        int direction
        ) {
        // Set the vehicle id.
        this.id = id;

        // Set the initial position.
        this.centerPosX = centerPosX;
        this.centerPosY = centerPosY;

        this.isHighlighted = false;

        // Set the initial direction.
        this.direction = direction;

        // Set linear speed.
        this.speedX = 1 * Vehicle.unitWidth;
        this.speedY = 1 * Vehicle.unitLength;

        // Set color.
        this.strokeColor = Color.black;
        this.fillColor = Color.yellow;

        this.collisionBoundary = this.getCollisionBoundary();
    }

    // Method draw.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws the light tank itself.
    public void draw(Graphics g) {
        // Draw ID.
        this.drawId(g);
        
        // Draw the tank.
        this.drawHull(g);
        this.drawTurret(g);
        this.drawArtillery(g);
        this.drawLeftTrack(g);
        this.drawRightTrack(g);
        
        // Draw the center point as reference.
        // this.drawCenter(g);

        this.drawCollisionBoundary(g);
    }

    // Draw the center point for reference.
    protected void drawCenter(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape center = new Ellipse2D.Double(
            this.centerPosX - Vehicle.unitWidth / 2, 
            this.centerPosY - Vehicle.unitLength / 2,
            Vehicle.unitWidth, 
            Vehicle.unitLength
        );

        g2.setColor(this.strokeColor);
        g2.fill(center);

        g2.setColor(this.strokeColor);
        g2.draw(center);
    }

    // Draw the vehicle id.
    protected void drawId(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.fillColor);
        g2.drawString(
            "ID:" + Integer.toString(this.id), 
            (float) (this.centerPosX - Vehicle.unitWidth * 1),
            (float) (this.centerPosY - Vehicle.unitLength * 2.5)
        );
    }

    // Create and return the a hull shape.
    protected Shape getHullShape() {
        // Set the hull size.
        this.hullWidth = Vehicle.unitWidth * 2;
        this.hullLength = Vehicle.unitLength * 2.5;

        // Set the hull center position.
        this.hullCenterPosX = this.centerPosX;
        this.hullCenterPosY = this.centerPosY + this.hullLength / 10;

        // Create a hull with the center position and the size.
        Shape hullShape = new Rectangle2D.Double(
            this.hullCenterPosX - this.hullWidth / 2, 
            this.hullCenterPosY - this.hullLength / 2, 
            this.hullWidth, 
            this.hullLength
        );

        // Adjust the direction.
        hullShape = this.rotateToCurrDirection(hullShape);

        return hullShape;
    }

    // Draw a hull.
    protected void drawHull(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape hullShape = getHullShape();

        g2.setColor(this.fillColor);
        g2.fill(hullShape);

        g2.setColor(this.strokeColor);
        g2.draw(hullShape);
    }

    // Create and return the a turret.
    protected Shape getTurretShape(){
        // Set the turret size.
        this.turretWidth = Vehicle.unitWidth * 2;
        this.turretLength = Vehicle.unitLength * 2;
        // Set the turret center position.
        this.turretCenterPosX = this.centerPosX;
        this.turretCenterPosY = this.centerPosY;
        // Create a turret with the center position and the size.
        Shape turretShape = new Ellipse2D.Double(
            this.turretCenterPosX - this.turretWidth / 2, 
            this.turretCenterPosY- this.turretLength / 2, 
            this.turretWidth, 
            this.turretLength
        );
        // Adjust the direction.
        turretShape = this.rotateToCurrDirection(turretShape);
        return turretShape;
    }

    // Draw a turret.
    protected void drawTurret(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape turretShape = getTurretShape();

        g2.setColor(this.fillColor);
        g2.fill(turretShape);

        g2.setColor(this.strokeColor);
        g2.draw(turretShape);
    }

    // Create and return the an artillery.
    protected Shape getArtilleryShape(){
        // Set the artillery size.
        this.artilleryWidth = Vehicle.unitWidth / 4;
        this.artilleryLength = Vehicle.unitLength * 1;
        // Set the artillery center position.
        // The artillery root attaches to the front of the turret.
        this.artilleryRootPosX = this.turretCenterPosX;
        this.artilleryRootPosY = this.turretCenterPosY - this.turretLength / 2;
        // Create a artillery with the center position and the size.
        Shape artilleryShape = new Rectangle2D.Double(
            this.artilleryRootPosX - this.artilleryWidth / 2, 
            this.artilleryRootPosY - this.artilleryLength,
            this.artilleryWidth, 
            this.artilleryLength
        );
        // Adjust the direction.
        artilleryShape = this.rotateToCurrDirection(artilleryShape);
        return artilleryShape;
    }

    // Draw the artillery.
    protected void drawArtillery(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape artilleryShape = getArtilleryShape();

        g2.setColor(this.fillColor);
        g2.fill(artilleryShape);

        g2.setColor(this.strokeColor);
        g2.draw(artilleryShape);
    }

    // Create and return the a left track.
    protected Shape getLeftTrackShape(){
        // Set the left track size.
        this.trackWidth = Vehicle.unitWidth * 1;
        this.trackLength = Vehicle.unitLength * 3.5;
        // Set the left track center position.
        // The right side of the left track attaches to the left side of the hull.
        this.leftTrackCenterPosX = this.centerPosX - this.hullWidth / 2 - this.trackWidth / 2;
        this.leftTrackCenterPosY = this.centerPosY;
        // Create a left track with the center position and the size.
        Shape leftTrackShape = new Rectangle2D.Double(
            this.leftTrackCenterPosX - this.trackWidth / 2, 
            this.leftTrackCenterPosY - this.trackLength / 2, 
            this.trackWidth,
            this.trackLength
        );
        // Adjust the direction.
        leftTrackShape = this.rotateToCurrDirection(leftTrackShape);
        return leftTrackShape;
    }

    // Draw the left track.
    protected void drawLeftTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape leftTrackShape = getLeftTrackShape();

        g2.setColor(this.fillColor);
        g2.fill(leftTrackShape);

        g2.setColor(this.strokeColor);
        g2.draw(leftTrackShape);
    }

    // Create and return the a right track.
    protected Shape getRightTrackShape(){
        // Set the right track size.
        this.trackWidth = Vehicle.unitWidth * 1;
        this.trackLength = Vehicle.unitLength * 3.5;
        // Set the right track center position.
        // The right side of the right track attaches to the right side of the hull.
        this.rightTrackCenterPosX = this.centerPosX + this.hullWidth / 2 + this.trackWidth / 2;
        this.rightTrackCenterPosY = this.centerPosY;
        // Create a right track with the center position and the size.
        Shape rightTrackShape = new Rectangle2D.Double(
            this.rightTrackCenterPosX - this.trackWidth / 2, 
            this.rightTrackCenterPosY - this.trackLength / 2, 
            this.trackWidth,
            this.trackLength
        );
        // Adjust the direction.
        rightTrackShape = this.rotateToCurrDirection(rightTrackShape);
        return rightTrackShape;
    }

    // Draw the left track.
    protected void drawRightTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Shape rightTrackShape = getRightTrackShape();

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
