/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019
*
* Author: Keren Zhou Student ID: 1328495
*
* 2019-10-03 Created.
* 2019-10-25 Last modified.
*
* Main.java: 
*   Main class contains the main method, the entrance of the program.
*   A Main class object works as the main window for this program. It contains 
* graphical and text displays for this game and a control panel for all control 
* widgets.
*   Main class has a listener and handlers for main window resized. The 
* information board (text displays for this game) in the information panel and 
* the background color panel in the control panel will be hidden to save space 
* for the canvas if the window is too small. They will show again if the window 
* is big enough.
*******************************************************************************/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class Main extends JFrame implements ComponentListener, KeyListener{
    // Size of the window.
    private int mainWindowWidth;
    private int mainWindowHeight;

    // Shared database of this program.
    private Model model; 

    // Graphical display of this program.
    private Canvas canvas; 

    // Control panel of this program.
    private ControlPanel controlPanel;

    // Text display of this program.
    private InfoPanel infoPanel;

    // Method main: the entrance of the program.
    // Parameters: String[] argv: An array of command line arguments.
    // Does: It is the entrance of the program. 
    //       It creates and initializes a Main class object.
    public static void main(String[] argv) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create the main window for the program.
                new Main();
            }
        });
    }

    // Default constructor.
    // Does: It setups the main window.
    public Main() {
        // Listener for the size of the main window.
        this.addComponentListener(this);

        // Listener for key events.
        this.addKeyListener(this);
        this.setFocusable(true);

        // Setup the shared database.
        this.model = new Model();

        // Setup the main window.
        // Exit the program when the window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the title.
        setTitle("Simple Tank Simulator");

        // Use BorderLayout.
        setLayout(new BorderLayout());

        // Add the graphical display to the center of the main window.
        this.canvas = new Canvas(this.model);
        this.canvas.setPreferredSize(new Dimension(600, 600));
        this.canvas.setBorder(new LineBorder(Color.black, 1));
        add(this.canvas, BorderLayout.CENTER);

        // Connect the model and the canvas.
        this.model.setCanvas(this.canvas);

        // Add the control panel to the right of the main window.
        this.controlPanel = new ControlPanel(this.model);
        this.controlPanel.setBorder(new LineBorder(Color.black, 1));
        add(this.controlPanel, BorderLayout.LINE_END);

        // Add the text display to the bottom of the main window.
        this.infoPanel = new InfoPanel();
        this.infoPanel.setBorder(new LineBorder(Color.black, 1));
        add(this.infoPanel, BorderLayout.PAGE_END);

        // Connect the model and the information panel.
        this.model.setInfoPanel(this.infoPanel);

        // Size the window to fit the layouts and the widgets.
        pack();

        // Set the window visible.
        setVisible(true);
    }

    // Handlers for main window resized.
    public void componentResized(ComponentEvent e) {
        this.mainWindowWidth = this.getWidth();
        this.mainWindowHeight = this.getHeight();
        System.out.println("Window Width = " + mainWindowWidth);
        System.out.println("Window Height = " + mainWindowHeight);

        // Hide or show background color panel in the control panel.
        if (this.mainWindowHeight < 550) {
            controlPanel.hideBackgroundColorPanel();
        } else {
            controlPanel.showBackgroundColorPanel();
        }

        // Hide or show information board and its scrollbar in the information 
        // panel.
        if (this.mainWindowHeight < 700) {
            this.infoPanel.hideInfoBoard();
        } else {
            this.infoPanel.showInfoBoard();
        }
    }
    public void componentMoved(ComponentEvent e) {

    }
    public void componentShown(ComponentEvent e) {

    }
    public void componentHidden(ComponentEvent e) {

    }

    // Handlers for key events.
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        System.out.println("Key " + e.getKeyChar()+" is pressed.");

        if (e.getKeyCode()==KeyEvent.VK_W) {
            this.model.moveControlledVehicle(Object.NORTH);
        }
        else if (e.getKeyCode()==KeyEvent.VK_A) {
            this.model.moveControlledVehicle(Object.WEST);
        }
        else if (e.getKeyCode()==KeyEvent.VK_S) {
            this.model.moveControlledVehicle(Object.SOUTH);
        }
        else if (e.getKeyCode()==KeyEvent.VK_D) {
            this.model.moveControlledVehicle(Object.EAST);
        }
        else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            this.model.fireControlledVehicle();
        }
    }
    public void keyReleased(KeyEvent e) {

    }
}