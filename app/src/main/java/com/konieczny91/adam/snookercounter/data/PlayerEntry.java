package com.konieczny91.adam.snookercounter.data;

import android.provider.BaseColumns;

/**
 * Created by Adam on 10.12.2017.
 */

public final class PlayerEntry
{
    public final static String TABLE_NAME = "players";

    public final static String _ID = BaseColumns._ID;

    public final static String COLUMN_PLAYER_NAME = "name";

    public final static String COLUMN_PLAYER_LASTNAME = "lastName";

    public final static String[] ALL_COLUMNS = {_ID,COLUMN_PLAYER_NAME,COLUMN_PLAYER_LASTNAME};
}
