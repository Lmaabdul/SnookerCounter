package com.konieczny91.adam.snookercounter.logic;

import java.util.ArrayList;

/**
 * Created by Adam on 25.11.2017.
 */




public class PlayerStatistics
{

    private int allShotsCount       = 0;
    private int pottedShotsCount    = 0;
    private int missedShotsCount    = 0;
    private int highestBreak        = 0;
    private int foulCount           = 0;
    private int safeCount           = 0;
    private int safeSuccessful      = 0;
    private ArrayList<Integer> imageBreak  = new ArrayList<>();

    public PlayerStatistics(){}


    public void setImageBreak(ArrayList<Integer> imageArray)
    {
        imageBreak = new ArrayList<>(imageArray);
    }

    public ArrayList<Integer> getImageBreak()
    {
        return imageBreak;
    }

    public int calcPottedShotsPercent()
    {
        int percent;
        if(allShotsCount==0) return 0;
        percent = Math.round(((float)pottedShotsCount/(float)allShotsCount)*100.0f);
        return percent;
    }
    public int calcSafeShotsPercent()
    {
        int percent;
        if(safeCount==0) return 0;
        percent = Math.round(((float)safeSuccessful/(float)safeCount)*100.0f);
        return percent;
    }

    public int calcPresence(int otherPlayerAllShots)
    {
        int percent;
        if(otherPlayerAllShots==0 && allShotsCount==0)  return 0;

        int shotsSum;

        shotsSum = allShotsCount + otherPlayerAllShots;

        percent = Math.round(((float)allShotsCount/(float)shotsSum)*100.0f);
        return percent;
    }

    public void safeSuccessful()
    {
        safeSuccessful++;
        safeCount++;
    }

    public void safeFailed()
    {
        safeCount++;
    }

    public void shotFouled()
    {
        foulCount++;
        allShotsCount++;
    }



    public void shotMissed()
    {
        missedShotsCount++;
        allShotsCount++;
    }

    public void shotPotted()
    {
        pottedShotsCount++;
        allShotsCount++;
    }

    public void undoMissed()
    {
        if(missedShotsCount>0) {
            missedShotsCount--;
        }
        if(allShotsCount>0) {
            allShotsCount--;
        }
    }

    public void undoPotted()
    {
        if(pottedShotsCount>0) {
            pottedShotsCount--;
        }
        if(allShotsCount>0) {
            allShotsCount--;
        }
    }

    public int getPottedShotsCount() {
        return pottedShotsCount;
    }

    public int getMissedShotsCount() {
        return missedShotsCount;
    }

    public int getFoulCount() {
        return foulCount;
    }

    public int getAllShotsCount() {
        return allShotsCount;
    }

    public int getSafeCount() {
        return safeCount;
    }

    public int getSafeSuccessful() {
        return safeSuccessful;
    }

    public int getSafeFailed()
    {
        return getSafeCount() - getSafeSuccessful();
    }
}
