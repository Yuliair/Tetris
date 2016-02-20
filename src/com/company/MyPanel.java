package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by Юлия on 16.02.2016.
 */
public class MyPanel extends JPanel implements KeyListener {
    Game game;
    Field field;
    private int m, n;
    private int sizeS, scores, scoresold;
    private int phraze;

    private boolean drawNumbers = false;
    private int colorShift = 0;


    MyPanel(Game game, int m, int n) {
        this.game = game;
        this.field = new Field(m, n, this);
        this.m = m;
        this.n = n;
//        this.addKeyListener(this);
        scores = 0;
        phraze = -2;

        Timer timer = new Timer();
        timer.setField(this.field);
        timer.start();


    }

    public void paintComponent(Graphics g) {
        int textLong = 100;
        int otstup2 = 30;
        int otstup = 30;

        super.paintComponent(g);
        int widthS = (this.getWidth() - textLong - 20) / this.n;
        int hightS = (this.getHeight() - otstup2) / this.m;
        if (widthS > hightS) sizeS = hightS;
        else sizeS = widthS;
        //int size =

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.field.getColorNumb(i, j) >= 0) {
                   g.setColor(getColor(field.getColorNumb(i,j)));
                    g.fillRect(j * sizeS, i * sizeS, sizeS - 1, sizeS - 1);

                }

            }
        }

        int alignLeft = sizeS * n + 10;

        g.setColor(Color.BLACK);
        //g.setFont();

        // g.drawString("Let's help squares ", alignLeft, otstup);
        //g.drawString("to leave cruel world", alignLeft, otstup * 2 - 10);
        g.drawString("Scores", alignLeft, otstup * 3);
        g.drawString(Integer.toString(field.getScores()), alignLeft, otstup * 4);
        //g.drawString(this.getPhraze(phraze), 10, sizeS * m + 20);


        //lscoreNumber = new JLabel(Integer.toString(field.getScores()));


    }

    private Color getColor(int i) {
        if (i==0) return Color.white;
        int r = i/1000000;
       // System.out.println(r+ " -r");
        i = i% 1000000;
        int g = i/1000;
       // System.out.println(g+ " -g");
        i = i & 1000;
           // System.out.println(i);
        int b = i%50;

        return new Color(r,g,b);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("WHAT");
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            //2
            try {
                field.acting(2);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        else
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            //3
            try {
                field.acting(3);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        else
        if (e.getKeyCode()==KeyEvent.VK_UP)
            //1
            try {
                field.acting(1);
            } catch (Exception e1) {
                e1.printStackTrace();
            }


    }

    @Override
    public void keyPressed(KeyEvent e) {
     //   System.out.println("WHAT");
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            //2
            try {
                field.acting(2);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        else
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            //3
            try {
                field.acting(3);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        else
        if (e.getKeyCode()==KeyEvent.VK_UP)
            //1
            try {
                field.acting(1);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        else
        if (e.getKeyCode()==KeyEvent.VK_DOWN)
            try {
                field.acting(0);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
