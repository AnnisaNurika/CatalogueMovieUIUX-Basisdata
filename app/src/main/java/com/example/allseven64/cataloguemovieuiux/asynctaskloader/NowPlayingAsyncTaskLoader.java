package com.example.allseven64.cataloguemovieuiux.asynctaskloader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.allseven64.cataloguemovieuiux.BuildConfig;
import com.example.allseven64.cataloguemovieuiux.MovieItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NowPlayingAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    public NowPlayingAsyncTaskLoader (final Context context,  ArrayList<MovieItems> mData){
        super(context);
        onForceLoad();

    }

    @Override
    protected void onStartLoading(){
        if(takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList <MovieItems> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset(){
        super.onReset();
        onStopLoading();
        if(mHasResult){
            onReleaseResource(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = BuildConfig.API_URL;

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("baca url", "onSuccess: Yes");
                try{
                    String result = new String (responseBody);
                    JSONObject responeObject = new JSONObject(result);
                    JSONArray list = responeObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemses.add(movieItems);
                        //Log.d("LIST", "onSuccess" + movieItems.getPosterPath());
                        //Log.d("LIST", "onSuccess" + movieItems.getTitle());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }

    protected void onReleaseResource(ArrayList<MovieItems> data){
        //nothing to do
    }
}
