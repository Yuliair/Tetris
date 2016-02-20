package com.company;

/**
 * Created by Юлия on 16.02.2016.
 */
public class Timer extends Thread{
    Field field;
    Boolean tic_tac;
    public void setField(Field field){

        this.field = field;
    }
    public void run(){
        tic_tac = true;
        int i = 0;
        while (tic_tac){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                field.acting(0);
            } catch (Exception e) {
               System.out.println("Something happen");
                if(field.stillPlaying()){
                    System.out.println("Something BAD happen");
                }
                else{
                    System.out.println("Oh it's juxt game over");
                    tic_tac=false;
                }
            }

        }
    }
}
