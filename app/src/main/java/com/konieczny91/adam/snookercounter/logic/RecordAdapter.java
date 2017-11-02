package com.konieczny91.adam.snookercounter.logic;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.konieczny91.adam.snookercounter.R;

import java.util.ArrayList;

/**
 * Created by Adam on 30.10.2017.
 */

public class RecordAdapter extends ArrayAdapter<Record>
{

    public RecordAdapter(Activity context, ArrayList<Record> records)
    {
        super(context,0,records);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Record currentRecord = getItem(position);

        Typeface retro = Typeface.createFromAsset(getContext().getAssets(),"fonts/8-BIT WONDER.ttf");

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.list_item_text);
        nameTextView.setTypeface(retro);
        nameTextView.setText(currentRecord.getPlayerName().substring(0,1) + "." + currentRecord.getPlayerLastName().substring(0,1));

        TextView breakTextView = (TextView) listItemView.findViewById(R.id.list_item_break);
        breakTextView.setTypeface(retro);
        breakTextView.setText(String.valueOf(currentRecord.getBreakScore()));

        LinearLayout imageContainer = (LinearLayout) listItemView.findViewById(R.id.list_item_image_container);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        imageContainer.removeAllViews();

        for (int i = 0; i<currentRecord.getImageArraySize(); i++)
        {
            ImageView ballImage = new ImageView(getContext());
            ballImage.setLayoutParams(layoutParams);
            ballImage.setImageResource(currentRecord.getImageId(i));
            imageContainer.addView(ballImage);
        }

        return listItemView;
    }

}





