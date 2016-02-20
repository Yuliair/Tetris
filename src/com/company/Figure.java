package com.company;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Юлия on 10.02.2016.
 */
public class Figure {
    private Type type;
    public int color;//0-6255255255
    private int condition,conditionMax;//kak povernuta

    private int piecesQount;//stepMax - count of squares which included in figure

    public Figure(){
    }

    public Figure( Type type){
        condition = 0;
        this.type=type;
        piecesQount = 4;
        if (type.getNumber()==0) conditionMax=1;
        else if(type.getNumber() < 4) conditionMax =2;
        else conditionMax = 4;
        this.setColor();
    }

    public void setCondition(int condition){
        this.condition = condition;
    }

    public int getCondition (){
        return condition;
    }

    public int getColor(){
        return color;
    }

    public void setColor(){
        Random random = new Random();
        color=0;
        int r = 240+random.nextInt(14);
        color = color+r*1000000;
        //int g = 255 - r + random.nextInt();
        int g = random.nextInt(254);
        color = color+g*1000;
        int b = random.nextInt(250);
        color = color +b;
        if (color < 1) color = 5;
        //if (color>255)
    }

    public XY getPiece(int i, XY xy){
        if (i >= 0 && i < piecesQount){
            switch (type){
                case SQUARE:
                    //  &*
                    //  **
                    switch (i){
                        case 0: return xy;
                        case 1: return new XY(xy.getX(), xy.getY()+1);
                        case 2: return new XY(xy.getX()+1, xy.getY()+1);
                        case 3: return new XY(xy.getX()+1, xy.getY());

                    }
                    //  *&
                    //   **
                case LIGHT1:
                    if (condition==0)
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()-1);
                            case 2: return new XY(xy.getX()+1, xy.getY());
                            case 3: return new XY(xy.getX()+1, xy.getY()+1);
                        }
                    //   &
                    //  **
                    //  *
                    else
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX()+1, xy.getY()-1);
                            case 2: return new XY(xy.getX()+1, xy.getY());
                            case 3: return new XY(xy.getX()+2, xy.getY()-1);
                        }

                case LIGHT2:
                    if (condition==0)
                        //  &*
                        // **
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()+1);
                            case 2: return new XY(xy.getX()+1, xy.getY()-1);
                            case 3: return new XY(xy.getX()+1, xy.getY());
                        }
                        //   &
                        //   **
                        //    *
                    else
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX()+1, xy.getY()+1);
                            case 2: return new XY(xy.getX()+1, xy.getY());
                            case 3: return new XY(xy.getX()+2, xy.getY()+1);
                        }
                case STICK:
                    if (condition == 0)
                        //**&*
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()+1);
                            case 2: return new XY(xy.getX(), xy.getY()-1);
                            case 3: return new XY(xy.getX(), xy.getY()-2);
                        }
                    else
                    //  *
                    //  &
                    //  *
                    //  *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX()-1, xy.getY());
                            case 2: return new XY(xy.getX()+1, xy.getY());
                            case 3: return new XY(xy.getX()+2, xy.getY());
                        }
                case HORSE1:
                    if (condition == 0)
                        //  *&*
                        //  *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()-1);
                            case 2: return new XY(xy.getX(), xy.getY()+1);
                            case 3: return new XY(xy.getX()+1, xy.getY()-1);
                        }
                    else if (condition == 1)
                        // &*
                        //  *
                        //  *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()+1);
                            case 2: return new XY(xy.getX()+1, xy.getY()+1);
                            case 3: return new XY(xy.getX()+2, xy.getY()+1);
                        }
                    else if (condition == 2)
                        //  (&)
                        //      *
                        // * *  *
                        switch (i){
                            case 0: return new XY(xy.getX()+1, xy.getY()+1);
                            case 1: return new XY(xy.getX()+2, xy.getY()+1);
                            case 2: return new XY(xy.getX()+2, xy.getY());
                            case 3: return new XY(xy.getX()+2, xy.getY()-1);
                        }
                    else
                        //  *(&)
                        //  *
                        //  **
                        switch (i){
                            case 0: return new XY(xy.getX(), xy.getY()-1);
                            case 1: return new XY(xy.getX()+1, xy.getY()-1);
                            case 2: return new XY(xy.getX()+2, xy.getY()-1);
                            case 3: return new XY(xy.getX()+2, xy.getY());
                        }
                case HORSE2:
                    if (condition == 0)
                        //  *&*
                        //    *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()-1);
                            case 2: return new XY(xy.getX(), xy.getY()+1);
                            case 3: return new XY(xy.getX()+1, xy.getY()+1);
                        }
                    else if (condition == 1)
                        //  *&
                        //  *
                        //  *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()-1);
                            case 2: return new XY(xy.getX()+1, xy.getY()-1);
                            case 3: return new XY(xy.getX()+2, xy.getY()-1);
                        }
                    else if (condition == 2)
                        //  (&)
                        // *
                        // * *  *
                        switch (i){
                            case 0: return new XY(xy.getX()+1, xy.getY()-1);
                            case 1: return new XY(xy.getX()+2, xy.getY()+1);
                            case 2: return new XY(xy.getX()+2, xy.getY());
                            case 3: return new XY(xy.getX()+2, xy.getY()-1);
                        }
                    else
                        //  (&)*
                        //     *
                        //   * *
                        switch (i){
                            case 0: return new XY(xy.getX(), xy.getY()+1);
                            case 1: return new XY(xy.getX()+1, xy.getY()+1);
                            case 2: return new XY(xy.getX()+2, xy.getY()+1);
                            case 3: return new XY(xy.getX()+2, xy.getY());
                        }
                case TRIANGLE:
                    if (condition == 0)
                        //  &
                        // ***
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX()+1, xy.getY()-1);
                            case 2: return new XY(xy.getX()+1, xy.getY());
                            case 3: return new XY(xy.getX()+1, xy.getY()+1);
                        }
                    else if (condition == 1)
                        //  &
                        //  **
                        //  *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX()+1, xy.getY());
                            case 2: return new XY(xy.getX()+1, xy.getY()+1);
                            case 3: return new XY(xy.getX()+2, xy.getY());
                        }
                    else if (condition == 2)
                        // *&*
                        //  *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX(), xy.getY()-1);
                            case 2: return new XY(xy.getX(), xy.getY()+1);
                            case 3: return new XY(xy.getX()+1, xy.getY());
                        }
                    else
                        //     &
                        //    **
                        //     *
                        switch (i){
                            case 0: return xy;
                            case 1: return new XY(xy.getX()+1, xy.getY());
                            case 2: return new XY(xy.getX()+1, xy.getY()-1);
                            case 3: return new XY(xy.getX()+2, xy.getY());
                        }
                default:  break;

            }
        }
        return null;

    }


    public Type getType(){
        return type;
    }


    public void turn(){
        condition++;
        condition = condition%conditionMax;
    }



}

/*

switch (type){
            case SQUARE:
            case LIGHT1:
                case LIGHT2:
            case HORSE1:
            case HORSE2:
            case STICK:
            case TRIANGLE:
 */

