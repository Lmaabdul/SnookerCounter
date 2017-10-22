package com.konieczny91.adam.snookercounter.logic;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.konieczny91.adam.snookercounter.R;

import java.util.ArrayList;

/**
 * Created by Adam on 27.08.2017.
 */

public class SpinnerAdapter extends ArrayAdapter<Player> {

    public SpinnerAdapter(Activity context,ArrayList<Player> players)
    {
        super(context,0,players);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View spinnerView = convertView;
        if (spinnerView == null)
        {
            spinnerView = LayoutInflater.from(getContext()).inflate(R.layout.spinner,parent,false);
        }

        Player currentPlayer = getItem(position);

        TextView nameView     = (TextView) spinnerView.findViewById(R.id.spinner_item_name);
        TextView lastNameView = (TextView) spinnerView.findViewById(R.id.spinner_item_last_name);

        Typeface retro = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        nameView.setTypeface(retro);
        lastNameView.setTypeface(retro);

        nameView.setText(currentPlayer.getFirstName());
        lastNameView.setText(currentPlayer.getLastName().substring(0,1) + ".");

        return spinnerView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View spinnerView = convertView;
        if (spinnerView == null)
        {
            spinnerView = LayoutInflater.from(getContext()).inflate(R.layout.spinner,parent,false);
        }

        Player currentPlayer = getItem(position);

        TextView nameView     = (TextView) spinnerView.findViewById(R.id.spinner_item_name);
        TextView lastNameView = (TextView) spinnerView.findViewById(R.id.spinner_item_last_name);

        Typeface retro = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        nameView.setTypeface(retro);
        lastNameView.setTypeface(retro);

        nameView.setText(currentPlayer.getFirstName());
        lastNameView.setText(currentPlayer.getLastName().substring(0,1) + ".");

        return spinnerView;
    }
}
