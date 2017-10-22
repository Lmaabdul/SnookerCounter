package com.konieczny91.adam.snookercounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {


    Button newMatchButton;
    Button statisticsButton;
    Button exitButton;
    Intent newMatchIntent;
    Intent statisticsIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initViews();
        initIntents();
        buttonListeners();
        customFontTexts();

    }


    private void buttonListeners()
    {
        newMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(newMatchIntent);
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(statisticsIntent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }


    private void customFontTexts()
    {
        Typeface retroFont = Typeface.createFromAsset(getAssets(),"fonts/8-BIT WONDER.ttf");
        newMatchButton.setTypeface(retroFont);
        statisticsButton.setTypeface(retroFont);
        exitButton.setTypeface(retroFont);

        newMatchButton.setText(getString(R.string.newMatchButton));
        statisticsButton.setText(getString(R.string.statisticsButton));
        exitButton.setText(getString(R.string.exitButton));
    }


    private void initViews()
    {
        newMatchButton      = (Button) findViewById(R.id.button_newMatch);
        statisticsButton    = (Button) findViewById(R.id.button_Statistics);
        exitButton          = (Button) findViewById(R.id.button_Exit);
    }

    private void initIntents()
    {
        newMatchIntent      = new Intent(StartActivity.this,MatchConfigurationActivity.class);
        statisticsIntent    = new Intent(StartActivity.this,StatisticActivity.class);
    }

}
