package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 23.09.2017.
 */

public class RedBall {

    private final int points = 1;
    private int count;
    private boolean isPotted = false;

    public RedBall(int count)
    {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getPoints()
    {
        return points;
    }


    public void redBallPotted()
    {
        if (count>0) {
            count = count - 1;
        }else
        {
            count = 0;
        }

        isPotted = true;

    }
    public void undoRedBallPotted()
    {
        count+=1;
    }

    public boolean noMoreRedBalls()
    {
        if (count==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void removeRedBalls(int number)
    {
        count = count - number;
    }

    public boolean isPotted() {
        return isPotted;
    }

    public void setPotted(boolean potted) {
        isPotted = potted;
    }




}
