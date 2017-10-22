package com.konieczny91.adam.snookercounter.logic;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Created by Adam on 27.08.2017.
 */

public class CustomNumberPicker extends NumberPicker {

    Typeface type;

    public CustomNumberPicker(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    @Override
    public void addView(View child)
    {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params)
    {
        super.addView(child, index, params);
        type = Typeface.createFromAsset(getContext().getAssets(), "fonts/8-BIT WONDER.ttf");
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params)
    {
        super.addView(child, params);
        type = Typeface.createFromAsset(getContext().getAssets(), "fonts/8-BIT WONDER.ttf");
        updateView(child);
    }

    private void updateView(View view)
    {
        if (view instanceof EditText)
        {
            ((EditText) view).setTypeface(type);
            ((EditText) view).setTextSize(25);
        }
    }
}






