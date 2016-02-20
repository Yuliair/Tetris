package com.company;

/**
 * Created by Юлия on 10.02.2016.
 */

    public enum Type {
    SQUARE(0),
    LIGHT1(1),
    LIGHT2(2),
    STICK(3),
    HORSE1(4),
    HORSE2(5),
    TRIANGLE(6);

    private  static final int qount =7;
    private final int number;

    public static int getQount(){
        return qount;
    }


    Type(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }


    public static Type getType(int i) {
        switch (i) {
            case 0: return SQUARE;
            case 1: return LIGHT1;
            case 2: return LIGHT2;
            case 3: return STICK;
            case 4: return HORSE1;
            case 5: return HORSE2;
            case 6: return TRIANGLE;
            default: System.out.print("No such figure"); break;

        }
        return null;
    }
    }



