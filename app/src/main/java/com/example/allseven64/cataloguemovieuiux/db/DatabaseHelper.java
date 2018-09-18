package com.example.allseven64.cataloguemovieuiux.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.POPULARITY;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.POSTER;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.RELEASE;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.TITLE;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.TABLE_MOVIE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbmovie";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_MOVIE = "CREATE TABLE " +TABLE_MOVIE+
            " ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            POSTER+ " TEXT NOT NULL, " +
            TITLE+ " TEXT NOT NULL, " +
            RELEASE+ " TEXT NOT NULL, " +
            POPULARITY+ " TEXT NOT NULL, " +
            OVERVIEW+ " TEXT NOT NULL);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MOVIE);
        onCreate(db);
    }
}
