package com.konieczny91.adam.snookercounter.logic.dialogs;

import java.util.ArrayList;

/**
 * Created by Adam on 19.11.2017.
 */

public class PreviousUiState
{

    private ArrayList<Integer> previousImagesArray;
    private int prevBreakScore;
    private int prevPlayerOneScore;
    private int prevPlayerTwoScore;
    private int prevRemainingPoints;
    private int prevButtonID;
    private int prevPositionAfterNoMoreReds;


    public PreviousUiState(int prevPositionAfterNoMoreReds,
                           int prevBreakScore,
                           int prevButtonID,
                           ArrayList previousImagesArray,
                           int prevPlayerOneScore,
                           int prevPlayerTwoScore,
                           int prevRemainingPoints)
    {
        this.prevPositionAfterNoMoreReds = prevPositionAfterNoMoreReds;
        this.prevBreakScore = prevBreakScore;
        this.prevButtonID = prevButtonID;
        this.previousImagesArray = new ArrayList<>(previousImagesArray);
        this.prevPlayerOneScore = prevPlayerOneScore;
        this.prevPlayerTwoScore = prevPlayerTwoScore;
        this.prevRemainingPoints = prevRemainingPoints;
    }


    public int getPrevPositionAfterNoMoreReds() {
        return prevPositionAfterNoMoreReds;
    }

    public ArrayList<Integer> getPreviousImagesArray() {
        return previousImagesArray;
    }

    public int getPrevPlayerOneScore() {
        return prevPlayerOneScore;
    }

    public int getPrevPlayerTwoScore() {
        return prevPlayerTwoScore;
    }

    public int getPrevRemainingPoints() {
        return prevRemainingPoints;
    }

    public int getPrevButtonID() {
        return prevButtonID;
    }

    public int getPrevBreakScore() {
        return prevBreakScore;
    }
}
