package com.konieczny91.adam.snookercounter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.konieczny91.adam.snookercounter.logic.Balls;
import com.konieczny91.adam.snookercounter.logic.ColorBalls;
import com.konieczny91.adam.snookercounter.logic.Enums.colors;
import com.konieczny91.adam.snookercounter.logic.Foul;
import com.konieczny91.adam.snookercounter.logic.FoulDialog;
import com.konieczny91.adam.snookercounter.logic.GamePlayers;
import com.konieczny91.adam.snookercounter.logic.Player;
import com.konieczny91.adam.snookercounter.logic.RedBall;

import static com.konieczny91.adam.snookercounter.logic.Enums.colors.BLACK;
import static com.konieczny91.adam.snookercounter.logic.Enums.colors.BROWN;
import static com.konieczny91.adam.snookercounter.logic.Enums.colors.NO_COLOR;
import static com.konieczny91.adam.snookercounter.logic.Enums.colors.PINK;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener, FoulDialog.FoulDialogListener{


    int reds;
    int frames;

    String playerOneFN;
    String playerTwoFN;
    String playerOneLN;
    String playerTwoLN;

    Button missButton;
    Button foulButton;
    Button moreButton;

    ImageButton redButton;
    ImageButton yellowButton;
    ImageButton greenButton;
    ImageButton brownButton;
    ImageButton blueButton;
    ImageButton pinkButton;
    ImageButton blackButton;

    TextView playerOneView;
    TextView playerTwoView;
    TextView playerOneFramesView;
    TextView playerTwoFramesView;
    TextView framesCountView;
    TextView playerOneScoreView;
    TextView playerTwoScoreView;
    TextView remainingPointsView;
    TextView remainingPointsDescriptionView;

    Typeface retroFont;

    RedBall redBall;
    ColorBalls colorBalls;
    Balls balls;

    GamePlayers gamePlayers;
    Player playerOne;
    Player playerTwo;
    Foul foul;

    colors currentColorPotted;
    int positionAfterNoMoreReds = 0;
    int currentScoredPoints;


    boolean playerOneStarts = false;
    boolean isFreeBall = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        initViews();
        initButtonsListeners();
        customFontTexts();
        if(bundle!=null)
        {
            playerOneFN = (String) bundle.get("playerOneFirstName");
            playerOneLN = (String) bundle.get("playerOneLastName");

            playerTwoFN = (String) bundle.get("playerTwoFirstName");
            playerTwoLN = (String) bundle.get("playerTwoLastName");

            reds = (int) bundle.get("reds");
            frames = (int) bundle.get("frames");
        }
        initPlayers();
        dialogWhoStarts();
    }

    @Override
    public void onFinishFoulDialog(int numberOfReds, int foulPoints, boolean repeatFoul, boolean freeBall)
    {
        boolean playerOneFouled = gamePlayers.isPlayerOneTurn();
        isFreeBall = freeBall;
        //give foul points to the player
        if (playerOneFouled)
        {
            playerTwo.addScore(foulPoints);
        }
        else
        {
            playerOne.addScore(foulPoints);
        }

        //remove fouled reds
        redBall.removeRedBalls(numberOfReds);

        //update score
        playerOneScoreView.setText(String.valueOf(playerOne.getScore()));
        playerTwoScoreView.setText(String.valueOf(playerTwo.getScore()));

        //update player active
        if (repeatFoul)
        {
            if(playerOneFouled)
            {
                playerOneView.setTextColor(ContextCompat.getColor(this, R.color.black));
                playerTwoView.setTextColor(ContextCompat.getColor(this, R.color.buttonBackgroundColor));
                gamePlayers.setPlayerOneTurn(true);
            }
            else
            {
                playerTwoView.setTextColor(ContextCompat.getColor(this,R.color.black));
                playerOneView.setTextColor(ContextCompat.getColor(this,R.color.buttonBackgroundColor));
                gamePlayers.setPlayerOneTurn(false);
            }
        }
        else
        {
            if(playerOneFouled)
            {
                playerTwoView.setTextColor(ContextCompat.getColor(this,R.color.black));
                playerOneView.setTextColor(ContextCompat.getColor(this,R.color.buttonBackgroundColor));
                gamePlayers.setPlayerOneTurn(false);
            }
            else
            {
                playerOneView.setTextColor(ContextCompat.getColor(this, R.color.black));
                playerTwoView.setTextColor(ContextCompat.getColor(this, R.color.buttonBackgroundColor));
                gamePlayers.setPlayerOneTurn(true);
            }
        }




        //calculate remaining points when reds are discarded
        remainingPointsView.setText(String.valueOf(balls.calculateRemainigPoints(8*numberOfReds)));

        //change to only red display when fouled player is not trying again
        if (!repeatFoul && !freeBall)
        {
            toggleColorButtons(false);
            redButton.setEnabled(true);
            redButton.setImageResource(R.drawable.redselector);
            if(redBall.isPotted()) //remove 7 points when red ball was potted before foul
            {
                remainingPointsView.setText(String.valueOf(balls.calculateRemainigPoints(colorBalls.getColorPoints(BLACK))));
            }
            redBall.setPotted(false);
        }

        if (freeBall)
        {
            Toast.makeText(getApplicationContext(),"Any color ball worth 1 point",Toast.LENGTH_SHORT).show();
            toggleColorButtons(true);
            redButton.setEnabled(false);
            redButton.setImageResource(R.drawable.disabledball);
            if(redBall.isPotted()) //remove 7 points when red ball was potted before foul
            {
                remainingPointsView.setText(String.valueOf(balls.calculateRemainigPoints(colorBalls.getColorPoints(BLACK))));
            }
            redBall.setPotted(true);
        }


    }

    @Override
    public void onClick(View v)
    {
        colorBalls.setCurrentColor(NO_COLOR);

        switch (v.getId())
        {
            case R.id.red_ball_button:

                currentScoredPoints = gamePlayers.addScore(redBall.getPoints());
                redBall.redBallPotted();
                break;

            case R.id.yellow_ball_button:

                currentScoredPoints = gamePlayers.addScore(colorBalls.getColorPoints(colors.YELLOW,redBall.noMoreRedBalls(),isFreeBall));
                if (!isFreeBall) redBall.setPotted(false);
                break;

            case R.id.green_ball_button:

                currentScoredPoints = gamePlayers.addScore(colorBalls.getColorPoints(colors.GREEN,redBall.noMoreRedBalls(),isFreeBall));
                if (!isFreeBall) redBall.setPotted(false);
                break;

            case R.id.brown_ball_button:

                currentScoredPoints = gamePlayers.addScore(colorBalls.getColorPoints(BROWN,redBall.noMoreRedBalls(),isFreeBall));
                if (!isFreeBall) redBall.setPotted(false);
                break;

            case R.id.blue_ball_button:

                currentScoredPoints = gamePlayers.addScore(colorBalls.getColorPoints(colors.BLUE,redBall.noMoreRedBalls(),isFreeBall));
                if (!isFreeBall) redBall.setPotted(false);
                break;

            case R.id.pink_ball_button:

                currentScoredPoints = gamePlayers.addScore(colorBalls.getColorPoints(PINK,redBall.noMoreRedBalls(),isFreeBall));
                if (!isFreeBall) redBall.setPotted(false);
                break;

            case R.id.black_ball_button:

                currentScoredPoints = gamePlayers.addScore(colorBalls.getColorPoints(BLACK,redBall.noMoreRedBalls(),isFreeBall));
                if (!isFreeBall) redBall.setPotted(false);
                break;

            case R.id.miss_button:

                /** if next frame then do nothing */
                if(gamePlayers.isNextFrame())
                {
                    break;
                }

                /** clear current score points do not update score with points before */
                currentScoredPoints = 0;

                /** if player didn't pot a color after red the points are lost so update score with 7 */
                if(redBall.isPotted() && !colorBalls.isLastColorChoosed()) currentScoredPoints = 7;

                /** if player pot last red and didn't pot color set the UI to only yellow on table*/
                if(redBall.noMoreRedBalls() && !colorBalls.isLastColorChoosed())
                {
                    toggleColorButtons(false);
                    yellowButton.setEnabled(true);
                    yellowButton.setImageResource(R.drawable.yellowselector);
                    colorBalls.setLastColorChoosed(true);
                    positionAfterNoMoreReds=1;
                }

                gamePlayers.changePlayer();
                gamePlayers.setPlayerMissed(true);
                redBall.setPotted(false);
                break;

            case R.id.foul_button:

                /** show foul dialog */
                FragmentManager manager = getSupportFragmentManager();
                FoulDialog dialog = FoulDialog.newInstance(reds);
                dialog.show(manager,"TAG");
                break;
        }

        /** update UI section */

        if (redBall.noMoreRedBalls() && colorBalls.isLastColorChoosed())
        {
            updateUIOnlyColorBalls(v);
        }
        else
        {
            updateUIAllBalls(v);
        }

     //   updateUI(v);

        /** ---------------------------------------------*/
    }

    private void updateUIAllBalls(View v)
    {

        int currentButton = v.getId();

        /** dont update UI when foul button was pushed */
        if (currentButton == R.id.foul_button) return;

        /** update UI when active player is changed */
        changePlayers();

        /** update remaining points or not when free ball is set -> free ball points are additional points */
        if(!isFreeBall) remainingPointsView.setText(String.valueOf(balls.calculateRemainigPoints(currentScoredPoints)));

        if (redBall.isPotted())
        {
            toggleColorButtons(true);
            redButton.setEnabled(false);
            redButton.setImageResource(R.drawable.disabledball);
        }
        else
        {
            toggleColorButtons(false);
            redButton.setEnabled(true);
            redButton.setImageResource(R.drawable.redselector);
        }

        /** clear state flags*/
        gamePlayers.setPlayerMissed(false);
        isFreeBall = false;
    }

    private void updateUIOnlyColorBalls(View v)
    {
        int currentButton = v.getId();

        /** dont update UI when foul button was pushed */
        if (currentButton == R.id.foul_button) return;

        /** update UI when active player is changed */
        changePlayers();

        /** update remaining points or not when free ball is set -> free ball points are additional points */
        if(!isFreeBall) remainingPointsView.setText(String.valueOf(balls.calculateRemainigPoints(currentScoredPoints)));

        redButton.setEnabled(false);
        redButton.setImageResource(R.drawable.disabledball);

        /** leave the current state of UI after player missed */
        if (gamePlayers.isPlayerMissed())
        {
            gamePlayers.setPlayerMissed(false);
            return;
        }

        toggleColorButtons(false);

        switch (positionAfterNoMoreReds)
        {
            case 0:
                yellowButton.setEnabled(true);
                yellowButton.setImageResource(R.drawable.yellowselector);
                break;
            case 1:
                greenButton.setEnabled(true);
                greenButton.setImageResource(R.drawable.greenselector);
                break;
            case 2:
                brownButton.setEnabled(true);
                brownButton.setImageResource(R.drawable.brownselector);
                break;
            case 3:
                blueButton.setEnabled(true);
                blueButton.setImageResource(R.drawable.blueselector);
                break;
            case 4:
                pinkButton.setEnabled(true);
                pinkButton.setImageResource(R.drawable.pinkselector);
                break;
            case 5:
                blackButton.setEnabled(true);
                blackButton.setImageResource(R.drawable.blackselector);
                break;
            case 6:
                gamePlayers.frameWin();
                break;


        }
        positionAfterNoMoreReds++;

        /** change miss button text to "next" */
        if (gamePlayers.isNextFrame())
        {
            missButton.setText(R.string.next);
            foulButton.setEnabled(false);
            moreButton.setEnabled(false);
        }

        /** if player pushed miss button (next button) than set new frame */
        if(gamePlayers.isNextFrame() && currentButton == R.id.miss_button)
        {
            playerOneStarts = !playerOneStarts;
            setFrame();
        }

        /** clear state flags*/
        gamePlayers.setPlayerMissed(false);
        isFreeBall = false;
    }

    private void toggleColorButtons(boolean toggle )
    {

        if(toggle)
        {
            yellowButton.setEnabled(true);
            yellowButton.setImageResource(R.drawable.yellowselector);
            greenButton.setEnabled(true);
            greenButton.setImageResource(R.drawable.greenselector);
            brownButton.setEnabled(true);
            brownButton.setImageResource(R.drawable.brownselector);
            blueButton.setEnabled(true);
            blueButton.setImageResource(R.drawable.blueselector);
            pinkButton.setEnabled(true);
            pinkButton.setImageResource(R.drawable.pinkselector);
            blackButton.setEnabled(true);
            blackButton.setImageResource(R.drawable.blackselector);

        }
        else
        {
            yellowButton.setEnabled(false);
            yellowButton.setImageResource(R.drawable.disabledball);
            greenButton.setEnabled(false);
            greenButton.setImageResource(R.drawable.disabledball);
            brownButton.setEnabled(false);
            brownButton.setImageResource(R.drawable.disabledball);
            blueButton.setEnabled(false);
            blueButton.setImageResource(R.drawable.disabledball);
            pinkButton.setEnabled(false);
            pinkButton.setImageResource(R.drawable.disabledball);
            blackButton.setEnabled(false);
            blackButton.setImageResource(R.drawable.disabledball);
        }
    }

    private void initButtonsListeners()
    {
        redButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        brownButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        pinkButton.setOnClickListener(this);
        blackButton.setOnClickListener(this);
        missButton.setOnClickListener(this);
        foulButton.setOnClickListener(this);
    }

    private void setFrame()
    {
        positionAfterNoMoreReds = 0;
        playerOneView.setText(playerOne.getFirstName() + " " + playerOne.getLastName().substring(0,1) + "." );
        playerTwoView.setText(playerTwo.getFirstName() + " " + playerTwo.getLastName().substring(0,1) + "." );
        framesCountView.setText("( "+String.valueOf(frames)+" )");
        playerOneFramesView.setText(String.valueOf(playerOne.getFrames()));
        playerTwoFramesView.setText(String.valueOf(playerTwo.getFrames()));
        redBall = new RedBall(reds);
        colorBalls = new ColorBalls();
        balls = new Balls(redBall,colorBalls);
        foul = new Foul();
        remainingPointsView.setText(String.valueOf(balls.maxPointsInFrame()));
        remainingPointsDescriptionView.setText(getString(R.string.remainingPoints));
        gamePlayers.cleanScore();
        playerOneScoreView.setText(String.valueOf(playerOne.getScore()));
        playerTwoScoreView.setText(String.valueOf(playerTwo.getScore()));
        gamePlayers.setNextFrame(false);
        missButton.setText(R.string.miss);
        foulButton.setEnabled(true);
        moreButton.setEnabled(true);


        gamePlayers.setPlayerOneTurn(playerOneStarts);

        /** update number of frames */
        framesCountView.setText("( " + String.valueOf(frames) + " )");

        if (playerOneStarts)
        {
            playerOneView.setTextColor(ContextCompat.getColor(this,R.color.black));
            playerTwoView.setTextColor(ContextCompat.getColor(this,R.color.buttonBackgroundColor));
        }
        else
        {
            playerTwoView.setTextColor(ContextCompat.getColor(this,R.color.black));
            playerOneView.setTextColor(ContextCompat.getColor(this,R.color.buttonBackgroundColor));
        }

        if (redBall.noMoreRedBalls())
        {
            redBall.isPotted();
            colorBalls.setLastColorChoosed(true);
            balls.setOnlyColorOnTableCount(true);
            onlyColorsOnTable();
        }
        else
        {
            redButton.setEnabled(true);
            redButton.setImageResource(R.drawable.redselector);
            toggleColorButtons(false);
        }
    }

    private void initViews()
    {
        missButton = (Button) findViewById(R.id.miss_button);
        foulButton = (Button) findViewById(R.id.foul_button);
        moreButton = (Button) findViewById(R.id.more_button);

        redButton       = (ImageButton) findViewById(R.id.red_ball_button);
        yellowButton    = (ImageButton) findViewById(R.id.yellow_ball_button);;
        greenButton     = (ImageButton) findViewById(R.id.green_ball_button);;
        brownButton     = (ImageButton) findViewById(R.id.brown_ball_button);;
        blueButton      = (ImageButton) findViewById(R.id.blue_ball_button);;
        pinkButton      = (ImageButton) findViewById(R.id.pink_ball_button);;
        blackButton     = (ImageButton) findViewById(R.id.black_ball_button);;


        playerOneView       = (TextView) findViewById(R.id.player_one);
        playerTwoView       = (TextView) findViewById(R.id.player_two);
        playerOneFramesView = (TextView) findViewById(R.id.player_one_frames);
        playerTwoFramesView = (TextView) findViewById(R.id.player_two_frames);
        framesCountView     = (TextView) findViewById(R.id.frames_count);
        playerOneScoreView  = (TextView) findViewById(R.id.player_one_score);
        playerTwoScoreView  = (TextView) findViewById(R.id.player_two_score);
        remainingPointsView = (TextView) findViewById(R.id.remaining_points);
        remainingPointsDescriptionView = (TextView) findViewById(R.id.remaining_points_text);
    }

    private void customFontTexts()
    {
        retroFont = Typeface.createFromAsset(getAssets(), "fonts/8-BIT WONDER.ttf");
        missButton.setTypeface(retroFont);
        foulButton.setTypeface(retroFont);
        moreButton.setTypeface(retroFont);
        playerOneView.setTypeface(retroFont);
        playerTwoView.setTypeface(retroFont);
        playerOneFramesView.setTypeface(retroFont);
        playerTwoFramesView.setTypeface(retroFont);
        framesCountView.setTypeface(retroFont);
        playerOneScoreView.setTypeface(retroFont);
        playerTwoScoreView.setTypeface(retroFont);
        remainingPointsView.setTypeface(retroFont);
        remainingPointsDescriptionView.setTypeface(retroFont);
    }

    private void initPlayers()
    {
        playerOne = new Player(playerOneFN,playerOneLN);
        playerTwo = new Player(playerTwoFN,playerTwoFN);
        gamePlayers = new GamePlayers(playerOne,playerTwo);
    }

    private void dialogWhoStarts()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_choose_player_first);

        final TextView textView = (TextView) dialog.findViewById(R.id.dialog_text_who_starts);
        textView.setText(R.string.whoStarts);
        textView.setTypeface(retroFont);

        final Button playerOneButton = (Button) dialog.findViewById(R.id.dialog_player_one);
        final Button playerTwoButton = (Button) dialog.findViewById(R.id.dialog_player_two);

        playerOneButton.setText(R.string.playerOne);
        playerTwoButton.setText(R.string.playerTwo);

        playerOneButton.setTypeface(retroFont);
        playerTwoButton.setTypeface(retroFont);


        playerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneStarts = true;
                dialog.dismiss();
                setFrame();
            }
        });

        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneStarts = false;
                dialog.dismiss();
                setFrame();
            }
        });

        dialog.show();
    }

    private void changePlayers()
    {
        if (gamePlayers.isPlayerOneTurn())
        {
            playerOneScoreView.setText(String.valueOf(gamePlayers.getScore()));
            playerOneFramesView.setText(String.valueOf(gamePlayers.getFrame()));
            playerOneView.setTextColor(ContextCompat.getColor(this,R.color.black));
            playerTwoView.setTextColor(ContextCompat.getColor(this,R.color.buttonBackgroundColor));
        }
        else
        {
            playerTwoScoreView.setText(String.valueOf(gamePlayers.getScore()));
            playerTwoFramesView.setText(String.valueOf(gamePlayers.getFrame()));
            playerTwoView.setTextColor(ContextCompat.getColor(this,R.color.black));
            playerOneView.setTextColor(ContextCompat.getColor(this,R.color.buttonBackgroundColor));
        }
    }
    private void onlyColorsOnTable()
    {
        if (gamePlayers.isPlayerMissed()) return;

        redButton.setEnabled(false);
        redButton.setImageResource(R.drawable.disabledball);

        toggleColorButtons(false);
        switch (positionAfterNoMoreReds)
        {
            case 0:
                yellowButton.setEnabled(true);
                yellowButton.setImageResource(R.drawable.yellowselector);
                break;
            case 1:
                greenButton.setEnabled(true);
                greenButton.setImageResource(R.drawable.greenselector);
                break;
            case 2:
                brownButton.setEnabled(true);
                brownButton.setImageResource(R.drawable.brownselector);
                break;
            case 3:
                blueButton.setEnabled(true);
                blueButton.setImageResource(R.drawable.blueselector);
                break;
            case 4:
                pinkButton.setEnabled(true);
                pinkButton.setImageResource(R.drawable.pinkselector);
                break;
            case 5:
                blackButton.setEnabled(true);
                blackButton.setImageResource(R.drawable.blackselector);
                break;
            case 6:
                gamePlayers.frameWin();
                break;


        }
        positionAfterNoMoreReds++;
    }

    private void updateUI(View v)
    {
        int currentButton = v.getId();

        /** dont update UI when foul button was pushed */
        if (currentButton == R.id.foul_button) return;

        /** update UI when active player is changed */
        changePlayers();

        /** update remaining points or not when free ball is set -> free ball points are additional points */
        if(!isFreeBall) remainingPointsView.setText(String.valueOf(balls.calculateRemainigPoints(currentScoredPoints)));

        /** update Balls */
        if (redBall.noMoreRedBalls() && colorBalls.isLastColorChoosed())
        {
            //  onlyColorsOnTable();
        }
        else
        {
            if (redBall.isPotted())
            {
                toggleColorButtons(true);
            }
            else
            {
                toggleColorButtons(false);
            }
        }

        if (redBall.noMoreRedBalls() || redBall.isPotted())
        {
            redButton.setEnabled(false);
            redButton.setImageResource(R.drawable.disabledball);
        }
        else
        {
            redButton.setEnabled(true);
            redButton.setImageResource(R.drawable.redselector);
        }


        /** change miss button text to "next" */
        if (gamePlayers.isNextFrame())
        {
            missButton.setText(R.string.next);
            foulButton.setEnabled(false);
            moreButton.setEnabled(false);
        }

        /** if player pushed miss button (next button) than set new frame */
        if(gamePlayers.isNextFrame() && currentButton == R.id.miss_button)
        {
            playerOneStarts = !playerOneStarts;
            setFrame();
        }

        /** clear state flags*/
        gamePlayers.setPlayerMissed(false);
        isFreeBall = false;
    }

}
