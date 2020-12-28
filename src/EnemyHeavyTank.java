/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* EnemyHeavyTank.java: 
* EnemyHeavyTank class is designed for enemy heavy tanks in this game.
* EnemyHeavyTank class is based on HeavyTank class but the fill color is set to 
* gray.
* It has its own tick method to move an enemy light tank itself.
*******************************************************************************/

import java.awt.*;

public class EnemyHeavyTank extends HeavyTank {
    // Steps this vehicle moved during animation.
    private int step;

    // Default constructor.
    public EnemyHeavyTank(
        int id,
        double centerPosX, 
        double centerPosY, 
        int direction
        ) {
        super(id, centerPosX, centerPosY, direction);
        
        // Set fill color to yellow.
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
        if (step % 60 > 15 && step % 60 < 30) {
            move(Vehicle.EAST);
        }
        else if (step % 60 > 45 && step % 60 < 60) {
            move(Vehicle.WEST);
        }
        else{
            move(Vehicle.SOUTH);
        }
        step++;
    }
}
