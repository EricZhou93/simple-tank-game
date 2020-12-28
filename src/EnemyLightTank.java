/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* EnemyLightTank.java: 
* EnemyLightTank class is designed for enemy light tanks in this game.
* EnemyLightTank class is based on LightTank class but the fill color is set to 
* gray.
* It has its own tick method (animation routine) to automatically move an enemy 
* light tank itself.
*******************************************************************************/

import java.awt.*;

public class EnemyLightTank extends LightTank {
    // Steps this vehicle moved during animation.
    private int step;
    
    // Default constructor.
    public EnemyLightTank(
        int id,
        double centerPosX, 
        double centerPosY, 
        int direction
        ) {
        super(id, centerPosX, centerPosY, direction);
        
        // Set the color to yellow.
        this.fillColor = Color.gray;

        // Initialize the steps this vehicle moved during animation.
        step = 0;
    }

    // Draw the vehicle id.
    @Override
    protected void drawId(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.fillColor);
        g2.drawString(
            "Enemy:" + Integer.toString(this.id), 
            (float) (this.centerPosX - Vehicle.unitWidth * 2.5),
            (float) (this.centerPosY - Vehicle.unitLength * 2.5)
        );
    }

    // Animation procedure method.
    @Override
    public void tick(){
        if (step % 40 > 10 && step % 40 < 20) {
            move(Vehicle.WEST);
        }
        else if (step % 40 > 30 && step % 40 < 40) {
            move(Vehicle.EAST);
        }
        else{
            move(Vehicle.SOUTH);
        }
        step++;
    }
}
