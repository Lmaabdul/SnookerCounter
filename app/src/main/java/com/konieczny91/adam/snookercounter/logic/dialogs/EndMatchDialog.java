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
 * Created by Adam on 29.10.2017.
 */

public class EndMatchDialog extends DialogFragment
{

    TextView textview;
    Button button;
    Typeface retro;
    EndMatchDialogListener listener;

    public EndMatchDialog(){}


    public interface EndMatchDialogListener
    {
        void onFinishEndMatchDialog();
    }



    public static EndMatchDialog newInstance(String name, String lastName)
    {
        EndMatchDialog dialog = new EndMatchDialog();
        Bundle arg = new Bundle();
        arg.putString("name",name);
        arg.putString("lastName",lastName);
        dialog.setArguments(arg);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_end_match,container,false);
        getDialog().setCancelable(false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        String playerName;
        String playerLastName;

        retro = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        textview = (TextView) view.findViewById(R.id.dialog_end_match_text);
        button = (Button) view.findViewById(R.id.dialog_end_match_end_button);

        textview.setTypeface(retro);
        if(getArguments()!= null)
        {
            playerName = getArguments().getString("name");
            playerLastName = getArguments().getString("lastName");
            textview.setText(playerName+" "+playerLastName+" won the match");
        }

        listener = (EndMatchDialogListener) getActivity();

        button.setTypeface(retro);
        button.setText("Ok");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onFinishEndMatchDialog();
                dismiss();
            }
        });










        return view;
    }
}
