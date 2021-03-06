package com.konieczny91.adam.snookercounter.logic;

/**
 * Created by Adam on 23.09.2017.
 */

public class GamePlayers {

    private Player one;
    private Player two;
    private boolean playerOneTurn = true;
    private boolean nextFrame = false;
    private boolean playerMissed = false;



    public GamePlayers(Player one, Player two)
    {
        this.one = one;
        this.two = two;
    }

    public void changePlayer()
    {
        playerOneTurn^=true;
    }

    public int addScore(int currentScore)
    {
        if (playerOneTurn)
        {
            one.addScore(currentScore);
        }
        else
        {
            two.addScore(currentScore);
        }

        return currentScore;
    }

    public Enums.winState frameWin(int maxFrames)
    {
        Enums.winState result = Enums.winState.NO_INFO;

        if (one.getScore()>two.getScore())
        {
            one.frameWin();
            result = Enums.winState.PLAYER_ONE_WIN_FRAME;
        }
        else if (one.getScore()<two.getScore())
        {
            two.frameWin();
            result = Enums.winState.PLAYER_TWO_WIN_FRAME;
        }
        else if (one.getScore()==two.getScore())
        {
            one.frameWin();
            two.frameWin();
            result = Enums.winState.PLAYERS_DRAW;
        }


        if(one.getFrames()+two.getFrames()== maxFrames || one.getFrames()>(float)maxFrames/2.0f || two.getFrames()>(float)maxFrames/2.0f)
        {
            if(result == Enums.winState.PLAYER_ONE_WIN_FRAME)
            {
                result = Enums.winState.PLAYER_ONE_WIN_MATCH;
            }
            else if(result == Enums.winState.PLAYER_TWO_WIN_FRAME)
            {
                result = Enums.winState.PLAYER_TWO_WIN_MATCH;
            }
        }

        nextFrame = true;
        return result;

    }

    public Enums.winState matchWin(int maxFrames)
    {
        Enums.winState result= Enums.winState.NO_INFO;

        if(one.getFrames()+two.getFrames()== maxFrames || one.getFrames()>(float)maxFrames/2.0f || two.getFrames()>(float)maxFrames/2.0f)
        {
            if (one.getFrames() > two.getFrames()) {
                result = Enums.winState.PLAYER_ONE_WIN_MATCH;
            } else if (one.getFrames() < two.getFrames()) {
                result = Enums.winState.PLAYER_TWO_WIN_MATCH;
            } else {
                result = Enums.winState.NO_INFO;
            }
        }

        return result;
    }


    public int getScore()
    {
        if (playerOneTurn)
        {
            return one.getScore();
        }
        else
        {
            return two.getScore();
        }
    }

    public int getFrame()
    {
        if (playerOneTurn)
        {
            return one.getFrames();
        }
        else
        {
            return two.getFrames();
        }
    }

    public void cleanScore()
    {
        one.setScore(0);
        two.setScore(0);
    }

    public String getFirstName()
    {
        if (playerOneTurn)
        {
            return one.getFirstName();
        }
        else
        {
            return two.getFirstName();
        }
    }

    public String getLastName()
    {
        if (playerOneTurn)
        {
            return one.getLastName();
        }
        else
        {
            return two.getLastName();
        }
    }



    public boolean isPlayerMissed() {
        return playerMissed;
    }

    public void setPlayerMissed(boolean playerMissed) {
        this.playerMissed = playerMissed;
    }

    public boolean isNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(boolean nextFrame) {
        this.nextFrame = nextFrame;
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneTurn(boolean playerOneTurn) {
        this.playerOneTurn = playerOneTurn;
    }
}
