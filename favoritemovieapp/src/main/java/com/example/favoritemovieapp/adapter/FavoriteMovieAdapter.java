package com.example.favoritemovieapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.favoritemovieapp.R;

import static com.example.favoritemovieapp.DatabaseContract.MovieColums.POPULARITY;
import static com.example.favoritemovieapp.DatabaseContract.MovieColums.TITLE;
import static com.example.favoritemovieapp.DatabaseContract.getColumnString;

public class FavoriteMovieAdapter extends CursorAdapter {
    public FavoriteMovieAdapter (Context context, Cursor c, boolean autoRequery){
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_movie, viewGroup, false);
        return view;
    }

    @Override
    public Cursor getCursor(){
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            TextView tvTitle = view.findViewById(R.id.tv_title);
            TextView tvPopularity = view.findViewById(R.id.tv_popularity);

            tvTitle.setText(getColumnString(cursor, TITLE));
            tvPopularity.setText(getColumnString(cursor, POPULARITY));

        }
    }
}
