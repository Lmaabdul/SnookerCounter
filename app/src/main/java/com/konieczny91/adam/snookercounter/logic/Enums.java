package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 29.09.2017.
 */

public  class Enums {

    public  enum colors
    {
            NO_COLOR,
            WHITE,
            RED,
            YELLOW,
            GREEN,
            BROWN,
            BLUE,
            PINK,
            BLACK
    }


    public enum winState
    {
        NO_INFO,
        PLAYER_ONE_WIN_FRAME,
        PLAYER_TWO_WIN_FRAME,
        PLAYERS_DRAW,
        PLAYER_ONE_WIN_MATCH,
        PLAYER_TWO_WIN_MATCH
    }


    public enum moreDialogButtonState
    {
        UNDO_BUTTON,
        EXIT_BUTTON,
        FORFEIT_BUTTON,
        MANY_REDS_BUTTON,
        NO_BUTTON
    }



}
