package com.konieczny91.adam.snookercounter.logic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.konieczny91.adam.snookercounter.R;

/**
 * Created by Adam on 07.10.2017.
 */


public class FoulDialog extends DialogFragment implements View.OnClickListener{

    private Typeface retroFont;

    ImageButton whiteBall;
    ImageButton redBall;
    ImageButton yellowBall;
    ImageButton greenBall;
    ImageButton brownBall;
    ImageButton blueBall;
    ImageButton pinkBall;
    ImageButton blackBall;

    Button incrementButton;
    Button decrementButton;
    Button nextPlayerButton;
    Button repeatFoulButton;
    Button freeballButton;

    Enums.colors colors = Enums.colors.NO_COLOR;

    TextView redsNumberText;

    FoulDialogListener listener;

    int numberOfReds = 0;
    int maxReds;
    int foulPoints;
    boolean playerOneTurn = false;
    boolean freeBall = false;

    boolean repeatFoul = false;



    public FoulDialog(){}

    public interface FoulDialogListener
    {
        void onFinishFoulDialog(int numberOfReds, int foulPoints, boolean repeatFoul, boolean freeBall);
    }

    public static FoulDialog newInstance(int maxReds)
    {
        Bundle args = new Bundle();
        args.putInt("maxReds",maxReds);
        FoulDialog dialog = new FoulDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.white_ball_button_foul:

                colors = Enums.colors.WHITE;
                foulPoints = 4;
                break;

            case R.id.red_ball_button_foul:

                colors = Enums.colors.RED;
                foulPoints = 4;
                break;

            case R.id.yellow_ball_button_foul:

                colors = Enums.colors.YELLOW;
                foulPoints = 4;
                break;

            case R.id.green_ball_button_foul:

                colors = Enums.colors.GREEN;
                foulPoints = 4;
                break;

            case R.id.brown_ball_button_foul:

                colors = Enums.colors.BROWN;
                foulPoints = 4;
                break;

            case R.id.blue_ball_button_foul:

                colors = Enums.colors.BLUE;
                foulPoints = 5;
                break;

            case R.id.pink_ball_button_foul:

                colors = Enums.colors.PINK;
                foulPoints = 6;
                break;

            case R.id.black_ball_button_foul:

                colors = Enums.colors.BLACK;
                foulPoints = 7;
                break;

            case R.id.increment_button_foul:

                numberOfReds++;
                if(numberOfReds>maxReds) numberOfReds=maxReds;
                break;

            case R.id.decrement_button_foul:

                numberOfReds--;
                if(numberOfReds<0) numberOfReds=0;
                break;

            case R.id.dialog_next_player_foul:

                if(colors == Enums.colors.NO_COLOR)
                {
                    Toast.makeText(getContext(),"You must pick the ball",Toast.LENGTH_LONG).show();
                    break;
                }
                repeatFoul = false;

                listener.onFinishFoulDialog(numberOfReds,foulPoints,repeatFoul,freeBall);

                dismiss();
                break;

            case R.id.dialog_repeat_foul:

                if(colors == Enums.colors.NO_COLOR)
                {
                    Toast.makeText(getContext(),"You must pick the ball",Toast.LENGTH_LONG).show();
                    break;
                }
                repeatFoul = true;

                listener.onFinishFoulDialog(numberOfReds,foulPoints,repeatFoul,freeBall);

                dismiss();
                break;

            case R.id.dialog_freeball_foul:
                if(colors == Enums.colors.NO_COLOR)
                {
                    Toast.makeText(getContext(),"You must pick the ball",Toast.LENGTH_LONG).show();
                    break;
                }

                freeBall = true;
                listener.onFinishFoulDialog(numberOfReds,foulPoints,repeatFoul,freeBall);

                dismiss();

        }

        toggleBallButtons(colors);
        redsNumberText.setText(String.valueOf(numberOfReds));




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_foul,container,false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().setCancelable(false);

        maxReds = getArguments().getInt("maxReds");

        retroFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/8-BIT WONDER.ttf");

        TextView ballTextView = (TextView) view.findViewById(R.id.dialog_foul_text);
        TextView whoStartTextView = (TextView) view.findViewById(R.id.dialog_text_who_starts);
        ballTextView.setTypeface(retroFont);
        ballTextView.setText(R.string.pickFoulBall);
        whoStartTextView.setTypeface(retroFont);
        whoStartTextView.setText(R.string.playerTurn);

