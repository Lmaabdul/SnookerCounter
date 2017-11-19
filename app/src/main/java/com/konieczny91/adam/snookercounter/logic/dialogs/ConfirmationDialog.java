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
 * Created by Adam on 15.11.2017.
 */

public class ConfirmationDialog extends DialogFragment implements View.OnClickListener
{
    TextView confirmationTextView;
    Button okButton;
    Button cancelButton;
    Typeface retro;
    ConfirmationDialogListener listener;


    public ConfirmationDialog(){}

    public static ConfirmationDialog newInstance(String confirmationText)
    {
        Bundle arg = new Bundle();
        arg.putString("confirmationText",confirmationText);
        ConfirmationDialog dialog = new ConfirmationDialog();
        dialog.setArguments(arg);
        return dialog;
    }

    public interface ConfirmationDialogListener
    {
        void onFinishConfirmationDialog();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_confirmation,container,false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);

        String text = getArguments().getString("confirmationText");

        retro = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        confirmationTextView = (TextView) view.findViewById(R.id.dialog_confirmation_text_view);
        confirmationTextView.setTypeface(retro);
        confirmationTextView.setText(text);

        okButton = (Button) view.findViewById(R.id.dialog_confirmation_button_ok);
        okButton.setTypeface(retro);
        okButton.setText("OK");
        okButton.setOnClickListener(this);

        cancelButton = (Button) view.findViewById(R.id.dialog_confirmation_button_cancel);
        cancelButton.setTypeface(retro);
        cancelButton.setText("CANCEL");
        cancelButton.setOnClickListener(this);

        listener = (ConfirmationDialogListener) getActivity();


       return view;
    }


    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {

            case R.id.dialog_confirmation_button_ok:

                listener.onFinishConfirmationDialog();
                dismiss();
                break;

            case R.id.dialog_confirmation_button_cancel:

                dismiss();
                break;
        }


    }
}
