/*******************************************************************************
* Assignment 6
* Comp 86 Fall 2019 
*
* Author: Keren Zhou    Student ID: 1328495
*
* 2019-10-18 Created.
* 2019-10-18 Last modified.
*
* TextField.java: 
* TextField class is designed for common text fields used in this game.
* If a user inputs something into a TextField object and hits enter, system 
* will print the content of the text field.
*******************************************************************************/

import javax.swing.*;
import java.awt.event.*;

public class TextField extends JTextField implements ActionListener {
    private String name;

    public TextField(String name, String initVal) {
        super(initVal, 5);
        this.name = name;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(name + ": " + e.getActionCommand());
    }
}