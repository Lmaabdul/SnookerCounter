package com.konieczny91.adam.snookercounter.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adam on 10.12.2017.
 */

public class StatisticDbHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "statistic.db";
    private static final int DATABASE_VERSION = 1;

    public StatisticDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_CREATE_STATISTIC_TABLE = "CREATE TABLE " + StatisticEntry.TABLE_NAME + " ("
                + StatisticEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StatisticEntry.COLUMN_PLAYER_ID + " INTEGER, "
                + StatisticEntry.COLUMN_WIN_RATE + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_POTTED_PERCENT + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_PRESENCE + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_AVERAGE_BREAK + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_SAFE_SHOTS_PERCENT + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_WIN_RATE_LAST + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_POTTED_PERCENT_LAST + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_PRESENCE_LAST + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_AVERAGE_BREAK_LAST + " INTEGER NOT NULL DEFAULT 0, "
                + StatisticEntry.COLUMN_SAFE_SHOTS_PERCENT_LAST + " INTEGER NOT NULL DEFAULT 0, "
                + ");";

        db.execSQL(SQL_CREATE_STATISTIC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
