
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

class Rock extends Object {
    // Rock size.
    // Since rock is a square, rock size is the length of its sides.
    private double rockSize;

    public Rock(double centerPosX, double centerPosY) {
        // Initialize the position.
        this.centerPosX = centerPosX;
        this.centerPosY = centerPosY;

        this.strokeColor = Color.black;
        this.fillColor = Color.lightGray;
    }

    private Shape getRockShape(double originPosX, double originPosY, 
            double zoomScale) {
        this.rockSize = 4 * Object.unitSize;

        // Convert "real" coordinates to drawing coordinates.
        double drawingCenterPosX = this.getDrawingPosX(originPosX, zoomScale);
        double drawingCenterPosY = this.getDrawingPosY(originPosY, zoomScale);

        Shape rockShape = new Rectangle2D.Double(
                drawingCenterPosX - this.rockSize / 2, 
                drawingCenterPosY - this.rockSize / 2,
                this.rockSize, 
                this.rockSize);
        return rockShape;
    }

    // Draw this rock.
    public void draw(Graphics g, double originPosX, double originPosY, 
            double zoomScale) {
        Graphics2D g2 = (Graphics2D) g;

        Shape rockShape = this.getRockShape(originPosX, originPosY, zoomScale);

        g2.setColor(this.fillColor);
        g2.fill(rockShape);

        g2.setColor(this.strokeColor);
        g2.draw(rockShape);

        this.drawCollisionBoundary(g, originPosX, originPosY, zoomScale);
    }

    @Override
    protected void tick() {

    }
}