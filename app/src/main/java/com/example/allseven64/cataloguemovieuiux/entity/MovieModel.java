package com.example.allseven64.cataloguemovieuiux.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.POPULARITY;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.POSTER;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.RELEASE;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.MovieColums.TITLE;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.getColumnInt;
import static com.example.allseven64.cataloguemovieuiux.db.DatabaseContract.getColumnString;

public class MovieModel implements Parcelable{
    private int id;
    private String posterPath;
    private String title;
    private String releaseDate;
    private String popularity;
    private String overview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.popularity);
    }

    public MovieModel(){

    }

    public MovieModel(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.posterPath = getColumnString(cursor, POSTER);
        this.title = getColumnString(cursor, TITLE);
        this.overview = getColumnString(cursor, OVERVIEW);
        this.releaseDate = getColumnString(cursor, RELEASE);
        this.popularity = getColumnString(cursor, POPULARITY);
    }

    protected MovieModel(Parcel in){
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.popularity = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>(){
        @Override
        public MovieModel createFromParcel (Parcel source){
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size){
            return new MovieModel[size];
        }
    };
}
