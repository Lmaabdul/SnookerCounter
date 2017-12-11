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
 * Created by Adam on 10.12.2017.
 */

public class StatisticPlayerAdapter extends ArrayAdapter<Player>
{

    public StatisticPlayerAdapter(Activity context, ArrayList<Player> players)
    {
        super(context,0,players);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.statistic_list_item,parent,false);
        }


        TextView textView = (TextView) listItemView.findViewById(R.id.statistic_list_view_item_text);

        Player player = getItem(position);

        String playerName = player.getFirstName() +" "+ player.getLastName();

        textView.setText(playerName);

        Typeface retro = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        textView.setTypeface(retro);



        return listItemView;
    }
}
