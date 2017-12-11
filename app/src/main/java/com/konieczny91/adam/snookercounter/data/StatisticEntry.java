package com.konieczny91.adam.snookercounter.data;

import android.provider.BaseColumns;

/**
 * Created by Adam on 10.12.2017.
 */

public class StatisticEntry
{

    public final static String TABLE_NAME = "statistic";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_PLAYER_ID                 = "playerID";

    public final static String COLUMN_WIN_RATE                  = "playerWinRate";
    public final static String COLUMN_POTTED_PERCENT            = "playerShotsPottedPercent";
    public final static String COLUMN_PRESENCE                  = "playerPresence";
    public final static String COLUMN_AVERAGE_BREAK             = "playerAverageBreak";
    public final static String COLUMN_SAFE_SHOTS_PERCENT        = "playerSafeShotsPercent";

    public final static String COLUMN_WIN_RATE_LAST             = "lastPlayerWinRate";
    public final static String COLUMN_POTTED_PERCENT_LAST       = "lastPlayerShotsPottedPercent";
    public final static String COLUMN_PRESENCE_LAST             = "lastPlayerPresence";
    public final static String COLUMN_AVERAGE_BREAK_LAST        = "lastPlayerAverageBreak";
    public final static String COLUMN_SAFE_SHOTS_PERCENT_LAST   = "lastPlayerSafeShotsPercent";

    public final static String[] ALL_COLUMNS = {_ID,COLUMN_PLAYER_ID,COLUMN_WIN_RATE,
            COLUMN_POTTED_PERCENT,COLUMN_PRESENCE,COLUMN_AVERAGE_BREAK,COLUMN_SAFE_SHOTS_PERCENT,
            COLUMN_WIN_RATE_LAST,COLUMN_POTTED_PERCENT_LAST,COLUMN_PRESENCE_LAST,COLUMN_AVERAGE_BREAK_LAST,
            COLUMN_SAFE_SHOTS_PERCENT_LAST};

}
