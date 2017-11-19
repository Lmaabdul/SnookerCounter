package com.konieczny91.adam.snookercounter.logic.dialogs;

import android.support.v4.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.konieczny91.adam.snookercounter.R;
import com.konieczny91.adam.snookercounter.logic.Enums;

/**
 * Created by Adam on 14.11.2017.
 */

public class MoreDialog extends DialogFragment implements View.OnClickListener
{

    Button manyRedsButton;
    Button forfeitButton;
    Button exitButton;
    Typeface retroFont;
    MoreDialogListener listener;

    Enums.moreDialogButtonState buttonClicked = null;

    public MoreDialog(){}

    public static MoreDialog newInstance()
    {
        MoreDialog moreDialog = new MoreDialog();
        return moreDialog;
    }
    public interface MoreDialogListener
    {
        void onFinishMoreDialog(Enums.moreDialogButtonState buttonClicked);
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_more,container,false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(true);

        retroFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/8-BIT WONDER.ttf");

        exitButton = (Button) view.findViewById(R.id.dialog_more_exit_button);
        exitButton.setTypeface(retroFont);
        exitButton.setText(R.string.exit);
        exitButton.setOnClickListener(this);

        forfeitButton = (Button) view.findViewById(R.id.dialog_more_forfeit_button);
        forfeitButton.setTypeface(retroFont);
        forfeitButton.setText(R.string.forfeit);
        forfeitButton.setOnClickListener(this);

        manyRedsButton = (Button) view.findViewById(R.id.dialog_more_many_reds_button);
        manyRedsButton.setTypeface(retroFont);
        manyRedsButton.setText(R.string.manyReds);
        manyRedsButton.setOnClickListener(this);

        listener = (MoreDialogListener) getActivity();
        return view;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.dialog_more_exit_button:
                buttonClicked = Enums.moreDialogButtonState.EXIT_BUTTON;
                listener.onFinishMoreDialog(buttonClicked);
                dismiss();
                break;

            case R.id.dialog_more_forfeit_button:
                buttonClicked = Enums.moreDialogButtonState.FORFEIT_BUTTON;
                listener.onFinishMoreDialog(buttonClicked);
                dismiss();
                break;

            case R.id.dialog_more_many_reds_button:
                buttonClicked = Enums.moreDialogButtonState.MANY_REDS_BUTTON;
                listener.onFinishMoreDialog(buttonClicked);
                dismiss();
                break;
        }
    }
}
