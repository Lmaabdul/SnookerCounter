package com.konieczny91.adam.snookercounter.logic;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
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
    private final int BALL_SIZE_DP = 24;

    public RecordAdapter(Activity context, ArrayList<Record> records)
    {
        super(context,0,records);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int row=1;
        ArrayList<LinearLayout> imageContainers = new ArrayList<>();
        LinearLayout newImageContainer;

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

        //Parameters for new
        LinearLayout.LayoutParams imageRowParameters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);

        LinearLayout imageContainer = new LinearLayout(getContext());
        imageContainer.setLayoutParams(imageRowParameters);
        imageContainer.setOrientation(LinearLayout.HORIZONTAL);



        LinearLayout allContainer = (LinearLayout) listItemView.findViewById(R.id.list_item_image_container);
        allContainer.setMinimumHeight(convertDpToPixel(BALL_SIZE_DP));
        if(allContainer.getChildCount()>0) allContainer.removeAllViews();

        //Calculate maximum number of balls in row
        int containerWidth = allContainer.getWidth();
        int containerWidthDP = convertPixelToDp(containerWidth);
        int maxNumberOfBallsInRow = containerWidthDP/BALL_SIZE_DP;

        //Add imageContainer to allContainer view
        allContainer.addView(imageContainer);

        //Add imageContainer to array holding imageContainers
        imageContainers.add(imageContainer);

        //Before updating the view remove all the views to prevent duplication
        imageContainer.removeAllViews();
        //Create parameters for ball images
        LinearLayout.LayoutParams ballImageParameters = new LinearLayout.LayoutParams(convertDpToPixel(BALL_SIZE_DP),convertDpToPixel(BALL_SIZE_DP), 0f);

        for (int i = 0; i<currentRecord.getImageArraySize(); i++)
        {
            //padding for the ball images
            int padding = convertPixelToDp(2);
            //Create new ball image view
            ImageView ballImage = new ImageView(getContext());
            //Set parameters to ball view
            ballImage.setLayoutParams(ballImageParameters);
            ballImage.setPadding(padding,padding,padding,padding);
            //Upload the current image of the ball to the current image view
            ballImage.setImageResource(currentRecord.getImageId(i));

            //if number of balls exceed max balls then create new row for the images
            if(i==maxNumberOfBallsInRow * row && maxNumberOfBallsInRow!=0)
            {

                    row++;
                    newImageContainer = new LinearLayout(getContext());
                    newImageContainer.setLayoutParams(imageRowParameters);

                    allContainer.addView(newImageContainer);
                    imageContainers.add(newImageContainer);
            }

            //Add ball view to the image container
            imageContainers.get(row-1).addView(ballImage);
         //   ballImage.setLayoutParams(ballImageParameters);
        }




        return listItemView;
    }

    private int convertDpToPixel(int dp)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return Math.round(dp * (metrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private int convertPixelToDp(int px)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int dp = Math.round(px / (metrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }



}





