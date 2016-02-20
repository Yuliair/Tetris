package com.company;

import javax.swing.*;

/**
 * Created by Юлия on 16.02.2016.
 */
public class Game {
    public void createAndShow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        MyPanel panel = new MyPanel(this, 25, 15);
        //panel.setLayout(null);
        frame.addKeyListener(panel);
        frame.add(panel);
        //  frame.pack();
        frame.setVisible(true);


    }
}
