package com.konieczny91.adam.snookercounter.logic;
import com.konieczny91.adam.snookercounter.logic.Enums.colors;
/**
 * Created by Adam on 23.09.2017.
 */

public class ColorBalls {

    private final int yellowPoints = 2;
    private final int greenPoints  = 3;
    private final int brownPoints  = 4;
    private final int bluePoints   = 5;
    private final int pinkPoints   = 6;
    private final int blackPoints  = 7;
    private final int maxPoints = 27;

    private boolean lastColorChosed = false;

    private colors currentColor;



    public ColorBalls() {}

    public int getColorPoints(colors ballColor, boolean lastRed, boolean freeBall)
    {
        int points = 0;

        switch (ballColor)
        {
            case YELLOW:

                points = yellowPoints;
                break;
            case GREEN:

                points = greenPoints;
                break;
            case BROWN:

                points = brownPoints;
                break;
            case BLUE:

                points = bluePoints;
                break;
            case PINK:

                points = pinkPoints;
                break;
            case BLACK:

                points = blackPoints;
                break;
        }

        if(freeBall)
        {
            points = 1;
        }


        if (lastRed)
        {
            lastColorChosed = true;
        }
        else
        {
            lastColorChosed = false;
        }

        currentColor = ballColor;

        return points;
    }

    public int getColorPoints(colors ballColor)
    {
        int points = 0;

        switch (ballColor)
        {
            case YELLOW:

                points = yellowPoints;
                break;
            case GREEN:

                points = greenPoints;
                break;
            case BROWN:

                points = brownPoints;
                break;
            case BLUE:

                points = bluePoints;
                break;
            case PINK:

                points = pinkPoints;
                break;
            case BLACK:

                points = blackPoints;
                break;
        }
        return points;
    }


    public void setCurrentColor(colors currentColor) {
        this.currentColor = currentColor;
    }

    public colors getCurrentColor() {
        return currentColor;
    }


    public boolean isLastColorChoosed() {
        return lastColorChosed;
    }

    public void setLastColorChoosed(boolean lastColorChosed) {
        this.lastColorChosed = lastColorChosed;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
