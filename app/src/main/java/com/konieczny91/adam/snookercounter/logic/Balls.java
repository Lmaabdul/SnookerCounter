package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 01.10.2017.
 */

public class Balls {

    private int maxPoints;
    private RedBall redBall;
    private ColorBalls colorBalls;
    private int remainingPoints;

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

    public int calculateRemainingPoints(int points)
    {
        if(colorBalls.isLastColorChoosed())
        {
            remainingPoints -= points;
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
        }

        /* foul logic used 8 points to subtract from remaining points */
        if(points>=8)
        {
            remainingPoints -= points;
        }


        return remainingPoints;
    }

}
