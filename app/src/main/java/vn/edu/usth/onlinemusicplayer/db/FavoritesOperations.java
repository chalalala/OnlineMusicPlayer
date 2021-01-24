package vn.edu.usth.onlinemusicplayer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.model.SongModel;

public class FavoritesOperations {

    public static final String TAG = "Favorites Database";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            FavoritesDBHandler.TRACKNUMBER,
            FavoritesDBHandler.YEAR,
            FavoritesDBHandler.ALBUMID,
            FavoritesDBHandler.ARTISTID,
            FavoritesDBHandler.DURATION,
            FavoritesDBHandler.ARTISTNAME,
            FavoritesDBHandler.DATEMODIFIED,
            FavoritesDBHandler.DATEADDED,
            FavoritesDBHandler.BOOKMARK,
            FavoritesDBHandler.COMPOSER,
            FavoritesDBHandler.ALBUMNAME,
            FavoritesDBHandler.ALBUMART,
            FavoritesDBHandler.DATA
    };

    public FavoritesOperations(Context context) {
        dbHandler = new FavoritesDBHandler(context);
    }

    public void open() {
        Log.i(TAG, " Database Opened");
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database Closed");
        dbHandler.close();
    }

    public void addSongFav(SongModel songsList) {
        open();
        ContentValues values = new ContentValues();
        values.put(FavoritesDBHandler.TITLE, songsList.getTitle());
        values.put(FavoritesDBHandler.ARTISTNAME, songsList.getArtistName());
        values.put(FavoritesDBHandler.COMPOSER, songsList.getComposer());
        values.put(FavoritesDBHandler.ALBUMNAME, songsList.getAlbumName());
        values.put(FavoritesDBHandler.ALBUMART, songsList.getAlbumArt());
        values.put(FavoritesDBHandler.DATA, songsList.getData());
        values.put(FavoritesDBHandler.TRACKNUMBER, songsList.getTrackNumber());
        values.put(FavoritesDBHandler.YEAR, songsList.getYear());
        values.put(FavoritesDBHandler.ALBUMID, songsList.getAlbumId());
        values.put(FavoritesDBHandler.ARTISTID, songsList.getArtistId());
        values.put(FavoritesDBHandler.DURATION, songsList.getDuration());
        values.put(FavoritesDBHandler.DATEMODIFIED, songsList.getDateModified());
        values.put(FavoritesDBHandler.DATEADDED, songsList.getDateAdded());
        values.put(FavoritesDBHandler.BOOKMARK, songsList.getBookmark());
        database.insertWithOnConflict(FavoritesDBHandler.TABLE_SONGS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        close();
    }

    public ArrayList<SongModel> getAllFavorites() {
        open();
        Cursor cursor = database.query(FavoritesDBHandler.TABLE_SONGS, allColumns,
                null, null, null, null, null);
        ArrayList<SongModel> favSongs = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                SongModel song = new SongModel(cursor.getInt(cursor.getColumnIndex(FavoritesDBHandler._ID))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.TITLE))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.ARTISTNAME))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.COMPOSER))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.ALBUMNAME))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.ALBUMART))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.DATA))
                        , cursor.getInt(cursor.getColumnIndex(FavoritesDBHandler.TRACKNUMBER))
                        , cursor.getInt(cursor.getColumnIndex(FavoritesDBHandler.YEAR))
                        , cursor.getLong(cursor.getColumnIndex(FavoritesDBHandler.DURATION))
                        , cursor.getLong(cursor.getColumnIndex(FavoritesDBHandler.DATEMODIFIED))
                        , cursor.getLong(cursor.getColumnIndex(FavoritesDBHandler.DATEADDED))
                        , cursor.getInt(cursor.getColumnIndex(FavoritesDBHandler.ALBUMID))
                        , cursor.getInt(cursor.getColumnIndex(FavoritesDBHandler.ARTISTID))
                        , cursor.getLong(cursor.getColumnIndex(FavoritesDBHandler.BOOKMARK)));
                favSongs.add(song);
            }
        }
        close();
        return favSongs;
    }

    public void removeSong(String songPath) {
        open();
        String whereClause =
                FavoritesDBHandler.DATA + "=?";
        String[] whereArgs = new String[]{songPath};

        database.delete(FavoritesDBHandler.TABLE_SONGS, whereClause, whereArgs);
        close();
    }

}