        whiteBall     = (ImageButton) view.findViewById(R.id.white_ball_button_foul);
        redBall       = (ImageButton) view.findViewById(R.id.red_ball_button_foul);
        yellowBall    = (ImageButton) view.findViewById(R.id.yellow_ball_button_foul);
        greenBall     = (ImageButton) view.findViewById(R.id.green_ball_button_foul);
        brownBall     = (ImageButton) view.findViewById(R.id.brown_ball_button_foul);
        blueBall      = (ImageButton) view.findViewById(R.id.blue_ball_button_foul);
        pinkBall      = (ImageButton) view.findViewById(R.id.pink_ball_button_foul);
        blackBall     = (ImageButton) view.findViewById(R.id.black_ball_button_foul);
        incrementButton  = (Button) view.findViewById(R.id.increment_button_foul);
        decrementButton  = (Button) view.findViewById(R.id.decrement_button_foul);
        nextPlayerButton = (Button) view.findViewById(R.id.dialog_next_player_foul);
        repeatFoulButton = (Button) view.findViewById(R.id.dialog_repeat_foul);
        freeballButton = (Button) view.findViewById(R.id.dialog_freeball_foul);

        initListeners();

        TextView redsInfoText = (TextView) view.findViewById(R.id.reds_info_foul);

        redsNumberText = (TextView) view.findViewById(R.id.reds_text_view_foul);
        redsInfoText.setText(R.string.redsFoulInfo);
        redsInfoText.setTypeface(retroFont);
        redsNumberText.setText("0");
        redsNumberText.setTypeface(retroFont);
        incrementButton.setText("+");
        decrementButton.setText("-");
        nextPlayerButton.setText(R.string.nextPlayer);
        repeatFoulButton.setText(R.string.repeatFoul);
        freeballButton.setText(R.string.freeball);
        nextPlayerButton.setTypeface(retroFont);
        repeatFoulButton.setTypeface(retroFont);
        freeballButton.setTypeface(retroFont);

        listener = (FoulDialogListener) getActivity();

        return  view;
    }

    private void toggleBallButtons(Enums.colors color)
    {

        if (color == Enums.colors.WHITE)
        {
            whiteBall.setImageResource(R.drawable.whiteballpressed);
        }
        else
        {
            whiteBall.setImageResource(R.drawable.whiteball);
        }

        if (color == Enums.colors.RED)
        {
            redBall.setImageResource(R.drawable.redballpressed);
        }
        else
        {
            redBall.setImageResource(R.drawable.redball);
        }

        if (color == Enums.colors.YELLOW)
        {
            yellowBall.setImageResource(R.drawable.yellowballpressed);
        }
        else
        {
            yellowBall.setImageResource(R.drawable.yellowball);
        }

        if (color == Enums.colors.GREEN)
        {
            greenBall.setImageResource(R.drawable.greenballpressed);
        }
        else
        {
            greenBall.setImageResource(R.drawable.greenball);
        }

        if (color == Enums.colors.BROWN)
        {
            brownBall.setImageResource(R.drawable.brownballpressed);
        }
        else
        {
            brownBall.setImageResource(R.drawable.brownball);
        }

        if (color == Enums.colors.BLUE)
        {
            blueBall.setImageResource(R.drawable.blueballpressed);
        }
        else
        {
            blueBall.setImageResource(R.drawable.blueball);
        }

        if (color == Enums.colors.PINK)
        {
            pinkBall.setImageResource(R.drawable.pinkballpressed);
        }
        else
        {
            pinkBall.setImageResource(R.drawable.pinkball);
        }

        if (color == Enums.colors.BLACK)
        {
            blackBall.setImageResource(R.drawable.blackballpressed);
        }
        else
        {
            blackBall.setImageResource(R.drawable.blackball);
        }
    }

    private void initListeners()
    {
        whiteBall.setOnClickListener(this);
        redBall.setOnClickListener(this);
        yellowBall.setOnClickListener(this);
        greenBall.setOnClickListener(this);
        brownBall.setOnClickListener(this);
        blueBall.setOnClickListener(this);
        pinkBall.setOnClickListener(this);
        blackBall.setOnClickListener(this);
        incrementButton.setOnClickListener(this);
        decrementButton.setOnClickListener(this);
        nextPlayerButton.setOnClickListener(this);
        repeatFoulButton.setOnClickListener(this);
        freeballButton.setOnClickListener(this);

    }




}
