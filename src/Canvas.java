/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-17 Last modified.
*
* Canvas.java: 
* A Canvas object works as a graphical display for the battlefield in this 
* game. Shapes and actions of player tanks, enemy tanks and landscape objects 
* are all showed on the canvas. 
*   It contains a pointer to model (shared database) that facilitates calling 
* drawing methods for all vehicles and landscape objects.
*******************************************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Canvas extends JPanel implements MouseListener {
    // Shared database of this game.
    private Model model;

    // Background color settings.
    private Color backgroundColor;
    private final Color DEFAULT_BACKGROUND_COLOR = Color.black;

    // Parameterized constructor.
    // Parameters: Model model: The shared database of this game.
    // Does: It connects to the model (shared database) and sets its background 
    //       to the default color.
    public Canvas(Model model) {
        this.model = model;

        // Set the background color to default.
        backgroundColor = DEFAULT_BACKGROUND_COLOR;
        this.setBackground(backgroundColor);

        addMouseListener(this);
    }

    // Method paintComponent.
    // Parameters: Graphics g: the Graphics object that draws.
    // Returns: nothing
    // Does: It draws game contents on this canvas.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw landscape objects and vehicles from model.
        model.draw(g);
    }

    // MouseListener callbacks
    public void mouseClicked(MouseEvent e) {
        System.out.println ("Mouse down at " + e.getPoint().x + ", " 
        + e.getPoint().y);

        this.model.selectPlayerTank(e.getPoint());
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}