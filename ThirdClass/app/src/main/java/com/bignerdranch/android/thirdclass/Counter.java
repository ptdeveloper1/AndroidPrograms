package com.bignerdranch.android.thirdclass;

/**
 * Created by ADMIN on 6/13/2017.
 */

public class Counter {
    private int count;
    Counter(){
        count=0;
    }
    public void update(){
        count++;
    }
    public int getCount(){
        return count;
    }
    /*public void reset(){
    count=0;
    }
     */
}
