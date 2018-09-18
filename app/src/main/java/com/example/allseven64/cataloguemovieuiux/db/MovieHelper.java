package com.example.allseven64.cataloguemovieuiux.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.allseven64.cataloguemovieuiux.entity.MovieModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.POPULARITY;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.POSTER;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.RELEASE;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.TITLE;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.TABLE_MOVIE;

public class MovieHelper {
    private static String DATABASE_TABLE = TABLE_MOVIE;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MovieHelper (Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<MovieModel> getAllData(){
        ArrayList<MovieModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,null,null,null,null,_ID +" ASC", null);
        cursor.moveToFirst();
        MovieModel movieModel;

        if (cursor.getCount()>0) {
            do{
                movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movieModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movieModel.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                movieModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movieModel.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                movieModel.setPopularity(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));

                arrayList.add(movieModel);
                cursor.moveToNext();

            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(MovieModel movieModel){
        ContentValues initialValues = new ContentValues();
        initialValues.put(POSTER, movieModel.getPosterPath());
        initialValues.put(TITLE, movieModel.getTitle());
        initialValues.put(OVERVIEW, movieModel.getOverview());
        initialValues.put(RELEASE, movieModel.getReleaseDate());
        initialValues.put(POPULARITY, movieModel.getPopularity());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(MovieModel movieModel){
        ContentValues args = new ContentValues();
        args.put(POSTER, movieModel.getPosterPath());
        args.put(TITLE, movieModel.getTitle());
        args.put(OVERVIEW, movieModel.getOverview());
        args.put(RELEASE, movieModel.getReleaseDate());
        args.put(POPULARITY, movieModel.getPopularity());
        return database.update(DATABASE_TABLE, args, _ID + "= '"+ movieModel.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(DATABASE_TABLE, _ID + " = '" +id+ "'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE
                ,null
                , _ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }

    public long insertProvider (ContentValues values){
        return  database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider (String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, _ID +" = ?", new String[]{id});
    }

    public int deleteProvider (String id){
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
