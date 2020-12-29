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

public class LightTank extends Tank{
    // Default constructor.
    public LightTank(int id, double centerPosX, double centerPosY, 
            int direction, double originPosX, double originPosY, 
            double zoomScale) {
        super(id, centerPosX, centerPosY, direction, originPosX, originPosY, 
                zoomScale);

        // Set linear speed.
        this.speed = 1 * Object.unitSize;
    }

    // Create and return the a hull shape.
    protected Shape getHullShape(double originPosX, double originPosY, 
            double zoomScale) {
        // Set the hull size.
        this.hullWidth = Object.unitSize * 2;
        this.hullLength = Object.unitSize * 2.5;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Set the hull center position.
        this.hullCenterPosX = drawingCenterPosX;
        this.hullCenterPosY = drawingCenterPosY + this.hullLength / 10;

        // Create a hull with the center position and the size.
        Shape hullShape = new Rectangle2D.Double(
            this.hullCenterPosX - this.hullWidth / 2, 
            this.hullCenterPosY - this.hullLength / 2, 
            this.hullWidth, 
            this.hullLength
        );

        // Adjust the direction.
        hullShape = this.rotateToCurrDirection(hullShape, originPosX, 
                originPosY, zoomScale);

        return hullShape;
    }

    // Create and return the a turret.
    protected Shape getTurretShape(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the turret size.
        this.turretWidth = Object.unitSize * 2;
        this.turretLength = Object.unitSize * 2;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Set the turret center position.
        this.turretCenterPosX = drawingCenterPosX;
        this.turretCenterPosY = drawingCenterPosY;

        // Create a turret with the center position and the size.
        Shape turretShape = new Ellipse2D.Double(
            this.turretCenterPosX - this.turretWidth / 2, 
            this.turretCenterPosY- this.turretLength / 2, 
            this.turretWidth, 
            this.turretLength
        );

        // Adjust the direction.
        turretShape = this.rotateToCurrDirection(turretShape, originPosX, 
                originPosY, zoomScale);

        return turretShape;
    }

    // Create and return the an artillery.
    protected Shape getArtilleryShape(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the artillery size.
        this.artilleryWidth = Object.unitSize / 4;
        this.artilleryLength = Object.unitSize * 1;

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
        artilleryShape = this.rotateToCurrDirection(artilleryShape, originPosX, 
                originPosY, zoomScale);
        return artilleryShape;
    }

    // Create and return the a left track.
    protected Shape getLeftTrackShape(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the left track size.
        this.trackWidth = Object.unitSize * 1;
        this.trackLength = Object.unitSize * 3.5;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Set the left track center position.
        // The right side of the left track attaches to the left side of the hull.
        this.leftTrackCenterPosX = drawingCenterPosX - this.hullWidth / 2 
                - this.trackWidth / 2;
        this.leftTrackCenterPosY = drawingCenterPosY;

        // Create a left track with the center position and the size.
        Shape leftTrackShape = new Rectangle2D.Double(
            this.leftTrackCenterPosX - this.trackWidth / 2, 
            this.leftTrackCenterPosY - this.trackLength / 2, 
            this.trackWidth,
            this.trackLength
        );

        // Adjust the direction.
        leftTrackShape = this.rotateToCurrDirection(leftTrackShape, originPosX, 
                originPosY, zoomScale);

        return leftTrackShape;
    }

    // Create and return the a right track.
    protected Shape getRightTrackShape(double originPosX, 
            double originPosY, double zoomScale) {
        // Set the right track size.
        this.trackWidth = Object.unitSize * 1;
        this.trackLength = Object.unitSize * 3.5;

        // Convert the "real" coordinate to drawing coordinate.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        // Set the right track center position.
        // The right side of the right track attaches to the right side of the hull.
        this.rightTrackCenterPosX = drawingCenterPosX + this.hullWidth / 2 
                + this.trackWidth / 2;
        this.rightTrackCenterPosY = drawingCenterPosY;

        // Create a right track with the center position and the size.
        Shape rightTrackShape = new Rectangle2D.Double(
            this.rightTrackCenterPosX - this.trackWidth / 2, 
            this.rightTrackCenterPosY - this.trackLength / 2, 
            this.trackWidth,
            this.trackLength
        );

        // Adjust the direction.
        rightTrackShape = this.rotateToCurrDirection(rightTrackShape, 
                originPosX, originPosY, zoomScale);

        return rightTrackShape;
    }

    // Animation procedure method.
    @Override
    protected void tick() {

    }
}
