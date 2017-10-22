package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 05.10.2017.
 */

public class Foul {

    private boolean playerOneFoul = false;
    private final int fourPoints = 4;
    private final int fivePoints = 5;
    private final int sixPoints = 6;
    private final int sevenPoints = 7;

    public Foul()
    {}

    public int getFoulPoints(Enums.colors color)
    {
        int foulPoints = 0;

        switch (color)
        {
            case WHITE:
                foulPoints = fourPoints;
                break;
            case RED:
                foulPoints = fourPoints;
                break;
            case YELLOW:
                foulPoints = fourPoints;
                break;
            case GREEN:
                foulPoints = fourPoints;
                break;
            case BROWN:
                foulPoints = fourPoints;
                break;
            case BLUE:
                foulPoints = fivePoints;
                break;
            case PINK:
                foulPoints = sixPoints;
                break;
            case BLACK:
                foulPoints = sevenPoints;
                break;
        }

        return foulPoints;
    }


}
