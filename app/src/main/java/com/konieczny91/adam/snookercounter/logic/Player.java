package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 21.08.2017.
 */

public class Player {

    private String firstName;
    private String lastName;
    private int score=0;
    private int frames=0;
    private int id = -1;

    public Player(String firstName,String lastName)
    {
        this.firstName  = firstName;
        this.lastName   = lastName;
    }

    public Player(int id, String firstName,String lastName)
    {
        this.id         = id;
        this.firstName  = firstName;
        this.lastName   = lastName;
    }


    public String getFirstName()     {return firstName;}
    public String getLastName()     {return lastName;}

    public int getFrames() {return frames;}
    public int getScore() {return score;}

    public void frameWin()
    {
        frames+=1;
    }

    public void addScore(int newPoints)
    {
        score = score + newPoints;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId(){ return id;}
}
