
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

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.*;

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
    // private ArrayList<LandscapeObject> landscapeObjectArrayList;
    private ArrayList<Object> landscapeObjectArrayList;

    // Array list for all the player vehicles.
    private ArrayList<Vehicle> playerVehicleArrayList;

    // Array list for all the enemy vehicles.
    private ArrayList<Vehicle> enemyVehicleArrayList;

    // Whether enemy vehicles automatically move.
    private boolean isEnemyActive;

    // Array list for all the shells.
    private ArrayList<Shell> shellArrayList;

    // Game data showed on the information panel.
    private int playerKills;
    private int enemyLeft;

    // Pointer to the information panel.
    private InfoPanel infoPanel;

    // Zoom settings.
    private double originPosX;
    private double originPosY;
    private double zoomScale; // 1 by default.

    // Default constructor.
    // Does: It initializes the landscape object and vehicle array lists.
    public Model() {
        // Initialize the timer.
        this.clockSpeed = 500;
        this.timer = new Timer(clockSpeed, this);
        this.frame = 0;

        this.playerKills = 0;
        this.enemyLeft = 0;

        // Initialize the zoom settings.
        this.originPosX = 300;
        this.originPosY = 300;
        this.zoomScale = 1;

        // Initialize the landscape object array list.
        this.landscapeObjectArrayList = new ArrayList<Object>();
        this.loadLandscape();

        // Initialize the player vehicle array list.
        this.playerVehicleArrayList = new ArrayList<Vehicle>();
        this.nextPlayerVehicleId = 0;
        // Add 2 player tanks into the battlefield.
        this.addPlayerLightTank(100, 500, Object.NORTH);
        this.addPlayerHeavyTank(150, 500, Object.NORTH);
        this.addPlayerLightTank(400, 500, Object.NORTH);
        this.addPlayerHeavyTank(450, 500, Object.NORTH);
        this.controlledVehicle = playerVehicleArrayList.get(0);
        this.controlledVehicle.setHighlighted(true);

        // Initialize the enemy vehicle array list.
        this.enemyVehicleArrayList = new ArrayList<Vehicle>();
        this.nextEnemyVehicleId = 0;
        // Add 2 enemy tanks into the battlefield.
        this.addEnemyLightTank(150, 100, Object.SOUTH);
        this.addEnemyHeavyTank(250, 100, Object.SOUTH);
        this.addEnemyLightTank(350, 100, Object.SOUTH);
        this.addEnemyHeavyTank(450, 100, Object.SOUTH);
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
        
        // Start animation.
        this.timer.start();

        // Update zooming center to the canvas center.
        this.originPosX = this.canvas.getWidth() / 2;
        this.originPosY = this.canvas.getHeight() / 2;
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
    }

    // Method draw.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws all the landscape objects and vehicles in the array lists.
    public void draw(Graphics g) {
        // Update zoom center.
        this.originPosX = canvas.getWidth() / 2;
        this.originPosY = canvas.getHeight() / 2;

        // Draw all landscape objects.
        for (Object landscapeObject : landscapeObjectArrayList) {
            landscapeObject.draw(g, this.originPosX, this.originPosY, 
                    this.zoomScale);
        }

        // Draw all enemy vehicles.
        for (Vehicle vehicle : enemyVehicleArrayList) {
            vehicle.draw(g, this.originPosX, this.originPosY, this.zoomScale);
        }

        // Draw all player vehicles.
        for (Vehicle vehicle : playerVehicleArrayList) {
            vehicle.draw(g, this.originPosX, this.originPosY, this.zoomScale);
        }

        // Draw all shells.
        for (Shell shell : shellArrayList) {
            shell.draw(g, this.originPosX, this.originPosY, this.zoomScale);
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
        playerVehicleArrayList.add(
                new PlayerLightTank(this.nextPlayerVehicleId, centerPosX, 
                        centerPosY, direction, this.originPosX,
                        this.originPosY, this.zoomScale));
        this.nextPlayerVehicleId++;
    }

    // Method addEnemyLightTank.
    // Parameters: double centerPosX: Initial x coordinate of the new tank.
    // double centerPosY: Initial y coordinate of the new tank.
    // int direction: Initial facing direction of the new tank.
    // Returns: nothing
    // Does: It creates a new enemy light tank according to the given position
    // and direction and adds it to the enemy vehicle array list.
    public void addEnemyLightTank(double centerPosX, double centerPosY, 
            int direction) {
        enemyVehicleArrayList.add(
                new EnemyLightTank(this.nextEnemyVehicleId, centerPosX, 
                        centerPosY, direction,this.originPosX,
                        this.originPosY, this.zoomScale));
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
    public void addPlayerHeavyTank(double centerPosX, double centerPosY, 
            int direction) {
        playerVehicleArrayList.add(
                new PlayerHeavyTank(this.nextPlayerVehicleId, centerPosX, 
                        centerPosY, direction, this.originPosX,
                        this.originPosY, this.zoomScale));
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
        enemyVehicleArrayList.add(
                new EnemyHeavyTank(this.nextEnemyVehicleId, centerPosX, 
                        centerPosY, direction, this.originPosX,
                        this.originPosY, this.zoomScale));
        this.nextEnemyVehicleId++;
        this.enemyLeft++;
        if (this.infoPanel != null) {
            this.infoPanel.setEnemyLeft(this.enemyLeft);
        }
    }

    // Add a shell to the shell array list.
    public void addShell(double centerPosX, double centerPosY, int direction, 
                         boolean isPlayerShell) {
        shellArrayList.add(
            new Shell(centerPosX, centerPosY, direction, isPlayerShell, 
                        this.originPosX, this.originPosY, this.zoomScale));
    }

    // Action handler for timer.
    public void actionPerformed(ActionEvent e) {
        frame++;

        // Animation for enemy tanks.
        if (isEnemyActive) {
            if (frame % 30 == 0) {
                addEnemyLightTank(250, 50, Object.SOUTH);
                addEnemyHeavyTank(350, 50, Object.SOUTH);
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
            for (int i = 0; i < 8; i++) {
                if (detectCollision(shell)) {
                    System.out.println("Shell hit!");
                    shellToRemove.add(shell);
                    break;
                }
                shell.tick();
            }
        }

        // Remove hit shells.
        for (Shell shell : shellToRemove) {
            shellArrayList.remove(shell);
        }

        this.canvas.repaint();
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
                if (direction == Object.NORTH) {
                    addShell(x, y - Object.unitSize * 2, direction, true);
                }
                else if (direction == Object.SOUTH) {
                    addShell(x, y + Object.unitSize * 2, direction, true);
                }
                else if (direction == Object.EAST) {
                    addShell(x + Object.unitSize * 2, y, direction, true);
                }
                else if (direction == Object.WEST) {
                    addShell(x - Object.unitSize * 2, y, direction, true);
                }
                canvas.repaint();
                break;
            }
        }
    }

    // Check if the given vehicle will collide with other object.
    private boolean detectCollision(Vehicle vehicle) {
        // Check if this vehicle collides with player vehicles.
        for (Vehicle playerVehicle : playerVehicleArrayList) {
            if (vehicle != playerVehicle 
                    && playerVehicle.isCollided(
                            vehicle.getCollisionBoundary(this.originPosX, 
                                    this.originPosY, this.zoomScale))) {
                return true;
            }
        }

        // Check if this vehicle collides with enemy vehicles.
        for (Vehicle enemyVehicle : enemyVehicleArrayList) {
            if (vehicle != enemyVehicle 
                    && enemyVehicle.isCollided(
                            vehicle.getCollisionBoundary(this.originPosX, 
                                    this.originPosY, this.zoomScale))) {
                return true;
            }
        }

        // Check if this vehicle collides with landscape objects.
        for (Object landscapeObject : landscapeObjectArrayList) {
            if (landscapeObject.isCollided(
                        vehicle.getCollisionBoundary(this.originPosX, 
                            this.originPosY, this.zoomScale))) {
                return true;
            }
        }

        return false;
    }

    // Check if the given shell will collide with other object.
    private boolean detectCollision(Shell shell) {

        // Check if this vehicle collides with player vehicles.
        if (!shell.is_playerShell()) {
            for (Vehicle playerVehicle : playerVehicleArrayList) {
                if (playerVehicle.isCollided(
                        shell.getCollisionBoundary(originPosX, originPosY, 
                                zoomScale))) {

                    // Remove the hit player vehicle.
                    this.playerVehicleArrayList.remove(playerVehicle);

                    return true;
                }
            }
        }

        // Check if this vehicle collides with enemy vehicles.
        if (shell.is_playerShell()) {
            for (Vehicle enemyVehicle : enemyVehicleArrayList) {
                if (enemyVehicle.isCollided(
                        shell.getCollisionBoundary(originPosX, originPosY, 
                                zoomScale))) {

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

                    return true;
                }
            }
        }

        // Check if this vehicle collides with landscape objects.
        for (Object landscapeObject : landscapeObjectArrayList) {
            if (landscapeObject.isCollided(
                    shell.getCollisionBoundary(originPosX, originPosY, 
                            zoomScale))) {
                return true;
            }
        }
        
        return false;
    }

    // Update the zooming center to the current canvas center.
    public void updateOriginPos(){
        this.originPosX = canvas.getWidth() / 2;
        this.originPosY = canvas.getHeight() / 2;
    }

    // Increase the zoom scale 10%.
    // The max zoom scale is 400%.
    public void zoomIn(){
        if (this.zoomScale < 4) {
            this.zoomScale += 0.1;
            this.canvas.repaint();
        }
    }

    // Decrease the zoom scale by 10%.
    // The min zoom scale is 50%.
    public void zoomOut(){
        if (this.zoomScale > 0.5) {
            this.zoomScale -= 0.1;
            this.canvas.repaint();
        }
    }

    public double getZoomScale() {
        return zoomScale;
    }
}