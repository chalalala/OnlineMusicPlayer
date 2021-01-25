package vn.edu.usth.onlinemusicplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SONGS           = "favorites";
    public static final String _ID                   = "songID";
    public static final String TITLE                 = "title";
    public static final String COMPOSER              = "composer";
    public static final String ARTISTNAME            = "artistName";
    public static final String ALBUMNAME             = "albumName";
    public static final String ALBUMART              = "albumArt";
    public static final String DATA                  = "data";
    public static final String TRACKNUMBER           = "trackNumber";
    public static final String YEAR                  = "year";
    public static final String ALBUMID               = "albumId";
    public static final String ARTISTID              = "artistId";
    public static final String DURATION              = "duration";
    public static final String DATEMODIFIED          = "dateModified";
    public static final String DATEADDED             = "dateAdded";
    public static final String BOOKMARK              = "bookmark";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_SONGS + " (" + _ID
            + " INTEGER, " + TITLE + " TEXT, " + ARTISTNAME
            + " TEXT, " + COMPOSER + " TEXT, " + ALBUMNAME
            + " TEXT, " + ALBUMART + " TEXT, " + TRACKNUMBER
            + " INTEGER, " + YEAR + " INTEGER, " + ALBUMID
            + " INTEGER, " + DURATION + " INTEGER, " + DATEMODIFIED
            + " INTEGER, " + DATEADDED + " INTEGER, " + BOOKMARK
            + " INTEGER, " +  DATA + " TEXT PRIMARY KEY " + ")";

    public FavoritesDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL(TABLE_CREATE);
    }
}
