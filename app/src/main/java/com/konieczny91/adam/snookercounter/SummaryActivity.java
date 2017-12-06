package com.konieczny91.adam.snookercounter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity
{

    int BALL_SIZE_DP = 16;

    TextView winnerView;
    TextView winnerNameView;
    TextView frameScoreView;

    TextView playersNamesView;
    TextView playerOneNameView;
    TextView playerTwoNameView;

    TextView allShotsView;
    TextView playerOneAllShotsView;
    TextView playerTwoAllShotsView;

    TextView shotsSuccessfulView;
    TextView playerOneShotsView;
    TextView playerTwoShotsView;

    TextView shotsMissedView;
    TextView playerOneShotsMissedView;
    TextView playerTwoShotsMissedView;


    TextView shotsSuccessfulPercentView;
    TextView playerOneShotsPercentView;
    TextView playerTwoShotsPercentView;

    TextView presenceView;
    TextView playerOnePresenceView;
    TextView playerTwoPresenceView;

    TextView highestBreakView;
    TextView playerOneHighestBreakView;
    TextView playerTwoHighestBreakView;

    TextView averageBreakView;
    TextView playerOneAverageBreakView;
    TextView playerTwoAverageBreakView;

    TextView allSafeShotsView;
    TextView playerOneAllSafeShotsView;
    TextView playerTwoAllSafeShotsView;

    TextView safeShotsSuccessfulView;
    TextView playerOneSafeShotsView;
    TextView playerTwoSafeShotsView;

    TextView safeShotsFailedView;
    TextView playerOneSafeShotsFailedView;
    TextView playerTwoSafeShotsFailedView;

    TextView safeShotsPercentView;
    TextView playerOneSafeShotsPercentView;
    TextView playerTwoSafeShotsPercentView;



    TextView foulCountView;
    TextView playerOneFoulCountView;
    TextView playerTwoFoulCountView;

    Button exitButton;

    int playerOneAllShots;
    int playerTwoAllShots;

    int playerOneShotsPotted;
    int playerTwoShotsPotted;

    int playerOneShotsMissed;
    int playerTwoShotsMissed;

    int playerOneShotsPottedPercent;
    int playerTwoShotsPottedPercent;

    int playerOnePresence;
    int playerTwoPresence;

    int playerOneBreak;
    int playerTwoBreak;

    int playerOneAverageBreak;
    int playerTwoAverageBreak;

    int playerOneAllSafeShots;
    int playerTwoAllSafeShots;

    int playerOneSafeShotsSuccessful;
    int playerTwoSafeShotsSuccessful;

    int playerOneSafeShotsFailed;
    int playerTwoSafeShotsFailed;

    int playerOneSafeShotsPercent;
    int playerTwoSafeShotsPercent;


    int playerOneFoulCount;
    int playerTwoFoulCount;
    int playerOneFrames;
    int playerTwoFrames;


    String playerOneFirstName;
    String playerOneLastName;
    String playerTwoFirstName;
    String playerTwoLastName;

    ArrayList<Integer> playerOneImageBreak = new ArrayList<>();
    ArrayList<Integer> playerTwoImageBreak = new ArrayList<>();



    Typeface retroFont;

    LinearLayout breakImageContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        retroFont = Typeface.createFromAsset(getAssets(),"fonts/8-BIT WONDER.ttf");
        initViews();
        getData();
        showResult();
    }

    public void exitClick(View v)
    {
        Intent goBackIntent = new Intent(SummaryActivity.this,StartActivity.class);
        startActivity(goBackIntent);
        finish();
    }

    private void getData()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            playerOneFrames                 = bundle.getInt("playerOneFrames");
            playerTwoFrames                 = bundle.getInt("playerTwoFrames");

            playerOneFirstName              = bundle.getString("playerOneFirstName");
            playerOneLastName               = bundle.getString("playerOneLastName");
            playerTwoFirstName              = bundle.getString("playerTwoFirstName");
            playerTwoLastName               = bundle.getString("playerTwoLastName");

            playerOneAllShots               = bundle.getInt("playerOneAllShots");
            playerTwoAllShots               = bundle.getInt("playerTwoAllShots");

            playerOneShotsPotted            = bundle.getInt("playerOnePottedShots");
            playerTwoShotsPotted            = bundle.getInt("playerTwoPottedShots");

            playerOneShotsMissed            = bundle.getInt("playerOneMissedShots");
            playerTwoShotsMissed            = bundle.getInt("playerTwoMissedShots");

            playerOneShotsPottedPercent     = bundle.getInt("playerOnePottedShotsPercent");
            playerTwoShotsPottedPercent     = bundle.getInt("playerTwoPottedShotsPercent");

            playerOnePresence               = bundle.getInt("playerOnePresence");
            playerTwoPresence               = bundle.getInt("playerTwoPresence");

            playerOneBreak                  = bundle.getInt("playerOneBreak");
            playerTwoBreak                  = bundle.getInt("playerTwoBreak");

            playerOneAverageBreak           = bundle.getInt("playerOneAverageBreak");
            playerTwoAverageBreak           = bundle.getInt("playerTwoAverageBreak");

            playerOneAllSafeShots           = bundle.getInt("playerOneAllSafeShots");
            playerTwoAllSafeShots           = bundle.getInt("playerTwoAllSafeShots");

            playerOneSafeShotsSuccessful    = bundle.getInt("playerOneSafeShotSuccessful");
            playerTwoSafeShotsSuccessful    = bundle.getInt("playerTwoSafeShotSuccessful");

            playerOneSafeShotsFailed        = bundle.getInt("playerOneSafeShotFailed");
            playerTwoSafeShotsFailed        = bundle.getInt("playerTwoSafeShotFailed");


            playerOneSafeShotsPercent       = bundle.getInt("playerOneSafeShotsPercent");
            playerTwoSafeShotsPercent       = bundle.getInt("playerTwoSafeShotsPercent");

            playerOneFoulCount              = bundle.getInt("playerOneFouls");
            playerTwoFoulCount              = bundle.getInt("playerTwoFouls");

            playerOneImageBreak             = bundle.getIntegerArrayList("playerOneImageBreak");
            playerTwoImageBreak             = bundle.getIntegerArrayList("playerTwoImageBreak");
        }
    }




    private void initViews()
    {
        winnerView                  = (TextView) findViewById(R.id.summary_winner_text_view);
        winnerNameView              = (TextView) findViewById(R.id.summary_winner_name_text_view);
        frameScoreView              = (TextView) findViewById(R.id.summary_frames_text_view);

        playerOneNameView           = (TextView) findViewById(R.id.summary_player_one_name);
        playerTwoNameView           = (TextView) findViewById(R.id.summary_player_two_name);
        playersNamesView            = (TextView) findViewById(R.id.summary_players_names);

        allShotsView                = (TextView) findViewById(R.id.summary_all_shots);
        playerOneAllShotsView       = (TextView) findViewById(R.id.summary_player_one_all_shots_view);
        playerTwoAllShotsView       = (TextView) findViewById(R.id.summary_player_two_all_shots_view);


        shotsSuccessfulView         = (TextView) findViewById(R.id.summary_shots_potted);
        playerOneShotsView          = (TextView) findViewById(R.id.summary_player_one_potted_shots_view);
        playerTwoShotsView          = (TextView) findViewById(R.id.summary_player_two_potted_shots_view);

        shotsMissedView             = (TextView) findViewById(R.id.summary_shots_missed);
        playerOneShotsMissedView    = (TextView) findViewById(R.id.summary_player_one_missed_shots_view);
        playerTwoShotsMissedView    = (TextView) findViewById(R.id.summary_player_two_missed_shots_view);

        shotsSuccessfulPercentView  = (TextView) findViewById(R.id.summary_shots_successful_percent);
        playerOneShotsPercentView   = (TextView) findViewById(R.id.summary_player_one_successful_shots_percent_view);
        playerTwoShotsPercentView   = (TextView) findViewById(R.id.summary_player_two_successful_shots_percent_view);

        presenceView                = (TextView) findViewById(R.id.summary_presence_at_the_table);
        playerOnePresenceView       = (TextView) findViewById(R.id.summary_player_one_presence);
        playerTwoPresenceView       = (TextView) findViewById(R.id.summary_player_two_presence);

        highestBreakView            = (TextView) findViewById(R.id.summary_highest_break);
        playerOneHighestBreakView   = (TextView) findViewById(R.id.summary_player_one_break_score);
        playerTwoHighestBreakView   = (TextView) findViewById(R.id.summary_player_two_break_score);

        averageBreakView            = (TextView) findViewById(R.id.summary_average_break);
        playerOneAverageBreakView   = (TextView) findViewById(R.id.summary_player_one_average_break);
        playerTwoAverageBreakView   = (TextView) findViewById(R.id.summary_player_two_average_break);

        allSafeShotsView            = (TextView) findViewById(R.id.summary_all_safe_shots);
        playerOneAllSafeShotsView   = (TextView) findViewById(R.id.summary_player_one_all_safe_shots_view);
        playerTwoAllSafeShotsView   = (TextView) findViewById(R.id.summary_player_two_all_safe_shots_view);

        safeShotsSuccessfulView     = (TextView) findViewById(R.id.summary_safe_shots_successful);
        playerOneSafeShotsView      = (TextView) findViewById(R.id.summary_player_one_safe_successful_shots_view);
        playerTwoSafeShotsView      = (TextView) findViewById(R.id.summary_player_two_safe_successful_shots_view);

        safeShotsFailedView             = (TextView) findViewById(R.id.summary_safe_shots_failed);
        playerOneSafeShotsFailedView    = (TextView) findViewById(R.id.summary_player_one_safe_failed_shots_view);
        playerTwoSafeShotsFailedView    = (TextView) findViewById(R.id.summary_player_two_safe_failed_shots_view);

        safeShotsPercentView            = (TextView) findViewById(R.id.summary_safe_shots_percent);
        playerOneSafeShotsPercentView   = (TextView) findViewById(R.id.summary_player_one_safe_percent_shots_view);
        playerTwoSafeShotsPercentView   = (TextView) findViewById(R.id.summary_player_two_safe_percent_shots_view);



        foulCountView               = (TextView) findViewById(R.id.summary_foul_count);
        playerOneFoulCountView      = (TextView) findViewById(R.id.summary_player_one_foul_count_view);
        playerTwoFoulCountView      = (TextView) findViewById(R.id.summary_player_two_foul_count_view);



        winnerNameView.setTypeface(retroFont);
        winnerView.setTypeface(retroFont);
        frameScoreView.setTypeface(retroFont);

        playersNamesView.setTypeface(retroFont);
        playerOneNameView.setTypeface(retroFont);
        playerTwoNameView.setTypeface(retroFont);

        allShotsView.setTypeface(retroFont);
        playerOneAllShotsView.setTypeface(retroFont);
        playerTwoAllShotsView.setTypeface(retroFont);

        shotsSuccessfulView.setTypeface(retroFont);
        playerOneShotsView.setTypeface(retroFont);
        playerTwoShotsView.setTypeface(retroFont);

        shotsMissedView.setTypeface(retroFont);
        playerOneShotsMissedView.setTypeface(retroFont);
        playerTwoShotsMissedView.setTypeface(retroFont);

        shotsSuccessfulPercentView.setTypeface(retroFont);
        playerOneShotsPercentView.setTypeface(retroFont);
        playerTwoShotsPercentView.setTypeface(retroFont);

        presenceView.setTypeface(retroFont);
        playerOnePresenceView.setTypeface(retroFont);
        playerTwoPresenceView.setTypeface(retroFont);

        highestBreakView.setTypeface(retroFont);
        playerOneHighestBreakView.setTypeface(retroFont);
        playerTwoHighestBreakView.setTypeface(retroFont);

        averageBreakView.setTypeface(retroFont);
        playerOneAverageBreakView.setTypeface(retroFont);
        playerTwoAverageBreakView.setTypeface(retroFont);

        playerOneAllSafeShotsView.setTypeface(retroFont);
        playerTwoAllSafeShotsView.setTypeface(retroFont);
        allSafeShotsView.setTypeface(retroFont);

        safeShotsSuccessfulView.setTypeface(retroFont);
        playerOneSafeShotsView.setTypeface(retroFont);
        playerTwoSafeShotsView.setTypeface(retroFont);

        safeShotsFailedView.setTypeface(retroFont);
        playerOneSafeShotsFailedView.setTypeface(retroFont);
        playerTwoSafeShotsFailedView.setTypeface(retroFont);

        safeShotsPercentView.setTypeface(retroFont);
        playerOneSafeShotsPercentView.setTypeface(retroFont);
        playerTwoSafeShotsPercentView.setTypeface(retroFont);

        foulCountView.setTypeface(retroFont);
        playerOneFoulCountView.setTypeface(retroFont);
        playerTwoFoulCountView.setTypeface(retroFont);

        exitButton = (Button) findViewById(R.id.summary_exit_button);
        exitButton.setTypeface(retroFont);


        breakImageContainer = (LinearLayout) findViewById(R.id.summary_player_one_item_image_container);



    }

    private void showResult()
    {

        if(playerOneFrames>playerTwoFrames)
        {
            winnerNameView.setText(playerOneFirstName+" "+playerOneLastName);
        }
        else
        {
            winnerNameView.setText(playerTwoFirstName+" "+playerTwoLastName);
        }

        frameScoreView.setText(String.valueOf(playerOneFrames)+"/"+String.valueOf(playerTwoFrames));

        playerOneNameView.setText(playerOneFirstName.substring(0,1)+"."+playerOneLastName);
        playerTwoNameView.setText(playerTwoFirstName.substring(0,1)+"."+playerTwoLastName);

        playerOneAllShotsView.setText(String.valueOf(playerOneAllShots));
        playerTwoAllShotsView.setText(String.valueOf(playerTwoAllShots));

        playerOneShotsView.setText(String.valueOf(playerOneShotsPotted));
        playerTwoShotsView.setText(String.valueOf(playerTwoShotsPotted));

        playerOneShotsMissedView.setText(String.valueOf(playerOneShotsMissed));
        playerTwoShotsMissedView.setText(String.valueOf(playerTwoShotsMissed));

        playerOneShotsPercentView.setText(String.valueOf(playerOneShotsPottedPercent)+"%");
        playerTwoShotsPercentView.setText(String.valueOf(playerTwoShotsPottedPercent)+"%");

        playerOnePresenceView.setText(String.valueOf(playerOnePresence)+"%");
        playerTwoPresenceView.setText(String.valueOf(playerTwoPresence)+"%");

        playerOneAverageBreakView.setText(String.valueOf(playerOneAverageBreak));
        playerTwoAverageBreakView.setText(String.valueOf(playerTwoAverageBreak));

        playerOneHighestBreakView.setText(String.valueOf(playerOneBreak));
        playerTwoHighestBreakView.setText(String.valueOf(playerTwoBreak));

        playerOneAllSafeShotsView.setText(String.valueOf(playerOneAllSafeShots));
        playerTwoAllSafeShotsView.setText(String.valueOf(playerTwoAllSafeShots));

        playerOneSafeShotsView.setText(String.valueOf(playerOneSafeShotsSuccessful));
        playerTwoSafeShotsView.setText(String.valueOf(playerTwoSafeShotsSuccessful));

        playerOneSafeShotsFailedView.setText(String.valueOf(playerOneSafeShotsFailed));
        playerTwoSafeShotsFailedView.setText(String.valueOf(playerTwoSafeShotsFailed));

        playerOneSafeShotsPercentView.setText(String.valueOf(playerOneSafeShotsPercent)+"%");
        playerTwoSafeShotsPercentView.setText(String.valueOf(playerTwoSafeShotsPercent)+"%");

        playerOneFoulCountView.setText(String.valueOf(playerOneFoulCount));
        playerTwoFoulCountView.setText(String.valueOf(playerTwoFoulCount));
        playerOneBreakImageShow();
        playerTwoBreakImageShow();






    }

    private void playerTwoBreakImageShow()
    {
        int padding = convertDpToPixel(32);
        ArrayList<LinearLayout> imageContainers = new ArrayList<>();
        //Parameters for new
        LinearLayout.LayoutParams imageRowParameters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
        LinearLayout imageContainer = new LinearLayout(this);
        imageContainer.setLayoutParams(imageRowParameters);
        imageContainer.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout allContainer = (LinearLayout) findViewById(R.id.summary_player_two_item_image_container);
        allContainer.setMinimumHeight(convertDpToPixel(BALL_SIZE_DP));
        allContainer.setPadding(BALL_SIZE_DP,0,padding,0);
        //Calculate maximum number of balls in row

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth()/2;


        int containerWidthDP = convertPixelToDp(width);
        containerWidthDP = containerWidthDP - padding;
        int maxNumberOfBallsInRow = containerWidthDP/BALL_SIZE_DP;
        int row=1;

        //Add imageContainer to allContainer view
        allContainer.addView(imageContainer);
        imageContainers.add(imageContainer);
        //Create parameters for ball images
        LinearLayout.LayoutParams ballImageParameters = new LinearLayout.LayoutParams(convertDpToPixel(BALL_SIZE_DP),convertDpToPixel(BALL_SIZE_DP), 0f);
        for(int i=0;i<playerTwoImageBreak.size();i++)
        {
            ImageView ballImage = new ImageView(this);
            //Set parameters to ball view
            ballImage.setLayoutParams(ballImageParameters);
            //Upload the current image of the ball to the current image view
            if(playerTwoImageBreak.isEmpty()) return;
            ballImage.setImageResource(playerTwoImageBreak.get(i));

            //if number of balls exceed max balls then create new row for the images
            if(i==maxNumberOfBallsInRow * row && maxNumberOfBallsInRow!=0)
            {

                row++;
                LinearLayout newImageContainer = new LinearLayout(this);
                newImageContainer.setLayoutParams(imageRowParameters);
                allContainer.addView(newImageContainer);
                imageContainers.add(newImageContainer);
            }

            //Add ball view to the image container
            imageContainers.get(row-1).addView(ballImage);

            //   ballImage.setLayoutParams(ballImageParameters);
        }
    }

    private void playerOneBreakImageShow()
    {
        int padding = convertDpToPixel(32);
        ArrayList<LinearLayout> imageContainers = new ArrayList<>();
        //Parameters for new
        LinearLayout.LayoutParams imageRowParameters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
        LinearLayout imageContainer = new LinearLayout(this);
        imageContainer.setLayoutParams(imageRowParameters);
        imageContainer.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout allContainer = (LinearLayout) findViewById(R.id.summary_player_one_item_image_container);
        allContainer.setMinimumHeight(convertDpToPixel(BALL_SIZE_DP));
        allContainer.setPadding(padding,0,BALL_SIZE_DP,0);
        //Calculate maximum number of balls in row

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth()/2;
        int containerWidthDP = convertPixelToDp(width);
        containerWidthDP = containerWidthDP - padding;
        int maxNumberOfBallsInRow = containerWidthDP/BALL_SIZE_DP;
        int row=1;

        //Add imageContainer to allContainer view
        allContainer.addView(imageContainer);
        imageContainers.add(imageContainer);
        //Create parameters for ball images
        LinearLayout.LayoutParams ballImageParameters = new LinearLayout.LayoutParams(convertDpToPixel(BALL_SIZE_DP),convertDpToPixel(BALL_SIZE_DP), 0f);

        for(int i=0;i<playerOneImageBreak.size();i++)
        {
            //Create new ball image view
            ImageView ballImage = new ImageView(this);
            //Set parameters to ball view
            ballImage.setLayoutParams(ballImageParameters);
            //Upload the current image of the ball to the current image view
            if(playerOneImageBreak.isEmpty()) return;
            ballImage.setImageResource(playerOneImageBreak.get(i));

            //if number of balls exceed max balls then create new row for the images
            if(i==maxNumberOfBallsInRow * row && maxNumberOfBallsInRow!=0)
            {
                row++;
                LinearLayout newImageContainer = new LinearLayout(this);
                newImageContainer.setLayoutParams(imageRowParameters);
                allContainer.addView(newImageContainer);
                imageContainers.add(newImageContainer);
            }

            //Add ball view to the image container
            imageContainers.get(row-1).addView(ballImage);
        }
    }

    private int convertDpToPixel(int dp)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return Math.round(dp * (metrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private int convertPixelToDp(int px)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int dp = Math.round(px / (metrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }



}
