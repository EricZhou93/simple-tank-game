
/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-11 Created.
* 2019-10-25 Last modified.
*
* Model.java: 
* A Model object works as a shared database for this program. 
*   It contains a unified timer to maintain the animation for this program.
*   It contains a pointer to canvas to facilitate (re-)drawing routines.
*   It contains array lists of vehicles and landscape objects facilitating 
* the maintenance of their drawing and animation routines.
*   All the widgets in control panel control vehicles and other objects through 
* this model (shared database) to unify and simply the wiring relationships.
*******************************************************************************/

import java.awt.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.geom.*;

public class Model implements ActionListener{
    // Unified timer for this game.
    private Timer timer;
    private int clockSpeed;
    private int frame;

    // Graphical display of this game.
    private Canvas canvas;

    // ID for next player vehicle.
    private int nextPlayerVehicleId;

    // ID for next enemy vehicle.
    private int nextEnemyVehicleId;

    // Pointer to the player vehicle being controlled.
    private Vehicle controlledVehicle;

    // Array list for all the landscape objects.
    private ArrayList<LandscapeObject> landscapeObjectArrayList;

    // Array list for all the player vehicles.
    private ArrayList<Vehicle> playerVehicleArrayList;

    // Array list for all the enemy vehicles.
    private ArrayList<Vehicle> enemyVehicleArrayList;

    // Whether enemy vehicles automatically move.
    private boolean isEnemyActive;

    // Array list for all the shells.
    private ArrayList<Shell> shellArrayList;

    private int playerKills;

    private int enemyLeft;

    // Pointer to infomation panel.
    private InfoPanel infoPanel;

    // Default constructor.
    // Does: It initializes the landscape object and vehicle array lists.
    public Model() {
        // Initialize the timer.
        this.clockSpeed = 500;
        this.timer = new Timer(clockSpeed, this);
        this.frame = 0;

        this.playerKills = 0;
        this.enemyLeft = 0;

        // Initialize the landscape object array list.
        this.landscapeObjectArrayList = new ArrayList<LandscapeObject>();
        this.loadLandscape();

        // Initialize the player vehicle array list.
        this.playerVehicleArrayList = new ArrayList<Vehicle>();
        this.nextPlayerVehicleId = 0;
        // Add 2 player tanks into the battlefield.
        this.addPlayerLightTank(100, 500, Vehicle.NORTH);
        this.addPlayerHeavyTank(150, 500, Vehicle.NORTH);
        this.addPlayerLightTank(400, 500, Vehicle.NORTH);
        this.addPlayerHeavyTank(450, 500, Vehicle.NORTH);
        this.controlledVehicle = playerVehicleArrayList.get(0);
        this.controlledVehicle.setHighlighted(true);

        // Initialize the enemy vehicle array list.
        this.enemyVehicleArrayList = new ArrayList<Vehicle>();
        this.nextEnemyVehicleId = 0;
        // Add 2 enemy tanks into the battlefield.
        this.addEnemyLightTank(150, 100, Vehicle.SOUTH);
        this.addEnemyHeavyTank(250, 100, Vehicle.SOUTH);
        this.addEnemyLightTank(350, 100, Vehicle.SOUTH);
        this.addEnemyHeavyTank(450, 100, Vehicle.SOUTH);
        this.isEnemyActive = true;

        // Initialize the shell array list.
        this.shellArrayList = new ArrayList<Shell>();
    }

