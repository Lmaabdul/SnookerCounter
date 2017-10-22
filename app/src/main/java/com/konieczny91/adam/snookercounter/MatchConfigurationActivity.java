package com.konieczny91.adam.snookercounter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.konieczny91.adam.snookercounter.logic.Player;
import com.konieczny91.adam.snookercounter.logic.SpinnerAdapter;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;


public class MatchConfigurationActivity extends AppCompatActivity {


    TextView            selectPlayerOneTextView;
    TextView            selectPlayerTwoTextView;
    TextView            numberOfFramesTextView;
    TextView            numberOfRedsTextView;
    Spinner             selectPlayerOneSpinner;
    Spinner             selectPlayerTwoSpinner;
    Button              createPlayerButton;
    Button              startButton;
    NumberPicker        numberPickerFrames;
    NumberPicker        numberPickerReds;
    Typeface            retroFont;

    private int frames = 1;
    private int reds = 15;
    private String firstName;
    private String lastName;

    ArrayList<Player> players = new ArrayList<>();
    Player playerOne;
    Player playerTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_configuration);

        initViews();
        customFontTexts();
        createPlayer();
        createGuestPlayers();
        spinnersInitListeners();
        numberPickerInit();
        startMatch();
    }

    private void spinnersInitListeners()
    {
        final SpinnerAdapter adapter = new SpinnerAdapter(this,players);

        selectPlayerOneSpinner.setAdapter(adapter);
        selectPlayerTwoSpinner.setAdapter(adapter);

        selectPlayerOneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                playerOne = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        selectPlayerTwoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                 playerTwo = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void customFontTexts()
    {
        retroFont      = Typeface.createFromAsset(getAssets(),"fonts/8-BIT WONDER.ttf");
        selectPlayerOneTextView.setTypeface(retroFont);
        selectPlayerTwoTextView.setTypeface(retroFont);
        createPlayerButton.setTypeface(retroFont);
        startButton.setTypeface(retroFont);
        numberOfFramesTextView.setTypeface(retroFont);
        numberOfRedsTextView.setTypeface(retroFont);

        selectPlayerOneTextView.setText(R.string.selectPlayerOne);
        selectPlayerTwoTextView.setText(R.string.selectPLayerTwo);
        createPlayerButton.setText(R.string.createPayer);
        numberOfFramesTextView.setText(R.string.numberOfFrames);
        numberOfRedsTextView.setText(R.string.numberOfReds);
        startButton.setText(R.string.startButton);
    }

    private void initViews()
    {
        selectPlayerOneTextView = (TextView)    findViewById(R.id.select_player_one_textView);
        selectPlayerTwoTextView = (TextView)    findViewById(R.id.select_player_two_textView);
        numberOfFramesTextView  = (TextView)    findViewById(R.id.select_number_of_frames_textView);
        numberOfRedsTextView    = (TextView)    findViewById(R.id.select_number_of_reds_textView);
        selectPlayerOneSpinner  = (Spinner)     findViewById(R.id.select_player_one_spinner);
        selectPlayerTwoSpinner  = (Spinner)     findViewById(R.id.select_player_two_spinner);
        createPlayerButton      = (Button)      findViewById(R.id.create_player_button);
        startButton             = (Button)      findViewById(R.id.start_match_button);
        numberPickerFrames      = (NumberPicker) findViewById(R.id.frames_number_picker);
        numberPickerReds        = (NumberPicker) findViewById(R.id.reds_number_picker);
        numberPickerReds.setValue(15);
    }

    private void createPlayer()
    {
        createPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();

            }
        });
    }

    private void startMatch()
    {
        final Intent intent = new Intent(MatchConfigurationActivity.this,FrameActivity.class);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(playerOne.equals(playerTwo) && (!playerOne.equals(players.get(0)) || !playerTwo.equals(players.get(0))))
                {
                    Toast.makeText(MatchConfigurationActivity.this, R.string.samePlayersIssueMessage, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent.putExtra("playerOneFirstName",playerOne.getFirstName());
                    intent.putExtra("playerOneLastName",playerOne.getLastName());
                    intent.putExtra("playerTwoFirstName",playerTwo.getFirstName());
                    intent.putExtra("playerTwoLastName",playerTwo.getLastName());
                    intent.putExtra("frames",frames);
                    intent.putExtra("reds",reds);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void createGuestPlayers()
    {
        players.add(new Player("Guest","Guest"));
        playerOne = players.get(0);
        playerTwo = players.get(0);
    }

    private void numberPickerInit()
    {
        numberPickerFrames.setWrapSelectorWheel(true);
        numberPickerFrames.setDividerColor(ContextCompat.getColor(this,R.color.buttonBackgroundColorPressed));
        numberPickerFrames.setTypeface(retroFont);

        final String[] frameNumbers= {"1","3","5","7","9","11","13","15","17","19","21","23","25","27","29","31"};

        numberPickerFrames.setMinValue(1);
        numberPickerFrames.setMaxValue(frameNumbers.length);
        numberPickerFrames.setDisplayedValues(frameNumbers);

        numberPickerReds.setMinValue(0);
        numberPickerReds.setMaxValue(15);
        numberPickerReds.setWrapSelectorWheel(true);
        numberPickerReds.setDividerColor(ContextCompat.getColor(this,R.color.buttonBackgroundColorPressed));
        numberPickerReds.setTypeface(retroFont);

        numberPickerReds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                reds = newVal;
            }
        });

        numberPickerFrames.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                frames = Integer.parseInt(frameNumbers[newVal-1]);

            }
        });
    }

    private void showDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_create_player);

        retroFont      = Typeface.createFromAsset(getAssets(),"fonts/8-BIT WONDER.ttf");

        final EditText firstNameEditText = (EditText) dialog.findViewById(R.id.firstname_edittext);
        final EditText lastNameEditText  = (EditText) dialog.findViewById(R.id.lastname_edittext);

        firstNameEditText.setTypeface(retroFont);
        firstNameEditText.setHint(R.string.firstName);

        lastNameEditText.setTypeface(retroFont);
        lastNameEditText.setHint(R.string.lastName);

        Button  createButton = (Button) dialog.findViewById(R.id.create_button);
        Button  cancelButton = (Button) dialog.findViewById(R.id.cancel_button);

        createButton.setTypeface(retroFont);
        cancelButton.setTypeface(retroFont);

        createButton.setText(R.string.create);
        cancelButton.setText(R.string.cancel);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEditText.getText().toString();
                lastName  = lastNameEditText.getText().toString();

                if (TextUtils.isEmpty(firstName))
                {
                    Toast.makeText(MatchConfigurationActivity.this, "Please, write the player first name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(lastName))
                {
                    Toast.makeText(MatchConfigurationActivity.this, "Please, write the player last name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    players.add(new Player(firstName, lastName));
                    dialog.dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }






}
