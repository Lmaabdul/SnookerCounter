package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 01.10.2017.
 */

public class Balls {

    private int maxPoints;
    private RedBall redBall;
    private ColorBalls colorBalls;
    private int remainingPoints;
    private boolean onlyColorOnTableCount = false;


    public Balls(RedBall redBall, ColorBalls colorBalls)
    {
        this.redBall = redBall;
        this.colorBalls = colorBalls;
    }

    public int maxPointsInFrame()
    {
        for (int i=1;i<=redBall.getCount();i++)
        {
            maxPoints += redBall.getPoints() + colorBalls.getColorPoints(Enums.colors.BLACK);
        }
        maxPoints += colorBalls.getMaxPoints();
        remainingPoints = maxPoints;
        return maxPoints;
    }

    public int calculateRemainigPoints(int points)
    {
        if(colorBalls.isLastColorChoosed())
        {
            if(onlyColorOnTableCount)
            {
                remainingPoints -= points;
            }
            else if(points>1 && points<=7)
            {
                remainingPoints -= colorBalls.getColorPoints(Enums.colors.BLACK);
            }
            onlyColorOnTableCount = true;
        }
        else
        {
            if(points==1)
            {
                remainingPoints -= redBall.getPoints();
            }
            else if(points>1 && points<=7)
            {
                remainingPoints -= colorBalls.getColorPoints(Enums.colors.BLACK);
            }
            else if(points>7) //for foul logic
            {
                remainingPoints -= points;
            }
        }

        return remainingPoints;
    }

    public void setOnlyColorOnTableCount(boolean onlyColorOnTableCount) {
        this.onlyColorOnTableCount = onlyColorOnTableCount;
    }
}
