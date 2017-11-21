package com.konieczny91.adam.snookercounter.logic;

import java.util.ArrayList;

/**
 * Created by Adam on 30.10.2017.
 */


public class Record
{

    private String playerName;
    private String playerLastName;
    private ArrayList<Integer> imageArray;
    private int breakScore=0;


    public Record(String playerName, String playerLastName)
    {
        this.playerName = playerName;
        this.playerLastName = playerLastName;
        imageArray = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public int addPointsToBreak(int points)
    {
        return breakScore+=points;
    }

    public void addImage(int imageId)
    {
        imageArray.add(imageId);
    }

    public int getImageId(int index)
    {
        return imageArray.get(index);
    }

    public void clearImageArray()
    {
        imageArray.clear();
    }
    public ArrayList<Integer> getImageArray()
    {
        return imageArray;
    }

    public void swapImageArray(ArrayList<Integer> imageArray)
    {
        this.imageArray = new ArrayList<>(imageArray);
    }


    public int getImageArraySize()
    {
        return imageArray.size();
    }

    public int getBreakScore() {
        return breakScore;
    }

    public void setBreakScore(int breakScore) {
        this.breakScore = breakScore;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