    // Method setCanvas.
    // Parameters: Canvas canvas: The graphical display of this game.
    // Returns: nothing
    // Does: It connects this shared database to a graphical display.
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.timer.start();
    }

    // Method getCanvas.
    // Parameters: None.
    // Returns: (Canvas) The graphical display of this program.
    // Does: It returns the graphical display connected by this shared database.
    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setInfoPanel(InfoPanel infoPanel){
        this.infoPanel = infoPanel;
        this.infoPanel.setPlayerKills(this.playerKills);
        this.infoPanel.setEnemyLeft(this.enemyLeft);
    }

    // Add landscape objects to the array list.
    private void loadLandscape() {
        // Add some rocks into the battlefield.
        this.addRock(60, 300);
        this.addRock(100, 300);
        this.addRock(140, 300);
        this.addRock(180, 300);
        this.addRock(420, 200);
        this.addRock(460, 200);
        this.addRock(500, 200);
        this.addRock(540, 200);
        this.addRock(580, 200);
    }

    // Method draw.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws all the landscape objects and vehicles in the array lists.
    public void draw(Graphics g) {
        // Draw all landscape objects.
        for (LandscapeObject landscapeObject : landscapeObjectArrayList) {
            landscapeObject.draw(g);
        }

        // Draw all enemy vehicles.
        for (Vehicle vehicle : enemyVehicleArrayList) {
            vehicle.draw(g);
        }

        // Draw all player vehicles.
        for (Vehicle vehicle : playerVehicleArrayList) {
            vehicle.draw(g);
        }

        // Draw all shells.
        for (Shell shell : shellArrayList) {
            shell.draw(g);
        }
    }

    // Method addRock.
    // Parameters: double centerPosX: Initial x coordinate of the new rock.
    // double centerPosY: Initial y coordinate of the new rock
    // Returns: nothing
    // Does: It creates a new rock according to the given position and adds it
    // to the landscape object array list.
    public void addRock(double centerPosX, double centerPosY) {
        this.landscapeObjectArrayList.add(new Rock(centerPosX, centerPosY));
    }

    // Method addPlayerLightTank.
    // Parameters: double centerPosX: Initial x coordinate of the new tank.
    // double centerPosY: Initial y coordinate of the new tank.
    // int direction: Initial facing direction of the new tank.
    // Returns: nothing
    // Does: It creates a new player light tank according to the given position
    // and direction and adds it to the player vehicle array list.
    public void addPlayerLightTank(double centerPosX, double centerPosY, int direction) {
        playerVehicleArrayList.add(new PlayerLightTank(this.nextPlayerVehicleId, centerPosX, centerPosY, direction));
        this.nextPlayerVehicleId++;
    }

    // Method addEnemyLightTank.
    // Parameters: double centerPosX: Initial x coordinate of the new tank.
    // double centerPosY: Initial y coordinate of the new tank.
    // int direction: Initial facing direction of the new tank.
    // Returns: nothing
    // Does: It creates a new enemy light tank according to the given position
    // and direction and adds it to the enemy vehicle array list.
    public void addEnemyLightTank(double centerPosX, double centerPosY, int direction) {
        enemyVehicleArrayList.add(new EnemyLightTank(this.nextEnemyVehicleId, centerPosX, centerPosY, direction));
        this.nextEnemyVehicleId++;
        this.enemyLeft++;
        if (this.infoPanel != null) {
            this.infoPanel.setEnemyLeft(this.enemyLeft);
        }
    }

    // Method addPlayerHeavyTank.
    // Parameters: double centerPosX: Initial x coordinate of the new tank.
    // double centerPosY: Initial y coordinate of the new tank.
    // int direction: Initial facing direction of the new tank.
    // Returns: nothing
    // Does: It creates a new player heavy tank according to the given position
    // and direction and adds it to the player vehicle array list.
    public void addPlayerHeavyTank(double centerPosX, double centerPosY, int direction) {
        playerVehicleArrayList.add(new PlayerHeavyTank(this.nextPlayerVehicleId, centerPosX, centerPosY, direction));
        this.nextPlayerVehicleId++;
    }

    // Method addEnemyHeavyTank.
    // Parameters: double centerPosX: Initial x coordinate of the new tank.
    // double centerPosY: Initial y coordinate of the new tank.
    // int direction: Initial facing direction of the new tank.
    // Returns: nothing
    // Does: It creates a new enemy heavy tank according to the given position
    // and direction and adds it to the enemy vehicle array list.
    public void addEnemyHeavyTank(double centerPosX, double centerPosY, 
                                  int direction) {
        enemyVehicleArrayList.add(new EnemyHeavyTank(this.nextEnemyVehicleId, centerPosX, centerPosY, direction));
        this.nextEnemyVehicleId++;
        this.enemyLeft++;
        if (this.infoPanel != null) {
            this.infoPanel.setEnemyLeft(this.enemyLeft);
        }
    }

    // Add a shell to the shell array list.
    public void addShell(double centerPosX, double centerPosY, int direction, 
                         boolean isPlayerShell) {
        shellArrayList.add(new Shell(centerPosX, centerPosY, direction, 
                                     isPlayerShell));
    }

    // Action handler for timer.
    public void actionPerformed(ActionEvent e) {
        frame++;

        // Animation for enemy tanks.
        if (isEnemyActive) {
            if (frame % 30 == 0) {
                addEnemyLightTank(250, 50, Vehicle.SOUTH);
                addEnemyHeavyTank(350, 50, Vehicle.SOUTH);
            }


            for (Vehicle vehicle : enemyVehicleArrayList) {
                // Record the old position.
                double oldPosX = vehicle.getPosX();
                double oldPosY = vehicle.getPosY();

                vehicle.tick();

                // If this vehicle collides with other objects, reset its
                // position (not direction).
                if (detectCollision(vehicle)) {
                    // System.out.println("Collision!");
                    vehicle.setPos(oldPosX, oldPosY);
                }
            }
        }
        
        // Animation for shells
        ArrayList<Shell> shellToRemove = new ArrayList<Shell>();
        for (Shell shell : shellArrayList) {
            shell.tick();

            if (detectCollision(shell)) {
                System.out.println("Shell hit!");
                shellToRemove.add(shell);
            }
        }

        // Remove hit shells.
        for (Shell shell : shellToRemove) {
            shellArrayList.remove(shell);
        }

        canvas.repaint();
    }

    // Stop the unified timer.
    public void stopTimer() {
        this.timer.stop();
    }

    // Start the unified timer.
    public void startTimer() {
        this.timer.start();
    }

    // Start animation for enemy vehicles.
    public void activateEnemy() {
        this.isEnemyActive = true;
    }

    // Stop animation for enemy vehicles.
    public void deactivateEnemy() {
        this.isEnemyActive = false;
    }

    // Set the controlled vehicle by mouse click.
    public void selectPlayerTank(Point mousePoint){
        for (Vehicle vehicle : playerVehicleArrayList) {
            if (vehicle.isMouseSelected(mousePoint))
            {
                // Unselect the old vehicle.
                this.controlledVehicle.setHighlighted(false);
                // Select the new vehicle.
                this.controlledVehicle = vehicle;
                this.controlledVehicle.setHighlighted(true);

                break;
            }
        }
        canvas.repaint();
    }

    public void moveControlledVehicle(int direction){
        for (Vehicle vehicle : playerVehicleArrayList){
            if (vehicle == this.controlledVehicle) {
                // Record the old position.
                double oldPosX = vehicle.getPosX();
                double oldPosY = vehicle.getPosY();

                vehicle.move(direction);

                // If this vehicle collides with other objects, reset its 
                // position (not direction).
                if (detectCollision(vehicle)) {
                    // System.out.println("Collision!");
                    vehicle.setPos(oldPosX, oldPosY);
                }

                canvas.repaint();
                break;
            }
        }
    }

    public void fireControlledVehicle(){
        for (Vehicle vehicle : playerVehicleArrayList){
            if (vehicle == this.controlledVehicle) {
                int direction = vehicle.getDirection();
                double x = vehicle.getPosX();
                double y = vehicle.getPosY();
                if (direction == Vehicle.NORTH) {
                    addShell(x, y - Vehicle.unitLength * 2, direction, true);
                }
                else if (direction == Vehicle.SOUTH) {
                    addShell(x, y + Vehicle.unitLength * 2, direction, true);
                }
                else if (direction == Vehicle.EAST) {
                    addShell(x + Vehicle.unitWidth * 2, y, direction, true);
                }
                else if (direction == Vehicle.WEST) {
                    addShell(x - Vehicle.unitWidth * 2, y, direction, true);
                }
                canvas.repaint();
                break;
            }
        }
    }

    // Check if the given vehicle will collide with other object.
    private boolean detectCollision(Vehicle vehicle){
        boolean isCollided = false;

        // Check if this vehicle collides with player vehicles.
        if (!isCollided) {
            for (Vehicle playerVehicle : playerVehicleArrayList) {
                if (vehicle != playerVehicle
                    && 
                    playerVehicle.isCollided(vehicle.getCollisionBoundary())){
                    isCollided = true;
                    break;
                }
            }
        }

        // Check if this vehicle collides with enemy vehicles.
        if (!isCollided) {
            for (Vehicle enemyVehicle : enemyVehicleArrayList) {
                if (vehicle != enemyVehicle
                && enemyVehicle.isCollided(vehicle.getCollisionBoundary())){
                    isCollided = true;
                    break;
                }
            }
        }

        // Check if this vehicle collides with landscape objects.
        if (!isCollided) {
            for (LandscapeObject landscapeObject : landscapeObjectArrayList) {
                if (landscapeObject.isCollided(vehicle.getCollisionBoundary())){
                    isCollided = true;
                    break;
                }
            }
        }

        return isCollided;
    }

    // Check if the given shell will collide with other object.
    private boolean detectCollision(Shell shell) {
        boolean isCollided = false;

        // Check if this vehicle collides with player vehicles.
        if (!shell.is_playerShell() && !isCollided) {
            for (Vehicle playerVehicle : playerVehicleArrayList) {
                if (playerVehicle.isCollided(shell.getCollisionBoundary())) {
                    isCollided = true;

                    // Remove the hit player vehicle.
                    this.playerVehicleArrayList.remove(playerVehicle);

                    break;
                }
            }
        }

        // Check if this vehicle collides with enemy vehicles.
        if (shell.is_playerShell() && !isCollided) {
            for (Vehicle enemyVehicle : enemyVehicleArrayList) {
                if (enemyVehicle.isCollided(shell.getCollisionBoundary())) {
                    isCollided = true;

                    // Remove the hit enemy vehicle.
                    this.enemyVehicleArrayList.remove(enemyVehicle);
                    this.playerKills++;
                    if (this.infoPanel != null) {
                        this.infoPanel.setPlayerKills(this.playerKills);
                    }
                    
                    this.enemyLeft--;
                    if (this.infoPanel != null) {
                        this.infoPanel.setEnemyLeft(this.enemyLeft);
                    }

                    break;
                }
            }
        }

        // Check if this vehicle collides with landscape objects.
        if (!isCollided) {
            for (LandscapeObject landscapeObject : landscapeObjectArrayList) {
                if (landscapeObject.isCollided(shell.getCollisionBoundary())) {
                    isCollided = true;
                    break;
                }
            }
        }
        return isCollided;
    }
}