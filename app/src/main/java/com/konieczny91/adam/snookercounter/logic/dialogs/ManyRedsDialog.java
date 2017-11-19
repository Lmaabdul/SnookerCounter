package com.konieczny91.adam.snookercounter.logic.dialogs;

import android.support.v4.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.konieczny91.adam.snookercounter.R;

/**
 * Created by Adam on 18.11.2017.
 */

public class ManyRedsDialog extends DialogFragment implements View.OnClickListener {
    TextView infoView;
    TextView redsCountView;
    Button incrementButton;
    Button decrementButton;
    Button okButton;
    Button cancelButton;

    ManyRedsDialogListener listener;

    int numberOfRedsPotted = 0;
    int maxNumberOfReds;


    public ManyRedsDialog(){}

    public static ManyRedsDialog newInstance(int maxNumberOfReds)
    {
        Bundle arg = new Bundle();
        arg.putInt("maxNumberOfReds",maxNumberOfReds);
        ManyRedsDialog dialog = new ManyRedsDialog();
        dialog.setArguments(arg);
        return dialog;
    }




    public interface ManyRedsDialogListener
    {
        void onFinishManyRedsDialog(int numberOfRedsPotted);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_many_reds,container,false);
        setCancelable(true);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        maxNumberOfReds = getArguments().getInt("maxNumberOfReds");

        Typeface retroFont = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        infoView = (TextView) view.findViewById(R.id.dialog_many_reds_info);
        infoView.setText(getString(R.string.howManyReds));
        infoView.setTypeface(retroFont);

        redsCountView = (TextView) view.findViewById(R.id.dialog_many_reds_count);
        redsCountView.setText(String.valueOf(numberOfRedsPotted));
        redsCountView.setTypeface(retroFont);

        incrementButton = (Button) view.findViewById(R.id.dialog_many_increment_button);
        incrementButton.setText("+");
        incrementButton.setTypeface(retroFont);
        incrementButton.setOnClickListener(this);

        decrementButton = (Button) view.findViewById(R.id.dialog_many_decrement_button);
        decrementButton.setText("-");
        decrementButton.setTypeface(retroFont);
        decrementButton.setOnClickListener(this);

        okButton = (Button) view.findViewById(R.id.dialog_many_reds_ok_button);
        okButton.setText(getString(R.string.ok));
        okButton.setTypeface(retroFont);
        okButton.setOnClickListener(this);

        cancelButton = (Button) view.findViewById(R.id.dialog_many_reds_cancel_button);
        cancelButton.setText(getString(R.string.cancel));
        cancelButton.setTypeface(retroFont);
        cancelButton.setOnClickListener(this);

        listener = (ManyRedsDialogListener) getActivity();

        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.dialog_many_increment_button:
                if(numberOfRedsPotted<maxNumberOfReds)
                {
                    redsCountView.setText(String.valueOf(++numberOfRedsPotted));
                }
                break;

            case R.id.dialog_many_decrement_button:
                if(numberOfRedsPotted>0)
                {
                    redsCountView.setText(String.valueOf(--numberOfRedsPotted));
                }
                break;

            case R.id.dialog_many_reds_ok_button:
                listener.onFinishManyRedsDialog(numberOfRedsPotted);
                dismiss();
                break;

            case R.id.dialog_many_reds_cancel_button:
                dismiss();
                break;

        }

    }
}



















