package com.konieczny91.adam.snookercounter.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.konieczny91.adam.snookercounter.logic.Player;

import java.util.ArrayList;

/**
 * Created by Adam on 07.12.2017.
 */

public class PlayerDbHelper extends SQLiteOpenHelper
{
    private String LOG_TAG = PlayerDbHelper.class.getName();

    public static final String DATABASE_NAME ="players.db";

    private static final int DATABASE_VERSION = 1;

    public PlayerDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_CREATE_PLAYER_TABLE = "CREATE TABLE " + PlayerEntry.TABLE_NAME + " ("
                + PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PlayerEntry.COLUMN_PLAYER_NAME + " TEXT NOT NULL, "
                + PlayerEntry.COLUMN_PLAYER_LASTNAME + " TEXT NOT NULL"
                + ");";

        db.execSQL(SQL_CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertPlayer(String name, String lastName)
    {
        if (name == null) {
            throw new IllegalArgumentException("Player requires a name");
        }

        if (lastName == null) {
            throw new IllegalArgumentException("Player requires a last name");
        }

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlayerEntry.COLUMN_PLAYER_NAME, name);
        contentValues.put(PlayerEntry.COLUMN_PLAYER_LASTNAME, lastName);
        long id = database.insert(PlayerEntry.TABLE_NAME,null,contentValues);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row");
            return false;
        }
        return true;
    }

    public ArrayList<Player> getAllPlayers()
    {
        ArrayList<Player> playerArrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(PlayerEntry.TABLE_NAME,PlayerEntry.ALL_COLUMNS,null,null,null,null,null,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false)
        {
            playerArrayList.add(new Player(
                    cursor.getInt(cursor.getColumnIndex(PlayerEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME)),
                    cursor.getString(cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_LASTNAME))
            ));
            cursor.moveToNext();
        }
        return playerArrayList;
    }

    public Cursor getPlayer(int id)
    {
        String selection = PlayerEntry._ID + " =?";
        String[] selectionArgs = {String.valueOf(id)};
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(PlayerEntry.TABLE_NAME, PlayerEntry.ALL_COLUMNS, selection,selectionArgs,null,null,null );
        return cursor;
    }

    public int delatePlayer(int id)
    {
        String selection = PlayerEntry._ID + " =?";
        String[] selectionArgs = {String.valueOf(id)};
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(PlayerEntry.TABLE_NAME,selection,selectionArgs);
    }

    public void delateAllPlayers()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(PlayerEntry.TABLE_NAME,null,null);
    }













}
