package com.konieczny91.adam.snookercounter.logic.dialogs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.konieczny91.adam.snookercounter.R;

/**
 * Created by Adam on 25.10.2017.
 */

public class NextFrameDialog extends DialogFragment {

    Button continueButton;
    TextView frameWinTextView;
    String playerName;
    String playerLastName;

    NextFrameListener listener;

    public interface NextFrameListener
    {
        void onFinishNextFrameDialog();
    }


    public NextFrameDialog(){}

    public static NextFrameDialog newInstance(String playerName, String playerLastName)
    {
        Bundle args = new Bundle();
        args.putString("playerName",playerName);
        args.putString("playerLastName",playerLastName);
        NextFrameDialog dialog = new NextFrameDialog();
        dialog.setArguments(args);
        return dialog;
    }

    public static NextFrameDialog newInstance()
    {
        NextFrameDialog dialog = new NextFrameDialog();
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_next_frame,container,false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);


        Typeface retro = Typeface.createFromAsset(getContext().getAssets(), "fonts/8-BIT WONDER.ttf");

        frameWinTextView = (TextView) view.findViewById(R.id.dialog_next_frame_text_view);
        continueButton = (Button) view.findViewById(R.id.dialog_next_frame_button);
        frameWinTextView.setTypeface(retro);

        listener = (NextFrameListener) getActivity();

        if(getArguments()!= null)
        {
            playerName = getArguments().getString("playerName");
            playerLastName = getArguments().getString("playerLastName");
            frameWinTextView.setText(playerName+" "+playerLastName+" won the frame");
        }
        else
        {
            frameWinTextView.setText("Draw");
        }


        continueButton.setTypeface(retro);
        continueButton.setText("Continue");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.onFinishNextFrameDialog();
                dismiss();
            }
        });

        return view;
    }
}
