package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Юлия on 10.02.2016.
 */
public class Generator {
    LinkedList<Type> bag;
    Random random;

    public Generator(){
        bag = new LinkedList<>();
       this.refresh();
    }

    public void refresh() {
        random = new Random();
        bag.clear();
        for (int i = 0; i < Type.getQount(); i++) {
            bag.add(Type.getType(i));
        }
    }

    public Type nextType(){
        if (bag.size()<=0) {
         this.refresh();
        }
        return this.getNextType();
    }

    public int nextNumber(){
        if (bag.size()<=0) {
            this.refresh();
        }
        return this.getNextumber();
    }

    private Type getNextType(){
        int weiht = bag.size();
        if (weiht>0){
            int cur = random.nextInt(weiht);
            Type type = bag.get(cur);
            bag.remove(cur);
            return type;
        }
        else System.out.println("Bag is empty my lord");
        return null;
    }

    private int getNextumber(){
        int weiht = bag.size();
        if (weiht>0){
            int cur = random.nextInt(weiht);
            Type type = bag.get(cur);
            bag.remove(cur);
            return type.getNumber();
        }
        else System.out.println("Bag is empty my lord");
        return -1;
    }
}
