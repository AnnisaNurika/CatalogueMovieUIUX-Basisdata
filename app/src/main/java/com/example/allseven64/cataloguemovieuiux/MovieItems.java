package com.example.allseven64.cataloguemovieuiux;

import org.json.JSONObject;

public class MovieItems {
    private String posterPath;
    private String title;
    private String releaseDate;
    private String popularity;
    private String overview;

    public MovieItems(JSONObject object){
        try {
            String posterPath = object.getString("poster_path");
            String title = object.getString("original_title");
            String release_date = object.getString("release_date");
            String popularity = object.getString("popularity");
            String overview = object.getString("overview");

            this.posterPath = "http://image.tmdb.org/t/p/w185"+posterPath;
            this.title = title;
            this.releaseDate = release_date;
            this.popularity = popularity;
            this.overview = overview;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getPosterPath() {
        return posterPath;
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

}