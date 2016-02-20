package com.company;

import java.util.Arrays;

/**
 * Created by Юлия on 10.02.2016.
 */
public class Field {
    private int[][] field;
    private int m, n;
    private int scores;
    Generator generator;
    Figure fallingFigure;
    XY xy; //where figure appeared last time
    Boolean newGame;
    private Boolean stillPlaying;
    MyPanel panel ;

    public Field(int m, int n, MyPanel panel) {
        this.m = m;
        this.n = n;
        field = new int[m][n];
        //Arrays.fill(field,0);
        generator = new Generator();
        xy = null;
        fallingFigure = null;
        newGame = true;
        scores = 0;
        stillPlaying = true;
        this.panel = panel;
    }

    public synchronized void acting(int reason) throws Exception {
        if (reason == 0) {///time
            timing();
        } else if (reason == 1) {//turn
            Figure fallingFigureNew = new Figure(fallingFigure.getType());
            fallingFigureNew.setCondition(fallingFigure.getCondition());
            fallingFigureNew.turn();
            if (isFreeForFigure(fallingFigureNew, xy)) {
                fillFigure(fallingFigure, 0, xy);
                fillFigure(fallingFigureNew, fallingFigureNew.getColor(), xy);
                fallingFigure = fallingFigureNew;
                repaint();
            }
        } else if (reason == 2) {//left
            shiftLeftOrRight(true);
        } else if (reason == 3) {//right
            shiftLeftOrRight(false);
        } else if (reason == 4) {//down
            down();
        } else throw new Exception("no such reason my lord");
    }

    public void timing() {
        //maybe react  to time
        if (newGame) {
            newGame = false;
            try {
                paintNewFigure();
            } catch (Exception e) {
                gameOver();
            }
        } else {
            XY xyNew = new XY(xy.getX()+1,xy.getY());
            if (isFreeForFigure(fallingFigure, xyNew)) {
                fillFigure(fallingFigure, 0, xy);
                fillFigure(fallingFigure, fallingFigure.getColor(), xyNew);
                xy = xyNew;

            } else {
                try {
                    checkFullString();
                    paintNewFigure();
                } catch (Exception e) {
                    gameOver();
                }
            }
        }
        repaint();
    }

    public void shiftLeftOrRight(Boolean shiftLeft){
        int i;
        if (shiftLeft) i = -1;
        else i = 1;

        XY xyNew = new XY(xy.getX(), xy.getY()+i);
        if (isFreeForFigure(fallingFigure, xyNew)) {
            fillFigure(fallingFigure, 0, xy);
            fillFigure(fallingFigure, fallingFigure.getColor(), xyNew);
            xy = xyNew;
            repaint();
        }
    }

    public void down(){
        timing();
    }

    public void paintNewFigure() throws Exception {
        //if here exist place for new figure  it will start to fall
        xy = new XY(0, n / 2);
        fallingFigure = new Figure(generator.nextType());
        if (isFreeForFigure(fallingFigure, xy)) {
            fillFigure(fallingFigure, fallingFigure.getColor(), xy);
        } else throw new Exception("Game over");
    }

    private void fillFigure(Figure figure, int liquid, XY begin) {
        //fill form of current figure by choosen numbers
        Boolean inBound = true;
        int x, y;
        for (int i = 0; i < 4; i++) {
            x = figure.getPiece(i, begin).getX();
            y = figure.getPiece(i, begin).getY();
            if (x < 0 || y < 0 || x >= m || y >= n) inBound = false;
        }

        if (inBound) {
            for (int i = 0; i < 4; i++) {
                x = figure.getPiece(i, begin).getX();
                y = figure.getPiece(i, begin).getY();
                field[x][y] = liquid;
            }
        }
    }

    private Boolean isFreeForFigure(Figure figureCheck, XY begin) {
        //return true if place fixed in this xy empty
        Boolean inBound = true;

        int x, y;
        for (int i = 0; i < 4; i++) {
            x = figureCheck.getPiece(i, begin).getX();
            y = figureCheck.getPiece(i, begin).getY();
            if (x < 0 || y < 0 || x >= m || y >= n) inBound = false;
        }

        if (inBound) {
            fillFigure(this.fallingFigure, 0, xy);
            for (int i = 0; i < 4; i++) {
                x = figureCheck.getPiece(i, begin).getX();
                y = figureCheck.getPiece(i, begin).getY();
                if (field[x][y]>0) inBound = false;
            }
            fillFigure(fallingFigure, fallingFigure.getColor(), xy);


        }

        return inBound;
    }

    private void checkFullString() {
        cleaning();
       // falling();
    }


    private void cleaning() {
        //delete full strings in field
        int[] hole = new int[m];
        Arrays.fill(hole, 0);
        for (int i = 0; i < m; i++) {
            int j = 0;
            while (j < n && field[i][j] > 0) j++;
            if (j == n) {
                scores = scores + n;
                hole[i]=1;
                for (int p = 0; p < n; p++) field[i][p] = 0;
            }
        }


        //when full strings became empty everything falling


        int catcher = m - 1;
        int thrower = m - 1;
        while (catcher > 0 && hole[catcher] < 1) {
            catcher--;
            thrower--;
        }
        if (catcher > 0) {
            repaint();
            thrower--;
            while (thrower >= 0) {

                while (thrower >= 0 && hole[thrower] > 0) thrower--;
                if (thrower >= 0) {
                    for (int j = 0; j < n; j++) {
                        field[catcher][j] = field[thrower][j];
                        field[thrower][j] = 0;
                       // hole[catcher] = 0;
                       // hole[thrower] = 1;
                       // catcher--;
                       // thrower--;
                    }
                    catcher--;
                    thrower--;

                }
            }
        }
        repaint();
    }


        private void gameOver(){
        System.out.print("BY BY");
        stillPlaying = false;
    }

    public Boolean stillPlaying(){
        return stillPlaying;
    }



    public int getColorNumb(int i, int j) {
        if (i >= 0 && i < m && j < n && j >= 0) {
            return field[i][j];
        } else return -1;
    }

    public int getScores(){
        return scores;
    }

    private void repaint(){
        panel.repaint();
    }
}
